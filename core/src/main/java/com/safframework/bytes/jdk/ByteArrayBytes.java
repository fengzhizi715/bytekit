package com.safframework.bytes.jdk;

import com.safframework.bytes.AbstractBytes;
import com.safframework.bytes.Bytes;
import com.safframework.bytes.exception.BytesException;
import com.safframework.bytes.transformer.BytesTransformer;
import com.safframework.tony.common.utils.IOUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by tony on 2018/11/20.
 */
public class ByteArrayBytes extends AbstractBytes {

    private final static ByteArrayBytes EMPTY = new ByteArrayBytes(new byte[0]);

    private final byte[] bytes;

    private final int offset;

    private final int length;

    public ByteArrayBytes(byte[] bytes) {

        this(bytes, 0, bytes.length);
    }

    public ByteArrayBytes(final byte[] bytes, int offset, int length) {

        if (offset < 0 || offset > bytes.length) {
            throw new BytesException("Invalid offset: " + offset + " for array of length: " + bytes.length);
        }
        if (length < 0 || length + offset > bytes.length) {
            throw new BytesException("Invalid length: " + length + " for array of length: " + bytes.length + " and offset: " + offset);
        }

        this.bytes = bytes;
        this.offset = offset;
        this.length = length;
    }

    @Override
    public Bytes empty() {

        return ByteArrayBytes.EMPTY;
    }

    public static ByteArrayBytes create(File file) {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return create(inputStream);
    }

    public static ByteArrayBytes create(InputStream inputStream) {

        byte[] bytes = null;
        try {
            bytes = IOUtils.readInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            bytes = new byte[0];
        }

        return new ByteArrayBytes(bytes);
    }

    public static ByteArrayBytes create(CharSequence utf8String) {

        return create(utf8String, StandardCharsets.UTF_8);
    }

    public static ByteArrayBytes create(CharSequence sequence, Charset charset) {

        return create(sequence.toString().getBytes(charset));
    }

    public static ByteArrayBytes create(byte[] bytes) {

        return new ByteArrayBytes(bytes);
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public byte byteAt(int index) {

        if (index<0 || index>=length) {

            throw new ArrayIndexOutOfBoundsException(index);
        }

        return bytes[offset+index];
    }

    @Override
    public byte[] toByteArray() {

        return bytes;
    }

    @Override
    public ByteBuffer toReadOnlyBuffer() {

        return ByteBuffer.wrap(bytes, offset, length).asReadOnlyBuffer();
    }

    @Override
    public InputStream newInputStream() {

        return new ByteArrayInputStream(bytes, offset, length);
    }

    @Override
    public Bytes transform(BytesTransformer transformer) {

        byte[] newBytes = transformer.transform(bytes);

        return new ByteArrayBytes(newBytes);
    }

    @Override
    public String toString(Charset charset) {

        return new String(bytes, offset, length, charset);
    }
}
