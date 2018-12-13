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
        return null;
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
}
