package com.safframework.bytes.jdk;

import com.safframework.bytes.AbstractBytes;
import com.safframework.bytes.Bytes;
import com.safframework.bytes.transformer.BytesTransformer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by tony on 2018/11/20.
 */
public class ByteBufferBytes extends AbstractBytes {

    private static final ByteBufferBytes EMPTY = new ByteBufferBytes(ByteBuffer.wrap(new byte[0]));

    private final ByteBuffer buffer;

    public ByteBufferBytes(ByteBuffer buffer) {

        this.buffer = buffer;
    }

    @Override
    public Bytes empty() {

        return ByteBufferBytes.EMPTY;
    }

    @Override
    public Bytes create(byte[] bytes) {

        return new ByteBufferBytes(ByteBuffer.wrap(bytes));
    }

    @Override
    public int size() {

        return buffer.remaining();
    }

    @Override
    public byte byteAt(int index) {

        return buffer.get(buffer.position() + index);
    }

    @Override
    public byte[] toByteArray() {

        byte[] dest = new byte[size()];
        toByteBuffer().get(dest);
        return dest;
    }

    @Override
    public ByteBuffer toByteBuffer() {

        return buffer.asReadOnlyBuffer();
    }

    @Override
    public InputStream newInputStream() {

        return new ByteArrayInputStream(toByteArray());
    }

    @Override
    public Bytes transform(BytesTransformer transformer) {

        byte[] newBytes = transformer.transform(toByteArray());

        return new ByteBufferBytes(ByteBuffer.wrap(newBytes));
    }

    @Override
    public String toString(Charset charset) {

        return new String(toByteArray(), charset);
    }
}
