package com.safframework.bytekit.utils;

import java.nio.ByteBuffer;

/**
 * Created by tony on 2018-12-19.
 */
public class Utils {

    public static int parse(char c) {
        if (c >= 'a')
            return (c - 'a' + 10) & 0x0f;
        if (c >= 'A')
            return (c - 'A' + 10) & 0x0f;
        return (c - '0') & 0x0f;
    }

    /**
     * 克隆ByteBuffer
     * @param original
     * @return
     */
    public static ByteBuffer cloneByteBuffer(ByteBuffer original) {
        ByteBuffer clone = ByteBuffer.allocate(original.capacity());
        original.rewind();//copy from the beginning
        clone.put(original);
        original.rewind();
        clone.flip();
        return clone;
    }
}
