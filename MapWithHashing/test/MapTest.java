import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @authors Dylan Jian and Charles Sirichoktanasup
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     * Tests Map4 constructor for an empty Map.
     */
    @Test
    public final void testConstructorEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests Map4 constructor for a map with 1 pair.
     */
    @Test
    public final void testConstructorOneItem() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("one", "1");
        Map<String, String> mExpected = this.createFromArgsRef("one", "1");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests Map4 constructor for a map with 2 pairs.
     */
    @Test
    public final void testConstructorTwoItems() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("one", "1", "two", "2");
        Map<String, String> mExpected = this.createFromArgsRef("one", "1",
                "two", "2");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests Map4 constructor for a map with 3 pairs.
     */
    @Test
    public final void testConstructorThreeItems() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("one", "1", "two", "2",
                "three", "3");
        Map<String, String> mExpected = this.createFromArgsRef("one", "1",
                "two", "2", "three", "3");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests adding 1 pair to a Map4 when it is initially empty.
     */
    @Test
    public final void testAddEmptyOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("north", "N");
        /*
         * Call method under test
         */
        m.add("north", "N");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests adding 2 pairs to a Map4 when it is initially empty.
     */
    @Test
    public final void testAddEmptyTwo() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("north", "N",
                "south", "S");
        /*
         * Call method under test
         */
        m.add("north", "N");
        m.add("south", "S");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests adding 1 pair to a Map4 when it already has pairs.
     */
    @Test
    public final void testAddNotEmptyOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("east", "E");
        Map<String, String> mExpected = this.createFromArgsRef("east", "E",
                "west", "W");
        /*
         * Call method under test
         */
        m.add("west", "W");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests adding 2 pairs to a Map4 when it already has pairs.
     */
    @Test
    public final void testAddNotEmptyTwo() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("north", "N");
        Map<String, String> mExpected = this.createFromArgsRef("north", "N",
                "south", "S", "east", "E");
        /*
         * Call method under test
         */
        m.add("south", "S");
        m.add("east", "E");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests removing 1 pair from a Map4 to make it empty.
     */
    @Test
    public final void testRemoveOneMakeEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("red", "r");
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        m.remove("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests removing 2 pairs from a Map4 to make it empty.
     */
    @Test
    public final void testRemoveTwoMakeEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("red", "r", "yellow",
                "y");
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        m.remove("red");
        m.remove("yellow");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests removing 1 pair from a Map4 resulting in other pairs remaining.
     */
    @Test
    public final void testRemoveOneMakeNotEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("green", "g", "blue",
                "b");
        Map<String, String> mExpected = this.createFromArgsRef("blue", "b");
        /*
         * Call method under test
         */
        m.remove("green");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests removing 2 pairs from a Map4 resulting in other pairs remaining.
     */
    @Test
    public final void testRemoveTwoMakeNotEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("red", "r", "yellow",
                "y", "blue", "b");
        Map<String, String> mExpected = this.createFromArgsRef("yellow", "y");
        /*
         * Call method under test
         */
        m.remove("red");
        m.remove("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests removeAny on a Map4 1 time to make it empty.
     */
    @Test
    public final void testRemoveAnyOneMakeEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("winter", "wi");
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        m.removeAny();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests removeAny on a Map4 2 times to make it empty.
     */
    @Test
    public final void testRemoveAnyTwoMakeEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("winter", "wi",
                "spring", "sp");
        Map<String, String> mExpected = this.createFromArgsRef("winter", "wi",
                "spring", "sp");
        /*
         * Call method under test
         */
        for (int i = 0; i < 2; i++) {
            Map.Pair<String, String> p = m.removeAny();
            assertEquals(true, mExpected.hasKey(p.key()));
            assertEquals(true, mExpected.hasValue(p.value()));
            mExpected.remove(p.key());
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests removeAny on a Map4 1 time resulting in other pairs remaining.
     */
    @Test
    public final void testRemoveAnyOneMakeNotEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("summer", "su",
                "autumn", "au");
        Map<String, String> mExpected = this.createFromArgsRef("summer", "su",
                "autumn", "au");
        /*
         * Call method under test
         */
        Map.Pair<String, String> p = m.removeAny();
        assertEquals(true, mExpected.hasKey(p.key()));
        assertEquals(true, mExpected.hasValue(p.value()));
        mExpected.remove(p.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests removeAny on a Map4 2 times resulting in other pairs remaining.
     */
    @Test
    public final void testRemoveAnyTwoMakeNotEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("spring", "su",
                "summer", "su", "autumn", "au");
        Map<String, String> mExpected = this.createFromArgsRef("spring", "su",
                "summer", "su", "autumn", "au");
        /*
         * Call method under test
         */
        for (int i = 0; i < 2; i++) {
            Map.Pair<String, String> p = m.removeAny();
            assertEquals(true, mExpected.hasKey(p.key()));
            assertEquals(true, mExpected.hasValue(p.value()));
            mExpected.remove(p.key());
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /**
     * Tests value for a Map4 with 1 pair.
     */
    @Test
    public final void testValueOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Monday", "M");
        Map<String, String> mExpected = this.createFromArgsRef("Monday", "M");
        /*
         * Call method under test
         */
        String s = m.value("Monday");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("M", s);
    }

    /**
     * Tests value for a Map4 with 2 pairs.
     */
    @Test
    public final void testValueTwo() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Monday", "M",
                "Tuesday", "T");
        Map<String, String> mExpected = this.createFromArgsRef("Monday", "M",
                "Tuesday", "T");
        /*
         * Call method under test
         */
        String s = m.value("Tuesday");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("T", s);
    }

    /**
     * Tests value for a Map4 with 3 pairs.
     */
    @Test
    public final void testValueThree() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Monday", "M",
                "Tuesday", "T", "Wednesday", "W");
        Map<String, String> mExpected = this.createFromArgsRef("Monday", "M",
                "Tuesday", "T", "Wednesday", "W");
        /*
         * Call method under test
         */
        String s1 = m.value("Wednesday");
        String s2 = m.value("Monday");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("W", s1);
        assertEquals("M", s2);
    }

    /**
     * Tests value for a Map4 with 4 pairs.
     */
    @Test
    public final void testValueFour() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Monday", "M",
                "Tuesday", "T", "Thursday", "Th", "Wednesday", "W");
        Map<String, String> mExpected = this.createFromArgsRef("Monday", "M",
                "Tuesday", "T", "Thursday", "Th", "Wednesday", "W");
        /*
         * Call method under test
         */
        String s1 = m.value("Thursday");
        String s2 = m.value("Tuesday");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("Th", s1);
        assertEquals("T", s2);
    }

    /**
     * Tests value for a Map4 in which the desired value is added to an empty
     * Map4.
     */
    @Test
    public final void testValueAddedToEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("Friday", "F");
        /*
         * Call method under test
         */
        m.add("Friday", "F");
        String s = m.value("Friday");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("F", s);
    }

    /**
     * Tests value for a Map4 in which the desired value is added to a Map4 with
     * other initial entries.
     */
    @Test
    public final void testValueAddedToNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Friday", "F");
        Map<String, String> mExpected = this.createFromArgsRef("Friday", "F",
                "Saturday", "S");
        /*
         * Call method under test
         */
        m.add("Saturday", "S");
        String s = m.value("Saturday");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals("S", s);
    }

    /**
     * Tests hasKey for a Map4 with 1 pair, containing the desired key.
     */
    @Test
    public final void testHasKeyTrueOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("January", "31");
        Map<String, String> mExpected = this.createFromArgsRef("January", "31");
        /*
         * Call method under test
         */
        boolean hasKey = m.hasKey("January");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, hasKey);
    }

    /**
     * Tests hasKey for a Map4 with 1 pair, not containing the desired key.
     */
    @Test
    public final void testHasKeyFalseOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("January", "31");
        Map<String, String> mExpected = this.createFromArgsRef("January", "31");
        /*
         * Call method under test
         */
        boolean hasKey = m.hasKey("February");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(false, hasKey);
    }

    /**
     * Tests hasKey for a Map4 with 2 pairs, containing the desired key.
     */
    @Test
    public final void testHasKeyTrueTwo() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("March", "31", "April",
                "30");
        Map<String, String> mExpected = this.createFromArgsRef("March", "31",
                "April", "30");
        /*
         * Call method under test
         */
        boolean hasKey = m.hasKey("March");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, hasKey);
    }

    /**
     * Tests hasKey for a Map4 with 2 pairs, not containing the desired key.
     */
    @Test
    public final void testHasKeyFalseTwo() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("April", "30", "May",
                "31");
        Map<String, String> mExpected = this.createFromArgsRef("April", "30",
                "May", "31");
        /*
         * Call method under test
         */
        boolean hasKey = m.hasKey("June");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(false, hasKey);
    }

    /**
     * Tests hasKey for an empty Map4, not containing the desired key.
     */
    @Test
    public final void testHasKeyFalseEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        boolean hasKey = m.hasKey("May");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(false, hasKey);
    }

    /**
     * Tests hasKey twice for a Map4, containing both of the desired keys.
     */
    @Test
    public final void testHasKeyTrueTwice() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("June", "30", "July",
                "31");
        Map<String, String> mExpected = this.createFromArgsRef("June", "30",
                "July", "31");
        /*
         * Call method under test
         */
        boolean hasKey1 = m.hasKey("June");
        boolean hasKey2 = m.hasKey("July");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, hasKey1);
        assertEquals(true, hasKey2);
    }

    /**
     * Tests hasKey twice for a Map4, containing neither of the desired keys.
     */
    @Test
    public final void testHasKeyFalseTwice() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("July", "31", "August",
                "31");
        Map<String, String> mExpected = this.createFromArgsRef("July", "31",
                "August", "31");
        /*
         * Call method under test
         */
        boolean hasKey1 = m.hasKey("September");
        boolean hasKey2 = m.hasKey("October");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(false, hasKey1);
        assertEquals(false, hasKey2);
    }

    /**
     * Tests hasKey twice for a Map4, containing 1 of the desired keys.
     */
    @Test
    public final void testHasKeyTrueAndFalse() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("August", "31",
                "September", "30");
        Map<String, String> mExpected = this.createFromArgsRef("August", "31",
                "September", "30");
        /*
         * Call method under test
         */
        boolean hasKey1 = m.hasKey("September");
        boolean hasKey2 = m.hasKey("October");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, hasKey1);
        assertEquals(false, hasKey2);
    }

    /**
     * Tests size of an empty Map4.
     */
    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int size = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(0, size);
    }

    /**
     * Tests size of a Map4 with 1 pair.
     */
    @Test
    public final void testSizeOne() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Math", "M");
        Map<String, String> mExpected = this.createFromArgsRef("Math", "M");
        /*
         * Call method under test
         */
        int size = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(1, size);
    }

    /**
     * Tests size of a Map4 with 2 pairs.
     */
    @Test
    public final void testSizeTwo() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Math", "M", "Science",
                "S");
        Map<String, String> mExpected = this.createFromArgsRef("Math", "M",
                "Science", "S");
        /*
         * Call method under test
         */
        int size = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(2, size);
    }

    /**
     * Tests size of a Map4 with 3 pairs.
     */
    @Test
    public final void testSizeThree() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Math", "M", "Science",
                "S", "English", "E");
        Map<String, String> mExpected = this.createFromArgsRef("Math", "M",
                "Science", "S", "English", "E");
        /*
         * Call method under test
         */
        int size = m.size();
        final int sizeExpected = 3;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(sizeExpected, size);
    }

    /**
     * Tests size of a Map4 after a pair has been added to an initially empty
     * Map.
     */
    @Test
    public final void testSizeAfterAddFromEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("Gym", "G");
        /*
         * Call method under test
         */
        m.add("Gym", "G");
        int size = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(1, size);
    }

    /**
     * Tests size of Map4 after a pair has been added to a map that already has
     * pairs.
     */
    @Test
    public final void testSizeAfterAddFromNonEmpty() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Gym", "G");
        Map<String, String> mExpected = this.createFromArgsRef("Gym", "G",
                "Lunch", "L");
        /*
         * Call method under test
         */
        m.add("Lunch", "L");
        int size = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(2, size);
    }

    /**
     * Tests size of a Map4 after an entry has been removed.
     */
    @Test
    public final void testSizeAfterRemove() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Lunch", "L");
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        m.remove("Lunch");
        int size = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(0, size);
    }

    /**
     * Tests size of a Map4 after removeAny has been called on it.
     */
    @Test
    public final void testSizeAfterRemoveAny() {
        /*
         * Set up variables and call method under test
         */
        Map<String, String> m = this.createFromArgsTest("Math", "M", "Science",
                "S");
        Map<String, String> mExpected = this.createFromArgsRef("Math", "M",
                "Science", "S");
        /*
         * Call method under test
         */
        Map.Pair<String, String> p = m.removeAny();
        assertEquals(true, mExpected.hasKey(p.key()));
        assertEquals(true, mExpected.hasValue(p.value()));
        mExpected.remove(p.key());
        int size = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(1, size);
    }

}
