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
}
