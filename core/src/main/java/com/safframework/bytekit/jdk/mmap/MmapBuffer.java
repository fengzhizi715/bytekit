package com.safframework.bytekit.jdk.mmap;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by tony on 2018-12-13.
 */
public class MmapBuffer {

    /**
     * the bucket's file
     */
    private RandomAccessFile randomAccessFile = null;

    /**
     * the buffer of this file in the memory
     */
    private MappedByteBuffer mappedByteBuffer = null;

    /**
     * the buffer's size
     */
    private Long mmapBufferSize = null;

    /**
     * the current position
     */
    private Long position = null;

    /**
     * the constructor
     * @param file
     * @param mmapBufferSize
     * @param
     */
    public MmapBuffer(String file,Long mmapBufferSize) {

        try {
            this.mmapBufferSize = mmapBufferSize;
            this.randomAccessFile = new RandomAccessFile(file,"rw");
            this.mappedByteBuffer = this.randomAccessFile.getChannel().map(FileChannel.MapMode.READ_WRITE,0,this.mmapBufferSize);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public RandomAccessFile getRandomAccessFile() {
        return randomAccessFile;
    }

    public void setRandomAccessFile(RandomAccessFile randomAccessFile) {
        this.randomAccessFile = randomAccessFile;
    }

    public MappedByteBuffer getMappedByteBuffer() {
        return mappedByteBuffer;
    }

    public void setMappedByteBuffer(MappedByteBuffer mappedByteBuffer) {
        this.mappedByteBuffer = mappedByteBuffer;
    }

    public Long getMmapBufferSize() {
        return mmapBufferSize;
    }

    public void setMmapBufferSize(Long mmapBufferSize) {
        this.mmapBufferSize = mmapBufferSize;
    }

    public Long getPosition() {
        return position;
    }

    public void setPosition(Long position) {
        this.position = position;
    }
}
