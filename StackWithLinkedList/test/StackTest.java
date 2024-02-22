import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.stack.Stack;

/**
 * JUnit test fixture for {@code Stack<String>}'s constructor and kernel
 * methods.
 *
 * @author Put your name here
 *
 */
public abstract class StackTest {

    /**
     * Invokes the appropriate {@code Stack} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new stack
     * @ensures constructorTest = <>
     */
    protected abstract Stack<String> constructorTest();

    /**
     * Invokes the appropriate {@code Stack} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new stack
     * @ensures constructorRef = <>
     */
    protected abstract Stack<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Stack<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsTest = [entries in args]
     */
    private Stack<String> createFromArgsTest(String... args) {
        Stack<String> stack = this.constructorTest();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    /**
     *
     * Creates and returns a {@code Stack<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the stack
     * @return the constructed stack
     * @ensures createFromArgsRef = [entries in args]
     */
    private Stack<String> createFromArgsRef(String... args) {
        Stack<String> stack = this.constructorRef();
        for (String s : args) {
            stack.push(s);
        }
        stack.flip();
        return stack;
    }

    @Test
    public final void testConstructor() {
        Stack<String> set = this.constructorTest();
        Stack<String> expected = this.createFromArgsTest();
        assertEquals(expected, set);
    }

    @Test
    public void testPush1() {
        Stack<String> set = this.createFromArgsTest();
        Stack<String> expected = this.createFromArgsTest("Hi");
        set.push("Hi");
        assertEquals(expected, set);
    }

    @Test
    public void testPush2() {
        Stack<String> set = this.createFromArgsTest("Hello");
        Stack<String> expected = this.createFromArgsTest("Hi", "Hello");
        set.push("Hi");
        assertEquals(expected, set);
    }

    @Test
    public void testPop1() {
        Stack<String> set = this.createFromArgsTest("Hello");
        Stack<String> expected = this.createFromArgsTest();
        String temp = set.pop();
        assertEquals("Hello", temp);
        assertEquals(expected, set);
    }

    @Test
    public void testPop2() {
        Stack<String> set = this.createFromArgsTest("Hello,", "Hi");
        Stack<String> expected = this.createFromArgsTest("Hi");
        String temp = set.pop();
        assertEquals("Hi", temp);
        assertEquals(expected, set);
    }

    @Test
    public void testLength1() {
        Stack<String> set = this.createFromArgsTest();
        assertEquals(0, set.length());
    }

    @Test
    public void testLength2() {
        Stack<String> set = this.createFromArgsTest("hello", "hi");
        assertEquals(2, set.length());
    }

}
