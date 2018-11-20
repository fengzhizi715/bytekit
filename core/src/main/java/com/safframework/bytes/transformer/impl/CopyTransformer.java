package com.safframework.bytes.transformer.impl;

import com.safframework.bytes.transformer.BytesTransformer;

/**
 * Created by tony on 2018/11/20.
 */
public class CopyTransformer implements BytesTransformer {

    private int offset;
    private int length;

    public CopyTransformer(int offset, int length) {

        this.offset = offset;
        this.length = length;
    }

    @Override
    public byte[] transform(byte[] currentArray) {

        byte[] copy = new byte[length];
        System.arraycopy(currentArray, offset, copy, 0, copy.length);
        return copy;
    }
}
