package com.safframework.bytekit;

import com.safframework.bytekit.domain.User;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotSame;

/**
 * Created by tony on 2018-12-24.
 */
public class BytesTest {

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
    }
}
