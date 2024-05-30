import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @authors Charles Sirichoktanasup, Dylan Jian
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /*
     * Test cases for constructor
     */

    /**
     * Test for empty constructor.
     */
    @Test
    public final void constructorTestEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test for constructor with 1 item.
     */
    @Test
    public final void constructorTestOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("one");
        Set<String> sExpected = this.createFromArgsRef("one");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Test for constructor with many items.
     */
    @Test
    public final void constructorTestMany() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("set", "of", "many", "items");
        Set<String> sExpected = this.createFromArgsRef("set", "of", "many",
                "items");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /*
     * Test cases for kernel methods
     */

    /**
     * Tests adding a blank string to an empty set.
     */
    @Test
    public final void testAddBlankToEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("");
        /*
         * Call method under test
         */
        s.add("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests adding 1 item to an empty set.
     */
    @Test
    public final void testAddOneToEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("red");
        /*
         * Call method under test
         */
        s.add("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests adding many items to an empty set.
     */
    @Test
    public final void testAddManyToEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("black", "white",
                "gray");
        /*
         * Call method under test
         */
        s.add("black");
        s.add("white");
        s.add("gray");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests adding a blank string to a set with 1 item.
     */
    @Test
    public final void testAddBlankToOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("blank");
        Set<String> sExpected = this.createFromArgsRef("blank", "");
        /*
         * Call method under test
         */
        s.add("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests adding 1 item to a set with 1 item.
     */
    @Test
    public final void testAddOneToOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("north");
        Set<String> sExpected = this.createFromArgsRef("north", "south");
        /*
         * Call method under test
         */
        s.add("south");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests adding many items to a set with 1 item.
     */
    @Test
    public final void testAddManyToOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Wednesday");
        Set<String> sExpected = this.createFromArgsRef("Monday", "Tuesday",
                "Wednesday", "Thursday");
        /*
         * Call method under test
         */
        s.add("Monday");
        s.add("Tuesday");
        s.add("Thursday");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests adding a blank string to a set with many items.
     */
    @Test
    public final void testAddBlankToMany() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("dog", "cat", "fish");
        Set<String> sExpected = this.createFromArgsRef("dog", "cat", "fish",
                "");
        /*
         * Call method under test
         */
        s.add("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests adding a 1 item to a set with many items.
     */
    @Test
    public final void testAddOneToMany() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Mercury", "Venus", "Mars");
        Set<String> sExpected = this.createFromArgsRef("Mercury", "Venus",
                "Earth", "Mars");
        /*
         * Call method under test
         */
        s.add("Earth");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests adding a many items to a set with many items.
     */
    @Test
    public final void testAddManyToMany() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Europe", "Africa", "Asia");
        Set<String> sExpected = this.createFromArgsRef("North America",
                "South America", "Europe", "Africa", "Asia", "Australia");
        /*
         * Call method under test
         */
        s.add("North America");
        s.add("South America");
        s.add("Australia");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests removing a blank item to make a set empty.
     */
    @Test
    public final void testRemoveBlankMakeEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String str = s.remove("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("", str);
    }

    /**
     * Tests removing 1 item to make a set empty.
     */
    @Test
    public final void testRemoveOneMakeEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("empty");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String str = s.remove("empty");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("empty", str);
    }

    /**
     * Tests removing many items to make a set empty.
     */
    @Test
    public final void testRemoveManyMakeEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("first", "second", "third");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String str1 = s.remove("first");
        String str2 = s.remove("second");
        String str3 = s.remove("third");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("first", str1);
        assertEquals("second", str2);
        assertEquals("third", str3);
    }

    /**
     * Tests removing a blank string so 1 item remains in a set.
     */
    @Test
    public final void testRemoveBlankMakeOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("person", "");
        Set<String> sExpected = this.createFromArgsRef("person");
        /*
         * Call method under test
         */
        String str = s.remove("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("", str);
    }

    /**
     * Tests removing 1 item so 1 item remains in a set.
     */
    @Test
    public final void testRemoveOneMakeOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("eat", "drink");
        Set<String> sExpected = this.createFromArgsRef("drink");
        /*
         * Call method under test
         */
        String str = s.remove("eat");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("eat", str);
    }

    /**
     * Tests removing many items so 1 item remains in a set.
     */
    @Test
    public final void testRemoveManyMakeOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("earth", "wind", "fire", "ice");
        Set<String> sExpected = this.createFromArgsRef("ice");
        /*
         * Call method under test
         */
        String str1 = s.remove("earth");
        String str2 = s.remove("wind");
        String str3 = s.remove("fire");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("earth", str1);
        assertEquals("wind", str2);
        assertEquals("fire", str3);
    }

    /**
     * Tests removing a blank string so many items remain in a set.
     */
    @Test
    public final void removeBlankMakeMany() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("city", "state", "country", "");
        Set<String> sExpected = this.createFromArgsRef("city", "state",
                "country");
        /*
         * Call method under test
         */
        String str = s.remove("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("", str);
    }

    /**
     * Tests removing a 1 items so many items remain in a set.
     */
    @Test
    public final void removeOneMakeMany() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("solid", "liquid", "gas",
                "plasma");
        Set<String> sExpected = this.createFromArgsRef("solid", "liquid",
                "gas");
        /*
         * Call method under test
         */
        String str = s.remove("plasma");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("plasma", str);
    }

    /**
     * Tests removing many items so many items remain in a set.
     */
    @Test
    public final void removeManyMakeMany() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Fortnite", "Minecraft", "GTA",
                "Clash Royale", "Call of Duty", "Super Mario");
        Set<String> sExpected = this.createFromArgsRef("Minecraft", "GTA",
                "Clash Royale");
        /*
         * Call method under test
         */
        String str1 = s.remove("Fortnite");
        String str2 = s.remove("Super Mario");
        String str3 = s.remove("Call of Duty");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals("Fortnite", str1);
        assertEquals("Super Mario", str2);
        assertEquals("Call of Duty", str3);
    }

    /**
     * Tests removeAny once on a set with a blank string, making it empty.
     */
    @Test
    public final void testRemoveAnyBlankMakeEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String str = s.removeAny();
        String strExpected = "";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Tests removeAny once on a set with 1 item, making it empty.
     */
    @Test
    public final void testRemoveAnyOneMakeEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("any");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        String str = s.removeAny();
        String strExpected = "any";
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(strExpected, str);
    }

    /**
     * Tests removeAny many times on a set with many items, making it empty.
     */
    @Test
    public final void testRemoveAnyManyMakeEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("left", "right", "center");
        Set<String> sExpected = this.createFromArgsRef("left", "right",
                "center");
        /*
         * Call method under test
         */
        final int removed = 3;
        for (int i = 0; i < removed; i++) {
            String str = s.removeAny();
            assertEquals(true, sExpected.contains(str));
            sExpected.remove(str);
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, sExpected);
    }

    /**
     * Tests removeAny once on a set with 2 items, leaving it with 1 item.
     */
    @Test
    public final void testRemoveAnyOneMakeOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("before", "after");
        Set<String> sExpected = this.createFromArgsRef("before", "after");
        /*
         * Call method under test
         */
        String str = s.removeAny();
        assertEquals(true, sExpected.contains(str));
        sExpected.remove(str);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests removeAny many times on a set with many items, leaving it with 1
     * item.
     */
    @Test
    public final void testRemoveAnyManyMakeOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("lift", "drag", "thrust",
                "weight");
        Set<String> sExpected = this.createFromArgsRef("lift", "drag", "thrust",
                "weight");
        /*
         * Call method under test
         */
        final int removed = 3;
        for (int i = 0; i < removed; i++) {
            String str = s.removeAny();
            assertEquals(true, sExpected.contains(str));
            sExpected.remove(str);
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests removeAny once on a set with many items, leaving it with many
     * items.
     */
    @Test
    public final void testRemoveAnyOneMakeMany() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("car", "plane", "train",
                "boat");
        Set<String> sExpected = this.createFromArgsRef("car", "plane", "train",
                "boat");
        /*
         * Call method under test
         */
        String str = s.removeAny();
        assertEquals(true, sExpected.contains(str));
        sExpected.remove(str);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /**
     * Tests removeAny many times on a set with many items, leaving it with many
     * items.
     */
    @Test
    public final void removeAnyManyMakeMany() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("England", "Italy", "Spain",
                "Germany", "France", "Portugal");
        Set<String> sExpected = this.createFromArgsTest("England", "Italy",
                "Spain", "Germany", "France", "Portugal");
        /*
         * Call method under test
         */
        final int removed = 3;
        for (int i = 0; i < removed; i++) {
            String str = s.removeAny();
            assertEquals(true, sExpected.contains(str));
            sExpected.remove(str);
        }
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, sExpected);
    }

    /**
     * Test contains on an empty set not containing the desired item.
     */
    @Test
    public final void testContainsEmptyFalse() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsTest();
        /*
         * Call method under test
         */
        boolean contains = s.contains("anything");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(s, sExpected);
        assertEquals(false, contains);
    }

    /**
     * Test contains on an set containing a blank string which is the desired
     * item.
     */
    @Test
    public final void testContainsBlankItemTrue() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("");
        Set<String> sExpected = this.createFromArgsRef("");
        /*
         * Call method under test
         */
        boolean contains = s.contains("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(true, contains);
    }

    /**
     * Test contains on an set containing a blank string which is not the
     * desired item.
     */
    @Test
    public final void testContainsBlankItemFalse() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("not blank");
        Set<String> sExpected = this.createFromArgsRef("not blank");
        /*
         * Call method under test
         */
        boolean contains = s.contains("");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(false, contains);
    }

    /**
     * Test contains on an set containing 1 item which is the desired item.
     */
    @Test
    public final void testContainsOneItemTrue() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("item");
        Set<String> sExpected = this.createFromArgsRef("item");
        /*
         * Call method under test
         */
        boolean contains = s.contains("item");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(true, contains);
    }

    /**
     * Test contains on an set containing 1 item which is not the desired item.
     */
    @Test
    public final void testContainsOneItemFalse() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("wrong");
        Set<String> sExpected = this.createFromArgsRef("wrong");
        /*
         * Call method under test
         */
        boolean contains = s.contains("right");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(false, contains);
    }

    /**
     * Test contains on an set containing many items which are all desired.
     */
    @Test
    public final void testContainsManyItemsTrue() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("red", "green", "blue");
        Set<String> sExpected = this.createFromArgsRef("red", "green", "blue");
        /*
         * Call method under test
         */
        boolean contains = s.contains("red");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(true, contains);
    }

    /**
     * Test contains on an set containing many items which are all desired.
     */
    @Test
    public final void testContainsManyItemsFalse() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("morning", "afternoon",
                "evening");
        Set<String> sExpected = this.createFromArgsRef("morning", "afternoon",
                "evening");
        /*
         * Call method under test
         */
        boolean contains = s.contains("night");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(false, contains);
    }

    /**
     * Tests size of an empty set.
     */
    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int i = s.size();
        int iExpected = 0;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

    /**
     * Tests size of a set with a blank string.
     */
    @Test
    public final void testSizeBlank() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("");
        Set<String> sExpected = this.createFromArgsRef("");
        /*
         * Call method under test
         */
        int i = s.size();
        int iExpected = 1;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

    /**
     * Tests size of a set with 1 item.
     */
    @Test
    public final void testSizeOne() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("hello");
        Set<String> sExpected = this.createFromArgsRef("hello");
        /*
         * Call method under test
         */
        int i = s.size();
        int iExpected = 1;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }

    /**
     * Tests size of a set with many items.
     */
    @Test
    public final void testSizeMany() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("football", "basketball",
                "soccer");
        Set<String> sExpected = this.createFromArgsRef("football", "basketball",
                "soccer");
        /*
         * Call method under test
         */
        int i = s.size();
        final int iExpected = 3;
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(iExpected, i);
    }
}
