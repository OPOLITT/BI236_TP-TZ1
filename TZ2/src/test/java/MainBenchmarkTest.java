import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class MainBenchmarkTest {

    private static final int[] FILE_SIZES = {10, 500, 5_000, 50_000, 100_000};
    private File file;

    @Before
    public void setUp() {
        file = new File("test_numbers.txt");
    }

    @After
    public void tearDown() {
        if (file.exists()) {
            file.delete();
        }
    }

    private void benchmarkTest(int count) {
        Main.testCreateNumberFile(file, count);
        String[] numbers = Main.getNumbers(file);

        long start, finish;

        // Benchmark getMax
        start = System.currentTimeMillis();
        Main.getMax(numbers);
        finish = System.currentTimeMillis();
        System.out.println("getMax with " + count + " numbers: " + (finish - start) + " ms");

        // Benchmark getMin
        start = System.currentTimeMillis();
        Main.getMin(numbers);
        finish = System.currentTimeMillis();
        System.out.println("getMin with " + count + " numbers: " + (finish - start) + " ms");

        // Benchmark getSum
        start = System.currentTimeMillis();
        Main.getSum(numbers);
        finish = System.currentTimeMillis();
        System.out.println("getSum with " + count + " numbers: " + (finish - start) + " ms");

        // Benchmark getMult
        start = System.currentTimeMillis();
        Main.getMult(numbers);
        finish = System.currentTimeMillis();
        System.out.println("getMult with " + count + " numbers: " + (finish - start) + " ms");
    }

    @Test
    public void testBenchmark10() {
        benchmarkTest(FILE_SIZES[0]);
    }

    @Test
    public void testBenchmark500() {
        benchmarkTest(FILE_SIZES[1]);
    }

    @Test
    public void testBenchmark5000() {
        benchmarkTest(FILE_SIZES[2]);
    }

    @Test
    public void testBenchmark50000() {
        benchmarkTest(FILE_SIZES[3]);
    }

    @Test
    public void testBenchmark100000() {
        benchmarkTest(FILE_SIZES[4]);
    }
}
