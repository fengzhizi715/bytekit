package com.safframework.bytes;

import java.nio.ByteOrder;

/**
 * Created by tony on 2018/11/17.
 */
public class SimpleBytesFactory implements BytesFactory {

    @Override
    public Bytes create(byte[] array, ByteOrder byteOrder) {

        return new Bytes(array, byteOrder);
    }
}
