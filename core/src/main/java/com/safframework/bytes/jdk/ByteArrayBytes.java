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
public class ByteArrayBytes extends AbstractBytes {

    public static final ByteArrayBytes EMPTY = new ByteArrayBytes(new byte[0]);

    private final byte[] bytes;

    private final int offset;

    private final int length;

    public ByteArrayBytes(final byte[] bytes) {

        this(bytes, 0, bytes.length);
    }

    public ByteArrayBytes(final byte[] bytes, int offset, int length) {

        if (offset < 0 || offset > bytes.length) {
            throw new IllegalArgumentException("Invalid offset: " + offset + " for array of length: " + bytes.length);
        }
        if (length < 0 || length + offset > bytes.length) {
            throw new IllegalArgumentException("Invalid length: " + length + " for array of length: " + bytes.length + " and offset: " + offset);
        }

        this.bytes = bytes;
        this.offset = offset;
        this.length = length;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public byte elementAt(int index) {

        if (index<0 || index>=length) {

            throw new ArrayIndexOutOfBoundsException(index);
        }

        return bytes[offset+index];
    }

    @Override
    public byte[] toByteArray() {

        final byte[] copy = new byte[length];
        System.arraycopy(bytes, offset, copy, 0, length);
        return copy;
    }

    @Override
    public ByteBuffer toByteBuffer() {

        return ByteBuffer.wrap(bytes, offset, length).asReadOnlyBuffer();
    }

    @Override
    public InputStream newInputStream() {

        return new ByteArrayInputStream(bytes, offset, length);
    }

    @Override
    public Bytes transform(BytesTransformer transformer) {
        return null;
    }

    @Override
    public String toString(Charset charset) {

        return new String(bytes, offset, length, charset);
    }
}
