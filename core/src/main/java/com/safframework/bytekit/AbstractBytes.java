package com.safframework.bytekit;

import com.safframework.bytekit.transformer.impl.BitWiseOperatorTransformer;
import com.safframework.bytekit.transformer.impl.ConcatTransformer;
import com.safframework.bytekit.transformer.impl.CopyTransformer;
import com.safframework.bytekit.transformer.impl.ReverseTransformer;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

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
    public Bytes not(byte[] bytes) {

        return transform(new BitWiseOperatorTransformer(bytes, BitWiseOperatorTransformer.Mode.NOT));
    }

    /**
     * 将当前的Bytes对象转换成base64的字节数组
     * @return
     */
    public byte[] encodeBase64() {

        return Base64.getEncoder().encode(toByteArray());
    }

    @Override
    public String toString() {

        return toString(StandardCharsets.UTF_8);
    }
}
