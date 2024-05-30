import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumberSecondary;

/**
 * {@code NaturalNumber} represented as a {@code String} with implementations of
 * primary methods.
 *
 * @convention <pre>
 * [all characters of $this.rep are '0' through '9']  and
 * [$this.rep does not start with '0']
 * </pre>
 * @correspondence <pre>
 * this = [if $this.rep = "" then 0
 *         else the decimal number whose ordinary depiction is $this.rep]
 * </pre>
 *
 * @author Charles Sirichoktanasup, Dylan Jian
 *
 */
public class NaturalNumber3 extends NaturalNumberSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private String rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.rep = "";
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public NaturalNumber3() {
        this.createNewRep();
    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumber3(int i) {
        assert i >= 0 : "Violation of: i >= 0";
        this.rep = String.valueOf(i);
        this.clearZero();
    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumber3(String s) {
        assert s != null : "Violation of: s is not null";
        assert s.matches("0|[1-9]\\d*") : ""
                + "Violation of: there exists n: NATURAL (s = TO_STRING(n))";
        this.rep = s;
        this.clearZero();
    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumber3(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        this.rep = n.toString();
        this.clearZero();
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final NaturalNumber newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(NaturalNumber source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof NaturalNumber3 : ""
                + "Violation of: source is of dynamic type NaturalNumberExample";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        NaturalNumber3 localSource = (NaturalNumber3) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    /*
     * concatenates the string value of k to a string representation of a
     * NaturalNumber, or setting the NaturalNumber to a string representation of
     * k if it is 0.
     */
    @Override
    public final void multiplyBy10(int k) {
        assert 0 <= k : "Violation of: 0 <= k";
        assert k < RADIX : "Violation of: k < 10";
        this.rep += String.valueOf(k);
        if (this.rep.length() == 0) {
            this.rep = String.valueOf(k);
        }
    }

    /*
     * Sets the default return value to 0 in case the NaturalNumber is empty
     * (representation of 0). If this is not true, the representation is
     * replaced with a substring of it containing everything except the last
     * digit. It is double-checked to make sure the last digit is less than 10.
     * The last digit is taken as a string and returned.
     */
    @Override
    public final int divideBy10() {
        String lastDigit = "0";
        int returnValue = Integer.parseInt(lastDigit);
        final int bound = 10;
        if (this.rep.length() > 0) {
            lastDigit = String.valueOf(this.rep.charAt(this.rep.length() - 1));
            this.rep = this.rep.substring(0, this.rep.length() - 1);
        }
        if (Integer.parseInt(lastDigit) < bound) {
            returnValue = Integer.parseInt(lastDigit);
        }
        return returnValue;
    }

    /**
     * Checks if the NaturalNumber is 0 (represented by empty string).
     */
    @Override
    public final boolean isZero() {
        return this.rep.length() == 0;
    }

    /**
     * Sets the receiver to the blank representation of zero if it is a string
     * containing zero.
     */
    public void clearZero() {
        if (this.rep.equals("0")) {
            this.rep = "";
        }
    }

}