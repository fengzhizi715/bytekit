package com.safframework.bytes;

import java.nio.ByteOrder;

/**
 * Created by tony on 2018/11/17.
 */
public interface BytesFactory {

    Bytes create(byte[] array, ByteOrder byteOrder);
}
