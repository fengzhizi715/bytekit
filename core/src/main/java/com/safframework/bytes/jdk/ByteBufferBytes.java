package com.safframework.bytes.jdk;

import com.safframework.bytes.AbstractBytes;
import com.safframework.bytes.Bytes;
import com.safframework.bytes.transformer.BytesTransformer;
import com.safframework.tony.common.utils.IOUtils;

import java.io.*;
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

    public static ByteBufferBytes create(byte[] bytes) {

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

        return new ByteBufferBytes(ByteBuffer.wrap(newBytes));
    }

    @Override
    public String toString(Charset charset) {

        return new String(toByteArray(), charset);
    }
}
