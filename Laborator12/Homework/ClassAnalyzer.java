package org.example;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;

public class ClassAnalyzer {

    private static int totalTests = 0;
    private static int successfulTests = 0;
    private static int failedTests = 0;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide the path to a class, folder, or JAR file.");
            return;
        }

        String inputPath = args[0];
        File inputFile = new File(inputPath);

        if (inputFile.isDirectory()) {
            processDirectory(inputFile);
        } else if (inputFile.getName().endsWith(".jar")) {
            processJarFile(inputFile);
        } else if (inputFile.getName().endsWith(".class")) {
            processClassFile(inputFile);
        } else {
            System.err.println("Unsupported file type.");
        }

        printStatistics();
    }

    private static void processDirectory(File directory) {
        try (Stream<Path> paths = Files.walk(directory.toPath())) {
            paths.filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".class"))
                    .forEach(path -> processClassFile(path.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processJarFile(File jarFile) {
        try (JarFile jar = new JarFile(jarFile)) {
            Enumeration<? extends ZipEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (entry.getName().endsWith(".class")) {
                    // Process the class entry within the JAR
                    String className = entry.getName()
                            .replace('/', '.')
                            .replace('\\', '.')
                            .replace(".class", "");
                    processClassName(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processClassFile(File classFile) {
        try {
            String className = getClassNameFromFile(classFile);
            processClassName(className);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getClassNameFromFile(File classFile) {

        String absolutePath = classFile.getAbsolutePath();
        String classPath = absolutePath.substring(absolutePath.indexOf("org"), absolutePath.length() - 6);
        String className = classPath.replace(File.separatorChar, '.');
        System.out.println("Class name extracted: " + className);
        return className;
    }

    private static void processClassName(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            analyzeClass(clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void analyzeClass(Class<?> clazz) {
        if (clazz.isAnnotationPresent(Test.class)) {
            System.out.println("Class: " + clazz.getName());
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    System.out.println("Invoking test method: " + method.getName());
                    invokeTestMethod(clazz, method);
                }
            }
        }
    }

    private static void invokeTestMethod(Class<?> clazz, Method method) {
        try {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Object[] mockArgs = generateMockArgs(method.getParameterTypes());
            method.invoke(instance, mockArgs);
            successfulTests++;
        } catch (Exception e) {
            e.printStackTrace();
            failedTests++;
        } finally {
            totalTests++;
        }
    }

    private static Object[] generateMockArgs(Class<?>[] parameterTypes) {
        Object[] mockArgs = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] == int.class) {
                mockArgs[i] = 42;
            } else if (parameterTypes[i] == String.class) {
                mockArgs[i] = "mockString";
            }
        }
        return mockArgs;
    }

    private static void printStatistics() {
        System.out.println("\u001B[44m \u001B[37m Total tests: " + totalTests+"\u001B[0m");
        System.out.println("\u001B[42m \u001B[37m Successful tests: " + successfulTests+"\u001B[0m");
        System.out.println("\u001B[41m \u001B[37m Failed tests: " + failedTests+"\u001B[0m");
    }
}

