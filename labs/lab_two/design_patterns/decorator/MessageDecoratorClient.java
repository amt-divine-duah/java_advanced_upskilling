package labs.lab_two.design_patterns.decorator;

import java.util.zip.Deflater;

interface Message {
    void send(String message);
}

class PlainTextMessage implements Message {
    @Override
    public void send(String message) {
        System.out.println("Sending message: " + message);
    }
}

abstract class MessageDecorator implements Message {
    protected Message decoratedMessage;

    public MessageDecorator(Message decoratedMessage) {
        this.decoratedMessage = decoratedMessage;
    }

    @Override
    public void send(String message) {
        decoratedMessage.send(message);
    }
}

class EncryptionDecorator extends MessageDecorator {
    public EncryptionDecorator(Message decoratedMessage) {
        super(decoratedMessage);
    }

    @Override
    public void send(String message) {
        String encryptedMessage = encrypt(message);
        decoratedMessage.send(encryptedMessage);
    }

    private String encrypt(String message) {
        // Simple encryption logic (reversing the string for illustration)
        return new StringBuilder(message).reverse().toString();
    }
}

class CompressionDecorator extends MessageDecorator {
    public CompressionDecorator(Message decoratedMessage) {
        super(decoratedMessage);
    }

    @Override
    public void send(String message) {
        String compressedMessage = compress(message);
        decoratedMessage.send(compressedMessage);
    }

    private String compress(String message) {
        // Simple compression logic (converting string to bytes and back for illustration)
        byte[] input = message.getBytes();
        byte[] output = new byte[100];
        Deflater deflater = new Deflater();
        deflater.setInput(input);
        deflater.finish();
        int compressedDataLength = deflater.deflate(output);
        deflater.end();
        return new String(output, 0, compressedDataLength);
    }
}


public class MessageDecoratorClient {
    public static void main(String[] args) {
        Message message = new PlainTextMessage();

        // Decorate with encryption
        Message encryptedMessage = new EncryptionDecorator(message);
        System.out.println("Sending encrypted message:");
        encryptedMessage.send("Hello, World!");

        // Decorate with compression
        Message compressedMessage = new CompressionDecorator(message);
        System.out.println("\nSending compressed message:");
        compressedMessage.send("Hello, World!");

        // Combine encryption and compression
        Message encryptedAndCompressedMessage = new CompressionDecorator(new EncryptionDecorator(message));
        System.out.println("\nSending encrypted and compressed message:");
        encryptedAndCompressedMessage.send("Hello, World!");
    }
}

