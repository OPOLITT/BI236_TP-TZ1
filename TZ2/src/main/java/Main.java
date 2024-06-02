import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File outputFile = new File("numbers.txt");
        //testCreateNumberFile(outputFile, 5_000);

        String[] numbers;
        numbers = getNumbers(outputFile);

        System.out.println("Max: " + getMax(numbers));
        System.out.println("Min: " + getMin(numbers));
        System.out.println("Sum: " + getSum(numbers));
        System.out.println("Product: " + getMult(numbers));
    }

    public static String[] getNumbers(File file) {
        try {
            return new Scanner(file).useDelimiter("\\A").next().split(" ");
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            System.exit(1);
        }
        return null;
    }

    public static BigDecimal getMax(String[] numbers) {
        if (numbers.length == 0) return null;
        BigDecimal max = new BigDecimal(numbers[0]);
        for (String number : numbers) {
            BigDecimal current = new BigDecimal(number);
            if (current.compareTo(max) > 0)
                max = current;
        }
        return max;
    }

    public static BigDecimal getMin(String[] numbers) {
        if (numbers.length == 0) return null;
        BigDecimal min = new BigDecimal(numbers[0]);
        for (String number : numbers) {
            BigDecimal current = new BigDecimal(number);
            if (current.compareTo(min) < 0)
                min = current;
        }
        return min;
    }

    public static BigDecimal getSum(String[] numbers) {
        if (numbers.length == 0) return null;
        BigDecimal sum = BigDecimal.ZERO;
        for (String number : numbers) {
            BigDecimal current = new BigDecimal(number);
            sum = sum.add(current);
        }
        return sum;
    }

    public static BigDecimal getMult(String[] numbers) {
        if (numbers.length == 0) return null;
        BigDecimal product = BigDecimal.ONE;
        for (String number : numbers) {
            BigDecimal current = new BigDecimal(number);
            product = product.multiply(current);
        }
        return product;
    }

    public static void testCreateNumberFile(File file, int count) { // extra test method
        try (PrintWriter writer = new PrintWriter(file)) {
            Random random = new Random();
            for (int i = 0; i < count; i++) {
                writer.print(new BigInteger(64, random) + "." +
                        new BigInteger(16, random) + " ");
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            System.err.println("Error creating file: " + e.getMessage());
        }
    }
}
