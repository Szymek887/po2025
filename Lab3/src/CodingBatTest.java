import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CodingBatTest {
    private CodingBat cb;

    @Before
    public void setUp() throws Exception {
        cb = new CodingBat();
    }

    @Test
    public void sleepIn() {
        assertTrue(cb.sleepIn(false, false));
        assertTrue(cb.sleepIn(false, true));
        assertFalse(cb.sleepIn(true, false));
    }

    @Test
    public void diff21() {
        assertEquals(2, cb.diff21(19));
        assertEquals(11, cb.diff21(10));
        assertEquals(0, cb.diff21(21));
        assertEquals(18, cb.diff21(30));
    }

    @Test
    public void helloName() {
        assertEquals("Hello Bob!", cb.helloName("Bob"));
        assertEquals("Hello Alice!", cb.helloName("Alice"));
        assertEquals("Hello X!", cb.helloName("X"));
    }

    @Test
    public void countEvens() {
        int[] array1 = {2, 1, 2, 3, 4};
        int[] array2 = {2, 2, 0};
        int[] array3 = {1, 3, 5};
        assertEquals(3, cb.countEvens(array1));
        assertEquals(3, cb.countEvens(array2));
        assertEquals(0, cb.countEvens(array3));
    }
}