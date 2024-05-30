import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /**
     * testing the constructor.
     */
    @Test
    public final void testConstructor() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * testing add, adding one to empty to make one.
     */
    @Test
    public final void testAddOneToEmptyMakeOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        /*
         * Call method under test
         */
        m.add("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * testing add, adding one to one to make two.
     */
    @Test
    public final void testAddOneToOneMakeTwo() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue");
        /*
         * Call method under test
         */
        m.add("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);

    }

    /**
     * testing add, adding two to two to make four.
     */
    @Test
    public final void testAddTwoToTwoMakeFour() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "yellow", "black");
        /*
         * Call method under test
         */
        m.add("yellow");
        m.add("black");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);

    }

    /**
     * testing add, adding three to three to make six.
     */
    @Test
    public final void testAddThreeToThreeMakeSix() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "yellow");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green", "blue", "yellow", "black", "purple", "red");
        /*
         * Call method under test
         */
        m.add("black");
        m.add("purple");
        m.add("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);

    }

    /**
     * Tests adding many items to a SortingMachine with many items.
     */
    @Test
    public final void testAddOneToMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "carbon", "oxygen", "nitrogen");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "carbon", "oxygen", "nitrogen", "hydrogen");
        /*
         * Call method under test
         */
        m.add("hydrogen");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests adding many items to an empty SortingMachine.
     */
    @Test
    public final void testAddManyToEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Trump", "Biden", "Obama");
        /*
         * Call method under test
         */
        m.add("Trump");
        m.add("Biden");
        m.add("Obama");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests adding many items to a SortingMachine with 1 item.
     */
    @Test
    public final void testAddManyToOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "Florida");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "Florida", "California", "Texas", "New York");
        /*
         * Call method under test
         */
        m.add("California");
        m.add("Texas");
        m.add("New York");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * testing isInInsertionMode, when it's true and empty.
     */
    @Test
    public final void testIsInInsertionModeTrueEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(m.isInInsertionMode(), true);

    }

    /**
     * testing isInInsertionMode, when it's true and has one element.
     */
    @Test
    public final void testIsInInsertionModeTrueOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(true, m.isInInsertionMode());

    }

    /**
     * testing isInInsertionMode, when it's true and has many elements.
     */
    @Test
    public final void testIsInInsertionModeTrueMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "black");
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(true, m.isInInsertionMode());

    }

    /**
     * testing isInInsertionMode, when it's false and empty.
     */
    @Test
    public final void testIsInInsertionModeFalseEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(false, m.isInInsertionMode());

    }

    /**
     * testing isInInsertionMode, when it's false and has one element.
     */
    @Test
    public final void testIsInInsertionModeFalseOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(false, m.isInInsertionMode());

    }

    /**
     * testing isInInsertionMode, when it's false and has many elements.
     */
    @Test
    public final void testIsInInsertionModeFalseMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "black");
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(false, m.isInInsertionMode());

    }

    /**
     * testing changeToExtraction mode when empty.
     */
    @Test
    public final void testChangeToExtractionModeEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        assertEquals(m.isInInsertionMode(), true);
        /*
         * Call method under test
         */
        m.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, m.isInInsertionMode());

    }

    /**
     * testing changeToExtraction mode when there's one element.
     */
    @Test
    public final void testChangeToExtractionModeOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        assertEquals(true, m.isInInsertionMode());
        /*
         * Call method under test
         */
        m.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, m.isInInsertionMode());

    }

    /**
     * testing changeToExtraction mode when there's two elements.
     */
    @Test
    public final void testChangeToExtractionModeTwo() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue");
        assertEquals(true, m.isInInsertionMode());
        /*
         * Call method under test
         */
        m.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, m.isInInsertionMode());

    }

    /**
     * testing changeToExtraction mode when there's many elements.
     */
    @Test
    public final void testChangeToExtractionModeMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "black");
        assertEquals(true, m.isInInsertionMode());
        /*
         * Call method under test
         */
        m.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, m.isInInsertionMode());

    }

    /**
     * Tests changeToExtractionMode on an initially empty SortingMachine that
     * has an item added to it.
     */
    @Test
    public final void testChangeToExtractionModeAfterAddToEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "extraction");
        /*
         * Call method under test
         */
        m.add("extraction");
        m.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests changeToExtractionMode on a SortingMachine with 1 item.
     */
    @Test
    public final void testChangeToExtractionModeAfterAddToOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "arms");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "arms", "legs");
        /*
         * Call method under test
         */
        m.add("legs");
        m.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests changeToExtractionMode on a SortingMachine that initially has 1
     * item, then has an item added to it.
     */
    @Test
    public final void testChangeToExtractionModeAfterAddToMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "logarithmic", "exponential", "quadratic");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "logarithmic", "linear", "exponential", "quadratic");
        /*
         * Call method under test
         */
        m.add("linear");
        m.changeToExtractionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * testing removeFirst, removing once from one to empty.
     */
    @Test
    public final void testRemoveFirstOneToEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green");
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER,
                false);
        /*
         * Call method under test
         */
        String x = m.removeFirst();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("green", x);

    }

    /**
     * testing removeFirst, removing once from two to one.
     */
    @Test
    public final void testRemoveFirstTwoToOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue");
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER, false,
                "green");
        /*
         * Call method under test
         */
        String x = m.removeFirst();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("blue", x);

    }

    /**
     * testing removeFirst, removing twice from three to one.
     */
    @Test
    public final void testRemoveFirstThreeToOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "black");
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER, false,
                "green");
        /*
         * Call method under test
         */
        String x = m.removeFirst();
        String y = m.removeFirst();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("black", x);
        assertEquals("blue", y);

    }

    /**
     * testing removeFirst, removing twice from four to two.
     */
    @Test
    public final void testRemoveFirstFourToTwo() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "yellow", "black");
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER, false,
                "green", "yellow");
        /*
         * Call method under test
         */
        String x = m.removeFirst();
        String y = m.removeFirst();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("black", x);
        assertEquals("blue", y);

    }

    /**
     * Tests removeFirst once to make a SortingMachine with many items.
     */
    @Test
    public final void testRemoveFirstOneMakeMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "Coca-Cola", "Pepsi", "Sprite", "Dr. Pepper");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false,
                "Pepsi", "Sprite", "Dr. Pepper");
        String sExpected = "Coca-Cola";
        m.changeToExtractionMode();
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(sExpected, m.removeFirst());
        assertEquals(mExpected, m);
    }

    /**
     * Tests removeFirst many times to make a SortingMachine empty.
     */
    @Test
    public final void testRemoveFirstManyMakeEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "strawberry", "chocolate", "vanilla");
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, false);
        String s1Expected = "chocolate";
        String s2Expected = "strawberry";
        String s3Expected = "vanilla";
        m.changeToExtractionMode();
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(s1Expected, m.removeFirst());
        assertEquals(s2Expected, m.removeFirst());
        assertEquals(s3Expected, m.removeFirst());
        assertEquals(mExpected, m);
    }

    /**
     * testing order when isInInsertionMode is true and it's empty.
     */
    @Test
    public final void testOrderTrueEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(ORDER, m.order());

    }

    /**
     * testing order when isInInsertionMode is true and there's many elements.
     */
    @Test
    public final void testOrderTrueMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "black", "yellow", "purple");
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(ORDER, m.order());

    }

    /**
     * testing order when isInInsertionMode is false and it's empty.
     */
    @Test
    public final void testOrderFalseEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false);
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(ORDER, m.order());

    }

    /**
     * testing order when isInInsertionMode is false and there's many elements.
     */
    @Test
    public final void testOrderFalseMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, false,
                "green", "blue", "black", "yellow", "purple");
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(ORDER, m.order());

    }

    /**
     * Tests order for SortingMachine with 1 item in insertion mode.
     */
    @Test
    public final void testOrderOneInsertionMode() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "order");
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER, true,
                "order");
        /*
         * Call method under test, assert that values of variables meet
         * expectations
         */
        assertEquals(m, mExpected);
        assertEquals(ORDER, m.order());
    }

    /**
     * Tests order for SortingMachine with 1 item in extraction mode.
     */
    @Test
    public final void testOrderOneExtractionMode() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "unordered");
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER, false,
                "unordered");
        m.changeToExtractionMode();
        /*
         * Call method under test, assert that values of variables meet
         * expectations
         */
        assertEquals(m, mExpected);
        assertEquals(ORDER, m.order());
    }

    /**
     * testing size when it's empty.
     */
    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(0, m.size());
    }

    /**
     * testing size when there's one element.
     */
    @Test
    public final void testSizeOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "green");
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(1, m.size());
    }

    /**
     * testing size when there's three elements.
     */
    @Test
    public final void testSizeThree() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "black");
        final int sizeExpected = 3;
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(sizeExpected, m.size());
    }

    /**
     * testing size when there's five elements.
     */
    @Test
    public final void testSizeFive() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "green",
                "blue", "black", "yellow", "purple");
        final int sizeExpected = 5;
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(sizeExpected, m.size());
    }

    /**
     * Tests size for empty SortingMachine in extraction mode.
     */
    @Test
    public final void testSizeExtractionModeEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER,
                false);
        m.changeToExtractionMode();
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(mExpected, m);
        assertEquals(0, m.size());
    }

    /**
     * Tests size for SortingMachine with 1 item in extraction mode.
     */
    @Test
    public final void testSizeExtractionModeOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true,
                "length");
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER, false,
                "length");
        m.changeToExtractionMode();
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(mExpected, m);
        assertEquals(1, m.size());
    }

    /**
     * Tests size for SortingMachine with many items in extraction mode.
     */
    @Test
    public final void testSizeExtractionModeMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true, "walk",
                "run", "bike");
        SortingMachine<String> mExpected = this.createFromArgsTest(ORDER, false,
                "walk", "run", "bike");
        final int sizeExpected = 3;
        m.changeToExtractionMode();
        /*
         * Call method under test, assert that values of variables match
         * expectations
         */
        assertEquals(mExpected, m);
        assertEquals(sizeExpected, m.size());
    }

}
