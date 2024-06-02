package org.example;

import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.nio.file.*;
import java.util.*;
import java.lang.reflect.*;
import org.objectweb.asm.*;

public class ClassAnalyzer {

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java org.example.ClassAnalyzer <input file or directory>");
            return;
        }

        String inputPath = args[0];
        File inputFile = new File(inputPath);

        if (!inputFile.exists()) {
            System.out.println("File or directory does not exist: " + inputPath);
            return;
        }

        List<File> javaFiles = new ArrayList<>();
        if (inputFile.isDirectory()) {
            findJavaFiles(inputFile, javaFiles);
        } else {
            if (inputPath.endsWith(".java")) {
                javaFiles.add(inputFile);
            } else {
                System.out.println("Unsupported file type.");
                return;
            }
        }

        if (!compileJavaFiles(javaFiles)) {
            System.out.println("Compilation failed.");
            return;
        }

        List<File> classFiles = findClassFiles(new File("out"));
        for (File classFile : classFiles) {
            analyzeClass(classFile);
        }

        System.out.println("Modified Class File added successfully!");
    }

    private static void findJavaFiles(File dir, List<File> javaFiles) {
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                findJavaFiles(file, javaFiles);
            } else if (file.getName().endsWith(".java")) {
                javaFiles.add(file);
            }
        }
    }

    private static boolean compileJavaFiles(List<File> javaFiles) throws IOException {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);

        Iterable<? extends JavaFileObject> compilationUnits = fileManager.getJavaFileObjectsFromFiles(javaFiles);
        return compiler.getTask(null, fileManager, null, Arrays.asList("-d", "out"), null, compilationUnits).call();
    }

    private static List<File> findClassFiles(File dir) {
        List<File> classFiles = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                classFiles.addAll(findClassFiles(file));
            } else if (file.getName().endsWith(".class")) {
                classFiles.add(file);
            }
        }
        return classFiles;
    }

    private static void analyzeClass(File classFile) throws Exception {
        // Use ASM to analyze and manipulate the bytecode
        ClassReader classReader = new ClassReader(Files.newInputStream(classFile.toPath()));
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_FRAMES | ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM9, classWriter) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                return new MethodVisitor(Opcodes.ASM9, mv) {
                    @Override
                    public void visitCode() {
                        super.visitCode();
                        // Inject code at the beginning of the method
                        if (name.equals("testMethod")) {
                            mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                            mv.visitLdcInsn("Injected code at the beginning of testMethod.");
                            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                        }
                    }
                };
            }
        };

        classReader.accept(classVisitor, 0);


        byte[] modifiedClass = classWriter.toByteArray();
        Path modifiedClassPath = Paths.get(classFile.getParent(), classFile.getName().replace(".class", "Modified.class"));
        Files.write(modifiedClassPath, modifiedClass);
    }
}
