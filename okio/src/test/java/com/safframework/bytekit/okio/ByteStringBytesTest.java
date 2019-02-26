package com.safframework.bytekit.okio;

import com.safframework.bytekit.Bytes;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by tony on 2019-02-26.
 */
public class ByteStringBytesTest {

    @Test
    public void testContact() {

        Bytes bytes = new ByteStringBytes("hello world".getBytes()).contact(" tony".getBytes());

        assertEquals(bytes.toString(), "hello world tony");
    }

    @Test
    public void testCopy() {

        Bytes bytes = new ByteStringBytes("hello world".getBytes()).contact(" tony".getBytes());

        assertEquals(bytes.toString(), bytes.copy().toString());
    }

    @Test
    public void testReverse() {

        Bytes bytes = new ByteStringBytes("hello world".getBytes()).contact(" tony".getBytes());

        assertEquals(bytes.toString(), bytes.reverse().reverse().toString());
    }

    @Test
    public void testBitWise() {

        ByteStringBytes bytes = new ByteStringBytes("hello world".getBytes());
        bytes.contact(" tony".getBytes());

        assertEquals(bytes.toString(), bytes.and(bytes.toByteArray()).or(bytes.toByteArray()).toString());
        assertEquals(bytes.toString(), bytes.not(bytes.toByteArray()).not(bytes.toByteArray()).toString());
        assertEquals(bytes.toString(), bytes.xor(bytes.toByteArray()).xor(bytes.toByteArray()).toString()); //两次xor 返回本身
    }

    @Test
    public void testBase64() {

        ByteStringBytes bytes = new ByteStringBytes("hello world".getBytes());
        bytes.contact(" tony".getBytes());

        String base64 = new String(bytes.encodeBase64());
        assertEquals(bytes.toString(), new String(Bytes.parseBase64(base64)));
    }

    @Test
    public void testToByteArray() {

        Bytes bytes = new ByteStringBytes("hello world".getBytes()).contact(" tony".getBytes());

        assertEquals(bytes.toString(), new String(bytes.toByteArray()));
    }
}
