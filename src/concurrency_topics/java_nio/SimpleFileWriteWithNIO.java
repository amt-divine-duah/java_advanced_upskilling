package concurrency_topics.java_nio;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public class SimpleFileWriteWithNIO {

    public static void main(String[] args) {
        // File to write to
        File file = new File("src/concurrency_topics/java_nio/output.txt");

        // Open file channel in write mode
        try (FileChannel channel = FileChannel.open(file.toPath(), StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {  // Open for writing and create if needed
            // Data to write
            String data = "This is a simple write file!";

            // Allocate a byte buffer
            ByteBuffer buffer = ByteBuffer.wrap(data.getBytes());  // Wrap the data in a buffer

            // Write the data from the buffer to the file
            int bytesWritten = channel.write(buffer);

            System.out.println("Bytes written: " + bytesWritten);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
