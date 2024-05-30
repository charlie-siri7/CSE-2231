import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Charles Sirichoktanasup, Dylan Jian
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    /*
     * Test cases for constructors
     */

    /**
     * Tests if two empty NaturalNumber classes are equal (boundary).
     */
    @Test
    public final void testConstructorZeroEmpty() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest();
        NaturalNumber qExpected = this.constructorRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes set to 0 as an int are equal.
     */
    @Test
    public final void testConstructorZeroInt() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest(0);
        NaturalNumber qExpected = this.constructorRef(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes set to 0 as a string are equal.
     */
    @Test
    public final void testConstructorZeroString() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest("0");
        NaturalNumber qExpected = this.constructorRef("0");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes, one set to an int and another as a
     * string, are equal for the value 0.
     */
    @Test
    public final void testConstructorZeroIntVersusString() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest(0);
        NaturalNumber qExpected = this.constructorRef("0");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes, one set to an int and another as a
     * string, are equal for the value 0.
     */
    @Test
    public final void testConstructorZeroStringVersusInt() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest("0");
        NaturalNumber qExpected = this.constructorRef(0);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes set to 0 as a NaturalNumber are equal.
     */
    @Test
    public final void testConstructorZeroNaturalNumber() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n1 = this.constructorRef(0);
        NaturalNumber n2 = this.constructorRef("0");

        NaturalNumber q = this.constructorTest(n1);
        NaturalNumber qExpected = this.constructorRef(n2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes set to 1 as an int are equal.
     */
    @Test
    public final void testConstructorOneDigitInt() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest(1);
        NaturalNumber qExpected = this.constructorRef(1);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes set to 5 as a string are equal.
     */
    @Test
    public final void testConstructorOneDigitString() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest("5");
        NaturalNumber qExpected = this.constructorRef("5");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes, one set to an int and another as a
     * string, are equal for the value 3.
     */
    @Test
    public final void testConstructorOneDigitIntVersusString() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(3);
        NaturalNumber qExpected = this.constructorRef("3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes, one set to an int and another as a
     * string, are equal for the value 6.
     */
    @Test
    public final void testConstructorOneDigitStringVersusInt() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest("6");
        final NaturalNumber qExpected = this.constructorRef(6);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes set to 9 as a NaturalNumber are equal.
     */
    @Test
    public final void testConstructorOneDigitNaturalNumber() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n1 = this.constructorRef("9");
        final NaturalNumber n2 = this.constructorRef(9);

        NaturalNumber q = this.constructorTest(n1);
        NaturalNumber qExpected = this.constructorRef(n2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes set to 72 as an int are equal.
     */
    @Test
    public final void testConstructorTwoDigitsInt() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(72);
        final NaturalNumber qExpected = this.constructorRef(72);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes set to 72 as a string are equal.
     */
    @Test
    public final void testConstructorTwoDigitsString() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest("72");
        NaturalNumber qExpected = this.constructorRef("72");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes, one set to an int and another as a
     * string, are equal for the value 47.
     */
    @Test
    public final void testConstructorTwoDigitsIntVersusString() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(47);
        NaturalNumber qExpected = this.constructorRef("47");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes, one set to an int and another as a
     * string, are equal for the value 25.
     */
    @Test
    public final void testConstructorTwoDigitsStringVersusInt() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest("25");
        final NaturalNumber qExpected = this.constructorRef(25);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes set to 54 as a NaturalNumber are
     * equal.
     */
    @Test
    public final void testConstructorTwoDigitsNaturalNumber() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber n1 = this.constructorRef(54);
        NaturalNumber n2 = this.constructorRef("54");

        NaturalNumber q = this.constructorTest(n1);
        NaturalNumber qExpected = this.constructorRef(n2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes set to 243 as an int are equal.
     */
    @Test
    public final void testConstructorThreeDigitsInt() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(243);
        final NaturalNumber qExpected = this.constructorRef(243);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes set to 243 as a string are equal.
     */
    @Test
    public final void testConstructorThreeDigitsString() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest("324");
        final NaturalNumber qExpected = this.constructorRef("324");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes, one set to an int and another as a
     * string, are equal for the value 768.
     */
    @Test
    public final void testConstructorThreeDigitsIntVersusString() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(768);
        final NaturalNumber qExpected = this.constructorRef("768");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes, one set to an int and another as a
     * string, are equal for the value 987.
     */
    @Test
    public final void testConstructorThreeDigitsStringVersusInt() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest("987");
        final NaturalNumber qExpected = this.constructorRef(987);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests if two NaturalNumber classes set to 432 as a NaturalNumber are
     * equal.
     */
    @Test
    public final void testConstructorThreeDigitsNaturalNumber() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n1 = this.constructorRef("432");
        final NaturalNumber n2 = this.constructorRef(432);

        NaturalNumber q = this.constructorTest(n1);
        NaturalNumber qExpected = this.constructorRef(n2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests multiplyBy10 on an empty NaturalNumber constructor, checking if the
     * method results in the correct value and the int used to multiply remains
     * the same.
     */
    @Test
    public final void multiplyBy10Empty() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest();
        final NaturalNumber qExpected = this.constructorRef(8);
        /*
         * Call method under test
         */
        final int i = 8;
        q.multiplyBy10(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests multiplyBy10 on 6 as an int, checking if the method results in the
     * correct value and the int used to multiply remains the same.
     */
    @Test
    public final void multiplyBy10OneDigitInt() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(6);
        final NaturalNumber qExpected = this.constructorRef(64);
        /*
         * Call method under test
         */
        final int i = 4;
        q.multiplyBy10(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests multiplyBy10 on 3 as a string, checking if the method results in
     * the correct value and the int used to multiply remains the same.
     */
    @Test
    public final void multiplyBy10OneDigitString() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest("3");
        final NaturalNumber qExpected = this.constructorRef("32");
        /*
         * Call method under test
         */
        int i = 2;
        q.multiplyBy10(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests multiplyBy10 on 9 as a NaturalNumber, checking if the method
     * results in the correct value and the int used to multiply remains the
     * same.
     */
    @Test
    public final void multiplyBy10OneDigitNaturalNumber() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber n1 = this.constructorRef(9);
        NaturalNumber n2 = this.constructorRef("96");

        NaturalNumber q = this.constructorTest(n1);
        NaturalNumber qExpected = this.constructorRef(n2);
        /*
         * Call method under test
         */
        final int i = 6;
        q.multiplyBy10(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests multiplyBy10 on 21 as an int, checking if the method results in the
     * correct value and the int used to multiply remains the same.
     */
    @Test
    public final void multiplyBy10TwoDigitsInt() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(21);
        final NaturalNumber qExpected = this.constructorRef(216);
        /*
         * Call method under test
         */
        final int i = 6;
        q.multiplyBy10(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests multiplyBy10 on 34 as a string, checking if the method results in
     * the correct value and the int used to multiply remains the same.
     */
    @Test
    public final void multiplyBy10TwoDigitsString() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(34);
        final NaturalNumber qExpected = this.constructorRef(343);
        /*
         * Call method under test
         */
        final int i = 3;
        q.multiplyBy10(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests multiplyBy10 on 51 as a NaturalNumber, checking if the method
     * results in the correct value and the int used to multiply remains the
     * same.
     */
    @Test
    public final void multiplyBy10TwoDigitsNaturalNumber() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n1 = this.constructorRef("51");
        final NaturalNumber n2 = this.constructorRef(512);

        NaturalNumber q = this.constructorTest(n1);
        NaturalNumber qExpected = this.constructorRef(n2);
        /*
         * Call method under test
         */
        int i = 2;
        q.multiplyBy10(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests multiplyBy10 on 2-4 as an int, checking if the method results in
     * the correct value and the int used to multiply remains the same.
     */
    @Test
    public final void multiplyBy10ThreeDigitsInt() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(204);
        final NaturalNumber qExpected = this.constructorRef(2048);
        /*
         * Call method under test
         */
        final int i = 8;
        q.multiplyBy10(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests multiplyBy10 on 656 as a string, checking if the method results in
     * the correct value and the int used to multiply remains the same.
     */
    @Test
    public final void multiplyBy10ThreeDigitsString() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest("656");
        final NaturalNumber qExpected = this.constructorRef("6561");
        /*
         * Call method under test
         */
        int i = 1;
        q.multiplyBy10(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests multiplyBy10 on 160 as a NaturalNumber, checking if the method
     * results in the correct value and the int used to multiply remains the
     * same.
     */
    @Test
    public final void multiplyBy10ThreeDigitsNaturalNumber() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n1 = this.constructorRef("160");
        final NaturalNumber n2 = this.constructorRef(1607);

        NaturalNumber q = this.constructorTest(n1);
        NaturalNumber qExpected = this.constructorRef(n2);
        /*
         * Call method under test
         */
        final int i = 7;
        q.multiplyBy10(i);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
    }

    /**
     * Tests divideBy10 on an empty constructor, checking if the method results
     * in the correct NaturalNumber affected and int returned from the method
     * call.
     */
    @Test
    public final void divideBy10Empty() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber q = this.constructorTest();
        NaturalNumber qExpected = this.constructorRef();
        /*
         * Call method under test
         */
        int i = q.divideBy10();
        int iExpected = 0;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(iExpected, i);
    }

    /**
     * Tests divideBy10 on 6 as an int, checking if the method results in the
     * correct NaturalNumber affected and int returned from the method call.
     */
    @Test
    public final void divideBy10OneDigitInt() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(6);
        final NaturalNumber qExpected = this.constructorRef();
        /*
         * Call method under test
         */
        int i = q.divideBy10();
        final int iExpected = 6;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(iExpected, i);
    }

    /**
     * Tests divideBy10 on 3 as a string, checking if the method results in the
     * correct NaturalNumber affected and int returned from the method call.
     */
    @Test
    public final void divideBy10OneDigitString() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest("3");
        final NaturalNumber qExpected = this.constructorRef();
        /*
         * Call method under test
         */
        int i = q.divideBy10();
        final int iExpected = 3;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(iExpected, i);
    }

    /**
     * Tests divideBy10 on 2 as a NaturalNumber, checking if the method results
     * in the correct NaturalNumber affected and int returned from the method
     * call.
     */
    @Test
    public final void divideBy10OneDigitNaturalNumber() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber n1 = this.constructorRef(2);
        NaturalNumber n2 = this.constructorRef();

        final NaturalNumber q = this.constructorTest(n1);
        final NaturalNumber qExpected = this.constructorRef(n2);
        /*
         * Call method under test
         */
        int i = q.divideBy10();
        final int iExpected = 2;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(iExpected, i);
    }

    /**
     * Tests divideBy10 on 59 as an int, checking if the method results in the
     * correct NaturalNumber affected and int returned from the method call.
     */
    @Test
    public final void divideBy10TwoDigitsInt() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(59);
        final NaturalNumber qExpected = this.constructorRef(5);
        /*
         * Call method under test
         */
        int i = q.divideBy10();
        final int iExpected = 9;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(iExpected, i);
    }

    /**
     * Tests divideBy10 on 61 as a string, checking if the method results in the
     * correct NaturalNumber affected and int returned from the method call.
     */
    @Test
    public final void divideBy10TwoDigitsString() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest("61");
        final NaturalNumber qExpected = this.constructorRef("6");
        /*
         * Call method under test
         */
        int i = q.divideBy10();
        final int iExpected = 1;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(iExpected, i);
    }

    /**
     * Tests divideBy10 on 32 as a NaturalNumber, checking if the method results
     * in the correct NaturalNumber affected and int returned from the method
     * call.
     */
    @Test
    public final void divideBy10TwoDigitsNaturalNumber() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber n1 = this.constructorRef(32);
        NaturalNumber n2 = this.constructorRef("3");

        final NaturalNumber q = this.constructorTest(n1);
        final NaturalNumber qExpected = this.constructorRef(n2);
        /*
         * Call method under test
         */
        int i = q.divideBy10();
        final int iExpected = 2;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(iExpected, i);
    }

    /**
     * Tests divideBy10 on 411 as an int, checking if the method results in the
     * correct NaturalNumber affected and int returned from the method call.
     */
    @Test
    public final void divideBy10ThreeDigitsInt() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(411);
        final NaturalNumber qExpected = this.constructorRef(41);
        /*
         * Call method under test
         */
        int i = q.divideBy10();
        int iExpected = 1;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(iExpected, i);
    }

    /**
     * Tests divideBy10 on 183 as a string, checking if the method results in
     * the correct NaturalNumber affected and int returned from the method call.
     */
    @Test
    public final void divideBy10ThreeDigitsString() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest("183");
        final NaturalNumber qExpected = this.constructorRef("18");
        /*
         * Call method under test
         */
        int i = q.divideBy10();
        final int iExpected = 3;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(iExpected, i);
    }

    /**
     * Tests divideBy10 on 651 as a NaturalNumber, checking if the method
     * results in the correct NaturalNumber affected and int returned from the
     * method call.
     */
    @Test
    public final void divideBy10ThreeDigitsNaturalNumber() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n1 = this.constructorRef("651");
        final NaturalNumber n2 = this.constructorRef(65);

        final NaturalNumber q = this.constructorTest(n1);
        final NaturalNumber qExpected = this.constructorRef(n2);
        /*
         * Call method under test
         */
        int i = q.divideBy10();
        int iExpected = 1;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(iExpected, i);
    }

    /**
     * Tests isZero for an empty constructor.
     */
    @Test
    public final void isZeroTrueEmpty() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest();
        final NaturalNumber qExpected = this.constructorRef();
        /*
         * Call method under test
         */
        boolean isZero = q.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(true, isZero);
    }

    /**
     * Tests isZero for 0 as an int.
     */
    @Test
    public final void isZeroTrueInt() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(0);
        final NaturalNumber qExpected = this.constructorRef(0);
        /*
         * Call method under test
         */
        boolean isZero = q.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(true, isZero);
    }

    /**
     * Tests isZero for 0 as a string.
     */
    @Test
    public final void isZeroTrueString() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest("0");
        final NaturalNumber qExpected = this.constructorRef("0");
        /*
         * Call method under test
         */
        boolean isZero = q.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(true, isZero);
    }

    /**
     * Tests isZero for 0 as a NaturalNumber.
     */
    @Test
    public final void isZeroTrueNaturalNumber() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n1 = this.constructorRef("0");
        NaturalNumber n2 = this.constructorRef(0);

        NaturalNumber q = this.constructorTest(n1);
        NaturalNumber qExpected = this.constructorRef(n2);
        /*
         * Call method under test
         */
        boolean isZero = q.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(true, isZero);
    }

    /**
     * Tests isZero for 2 as an int.
     */
    @Test
    public final void isZeroFalseOneDigitInt() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest(2);
        final NaturalNumber qExpected = this.constructorRef(2);
        /*
         * Call method under test
         */
        boolean isZero = q.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(false, isZero);
    }

    /**
     * Tests isZero for 43 as a string.
     */
    @Test
    public final void isZeroFalseOneDigitString() {
        /*
         * Set up variables and call method under test
         */
        final NaturalNumber q = this.constructorTest("43");
        final NaturalNumber qExpected = this.constructorRef("43");
        /*
         * Call method under test
         */
        boolean isZero = q.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(false, isZero);
    }

    /**
     * Tests isZero for 562 as a NaturalNumber.
     */
    @Test
    public final void isZeroFalseOneDigitNaturalNumber() {
        /*
         * Set up variables and call method under test
         */
        NaturalNumber n1 = this.constructorRef("562");

        final NaturalNumber q = this.constructorTest(n1);
        final NaturalNumber qExpected = this.constructorRef(n1);
        /*
         * Call method under test
         */
        boolean isZero = q.isZero();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(qExpected, q);
        assertEquals(false, isZero);
    }

}
