/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package euromillions;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.*;

/**
 * @author ico0
 */
public class DipTest {

    private Dip instance;


    public DipTest() {
    }

    @BeforeEach
    public void setUp() {
        instance = new Dip(new int[]{10, 20, 30, 40, 50}, new int[]{1, 2});
    }

    @AfterEach
    public void tearDown() {
        instance = null;
    }


    @Test
    public void testConstructorFromBadArrays() {
        // todo: instantiate a dip passing valid or invalid arrays
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            instance = new Dip(new int[]{1, 2, 3, 4}, new int[]{1, 2, 3});
        });
        assertEquals("wrong number of elements in numbers/stars", thrown.getMessage());
    }

    @Test
    public void testFormat() {
        // note: correct the implementation of the format(), not the test...
        String result = instance.format();
        assertEquals("N[ 10 20 30 40 50] S[  1  2]", result, "format as string: formatted string not as expected. ");
    }

    @Test
    public void testRangeStars() {
        
        Iterator<Integer> it = instance.getStarsColl().iterator();

        while(it.hasNext()) {
            int val = it.next();
            assertTrue(0 <= val && val <= 12);
        }
    }

}
