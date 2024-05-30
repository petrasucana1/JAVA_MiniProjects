package org.example.testDirectory;

import org.example.Test;

@Test
public class TestClass2 {

    @Test
    public static void testMethod1() {
        System.out.println("Test2 Method 1 executed.");
    }

    @Test
    public void testMethod2(int value) {
        System.out.println("Test2 Method 2 executed with value: " + value);
    }

    @Test
    public void testMethod3(String message) {
        System.out.println("Test2 Method 3 executed with message: " + message);
    }

    public void nonTestMethod() {
        System.out.println("This is not a test2 method.");
    }
}
