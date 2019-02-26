package com.safframework.bytekit.okio;

import com.safframework.bytekit.AbstractBytes;
import com.safframework.bytekit.Bytes;
import com.safframework.bytekit.bytes.ByteBufferBytes;
import com.safframework.bytekit.transformer.BytesTransformer;
import okio.Buffer;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by tony on 2019-02-26.
 */
public class BufferBytes extends AbstractBytes {

    private final static BufferBytes EMPTY = new BufferBytes(new byte[0]);

    private Buffer buffer = new Buffer();

    public BufferBytes(final byte[] bytes) {
        buffer.write(bytes);
    }

    @Override
    public Bytes empty() {
        return BufferBytes.EMPTY;
    }

    @Override
    public int size() {
        return (int) buffer.size();
    }

    @Override
    public byte byteAt(int index) {
        return buffer.getByte(index);
    }

    @Override
    public byte[] toByteArray() {
        return buffer.readByteArray();
    }

    @Override
    public ByteBuffer toReadOnlyBuffer() {
        return ByteBuffer.wrap(toByteArray());
    }

    @Override
    public InputStream newInputStream() {
        return buffer.inputStream();
    }

    @Override
    public Bytes transform(BytesTransformer transformer) {
        byte[] newBytes = transformer.transform(toByteArray());

        return new ByteBufferBytes(newBytes);
    }

    @Override
    public String toString(Charset charset) {
        return buffer.readString(charset);
    }
}
