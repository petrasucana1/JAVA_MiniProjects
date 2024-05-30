package org.example.testDirectory;

import org.example.Test;

@Test
public class TestClass1 {

    @Test
    public static void testMethod1() {
        System.out.println("Test1 Method 1 executed.");
    }

    @Test
    public void testMethod2(int value) {
        System.out.println("Test1 Method 2 executed with value: " + value);
    }

    @Test
    public void testMethod3(String message) {
        System.out.println("Test1 Method 3 executed with message: " + message);
    }

    public void nonTestMethod() {
        System.out.println("This is not a test1 method.");
    }
}
