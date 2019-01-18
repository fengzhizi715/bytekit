package com.safframework.bytekit.transformer.impl;

import com.safframework.bytekit.transformer.BytesTransformer;
import com.safframework.bytekit.utils.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by tony on 2019-01-18.
 */
public class GzipCompressionTransformer implements BytesTransformer {

    private boolean compress;

    public GzipCompressionTransformer(boolean compress) {
        this.compress = compress;
    }

    @Override
    public byte[] transform(byte[] currentArray) {
        return compress ? compress(currentArray) : decompress(currentArray);
    }

    private byte[] compress(byte[] content) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream(content.length);
        GZIPOutputStream gzipOutputStream = null;
        byte[] returnBuffer;
        try {
            gzipOutputStream = new GZIPOutputStream(bos);
            gzipOutputStream.write(content);
            gzipOutputStream.close();
            returnBuffer = bos.toByteArray();
            bos.close();
            return returnBuffer;
        } catch (Exception e) {
            throw new IllegalStateException("could not compress gzip", e);
        } finally {
            IOUtils.closeQuietly(bos);
            IOUtils.closeQuietly(gzipOutputStream);
        }
    }

    private byte[] decompress(byte[] compressedContent) {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPInputStream gzipInputStream = null;
        byte[] returnBuffer;
        try {
            int len;
            byte[] buffer = new byte[4 * 1024];
            gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(compressedContent));

            while ((len = gzipInputStream.read(buffer)) > 0) {
                bos.write(buffer, 0, len);
            }

            gzipInputStream.close();
            returnBuffer = bos.toByteArray();
            bos.close();
            return returnBuffer;
        } catch (Exception e) {
            throw new IllegalStateException("could not decompress gzip", e);
        } finally {
            IOUtils.closeQuietly(bos);
            IOUtils.closeQuietly(gzipInputStream);
        }
    }
}
