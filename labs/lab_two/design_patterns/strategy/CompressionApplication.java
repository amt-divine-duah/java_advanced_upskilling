package labs.lab_two.design_patterns.strategy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

interface CompressionStrategy {
    void compress(String inputFilePath, String outputFilePath) throws IOException;
}

class ZipCompressionStrategy implements CompressionStrategy {

    @Override
    public void compress(String inputFilePath, String outputFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(outputFilePath);
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(inputFilePath)) {

            ZipEntry zipEntry = new ZipEntry(new File(inputFilePath).getName());
            zos.putNextEntry(zipEntry);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }

            zos.closeEntry();
        }
    }
}

class GzipCompressionStrategy implements CompressionStrategy {

    @Override
    public void compress(String inputFilePath, String outputFilePath) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(outputFilePath);
             GZIPOutputStream gos = new GZIPOutputStream(fos);
             FileInputStream fis = new FileInputStream(inputFilePath)) {

            byte[] buffer = new byte[1024];
            int length;
            while ((length = fis.read(buffer)) > 0) {
                gos.write(buffer, 0, length);
            }
        }
    }
}

class Compressor {

    private CompressionStrategy compressionStrategy;

    public void setCompressionStrategy(CompressionStrategy compressionStrategy) {
        this.compressionStrategy = compressionStrategy;
    }

    public void compressFile(String inputFilePath, String outputFilePath) throws IOException {
        if (compressionStrategy != null) {
            compressionStrategy.compress(inputFilePath, outputFilePath);
        } else {
            throw new IllegalStateException("Compression strategy not set");
        }
    }
}


public class CompressionApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Compressor compressor = new Compressor();

        System.out.println("Enter the input file path:");
        String inputFilePath = scanner.nextLine();

        System.out.println("Enter the output file path:");
        String outputFilePath = scanner.nextLine();

        System.out.println("Choose a compression algorithm:");
        System.out.println("1. Zip Compression");
        System.out.println("2. Gzip Compression");
        int choice = scanner.nextInt();

        try {
            switch (choice) {
                case 1:
                    compressor.setCompressionStrategy(new ZipCompressionStrategy());
                    break;
                case 2:
                    compressor.setCompressionStrategy(new GzipCompressionStrategy());
                    break;
                default:
                    System.out.println("Invalid choice. Exiting.");
                    return;
            }
            compressor.compressFile(inputFilePath, outputFilePath);
            System.out.println("File compressed successfully.");
        } catch (IOException e) {
            System.err.println("Error during compression: " + e.getMessage());
        }
    }
}
