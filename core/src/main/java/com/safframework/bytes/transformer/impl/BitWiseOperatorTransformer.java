package com.safframework.bytes.transformer.impl;

import com.safframework.bytes.exception.BytesException;
import com.safframework.bytes.transformer.BytesTransformer;

/**
 * Created by tony on 2018/11/20.
 */
public class BitWiseOperatorTransformer implements BytesTransformer {

    public enum Mode {
        AND, OR, XOR, NOT
    }

    private byte[] bytes;
    private Mode mode;

    public BitWiseOperatorTransformer(byte[] bytes, Mode mode) {

        this.bytes = bytes;
        this.mode = mode;
    }

    @Override
    public byte[] transform(byte[] currentArray) {

        if (currentArray.length != bytes.length) {

            throw new BytesException("all byte array must be of same length doing bit wise operation");
        }

        byte[] result = new byte[currentArray.length];

        for (int i = 0; i < currentArray.length; i++) {

            switch (mode) {

                case AND:
                    result[i] = (byte) (currentArray[i] & bytes[i]);
                    break;

                case XOR:
                    result[i] = (byte) (currentArray[i] ^ bytes[i]);
                    break;

                case NOT:
                    result[i] = (byte) ~currentArray[i];
                    break;

                default:
                case OR:
                    result[i] = (byte) (currentArray[i] | bytes[i]);
                    break;
            }
        }

        return result;
    }
}
