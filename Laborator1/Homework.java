// A number is k-reductible if it can be reduced to the value k by adding each of its digits, multiplied by itself, repeatedly. For example 13 is 1-reductible since 1*1 + 3*3 = 10 and 1*1 + 0*0=1.
// Write a program that finds all k-reductible numbers in a given interval [a,b]. The values a,b,k are given as command line argumens. Validate them.
// Create a String that contains the identified numbers and display it on the screen.
// Display the running time of the application in nanoseconds or milliseconds.
// Launch the application from the command line, for example: java Lab1 100 200 1.

import java.util.*;

public class Main {
    public static void main(String[] args) {

        if(args.length != 3){
            System.out.println("Error: The arguments should be: a,b,k");
            return;
        }
        try{
            int a = Integer.parseInt(args[0]);
            int b = Integer.parseInt(args[1]);
            int k = Integer.parseInt(args[2]);

            if(a>b){
                System.out.println("Error: Value of a must be less or equal to value of b.");
                return;
            }

            long startTime = System.nanoTime();

            String[] KReductibleNumbers = new String[b-a+1];
            for(int i=a; i<=b; i++)
            {
                int result = FindK(i);
                if(result==k)
                    KReductibleNumbers[i-a] = String.valueOf(i); //String.valueOf(x) converteste orice tip de date la un sir de carctere
            }

            long endTime = System.nanoTime();
            long elapsedTime = endTime - startTime;

            System.out.println(k+"-reductible elements from the given interval ["+a+","+b+"] are:");
            for(String number: KReductibleNumbers){
                if (number != null)
                    System.out.print(number + " ");
            }
            System.out.println();

            // Afisare timp de executie
            System.out.println("Elapsed Time: " + elapsedTime + " nanoseconds");

        } catch (NumberFormatException e){
            System.out.println("Error: Invalid input...");
        }
    }

    static int FindK (int n) {
        int sum;
        do {
            sum = 0;
            while (n != 0) {
                sum += n % 10 * n % 10;
                n /= 10;
            }
            n = sum;
        } while (sum > 9);
        return sum;
    }
}