package com.safframework.bytes;

import com.safframework.bytes.transformer.BytesTransformer;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * Created by tony on 2018/11/19.
 */
public interface Bytes {

    default boolean isEmpty() {
        return size() == 0;
    }

    int size();

    byte byteAt(int index);

    byte[] toByteArray();

    ByteBuffer toByteBuffer();

    InputStream newInputStream();

    Bytes transform(BytesTransformer transformer);

    String toString(Charset charset);
}
