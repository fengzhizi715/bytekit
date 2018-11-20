package com.safframework.bytes;

import com.safframework.bytes.transformer.impl.ConcatTransformer;
import com.safframework.bytes.transformer.impl.CopyTransformer;
import com.safframework.bytes.transformer.impl.ReverseTransformer;

import java.nio.charset.StandardCharsets;

/**
 * Created by tony on 2018/11/19.
 */
public abstract class AbstractBytes implements Bytes {

    public Bytes copy() {

        return transform(new CopyTransformer(0, size()));
    }

    public Bytes copy(int offset, int length) {

        return transform(new CopyTransformer(offset, length));
    }

    public Bytes contact(byte[] bytes) {

        return transform(new ConcatTransformer(bytes));
    }

    public Bytes reverse() {

        return transform(new ReverseTransformer());
    }

    @Override
    public String toString() {
        return toString(StandardCharsets.UTF_8);
    }
}
