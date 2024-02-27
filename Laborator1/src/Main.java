public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};

        int n = (int) (Math.random() * 1_000_000);

        int result = performCalculations(n);

        System.out.println("Willy-nilly, this semester I will learn " + languages[result]);
    }

    private static int performCalculations(int n) {
        int step1 = n * 3;
        int step2 = step1 + 0b10101;
        int step3 = step2 + 0xFF;
        int result = step3 * 6;

        while (result >= 10) {
            result = computeDigitSum(result);
        }

        return result;
    }

    private static int computeDigitSum(int number) {
        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        return sum;
    }
}
