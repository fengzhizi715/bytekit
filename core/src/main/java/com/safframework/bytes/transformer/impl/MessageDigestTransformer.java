package com.safframework.bytes.transformer.impl;

import com.safframework.bytes.exception.BytesException;
import com.safframework.bytes.transformer.BytesTransformer;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by tony on 2018-12-10.
 */
public class MessageDigestTransformer implements BytesTransformer {

    private final MessageDigest messageDigest;

    public MessageDigestTransformer(String digestName) {

        try {
            this.messageDigest = MessageDigest.getInstance(digestName);
        } catch (NoSuchAlgorithmException e) {
            throw new BytesException("could not get message digest algorithm " + digestName, e);
        }
    }

    @Override
    public byte[] transform(byte[] currentArray) {

        messageDigest.update(currentArray);
        return messageDigest.digest();
    }
}
