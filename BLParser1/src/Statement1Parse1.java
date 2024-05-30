import components.queue.Queue;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.statement.Statement1;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary methods {@code parse} and
 * {@code parseBlock} for {@code Statement}.
 *
 * @author Charles Sirichoktanasup, Dylan Jian
 *
 */
public final class Statement1Parse1 extends Statement1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Converts {@code c} into the corresponding {@code Condition}.
     *
     * @param c
     *            the condition to convert
     * @return the {@code Condition} corresponding to {@code c}
     * @requires [c is a condition string]
     * @ensures parseCondition = [Condition corresponding to c]
     */
    private static Condition parseCondition(String c) {
        assert c != null : "Violation of: c is not null";
        assert Tokenizer
                .isCondition(c) : "Violation of: c is a condition string";
        return Condition.valueOf(c.replace('-', '_').toUpperCase());
    }

    /**
     * Parses an IF or IF_ELSE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"IF"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an if string is a proper prefix of #tokens] then
     *  s = [IF or IF_ELSE Statement corresponding to if string at start of #tokens]  and
     *  #tokens = [if string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseIf(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("IF") : ""
                + "Violation of: <\"IF\"> is proper prefix of tokens";

        // dequeue 'IF' and assert if correct
        String ifStr = tokens.dequeue();
        Reporter.assertElseFatalError(ifStr.equals("IF"),
                "Error: expected IF instead of " + ifStr);

        // dequeue condition and assert that it is a valid one
        String condStr = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isCondition(condStr),
                "Error: " + condStr + " is not a condition");

        // parse dequeued condition
        Condition c = parseCondition(condStr);

        // dequeue 'THEN' and assert if correct
        String thenStr = tokens.dequeue();
        Reporter.assertElseFatalError(thenStr.equals("THEN"),
                "Error: expected THEN instead of " + thenStr);

        // make new statement and parse if body into it
        Statement ifBlock = s.newInstance();
        ifBlock.parseBlock(tokens);

        // 1st part runs if the front token is 'ELSE'
        if (tokens.front().equals("ELSE")) {
            // dequeue 'ELSE'
            String elseStr = tokens.dequeue();
            Reporter.assertElseFatalError(elseStr.equals("ELSE"),
                    "Error: expected ELSE instead of " + elseStr);

            // make new statement and parse else body into it
            Statement elseBlock = s.newInstance();
            elseBlock.parseBlock(tokens);

            // assemble statement with condition, if body, and else body
            s.assembleIfElse(c, ifBlock, elseBlock);
        } else {
            // if there is no 'ELSE', assemble statement with just condition and if
            s.assembleIf(c, ifBlock);
        }
        // dequeue 'END' in 'END IF' and assert if correct
        String endStr = tokens.dequeue();
        Reporter.assertElseFatalError(endStr.equals("END"),
                "Error: expected END instead of " + endStr);

        // dequeue 'IF' in 'END IF' and assert if correct
        String endIfStr = tokens.dequeue();
        Reporter.assertElseFatalError(endIfStr.equals(ifStr),
                "Error: expected " + ifStr + " instead of " + endIfStr);
    }

    /**
     * Parses a WHILE statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires <pre>
     * [<"WHILE"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [a while string is a proper prefix of #tokens] then
     *  s = [WHILE Statement corresponding to while string at start of #tokens]  and
     *  #tokens = [while string at start of #tokens] * tokens
     * else
     *  [reports an appropriate error message to the console and terminates client]
     * </pre>
     */
    private static void parseWhile(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0 && tokens.front().equals("WHILE") : ""
                + "Violation of: <\"WHILE\"> is proper prefix of tokens";

        // dequeue 'WHILE' and assert if correct
        String whileStr = tokens.dequeue();
        Reporter.assertElseFatalError(whileStr.equals("WHILE"),
                "Error: expected WHILE instead of " + whileStr);

        // dequeue condition, assert if it is a valid one
        String condStr = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isCondition(condStr),
                "Error: " + condStr + " is not a condition");

        // parse dequeued condition
        Condition c = parseCondition(condStr);

        // dequeue 'DO' and assert if correct
        String doStr = tokens.dequeue();
        Reporter.assertElseFatalError(doStr.equals("DO"),
                "Error: expected DO instead of " + doStr);

        // declare statement and parse while body into it
        Statement whileBlock = s.newInstance();
        whileBlock.parseBlock(tokens);

        // dequeue 'END' in 'END IF' and assert if correct
        String endStr = tokens.dequeue();
        Reporter.assertElseFatalError(endStr.equals("END"),
                "Error: expected END instead of " + endStr);

        // dequeue 'IF' in 'END IF' and assert if correct
        String endIfStr = tokens.dequeue();
        Reporter.assertElseFatalError(endIfStr.equals("WHILE"),
                "Error: expected WHILE instead of " + endIfStr);
        s.assembleWhile(c, whileBlock);
    }

    /**
     * Parses a CALL statement from {@code tokens} into {@code s}.
     *
     * @param tokens
     *            the input tokens
     * @param s
     *            the parsed statement
     * @replaces s
     * @updates tokens
     * @requires [identifier string is a proper prefix of tokens]
     * @ensures <pre>
     * s =
     *   [CALL Statement corresponding to identifier string at start of #tokens]  and
     *  #tokens = [identifier string at start of #tokens] * tokens
     * </pre>
     */
    private static void parseCall(Queue<String> tokens, Statement s) {
        assert tokens != null : "Violation of: tokens is not null";
        assert s != null : "Violation of: s is not null";
        assert tokens.length() > 0
                && Tokenizer.isIdentifier(tokens.front()) : ""
                        + "Violation of: identifier string is proper prefix of tokens";

        // dequeue first string from tokens and assemble it as a call
        String call = tokens.dequeue();
        s.assembleCall(call);
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Statement1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        /*
         * Parse first token as an if or while if it is 'IF' or 'WHILE'
         * respectively. Otherwise, assert that the token isn't a condition and
         * is a call. If this is the case, parse token as a call.
         */
        if (tokens.front().equals("IF")) {
            parseIf(tokens, this);
        } else if (tokens.front().equals("WHILE")) {
            parseWhile(tokens, this);
        } else {
            Reporter.assertElseFatalError(
                    !Tokenizer.isCondition(tokens.front()),
                    "Error: " + tokens.front() + " is a condition");
            Reporter.assertElseFatalError(
                    Tokenizer.isIdentifier(tokens.front()),
                    "Error: " + tokens.front() + " is not a call");
            parseCall(tokens, this);
        }
    }

    @Override
    public void parseBlock(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        /*
         * parse entries in tokens to an empty statement until block is
         * completed, which is determined by the keyword 'END' or 'ELSE', or by
         * the end of input string. Add each token to the last position of the
         * block at its respective entry time.
         */
        Statement s = this.newInstance();
        while (!tokens.front().equals("END") && !tokens.front().equals("ELSE")
                && !tokens.front().equals("### END OF INPUT ###")) {
            s.parse(tokens);
            this.addToBlock(this.lengthOfBlock(), s);
        }
    }

    /*
     * Main test method -------------------------------------------------------
     */

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
         * Get input file name
         */
        out.print("Enter valid BL statement(s) file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Statement s = new Statement1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        s.parseBlock(tokens); // replace with parseBlock to test other method
        /*
         * Pretty print the statement(s)
         */
        out.println("*** Pretty print of parsed statement(s) ***");
        s.prettyPrint(out, 0);

        in.close();
        out.close();
    }

}
