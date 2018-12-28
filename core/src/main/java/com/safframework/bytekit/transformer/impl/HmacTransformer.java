package com.safframework.bytekit.transformer.impl;

import com.safframework.bytekit.transformer.BytesTransformer;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by tony on 2018-12-28.
 */
public class HmacTransformer implements BytesTransformer {

    private String macAlgorithmName;
    private byte[] secretKey;

    public HmacTransformer(byte[] key, String macAlgorithmName) {

        this.secretKey = key;
        this.macAlgorithmName = macAlgorithmName;
    }

    @Override
    public byte[] transform(byte[] currentArray) {

        try {
            Mac mac = Mac.getInstance(macAlgorithmName);
            mac.init(new SecretKeySpec(secretKey, macAlgorithmName));
            return mac.doFinal(currentArray);
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}
