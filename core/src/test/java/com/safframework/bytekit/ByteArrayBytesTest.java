package com.safframework.bytekit;

import com.safframework.bytekit.jdk.ByteArrayBytes;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by tony on 2018-12-24.
 */
public class ByteArrayBytesTest {

    @Test
    public void testContact() {

        Bytes bytes = ByteArrayBytes.create("hello world").contact(" tony".getBytes());

        assertEquals(bytes.toString(), "hello world tony");
    }

    @Test
    public void testCopy() {

        Bytes bytes = ByteArrayBytes.create("hello world").contact(" tony".getBytes());

        assertEquals(bytes.toString(), bytes.copy().toString());
    }

    @Test
    public void testReverse() {

        Bytes bytes = ByteArrayBytes.create("hello world").contact(" tony".getBytes());

        assertEquals(bytes.toString(), bytes.reverse().reverse().toString());
    }

    @Test
    public void testBase64() {

        ByteArrayBytes bytes = (ByteArrayBytes)ByteArrayBytes.create("hello world").contact(" tony".getBytes());

        assertEquals(bytes.toString(), new String(Bytes.parseBase64(new String(bytes.encodeBase64()))));
    }
}
