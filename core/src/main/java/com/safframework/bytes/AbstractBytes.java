package com.safframework.bytes;

import java.nio.charset.StandardCharsets;

/**
 * Created by tony on 2018/11/19.
 */
public abstract class AbstractBytes implements Bytes {

    @Override
    public String toString() {
        return toString(StandardCharsets.UTF_8);
    }
}
