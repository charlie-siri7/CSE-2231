import java.util.Comparator;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Outputs an HTML page containing a table of unique words and their counts from
 * an input file.
 *
 * @author Charles Sirichoktanasup
 *
 */
public final class WordCounter {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private WordCounter() {
    }

    /**
     * Comparator for two strings when both are converted to lower case.
     */
    private static class StringLT implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s1.toLowerCase().compareTo(s2.toLowerCase());
        }
    }

    /**
     * Generates the set of characters in the given {@code String} into the
     * given {@code Set} (Code taken from Software I project).
     *
     * @param str
     *            the given {@code String}
     * @param charSet
     *            the {@code Set} to be replaced
     * @replaces charSet
     * @requires str != null and charSet != null
     * @ensures charSet = entries(str)
     */
    public static void generateElements(String str, Set<Character> charSet) {
        assert str != null : "Violation of: str is not null";
        assert charSet != null : "Violation of: charSet is not null";

        for (int i = 0; i < str.length(); i++) {
            if (!charSet.contains(str.charAt(i))) {
                charSet.add(str.charAt(i));
            }
        }

    }

    /**
     * Returns the first "word" (maximal length string of characters not in
     * {@code separators}) or "separator string" (maximal length string of
     * characters in {@code separators}) in the given {@code text} starting at
     * the given {@code position} (Code taken from Software I project).
     *
     * @param text
     *            the {@code String} from which to get the word or separator
     *            string
     * @param position
     *            the starting index
     * @param separators
     *            the {@code Set} of separator characters
     * @return the first word or separator string found in {@code text} starting
     *         at index {@code position}
     * @requires 0 <= position < |text|, text != null and separators != null
     * @ensures <pre>
     * nextWordOrSeparator =
     *   text[position, position + |nextWordOrSeparator|)  and
     * if entries(text[position, position + 1)) intersection separators = {}
     * then
     *   entries(nextWordOrSeparator) intersection separators = {}  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      intersection separators /= {})
     * else
     *   entries(nextWordOrSeparator) is subset of separators  and
     *   (position + |nextWordOrSeparator| = |text|  or
     *    entries(text[position, position + |nextWordOrSeparator| + 1))
     *      is not subset of separators)
     * </pre>
     */
    public static String nextWordOrSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";
        /*
         * Use of StringBuilder in case there is a very long word or separator
         */
        StringBuilder textStringBuilder = new StringBuilder();
        int pos = position;
        if (separators.contains(text.charAt(pos))) {
            /*
             * If the next character at the current position in the text is a
             * separator, concatenate all subsequent separators and return as
             * string.
             */
            while (pos < text.length()
                    && separators.contains(text.charAt(pos))) {
                textStringBuilder.append(text.charAt(pos));
                pos++;
            }
        } else {
            /*
             * Otherwise, the if the next character isn't a separator, all
             * subsequent non-separator terms are added to a string to be
             * returned.
             */
            while (pos < text.length()
                    && !separators.contains(text.charAt(pos))) {
                textStringBuilder.append(text.charAt(pos));
                pos++;
            }
        }
        return textStringBuilder.toString();
    }

    /**
     * Returns a string made from the lines of a .txt file using a SimpleReader
     * of the file. Lines are concatenated into the string and separated by
     * spaces.
     *
     * @param in
     *            the SimpleReader of the .txt file that the user wants to
     *            convert to a String.
     * @return a string made of lines of the file read from the separator, with
     *         each line being separated by a space in the text.
     * @requires in is open, in is already connected to a file.
     * @ensures a string of the file's contents will be returned.
     */
    public static String makeStringFromFile(SimpleReader in) {
        /*
         * Makes a string builder. Each line of the file being read from is
         * added to the string builder until the end of the file, each being
         * separated by spaces. The string builder's transformation into a
         * string is returned.
         */
        StringBuilder inputStringBuilder = new StringBuilder();
        while (!in.atEOS()) {
            inputStringBuilder.append(in.nextLine());
            inputStringBuilder.append(" ");
        }
        return inputStringBuilder.toString();
    }

    /**
     * Makes a Set<String> containing all the words contained in a given string,
     * with words being defined as a concatenation of any characters that aren't
     * in the separators set. There are no duplicate words in the set.
     *
     * @param text
     *            the string containing the words that will be added to a
     *            Set<String> to return.
     * @param separators
     *            a set of all characters that are not considered to be part of
     *            a word.
     * @return a set containing all words in the given string.
     * @requires text != null
     * @ensures All consecutive groups of characters in the given text that
     *          aren't in the given separator set will be added to the set
     *          returned without duplicates.
     */
    public static Set<String> makeWordSet(String text,
            Set<Character> separators) {
        /*
         * Loops through the length of the text using position as an index
         * tracker. nextWordOrSeparator is called to get the next instance of a
         * word or separator in the text, and this instance is added to wordSet
         * if it is a word not already present in the set (determined by the
         * first character). position is increased by the length of the term
         * returned by nextWordOrSeparator per each iteration.
         */
        Set<String> wordSet = new Set1L<>();
        int position = 0;
        while (position < text.length()) {
            String wordOrSeparator = nextWordOrSeparator(text, position,
                    separators);
            if (!separators.contains(wordOrSeparator.charAt(0))
                    && !wordSet.contains(wordOrSeparator)) {
                wordSet.add(wordOrSeparator);
            }
            position += wordOrSeparator.length();
        }
        return wordSet;
    }

    /**
     * Makes and returns a queue containing each term of the given Set<String>.
     *
     * @param wordSet
     *            a Set<String> containing the words to be added to the queue
     * @return a queue containing each term of the given Set<String>
     * @restores wordSet
     * @requires wordSet != null
     * @ensures wordQueue contains a copy of all elements in wordSet. This queue
     *          is returned.
     */
    public static Queue<String> makeWordQueue(Set<String> wordSet) {
        /*
         * Recursively loops through wordSet, removing each term to store as a
         * string. The method repeats itself until wordSet is empty, and then
         * adds the string gathered in each recursive call to the Queue and back
         * into the Set.
         */
        Queue<String> wordQueue = new Queue1L<>();
        if (wordSet.size() > 0) {
            String item = wordSet.removeAny();
            wordQueue = makeWordQueue(wordSet);
            wordQueue.enqueue(item);
            wordSet.add(item);
        }
        return wordQueue;
    }

    /**
     * Makes a map whose keys are each of the individual words in the text taken
     * as an input, and the values being how many times the word representing
     * each key appears in the text. Separators between what is and isn't a word
     * are denoted by the characters in the separators set.
     *
     * @param text
     *            The string to be converted to a map of unique words and how
     *            many times they appear within the string.
     * @param separators
     *            A set of characters representing what defines boundaries
     *            between words
     * @return A map containing unique words from the input text as keys, and
     *         the number of times each of these words appear within the text as
     *         the key's value
     * @ensures a Map<String, Integer> is returned containing each unique word
     *          in the text as a key, and the number of times each word appears
     *          in the text as the corresponding value to each key.
     */
    public static Map<String, Integer> makeWordMap(String text,
            Set<Character> separators) {
        /*
         * Similarly to makeWordSet, this method loops through the entirety of
         * the text input using an int to keep track of the index within the
         * text. It gets the next word or separator in the text using
         * nextWordOrSeparator and stores it.
         */
        Map<String, Integer> wordMap = new Map1L<>();
        int position = 0;
        while (position < text.length()) {
            String wordOrSeparator = nextWordOrSeparator(text, position,
                    separators);
            /*
             * If the text fragment isn't a separator, it is further determined
             * if the word is a key in the map already. If it is new, a new pair
             * is made with the word as the key and 1 as the value (representing
             * 1 instance of the word). If the word is already a key present in
             * the Map, the value of the key is increased by 1 to denote an
             * increase in appearances.
             */
            if (!separators.contains(wordOrSeparator.charAt(0))) {
                if (!wordMap.hasKey(wordOrSeparator)) {
                    wordMap.add(wordOrSeparator, 1);
                } else {
                    int count = wordMap.value(wordOrSeparator);
                    wordMap.replaceValue(wordOrSeparator, count + 1);
                }
            }
            position += wordOrSeparator.length();
        }
        return wordMap;
    }

    /**
     * Prints HTML code for a table of unique words and how many times they
     * appear in a passage using the SimpleWriter out. The queue contains the
     * alphabetical order of unique words and the map contains how many times
     * each word appears in the text.
     *
     * @param out
     *            SimpleWriter to the file in which to print the HTML table
     * @param queue
     *            Alphabetically sorted queue containing unique words
     * @param map
     *            Map containing each word in the queue as keys, and the number
     *            of appearances of each word in the text as values
     * @requires queue is sorted alphabetically, each item of queue is also a
     *           key in map, |queue| = |map|, out is open and writes to a valid
     *           HTML file
     * @ensures a table is printed to the HTML file connected to the
     *          SimpleWriter that contains each word sorted alphabetically in
     *          the left column and the numeric values representing each word
     *          key from the map in the right column (intended to be word count
     *          in a text passage).
     */
    public static void printTable(SimpleWriter out, Queue<String> queue,
            Map<String, Integer> map) {
        /*
         * Prints a table with borders and headers describing the table contents
         */
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>Words</th>");
        out.println("<th>Counts</th>");
        out.println("</tr>");
        /*
         * For each item in the queue, the word name is printed in the words
         * column and its value as a key in the map is printed in the count
         * column.
         */
        for (int i = 0; i < queue.length(); i++) {
            out.println("<tr>");
            out.println("<td>" + queue.front() + "</td>");
            out.println("<td>" + map.value(queue.front()) + "</td>");
            out.println("</tr>");
            queue.rotate(1);
        }
        out.println("</table>");
    }

    /**
     * Prints the HTML file using the given SimpleWriter displaying the input
     * string in the header.
     *
     * @param out
     *            SimpleWriter that writes the header to an HTML file
     * @param input
     *            String input to be used in the header describing the HTML page
     * @requires out is open and is connected to an HTML file
     * @ensures A header describing the count of the HTML file will be printed
     *          along with a proper title and break line.
     */
    public static void printHeader(SimpleWriter out, String input) {
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Word Counter</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h2>Words Counted in " + input + "</h2>");
        out.println("<hr>");
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
         * Put your main program code here
         */

        /* Ask user to enter input/output files and store as strings. */
        out.print("Enter an input file (.txt): ");
        String input = in.nextLine();
        out.print("Enter an output file (.html): ");
        String output = in.nextLine();

        /*
         * Generate a Set<Character> of separators based using the characters in
         * separatorString as separators
         */
        String separatorString = " ,./\"~!?()-\t";
        Set<Character> separators = new Set1L<>();
        generateElements(separatorString, separators);

        /*
         * Make a String from the file address given by the user using a
         * SimpleReader to the file
         */
        SimpleReader inputReader = new SimpleReader1L(input);
        String inputString = makeStringFromFile(inputReader);

        /*
         * Make a Set<String> and Map<String> using the string of the input
         * file's data, make a Queue<String> using the generated Set and sort
         * alphabetically
         */
        Set<String> wordSet = makeWordSet(inputString, separators);
        Map<String, Integer> wordMap = makeWordMap(inputString, separators);
        Queue<String> wordQueue = makeWordQueue(wordSet);
        Comparator<String> comparatorString = new StringLT();
        wordQueue.sort(comparatorString);

        /*
         * Declare writer to the output file entered by user, print title,
         * header, line, and table containing each word in the text no more than
         * once (except for upper case vs. lower case versions) and the # of
         * times each word appears in the text. Print closing HTML tags.
         */
        SimpleWriter outputWriter = new SimpleWriter1L(output);
        printHeader(outputWriter, input);
        printTable(outputWriter, wordQueue, wordMap);
        outputWriter.println("</body>");
        outputWriter.println("</html>");

        /*
         * Close input and output streams
         */
        inputReader.close();
        outputWriter.close();
        in.close();
        out.close();
    }

}
