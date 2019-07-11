package nio;


import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class Example {
    public static void main(String ...args) throws Exception{
        RandomAccessFile aFile = new RandomAccessFile("/Users/Acker/Desktop/HeapOOM.java", "rw");
        FileChannel inChannel = aFile.getChannel();


    }
}
