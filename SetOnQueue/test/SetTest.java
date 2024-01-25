import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Put your name here
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor and returns the result.
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

    /**
     * Tests for constructor with an empty set.
     */
    @Test
    public void testConstructorEmptySet() {
        Set<String> set = this.constructorTest();
        assertTrue(set.size() == 0);
        assertEquals(0, set.size());
    }

    /**
     * Tests for add method with a simple set.
     */
    @Test
    public void testAdd() {
        Set<String> set = this.constructorTest();
        set.add("A");
        set.add("B");
        assertTrue(set.contains("A"));
        assertTrue(set.contains("B"));
        assertFalse(set.contains("C"));
        assertEquals(2, set.size());
    }

    /**
     * Tests for remove method with a simple set.
     */
    @Test
    public void testRemove() {
        Set<String> set = this.createFromArgsTest("A", "B", "C");
        set.remove("B");
        assertFalse(set.contains("B"));
        assertEquals(2, set.size());
    }

    /**
     * Tests for removeAny method with a simple set.
     */
    @Test
    public void testRemoveAny() {
        Set<String> set = this.createFromArgsTest("A", "B", "C");
        String removed = set.removeAny();
        assertFalse(set.contains(removed));
        assertEquals(2, set.size());
    }

    /**
     * Tests for contains method with a simple set.
     */
    @Test
    public void testContains() {
        Set<String> set = this.createFromArgsTest("A", "B");
        assertTrue(set.contains("A"));
        assertTrue(set.contains("B"));
        assertFalse(set.contains("C"));
    }

    /**
     * Test for size method with a simple set.
     */
    @Test
    public void testSize() {
        Set<String> set = this.createFromArgsTest("A", "B", "C");
        assertEquals(3, set.size());
        set.remove("B");
        assertEquals(2, set.size());
        set.removeAny();
        assertEquals(1, set.size());
    }

}
