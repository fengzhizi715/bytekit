package com.safframework.bytekit.transformer.impl;

import com.safframework.bytekit.transformer.BytesTransformer;

/**
 * Created by tony on 2018/11/20.
 */
public class ReverseTransformer implements BytesTransformer {

    @Override
    public byte[] transform(byte[] currentArray) {

        for (int i = 0, j = currentArray.length - 1; i < j; i++, j--) {
            byte tmp = currentArray[i];
            currentArray[i] = currentArray[j];
            currentArray[j] = tmp;
        }

        return currentArray;
    }
}
