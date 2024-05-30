package org.example;
import org.example.Test;
import java.lang.reflect.Modifier;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class ClassAnalyzer {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide the fully qualified class name as an argument.");
            return;
        }

        String className = args[0];
        try {

            Class<?> clazz = Class.forName(className);
            System.out.println("Class: " + clazz.getName());

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                System.out.println("---------------------------");
                System.out.println("Method Name: " + method.getName());
                System.out.println("Return Type: " + method.getReturnType());
                System.out.println("Modifiers: " + Modifier.toString(method.getModifiers()));
                System.out.println("Is Static: " + Modifier.isStatic(method.getModifiers()));
                System.out.println("Parameters: " + method.getParameterCount());


                if (method.isAnnotationPresent(Test.class) && Modifier.isStatic(method.getModifiers()) && method.getParameterCount() == 0 ) {
                    System.out.println("\u001B[44m \u001B[37m Invoking test method: " + method.getName()+"\u001B[0m");
                    method.invoke(null);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isStatic(Method method) {
        return (method.getModifiers() & java.lang.reflect.Modifier.STATIC) != 0;
    }
}
