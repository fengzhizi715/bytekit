package com.safframework.bytekit;

import com.safframework.bytekit.config.Constants;
import com.safframework.bytekit.transformer.BytesTransformer;
import com.safframework.bytekit.transformer.impl.GzipCompressionTransformer;
import com.safframework.bytekit.transformer.impl.HmacTransformer;
import com.safframework.bytekit.transformer.impl.MessageDigestTransformer;
import com.safframework.bytekit.utils.IOUtils;
import com.safframework.bytekit.utils.Preconditions;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Base64;

import static com.safframework.bytekit.utils.Utils.parse;

/**
 * Created by tony on 2018/11/19.
 */
public interface Bytes {

    char[] HEX_ARRAY = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    int length = 0x1000; // 4096

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

    default Bytes compressGzip() {

        return transform(new GzipCompressionTransformer(true));
    }

    default Bytes decompressGzip() {

        return transform(new GzipCompressionTransformer(false));
    }

    /**
     * 将 Bytes 写入到输出流
     * @param out
     */
    default void toStream(OutputStream out) {

        byte[] buffer;
        if (size() < length) {
            buffer = new byte[(int)size()];
        } else {
            buffer = new byte[length];
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

    /**
     * 将 Bytes 写入到文件中
     * @param file
     */
    default void toFile(File file) {

        try {
            Files.write(file.toPath(), toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把 Bytes 转换成十六进制字符串
     * @return
     */
    default String toHexString() {
        byte[] arr = toByteArray();
        char[] result = new char[arr.length * 2];
        for (int i = 0; i < arr.length; ++i) {
            result[i * 2] = HEX_ARRAY[(arr[i] >> 4) & 0xF];
            result[i * 2 + 1] = HEX_ARRAY[(arr[i] & 0xF)];
        }
        return new String(result);
    }

    /**
     * 使用 md5 加密
     * @return
     */
    default Bytes md5() {

        return transform(new MessageDigestTransformer(Constants.MD5));
    }

    /**
     * 使用 sha1 加密
     * @return
     */
    default Bytes sha1() {

        return transform(new MessageDigestTransformer(Constants.SHA_1));
    }

    /**
     * 使用 sha256 加密
     * @return
     */
    default Bytes sha256() {

        return transform(new MessageDigestTransformer(Constants.SHA_256));
    }

    default Bytes hmacMD5(byte[] key) {

        return transform(new HmacTransformer(key,Constants.HMACMD5));
    }

    default Bytes hmacSha1(byte[] key) {

        return transform(new HmacTransformer(key,Constants.HMACSHA1));
    }

    default Bytes hmacSha256(byte[] key) {

        return transform(new HmacTransformer(key,Constants.HMACSHA256));
    }

    /**
     * 把十六进制字符串还原成二进制字节数组
     * @param hexString
     * @return
     */
    static byte[] getByteArray(String hexString) {

        if (Preconditions.isBlank(hexString)) {
            return null;
        }

        byte[] result = new byte[hexString.length() / 2];
        int j = 0;
        char c0;
        char c1;
        for (int i = 0; i < result.length; i++) {

            c0 = hexString.charAt(j++);
            c1 = hexString.charAt(j++);
            result[i] = (byte) ((parse(c0) << 4) | parse(c1));
        }
        return result;
    }

    /**
     * 将 base64 的字符串转换成字节数组 (仅支持jdk 1.8以上)
     * @param base64
     * @return
     */
    static byte[] parseBase64(String base64) {

        return Base64.getDecoder().decode(base64);
    }

    /**
     * 序列化对象，转换成字节数组
     * @param obj
     * @return
     */
    static byte[] serialize(Object obj) {
        byte[] result = null;
        ByteArrayOutputStream fos = null;
        ObjectOutputStream o = null;

        try {
            fos = new ByteArrayOutputStream();
            o = new ObjectOutputStream(fos);
            o.writeObject(obj);
            result = fos.toByteArray();
        } catch (IOException e) {
            System.err.println(e);
        } finally {

            IOUtils.closeQuietly(fos,o);
        }

        return result;
    }

    /**
     * 反序列化字节数字，转换成对象
     * @param bytes
     * @return
     */
    static Object deserialize(byte[] bytes) {
        InputStream fis = null;
        ObjectInputStream o = null;

        try {
            fis = new ByteArrayInputStream(bytes);
            o = new ObjectInputStream(fis);
            return o.readObject();
        } catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        } finally {

            IOUtils.closeQuietly(fis,o);
        }

        return null;
    }

    /**
     * 通过序列化/反序列化实现对象的深拷贝
     * @param obj
     * @param <T>
     * @return
     */
    static <T> T cloneObject(T obj) {
        
        return (T) deserialize(serialize(obj));
    }
}
