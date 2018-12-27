package com.safframework.bytekit.netty;

import com.safframework.bytekit.AbstractBytes;
import com.safframework.bytekit.Bytes;
import com.safframework.bytekit.bytes.ByteBufferBytes;
import com.safframework.bytekit.transformer.BytesTransformer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by tony on 2018-12-27.
 */
public final class ByteBufBytes extends AbstractBytes {

    private final ByteBuf byteBuf;

    public ByteBufBytes(final byte[] bytes) {

        this.byteBuf = Unpooled.wrappedBuffer(bytes);
    }

    @Override
    public Bytes empty() {
        return null;
    }

    @Override
    public int size() {
        return byteBuf.capacity();
    }

    @Override
    public byte byteAt(int index) {
        return byteBuf.getByte(index);
    }

    @Override
    public byte[] toByteArray() {
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        return bytes;
    }

    @Override
    public ByteBuffer toReadOnlyBuffer() {
        return byteBuf.nioBuffer().asReadOnlyBuffer();
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
