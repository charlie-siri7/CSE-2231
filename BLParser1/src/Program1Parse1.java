import components.map.Map;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Charles Sirichoktanasup, Dylan Jian
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        // dequeue 'INSTRUCTION' and assert if correct
        String instruction = tokens.dequeue();
        Reporter.assertElseFatalError(instruction.equals("INSTRUCTION"),
                "Error: expected INSTRUCTION instead of " + instruction);

        // dequeue instruction name and assert if identifier
        String name = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(name),
                "Error: program name isn't identifier");

        // dequeue 'IS' and assert if correct
        String is = tokens.dequeue();
        Reporter.assertElseFatalError(is.equals("IS"),
                "Error: expected IS instead of " + is);

        // form body by parsing tokens of block
        body.parseBlock(tokens);

        // dequeue 'END' in 'END (instruction name)' and assert if correct
        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "Error: expected END instead of " + end);

        /*
         * dequeue '(instruction name)' in 'END (instruction name)' and assert
         * that it equals the instruction name.
         */
        String endName = tokens.dequeue();
        Reporter.assertElseFatalError(name.equals(endName), "Error: " + name
                + " and " + endName
                + " (beginning and ending instruction titles) aren't equal");
        // return instruction name
        return name;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // Set of primitive instructions
        Set<String> primitiveInstructions = new Set1L<>();
        primitiveInstructions.add("move");
        primitiveInstructions.add("turnleft");
        primitiveInstructions.add("turnright");
        primitiveInstructions.add("infect");
        primitiveInstructions.add("skip");

        // dequeue 'PROGRAM' and assert if correct
        String programStr = tokens.dequeue();
        Reporter.assertElseFatalError(programStr.equals("PROGRAM"),
                "Error: expected PROGRAM instead of " + programStr);

        // dequeue program name and assert if it is a valid identifier and not primitive
        String nameStr = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(nameStr),
                "Error: " + nameStr + " isn't a valid identifier.");
        Reporter.assertElseFatalError(!primitiveInstructions.contains(nameStr),
                "Error: " + nameStr
                        + " is the name of a primitive instruction");

        /*
         * set name of this to dequeued program name, assert that this.name()
         * was successfully set
         */
        this.setName(nameStr);
        Reporter.assertElseFatalError(this.name().equals(nameStr),
                "Error: " + this.name() + " doesn't equal " + nameStr);

        // dequeue 'IS' and assert if correct
        String isStr = tokens.dequeue();
        Reporter.assertElseFatalError(isStr.equals("IS"),
                "Error: expected IS instead of " + isStr);

        // make new map to temporarily store context; make string to store name
        Map<String, Statement> context = this.newContext();
        String instrName;

        /*
         * while instruction tokens remain, declare new body to store
         * instruction body, add instruction name and body to respective
         * variables using parseInstruction, assert that instruction names don't
         * repeat and aren't primitive, add instruction name and body to context
         * variable if assertions met
         */
        while (tokens.front().equals("INSTRUCTION")) {
            Statement instrBody = this.newBody();
            instrName = parseInstruction(tokens, instrBody);
            Reporter.assertElseFatalError(!context.hasKey(instrName),
                    "Error: " + instrName
                            + " appears as an instruction name more than once");
            Reporter.assertElseFatalError(
                    !primitiveInstructions.contains(instrName),
                    "Error: " + instrName
                            + " is the name of a primitive instruction");
            context.add(instrName, instrBody);
        }

        // swap initial context into program context
        this.swapContext(context);

        // dequeue 'BEGIN' and assert if correct
        String beginStr = tokens.dequeue();
        Reporter.assertElseFatalError(beginStr.equals("BEGIN"),
                "Error: expected BEGIN instead of " + beginStr);

        // parse new body statement
        Statement body = this.newBody();
        body.parseBlock(tokens);

        // dequeue 'END' in 'END (program name)' and assert if correct
        String endStr = tokens.dequeue();
        Reporter.assertElseFatalError(endStr.equals("END"),
                "Error: expected END instead of " + endStr);

        // dequeue '(program name)' in 'END (program name)' and assert if correct
        String endNameStr = tokens.dequeue();
        Reporter.assertElseFatalError(endNameStr.equals(this.name()),
                "Error: expected " + this.name() + " instead of " + endNameStr
                        + " (identifier at end of program must be the same as "
                        + "that of the beginning)");

        // assert that there are no leftover tokens besides those used in the program
        Reporter.assertElseFatalError(
                tokens.front().equals("### END OF INPUT ###"),
                "Error: terms still remain");

        // swap body statement into program body
        this.swapBody(body);
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
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
