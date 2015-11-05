package br.com.schumaker.core;

/**
 *
 * @author hudson.sales
 * @verison 1.0.0
 */
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ReadFileWithMappedByteBuffer {

    public static String readFile(String path, int ini, int end) {
        String out = "<html><p>";
        try {
            RandomAccessFile aFile = new RandomAccessFile(path, "r");
            FileChannel inChannel = aFile.getChannel();
            MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, ini, end);
            buffer.load();
            
            for (int i = 0; i < 10240; i++) {
                out = out + ((char) buffer.get());
            }
            out = out +"</p></html>";
            buffer.clear(); // do something with the data and clear/compact it.
            inChannel.close();
            aFile.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        return out;
    }

    public static long getSizeString(String path) {
        long s = 0;
        try {
            RandomAccessFile aFile = new RandomAccessFile(path, "r");
            FileChannel inChannel = aFile.getChannel();
            s = inChannel.size();
            inChannel.close();
            aFile.close();
        } catch (FileNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        return s;
    }
}
