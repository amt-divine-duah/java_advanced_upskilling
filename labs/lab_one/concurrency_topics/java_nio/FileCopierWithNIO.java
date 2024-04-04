package labs.lab_one.concurrency_topics.java_nio;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.StandardOpenOption;

public class FileCopierWithNIO {

    public static void main(String[] args) throws IOException {
        // Specify source and destination files directly
        File sourceFile = new File("src/concurrency_topics/java_nio/copier_source.txt");
        File destinationFile = new File("src/concurrency_topics/java_nio/copier_destination.txt");

        // Open source and destination channels
        try (FileChannel sourceChannel = FileChannel.open(sourceFile.toPath(), StandardOpenOption.READ);
             FileChannel destinationChannel = FileChannel.open(destinationFile.toPath(), StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

            // Allocate a byte buffer
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // Copy data in chunks
            long bytesCopied = 0;
            int bytesRead;
            do {
                bytesRead = sourceChannel.read(buffer);
                bytesCopied += bytesRead;

                // Prepare the buffer for writing
                buffer.flip();

                // Write data to destination channel
                destinationChannel.write(buffer);

                // Clear the buffer for the next read
                buffer.clear();

            } while (bytesRead > 0);

            System.out.println("File copied successfully! Bytes copied: " + bytesCopied);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
