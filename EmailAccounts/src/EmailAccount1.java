import components.map.Map;
import components.map.Map1L;

/**
 * Implementation of {@code EmailAccount}.
 *
 * @author Layan Abdallah & Oak Hodous
 *
 */
public final class EmailAccount1 implements EmailAccount {

    /*
     * Private members --------------------------------------------------------
     */

    private String firstName;
    private String lastName;
    private String emailAddress;
    private static Map<String, Integer> map = new Map1L<>();

    /*
     * Constructor ------------------------------------------------------------
     */

    /**
     * Constructor.
     *
     * @param firstName
     *            the first name
     * @param lastName
     *            the last name
     */
    public EmailAccount1(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
        if (map.hasKey(lastName.toLowerCase())) {
            Integer n = map.value(lastName.toLowerCase());
            n = n + 1;
            this.emailAddress = lastName.toLowerCase() + "." + n + "@osu.edu";
            map.replaceValue(lastName.toLowerCase(), n);
        } else {
            map.add(lastName.toLowerCase(), 1);
            this.emailAddress = lastName.toLowerCase() + ".1@osu.edu";
        }

    }

    /*
     * Methods ----------------------------------------------------------------
     */

    @Override
    public String name() {

        return this.firstName + " "
                + this.lastName.substring(0, 1).toUpperCase()
                + this.lastName.substring(1);
    }

    @Override
    public String emailAddress() {

        return this.emailAddress;
    }

    @Override
    public String toString() {

        return "Name: " + this.name() + ", Email: " + this.emailAddress;

    }

}
