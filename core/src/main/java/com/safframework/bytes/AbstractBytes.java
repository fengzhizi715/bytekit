package com.safframework.bytes;

import com.safframework.bytes.transformer.impl.ConcatTransformer;

import java.nio.charset.StandardCharsets;

/**
 * Created by tony on 2018/11/19.
 */
public abstract class AbstractBytes implements Bytes {

    public Bytes contact(byte[] bytes) {

        return transform(new ConcatTransformer(bytes));
    }

    @Override
    public String toString() {
        return toString(StandardCharsets.UTF_8);
    }
}
