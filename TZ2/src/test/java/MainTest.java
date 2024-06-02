import static org.junit.Assert.assertEquals;
import java.math.BigDecimal;
import org.junit.Test;

public class MainTest {
    @Test
    public void testGetMax() {
        String[] numbers = {"-1.23", "4.56", "7.89", "0.12"};
        BigDecimal expectedMax = new BigDecimal("7.89");
        assertEquals(expectedMax, Main.getMax(numbers));
    }

    @Test
    public void testGetMin() {
        String[] numbers = {"1.23", "4.56", "7.89", "-0.12"};
        BigDecimal expectedMin = new BigDecimal("-0.12");
        assertEquals(expectedMin, Main.getMin(numbers));
    }

    @Test
    public void testGetSum() {
        String[] numbers = {"1.23", "4.56", "7.89", "0.12"};
        BigDecimal expectedSum = new BigDecimal("13.80");
        assertEquals(expectedSum, Main.getSum(numbers));
    }

    @Test
    public void testGetMult() {
        String[] numbers = {"1.23", "4.56", "2.00"};
        BigDecimal expectedMult = new BigDecimal("11.217600");
        assertEquals(expectedMult, Main.getMult(numbers));
    }
}
