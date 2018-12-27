package com.safframework.bytekit.mmap;

import com.safframework.bytekit.mmap.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by tony on 2018-12-24.
 */
public class MmapBytesTest {

    private MmapBytes mmapBytes;
    private String file;

    @Before
    public void setUp() {

        file = "test";
        mmapBytes = new MmapBytes(file, (long) 1024 * 10); // 10M
    }

    @Test
    public void testWriteAndRead() throws Exception {

        mmapBytes.writeInt(12);
        mmapBytes.writeInt(34);
        mmapBytes.writeByte((byte) 5);
        mmapBytes.writeBytes(("this is tony").getBytes());
        mmapBytes.writeLong(6666L);
        mmapBytes.writeDouble(3.14d);

        assertEquals(12, mmapBytes.readInt());
        assertEquals(34, mmapBytes.readInt());
        assertEquals((byte) 5, mmapBytes.readByte());
        assertEquals("this is tony", new String(mmapBytes.readBytes(12)));
        assertEquals(6666L, mmapBytes.readLong());
        assertEquals(3.14d, mmapBytes.readDouble());
    }

    @Test
    public void testObject() throws Exception {

        User u = new User();
        u.name = "tony";
        u.password = "123456";

        mmapBytes.writeObject(u);

        User temp = (User)mmapBytes.readObject(122);

        assertEquals(u.name, temp.name);
        assertEquals(u.password, temp.password);
    }

    @Test
    public void testFree() throws Exception {

        mmapBytes.writeInt(12);
        mmapBytes.writeInt(34);
        mmapBytes.writeByte((byte) 5);

        mmapBytes.free();

        mmapBytes = new MmapBytes(file, (long) 1024 * 10); // 10M
        mmapBytes.writeInt(67);

        assertEquals(67, mmapBytes.readInt());
    }

    @Test
    public void testRemap() throws Exception {

        mmapBytes.writeInt(12);
        mmapBytes.writeInt(34);

        mmapBytes = new MmapBytes(file, (long) 1024 * 20); // 20M

        assertEquals(12, mmapBytes.readInt());
        assertEquals(34, mmapBytes.readInt());
    }

    @After
    public void tearDown() {
        mmapBytes.free();
    }
}
