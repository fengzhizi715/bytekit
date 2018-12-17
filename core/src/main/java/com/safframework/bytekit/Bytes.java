package com.safframework.bytekit;

import com.safframework.bytekit.transformer.BytesTransformer;
import com.safframework.bytekit.transformer.impl.MessageDigestTransformer;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Created by tony on 2018/11/19.
 */
public interface Bytes {

    default boolean isEmpty() {
        return size() == 0;
    }

    Bytes empty();

    int size();

    byte byteAt(int index);

    byte[] toByteArray();

    ByteBuffer toReadOnlyBuffer();

    InputStream newInputStream();

    Bytes transform(BytesTransformer transformer);

    Bytes copy();

    Bytes copy(int offset, int length);

    Bytes contact(byte[] bytes);

    Bytes reverse();

    Bytes xor(byte[] bytes);

    Bytes and(byte[] bytes);

    Bytes or(byte[] bytes);

    Bytes not(byte[] bytes);

    String toString(Charset charset);

    default String toHexString() {
        byte[] arr = toByteArray();
        char[] result = new char[arr.length * 2];
        for (int i = 0; i < arr.length; ++i) {
            result[i * 2] = Constants.HEX_ARRAY[(arr[i] >> 4) & 0xF];
            result[i * 2 + 1] = Constants.HEX_ARRAY[(arr[i] & 0xF)];
        }
        return new String(result);
    }

    default Bytes md5() {

        return transform(new MessageDigestTransformer("MD5"));
    }

    default Bytes sha1() {

        return transform(new MessageDigestTransformer("SHA-1"));
    }

    default Bytes sha256() {

        return transform(new MessageDigestTransformer("SHA-256"));
    }

    static byte[] parseBase64(String base64) {

        return Base64.getDecoder().decode(base64);
    }

    static byte[] serialize(Object obj) {
        byte[] result = null;
        ByteArrayOutputStream fos = null;

        try {
            fos = new ByteArrayOutputStream();
            ObjectOutputStream o = new ObjectOutputStream(fos);
            o.writeObject(obj);
            result = fos.toByteArray();
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    static Object deserialize(byte[] arr) {
        InputStream fis = null;

        try {
            fis = new ByteArrayInputStream(arr);
            ObjectInputStream o = new ObjectInputStream(fis);
            return o.readObject();
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        } finally {
            try {
                fis.close();
            } catch (Exception e) {
            }
        }

        return null;
    }

    /**
     * 通过序列化实现对象的深拷贝
     * @param obj
     * @param <T>
     * @return
     */
    static <T> T cloneObject(T obj) {
        
        return (T) deserialize(serialize(obj));
    }
}
