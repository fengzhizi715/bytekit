package com.safframework.bytes.jdk;

import com.safframework.bytes.AbstractBytes;
import com.safframework.bytes.Bytes;
import com.safframework.bytes.exception.BytesException;
import com.safframework.bytes.transformer.BytesTransformer;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by tony on 2018-12-13.
 */
public class MmapBytes extends AbstractBytes {

    private MmapBuffer buffer = null;

    private int position = 0;

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
        return 0;
    }

    @Override
    public byte byteAt(int index) {
        return 0;
    }

    @Override
    public byte[] toByteArray() {
        return new byte[0];
    }

    @Override
    public ByteBuffer toReadOnlyBuffer() {
        return null;
    }

    @Override
    public InputStream newInputStream() {
        return null;
    }

    @Override
    public Bytes transform(BytesTransformer transformer) {
        return null;
    }

    @Override
    public String toString(Charset charset) {
        return null;
    }
}
