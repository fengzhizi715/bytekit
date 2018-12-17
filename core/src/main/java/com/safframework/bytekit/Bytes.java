package com.safframework.bytekit;

import com.safframework.bytekit.transformer.BytesTransformer;
import com.safframework.bytekit.transformer.impl.MessageDigestTransformer;
import com.safframework.bytekit.utils.IOUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Base64;

/**
 * Created by tony on 2018/11/19.
 */
public interface Bytes {

    char[] HEX_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

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

    /**
     * 写入到输出流
     * @param out
     */
    default void toStream(OutputStream out) {

        byte[] buffer;
        if (size() < 4096) {
            buffer = new byte[(int)size()];
        } else {
            buffer = new byte[4096];
        }

        InputStream in = newInputStream();
        try {
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            IOUtils.closeQuietly(in);
        }
    }

    default String toHexString() {
        byte[] arr = toByteArray();
        char[] result = new char[arr.length * 2];
        for (int i = 0; i < arr.length; ++i) {
            result[i * 2] = HEX_ARRAY[(arr[i] >> 4) & 0xF];
            result[i * 2 + 1] = HEX_ARRAY[(arr[i] & 0xF)];
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

    /**
     * 将base64的字符串转换成字节数组
     * @param base64
     * @return
     */
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
