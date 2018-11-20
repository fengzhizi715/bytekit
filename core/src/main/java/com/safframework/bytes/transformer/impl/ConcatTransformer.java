package com.safframework.bytes.transformer.impl;

import com.safframework.bytes.transformer.BytesTransformer;

/**
 * Created by tony on 2018/11/20.
 */
public class ConcatTransformer implements BytesTransformer {

    private byte[] bytes;

    public ConcatTransformer(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public byte[] transform(byte[] currentArray) {

        int length = bytes.length+currentArray.length;
        byte[] result = new byte[length];

        System.arraycopy(currentArray, 0, result, 0, currentArray.length);
        System.arraycopy(bytes, 0, result, currentArray.length, bytes.length);
        return result;
    }
}
