package labs.lab_one.reflection_and_annotations;

import java.io.*;

public class CustomDirectoryClassLoader extends ClassLoader {

    private final String directoryPath;

    public CustomDirectoryClassLoader(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        try {
            byte[] classBytes = loadClassBytes(className);
            return defineClass(className, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("Failed to load class " + className, e);
        }
    }

    private byte[] loadClassBytes(String className) throws IOException {
        String classFilePath = directoryPath + File.separator + className.replace('.', File.separatorChar) + ".class";
        try (InputStream inputStream = new FileInputStream(classFilePath)) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int bytesRead;
            byte[] buffer = new byte[4096];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            return outputStream.toByteArray();
        }
    }

    public static void main(String[] args) {
        String directoryPath = "C:\\Santana\\Java Tutorials\\Amalitech Upskilling\\java_advanced_upskilling\\labs\\lab_one\\concurrency_topics\\array_summation";
        CustomDirectoryClassLoader classLoader = new CustomDirectoryClassLoader(directoryPath);

        try {
            Class<?> loadedClass = classLoader.loadClass("labs.lab_one.concurrency_topics.array_summation.ArraySummation");
            String simpleName = loadedClass.getSimpleName();
            System.out.println("I have loaded " + simpleName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
