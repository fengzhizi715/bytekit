package com.safframework.bytekit.okio;

import com.safframework.bytekit.AbstractBytes;
import com.safframework.bytekit.Bytes;
import com.safframework.bytekit.bytes.ByteBufferBytes;
import com.safframework.bytekit.transformer.BytesTransformer;
import okio.ByteString;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by tony on 2019-02-26.
 */
public class ByteStringBytes extends AbstractBytes {

    private final static ByteStringBytes EMPTY = new ByteStringBytes(new byte[0]);

    private ByteString byteString;

    public ByteStringBytes(final byte[] bytes) {

        byteString = ByteString.of(bytes);
    }

    @Override
    public Bytes empty() {

        return ByteStringBytes.EMPTY;
    }

    @Override
    public int size() {
        return byteString.size();
    }

    @Override
    public byte byteAt(int index) {

        return byteString.getByte(index);
    }

    @Override
    public byte[] toByteArray() {

        return byteString.toByteArray();
    }

    @Override
    public ByteBuffer toReadOnlyBuffer() {

        return byteString.asByteBuffer();
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
        return byteString.string(charset);
    }
}
