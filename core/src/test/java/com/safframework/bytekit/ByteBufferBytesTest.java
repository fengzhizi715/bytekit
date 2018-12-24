package com.safframework.bytekit;

import com.safframework.bytekit.jdk.ByteBufferBytes;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by tony on 2018-12-24.
 */
public class ByteBufferBytesTest {

    @Test
    public void testContact() {

        Bytes bytes = ByteBufferBytes.create("hello world").contact(" tony".getBytes());

        assertEquals(bytes.toString(), "hello world tony");
    }

    @Test
    public void testCopy() {

        Bytes bytes = ByteBufferBytes.create("hello world").contact(" tony".getBytes());

        assertEquals(bytes.toString(), bytes.copy().toString());
    }

    @Test
    public void testReverse() {

        Bytes bytes = ByteBufferBytes.create("hello world").contact(" tony".getBytes());

        assertEquals(bytes.toString(), bytes.reverse().reverse().toString());
    }

    @Test
    public void testBase64() {

        ByteBufferBytes bytes = (ByteBufferBytes)ByteBufferBytes.create("hello world").contact(" tony".getBytes());

        String base64 = new String(bytes.encodeBase64());
        assertEquals(bytes.toString(), new String(Bytes.parseBase64(base64)));
    }

    @Test
    public void testToByteArray() {

        Bytes bytes = ByteBufferBytes.create("hello world").contact(" tony".getBytes());

        assertEquals(bytes.toString(), new String(bytes.toByteArray()));
    }
}
