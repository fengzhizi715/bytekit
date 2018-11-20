package com.safframework.bytes.transformer;

/**
 * Created by tony on 2018/11/20.
 */
public class ConcatTransformer implements BytesTransformer {

    private byte[] bytes;

    private ConcatTransformer(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public byte[] transform(byte[] currentArray) {

        int length = bytes.length+currentArray.length;
        byte[] result = new byte[length];

        System.arraycopy(bytes, 0, result, 0, bytes.length);
        System.arraycopy(currentArray, 0, result, bytes.length, currentArray.length);
        return result;
    }
}
