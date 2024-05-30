package org.example;
public class TestClass {

    @Test
    public static void testMethod1() {
        System.out.println("Test Method 1 executed.");
    }

    @Test
    public static void testMethod2() {
        System.out.println("Test Method 2 executed.");
    }

    public void nonTestMethod() {
        System.out.println("This is not a test method.");
    }
}
