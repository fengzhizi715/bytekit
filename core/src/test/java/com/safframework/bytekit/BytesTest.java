package com.safframework.bytekit;

import com.safframework.bytekit.domain.User;
import com.safframework.bytekit.bytes.ByteArrayBytes;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;

/**
 * Created by tony on 2018-12-24.
 */
public class BytesTest {

    @Test
    public void testHexString() {

        Bytes bytes = ByteArrayBytes.create("hello world");
        assertEquals("68656c6c6f20776f726c64", bytes.toHexString());
    }

    @Test
    public void testHash() {

        Bytes bytes = ByteArrayBytes.create("hello world");

        assertEquals("5eb63bbbe01eeed093cb22bb8f5acdc3", bytes.md5().toHexString());
        assertEquals("2aae6c35c94fcfb415dbe95f408b9ce91ee846ed", bytes.sha1().toHexString());
        assertEquals("b94d27b9934d3e08a52e52d7da7dabfac484efe37a5380ee9088f7ace2efcde9", bytes.sha256().toHexString());
    }

    @Test
    public void testSerializeAndDeserialize() {

        User u = new User();
        u.name = "tony";
        u.password = "123456";

        byte[] bytes = Bytes.serialize(u);

        User newUser = (User)Bytes.deserialize(bytes);
        assertEquals(u.name, newUser.name);
        assertEquals(u.password,newUser.password);
    }

    @Test
    public void testDeepCopy() {

        User u = new User();
        u.name = "tony";
        u.password = "123456";

        User newUser = Bytes.cloneObject(u);
        System.out.println(u);
        System.out.println(newUser);
        assertNotSame(u,newUser);
        assertNotSame(u.name,newUser.name);
    }
}
