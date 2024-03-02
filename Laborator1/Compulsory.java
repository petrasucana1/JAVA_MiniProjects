import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");

        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        for(String language: languages)
            System.out.print(language+" ");
        System.out.println();

        int n = (int) (Math.random() * 1000000);
        System.out.println(n);

        int result1 = PerformingCalculations(n);
        System.out.println(result1);

        int result2 = SumOfDigits(result1);
        System.out.println("Willy-nilly, this semester I will learn " + languages[result2]);
    }

    static int PerformingCalculations(int n)
    {
        n*=3;
        n+=Integer.parseInt("10101",2);
        n+=Integer.parseInt("FF",16);
        n*=6;

        return n;
    }

    static int SumOfDigits(int n)
    {
        int sum=0;
        while(n!=0)
        {
            sum+= n%10;
            n/=10;
        }
        if(sum>9)
        { return SumOfDigits(sum);}
        else
        { return sum;}

    }
}