import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.sequence.Sequence;

/**
 * JUnit test fixture for {@code Sequence<String>}'s constructor and kernel
 * methods.
 *
 * @author Layan & Oak
 *
 */
public abstract class SequenceTest {

    /**
     * Invokes the appropriate {@code Sequence} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new sequence
     * @ensures constructorTest = <>
     */
    protected abstract Sequence<String> constructorTest();

    /**
     * Invokes the appropriate {@code Sequence} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new sequence
     * @ensures constructorRef = <>
     */
    protected abstract Sequence<String> constructorRef();

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsTest = [entries in args]
     */
    private Sequence<String> createFromArgsTest(String... args) {
        Sequence<String> sequence = this.constructorTest();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     *
     * Creates and returns a {@code Sequence<String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the entries for the sequence
     * @return the constructed sequence
     * @ensures createFromArgsRef = [entries in args]
     */
    private Sequence<String> createFromArgsRef(String... args) {
        Sequence<String> sequence = this.constructorRef();
        for (String s : args) {
            sequence.add(sequence.length(), s);
        }
        return sequence;
    }

    /**
     * Test constructor.
     */
    @Test
    public void testConstructor() {
        Sequence<String> sequence = this.constructorTest();
        Sequence<String> sequenceExpected = this.constructorRef();
        assertEquals(sequence, sequenceExpected);
    }

    /**
     * Test add method with regular elements.
     */

    @Test
    public void testAddRegular() {
        Sequence<String> sequence = this.constructorTest();
        sequence.add(0, "A");
        sequence.add(1, "B");
        assertEquals(2, sequence.length());
    }

    /**
     * Test add method when adding beyond current length.
     */
    @Test
    public void testAddMoreThanLength() {
        Sequence<String> sequence = this.constructorTest();
        sequence.add(5, "C");
        assertEquals(3, sequence.length());
    }

    /**
     * Test remove method with regular elements.
     */
    @Test
    public void testRemove() {
        Sequence<String> sequence = this.createFromArgsTest("A", "B", "C");
        sequence.remove(1);
        assertEquals(2, sequence.length());
    }

    /**
     * Test remove method removing from an empty sequence.
     */
    @Test
    public void testRemoveEmpty() {
        Sequence<String> emptySequence = this.constructorTest();
        emptySequence.remove(0); // No exception should be thrown
        assertEquals(0, emptySequence.length());
    }

    /**
     * Test remove method removing from an index beyond the current length.
     */
    @Test
    public void testRemoveMoreThanLength() {
        Sequence<String> sequence = this.createFromArgsTest("A", "B", "C");
        sequence.remove(5);
        assertEquals(2, sequence.length());
    }

    /**
     * Test length method with a non empty sequence.
     */
    @Test
    public void testLengthNonEmpty() {
        Sequence<String> sequence = this.createFromArgsTest("A", "B", "C");
        assertEquals(3, sequence.length());
    }

    /**
     * Test length method with an empty sequence.
     */
    @Test
    public void testLengthEmpty() {
        Sequence<String> emptySequence = this.constructorTest();
        assertEquals(0, emptySequence.length());
    }

}
