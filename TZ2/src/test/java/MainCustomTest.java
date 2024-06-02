import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MainCustomTest {

    @Test
    public void testEmptyArray() {
        String[] emptyArray = {};

        assertNull(Main.getMax(emptyArray));

        assertNull(Main.getMin(emptyArray));

        assertNull(Main.getSum(emptyArray));

        assertNull(Main.getMult(emptyArray));
    }
}
