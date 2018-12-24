package com.safframework.bytekit.jdk;

import com.safframework.bytekit.AbstractBytes;
import com.safframework.bytekit.Bytes;
import com.safframework.bytekit.transformer.BytesTransformer;
import com.safframework.bytekit.utils.IOUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Created by tony on 2018/11/20.
 */
public final class ByteBufferBytes extends AbstractBytes {

    private final static ByteBufferBytes EMPTY = new ByteBufferBytes(ByteBuffer.wrap(new byte[0]));

    private final ByteBuffer buffer;

    public ByteBufferBytes(final byte[] bytes) {

        this.buffer = ByteBuffer.wrap(bytes);
    }

    public ByteBufferBytes(ByteBuffer buffer) {

        this.buffer = buffer;
    }

    @Override
    public Bytes empty() {

        return ByteBufferBytes.EMPTY;
    }

    public static ByteBufferBytes create(File file) {

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return create(inputStream);
    }

    public static ByteBufferBytes create(InputStream inputStream) {

        byte[] bytes = null;
        try {
            bytes = IOUtils.readInputStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            bytes = new byte[0];
        }

        return create(bytes);
    }

    public static ByteBufferBytes create(CharSequence utf8String) {

        return create(utf8String, StandardCharsets.UTF_8);
    }

    public static ByteBufferBytes create(CharSequence sequence, Charset charset) {

        return create(sequence.toString().getBytes(charset));
    }

    public static ByteBufferBytes create(byte[] bytes) {

        return new ByteBufferBytes(bytes);
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
        toReadOnlyBuffer().get(dest);
        return dest;
    }

    @Override
    public ByteBuffer toReadOnlyBuffer() {

        return buffer.asReadOnlyBuffer();
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
}
