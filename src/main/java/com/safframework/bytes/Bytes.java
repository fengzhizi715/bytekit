package com.safframework.bytes;

import java.io.Serializable;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Created by tony on 2018/11/17.
 */
public class Bytes implements Comparable<Bytes>, Serializable, Iterable<Byte> {

    private static final long serialVersionUID = -6816199822499578917L;

    private static final Bytes EMPTY = Bytes.wrap(new byte[0], ByteOrder.BIG_ENDIAN);

    public Bytes(byte[] byteArray, ByteOrder byteOrder) {
        this(byteArray, byteOrder, new SimpleBytesFactory());
    }

    public Bytes(byte[] byteArray, ByteOrder byteOrder,BytesFactory factory) {

    }

    public static Bytes wrap(byte[] bytes) {
        return wrap(bytes, ByteOrder.BIG_ENDIAN);
    }

    public static Bytes wrap(byte[] bytes,ByteOrder byteOrder) {

        return new Bytes(bytes,byteOrder);
    }

    public static Bytes empty() {
        return EMPTY;
    }

    @Override
    public int compareTo(Bytes o) {
        return 0;
    }

    @Override
    public Iterator<Byte> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super Byte> action) {

    }

    @Override
    public Spliterator<Byte> spliterator() {
        return null;
    }
}
