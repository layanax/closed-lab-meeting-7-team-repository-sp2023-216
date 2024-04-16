import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Program to copy a text file into another file.
 *
 * @author Layan Abdallah & Oak Hodous
 *
 */
public final class CopyFileStdJava {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CopyFileStdJava() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments: input-file-name output-file-name
     */
    public static void main(String[] args) {

        BufferedReader in;
        PrintWriter out;
        try {
            in = new BufferedReader(new FileReader(args[0]));
            out = new PrintWriter(new BufferedWriter(new FileWriter(args[1])));
        } catch (IOException error) {
            System.err.println("Error: could not open file");
            return;
        }
        try {
            String input = in.readLine();
            while (input != null) {
                out.println(in);
                input = in.readLine();
            }
        } catch (IOException error) {
            System.err.println(
                    "Error: could not read from file or write to file");
        }
        try {

            in.close();
            out.close();
        } catch (IOException error) {
            System.err.println("Error: could not close file");
        }

    }

}
