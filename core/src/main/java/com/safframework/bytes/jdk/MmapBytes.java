package com.safframework.bytes.jdk;

import com.safframework.bytes.AbstractBytes;
import com.safframework.bytes.Bytes;
import com.safframework.bytes.exception.BytesException;
import com.safframework.bytes.transformer.BytesTransformer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by tony on 2018-12-13.
 */
public class MmapBytes extends AbstractBytes {

    private MmapBuffer buffer = null;

    private int position = 0; // current the position for reader

    public MmapBytes(String file,Long mapSize) {

        this.buffer = new MmapBuffer(file,mapSize);

        System.out.println("initializer with " + mapSize + " bytes map buffer");
    }

    /**
     * require {@code byteCount bytes,if the map buffer's size is limit,just re-map}
     * @param byteCount
     */
    private void require(int byteCount) throws Exception {

        int response = (int) (this.buffer.getMmapBufferSize() -
                this.buffer.getMappedByteBuffer().position());
        if( response < byteCount ){

            throw new BytesException("out of map buffer size limit");
        }

        System.out.println("require " + byteCount + " byte(s),response " + response + " bytes");
    }


    @Override
    public Bytes empty() {

        return new ByteBufferBytes(ByteBuffer.wrap(new byte[0]));
    }

    /**
     * get current the position
     * @return
     */
    public int getReaderPosition(){
        return position;
    }

    /**
     * get write position
     * @return
     */
    public int getWriterPosition(){
        return this.buffer.getMappedByteBuffer().position();
    }

    public void writeByte(byte b) throws Exception {
        this.require(1);
        this.buffer.getMappedByteBuffer().put(b);
    }

    public byte readByte() {
        byte b = this.buffer.getMappedByteBuffer().get(position);
        position ++;
        return b;
    }

    public void writeBytes(byte[] bytes) throws Exception {
        this.require(bytes.length);
        this.buffer.getMappedByteBuffer().put(bytes);
    }

    public byte[] readBytes(int byteCount) {
        byte[] bytes = new byte[byteCount];
        for( int i = 0; i < byteCount; i ++ ){
            bytes[i] = readByte();
        }
        return bytes;
    }

    public void writeInt(int i) throws Exception {
        this.require(4);
        this.buffer.getMappedByteBuffer().putInt(i);
    }

    public int readInt() {
        int i = this.buffer.getMappedByteBuffer().getInt(position);
        position += 4;
        return i;
    }

    public void writeDouble(double d) throws Exception {
        this.require(8);
        this.buffer.getMappedByteBuffer().putDouble(d);
    }

    public double readDouble() {
        double d = this.buffer.getMappedByteBuffer().getDouble(position);
        position += 8;
        return d;
    }

    public void writeLong(long l) throws Exception {
        this.require(8);
        this.buffer.getMappedByteBuffer().putLong(l);
    }

    public long readLong() {
        long l = this.buffer.getMappedByteBuffer().getLong(position);
        position += 8;
        return l;
    }

    @Override
    public int size() {

        return buffer.getMappedByteBuffer().position();
    }

    @Override
    public byte byteAt(int index) {

        return buffer.getMappedByteBuffer().get(index);
    }

    @Override
    public byte[] toByteArray() {

        byte[] dest = new byte[size()];
        toReadOnlyBuffer().get(dest);
        return dest;
    }

    @Override
    public ByteBuffer toReadOnlyBuffer() {

        return buffer.getMappedByteBuffer().asReadOnlyBuffer();
    }

    @Override
    public InputStream newInputStream() {

        return new ByteArrayInputStream(toByteArray());
    }

    @Override
    public Bytes transform(BytesTransformer transformer) {

        byte[] newBytes = transformer.transform(toByteArray());

        return new ByteBufferBytes(newBytes);
    }

    @Override
    public String toString(Charset charset) {

        return new String(toByteArray(), charset);
    }

    public void clear() {

        if (buffer!=null) {

            unmap(buffer.getMappedByteBuffer());
            close();
        }
    }

    public void close(){

        try {
            buffer.getRandomAccessFile().close();
            buffer.getMappedByteBuffer().clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解除内存与文件的映射
     * @param mbb
     */
    private void unmap(MappedByteBuffer mbb) {

        if (mbb == null) {
            return;
        }

        try {
            Class<?> clazz = Class.forName("sun.nio.ch.FileChannelImpl");
            Method m = clazz.getDeclaredMethod("unmap", MappedByteBuffer.class);
            m.setAccessible(true);
            m.invoke(null, mbb);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    @Override
    public Bytes contact(byte[] bytes) {

        try {
            this.require(bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.contact(bytes);
    }
}
