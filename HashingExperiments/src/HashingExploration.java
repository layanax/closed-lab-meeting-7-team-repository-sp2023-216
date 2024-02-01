import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Lets the user test the {@code hashCode(String)} method, by reading text lines
 * from a file (whose name is supplied by the user), and then outputting the
 * distribution of lines into buckets.
 *
 * @author Layan Abdallah
 *
 */

//Questions from 5:
//The file that came from the adversary is likely "mod30.txt". When
//using a table size relatively prime to 30, such as a prime number around 1000,
//the impact of the adversary's scheme is lessened, but the distribution might
//still not be optimal depending on the characteristics of the input data. Using
//an actual prime number as the table size might have an advantage because it can
//provide a more uniform distribution and reduce the likelihood of collisions for
//certain types of input data. However, the advantage may not always be significant
//compared to using a number that is merely relatively prime to certain values.
//The hash function implemented in this code may not be doing a "good" job in
//distributing the values evenly among the buckets, especially for certain input
//data. It could be improved by implementing a more sophisticated hash function
//tailored to characteristics of the input data.

//Questions from 6:
//The file with the surprising distribution of bucket hits is likely
//"src/HashingExploration.java".The distribution looks surprising because source
//code files often contain patterns and structures that are not present in natural
//language text files.This can lead to non-uniform distributions when using a
//simple hash function like summing the char values.

//Questions from 7:
//The program calculates its results by hashing the lines of its own source code
//and then reporting the distribution of those hashed values among the buckets.
//This additional test does not necessarily shake confidence in the "goodness" of
//the hash function because source code files have different characteristics compared
//to natural language text files. However, it highlights the limitations of the
//hash function when dealing with certain types of data.

//Questions from 8:
//The alternate hash function should be chosen based on its ability to distribute
//the values evenly among the buckets for various types of input data. It should
//provide better performance than the original hash function in terms of minimizing
//collisions and achieving a more uniform distribution.

public final class HashingExploration {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private HashingExploration() {
    }

    /**
     * Computes {@code a} mod {@code b} as % should have been defined to work.
     *
     * @param a
     *            the number being reduced
     * @param b
     *            the modulus
     * @return the result of a mod b, which satisfies 0 <= {@code mod} < b
     * @requires b > 0
     * @ensures <pre>
     * 0 <= mod  and  mod < b  and
     * there exists k: integer (a = k * b + mod)
     * </pre>
     */
    public static int mod(int a, int b) {
        assert b > 0 : "Violation of: b > 0";

        //this is the version from class, but I had the same implementation before
        int mod = a % b;
        if (mod < 0) {
            mod += b;
        }
        return mod;

    }

    /**
     * Returns a hash code value for the given {@code String}.
     *
     * @param s
     *            the {@code String} whose hash code is computed
     * @return a hash code value for the given {@code String}
     * @ensures hashCode = [hash code value for the given String]
     */
    private static int hashCode(String s) {
        assert s != null : "Violation of: s is not null";

        int hashCode = 0;
        for (int i = 0; i < s.length(); i++) {
            hashCode += s.charAt(i);
        }
        return hashCode;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get hash table size and file name.
         */
        out.print("Hash table size: ");
        int hashTableSize = in.nextInteger();
        out.print("Text file name: ");
        String textFileName = in.nextLine();
        /*
         * Set up counts and counted. All entries in counts are automatically
         * initialized to 0.
         */
        int[] counts = new int[hashTableSize];
        Set<String> counted = new Set1L<String>();
        /*
         * Get some lines of input, hash them, and record counts.
         */
        SimpleReader textFile = new SimpleReader1L(textFileName);
        while (!textFile.atEOS()) {
            String line = textFile.nextLine();
            if (!counted.contains(line)) {
                int bucket = mod(hashCode(line), hashTableSize);
                counts[bucket]++;
                counted.add(line);
            }
        }
        textFile.close();
        /*
         * Report results.
         */
        out.println();
        out.println("Bucket\tHits\tBar");
        out.println("------\t----\t---");
        for (int i = 0; i < counts.length; i++) {
            out.print(i + "\t" + counts[i] + "\t");
            for (int j = 0; j < counts[i]; j++) {
                out.print("*");
            }
            out.println();
        }
        out.println();
        out.println("Total:\t" + counted.size());
        in.close();
        out.close();
    }

}
