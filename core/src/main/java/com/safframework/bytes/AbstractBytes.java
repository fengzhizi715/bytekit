package com.safframework.bytes;

import com.safframework.bytes.transformer.impl.BitWiseOperatorTransformer;
import com.safframework.bytes.transformer.impl.ConcatTransformer;
import com.safframework.bytes.transformer.impl.CopyTransformer;
import com.safframework.bytes.transformer.impl.ReverseTransformer;

import java.nio.charset.StandardCharsets;

/**
 * Created by tony on 2018/11/19.
 */
public abstract class AbstractBytes implements Bytes {

    @Override
    public Bytes copy() {

        return transform(new CopyTransformer(0, size()));
    }

    @Override
    public Bytes copy(int offset, int length) {

        return transform(new CopyTransformer(offset, length));
    }

    @Override
    public Bytes contact(byte[] bytes) {

        return transform(new ConcatTransformer(bytes));
    }

    @Override
    public Bytes reverse() {

        return transform(new ReverseTransformer());
    }

    @Override
    public Bytes xor(byte[] bytes) {

        return transform(new BitWiseOperatorTransformer(bytes,BitWiseOperatorTransformer.Mode.XOR));
    }

    @Override
    public Bytes and(byte[] bytes) {
        return transform(new BitWiseOperatorTransformer(bytes, BitWiseOperatorTransformer.Mode.AND));
    }

    @Override
    public Bytes or(byte[] bytes) {
        return transform(new BitWiseOperatorTransformer(bytes, BitWiseOperatorTransformer.Mode.OR));
    }

    @Override
    public String toString() {
        return toString(StandardCharsets.UTF_8);
    }
}
