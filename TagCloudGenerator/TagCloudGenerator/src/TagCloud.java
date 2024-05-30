import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

/**
 * CSE 2231 Project 9: TagCloud generator
 *
 * This program takes a .txt file as an input and then outputs a beautifully
 * formatted tag cloud (displayed with HTML and CSS) with the top words that the
 * file contains; words' sizes are determined by their frequency.
 *
 * @author Dylan Jian and Charles Sirichoktanasup
 *
 */
public final class TagCloud {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private TagCloud() {
    }

    /**
     * Lowest frequency of tag cloud elements. Made for ease of use when
     * calculating font size.
     */
    private static int minFrequency = Integer.MAX_VALUE;

    /**
     * Highest frequency of tag cloud elements. Made for ease of use when
     * calculating font size.
     */
    private static int maxFrequency = Integer.MIN_VALUE;

    /**
     *
     * From CSE 2221 -> Project: Glossary
     *
     * This method takes in a string as a parameter and then returns either the
     * first found word or the separator.
     *
     * @param definition
     *            a definition from the glossary, the string that this method
     *            works on
     * @param position
     *            The position that this method starts checking from
     * @param separators
     *            A Set filled with characters that are the separators
     * @return String that is either the separator or a word
     * @requires text is not null, position is equal or greater than 0, position
     *           is less than definition.length
     */

    private static String nextWordOrSeparator(String definition, int position,
            Set<Character> separators) {
        assert definition != null : "Violation of: definition is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < definition
                .length() : "Violation of: position < |definition|";

        /*
         * StringBuilder is a more efficient way of making a string, since
         * Strings are immutable
         */
        StringBuilder word = new StringBuilder();
        char character = definition.charAt(position);
        int i = position;

        /*
         * if character is in separators, then add character to word while
         * separators contains definition.charAt(i)
         */
        if (separators.contains(character)) {
            while (i < definition.length()
                    && separators.contains(definition.charAt(i))) {
                character = definition.charAt(i);
                word.append(character);
                i++;
            }
        } else {
            /*
             * if character is not in separators, then add character to word
             * while separators does not contain definition.charAt(i)
             */
            while (i < definition.length()
                    && !separators.contains(definition.charAt(i))) {
                character = definition.charAt(i);
                word.append(character);
                i++;
            }
        }
        //return the String version of this StringBuilder
        //toLowerCase() is added in order to provide case insensitivity
        return word.toString().toLowerCase();
    }

    /**
     *
     * This method reads the file that the user has specified to use, and then
     * throughout reading the file, continuously calls the nextWordOrSeparator
     * method in order to determine which words can be added to wordsAndCounts
     * and words.
     *
     * Taken from CSE 2231 Project 1: WordCounter -> WordCounter.java
     *
     * @param wordsAndCounts
     *            Map that has keys of unique words and values that are the
     *            words' corresponding frequencies. Not meant to be sorted
     * @param words
     *            Queue of unique words, meant to be sorted later in the main
     *            method after this method finishes execution
     * @param separators
     *            list of appropriate word separators
     * @param in
     *            input file
     * @requires in.is_open
     */
    private static void wordsAndCountsGetter(
            Map<String, Integer> wordsAndCounts, Queue<String> words,
            Set<Character> separators, BufferedReader in) throws IOException {
        // Try catch in case the buffered reader can't be read from
        try {
            String line = in.readLine();
            while (line != null) {
                String word;
                /*
                 * This for loop goes through the text file and continuously
                 * calls the nextWordOrSeparator method in order to determine
                 * which words can be added to wordsAndCounts and words. It also
                 * keeps track of a word's frequency.
                 */
                for (int i = 0; i < line.length(); i += word.length()) {

                    word = nextWordOrSeparator(line, i, separators);
                    char startValue = word.charAt(0);
                    if (!wordsAndCounts.containsKey(word)
                            && !separators.contains(startValue)) {
                        Entry<String, Integer> e = new SimpleEntry<String, Integer>(
                                word, 1);
                        wordsAndCounts.put(e.getKey(), e.getValue());
                        words.add(word);
                    } else if (wordsAndCounts.containsKey(word)) {
                        int value = wordsAndCounts.get(word);
                        wordsAndCounts.replace(word, value + 1);
                    }
                }
                line = in.readLine();
            }
        } catch (IOException e) {
            System.err.print("Error reading input");
        }

    }

    /**
     *
     * This method creates and returns a list of separators. Added are
     * appropriate separators.
     *
     * @return separators Character Set of separator list
     */
    private static Set<Character> separatorGetter() {
        Set<Character> separators = new HashSet<>();
        separators.add('.');
        separators.add(',');
        separators.add(' ');
        separators.add(':');
        separators.add('!');
        separators.add('?');
        separators.add('-');
        separators.add(';');
        separators.add('(');
        separators.add(')');
        separators.add('[');
        separators.add(']');
        separators.add('/');
        separators.add('\'');
        separators.add('*');
        separators.add('`');
        separators.add('"');
        separators.add('\\');

        return separators;
    }

    /**
     * Transfers MapPairs in {@code wordsAndCounts} into
     * {@code tagCloudElements}, which allows for sorting.
     * {@code tagCloutElements} is then sorted descendingly based on how
     * frequent a word shows up.
     *
     * @param tagCloudElements
     *            {@code SortingMachine} that contains words and their
     *            frequencies
     * @param wordsAndCounts
     *            a Map with words and their frequencies
     */
    private static void descendingSort(
            Queue<java.util.Map.Entry<String, Integer>> tagCloudElements,
            Map<String, Integer> wordsAndCounts) {
        // Remove items from map until it is empty
        while (wordsAndCounts.size() > 0) {
            /*
             * Iterate through each entry in map to get the max value and its
             * respective key
             */
            String maxKey = "";
            int maxValue = -1;
            for (Map.Entry<String, Integer> e : wordsAndCounts.entrySet()) {
                if (e.getValue() > maxValue) {
                    maxKey = e.getKey();
                    maxValue = e.getValue();
                }
            }
            // Add entry of the highest count to queue and remove it from map
            Map.Entry<String, Integer> max = new SimpleEntry<String, Integer>(
                    maxKey, maxValue);
            tagCloudElements.add(max);
            wordsAndCounts.entrySet().remove(max);
        }
    }

    /**
     * This alphabetically sorts the {@code tagCloudElements}.
     *
     * @param tagCloudElements
     *            {@code SortingMachine} that contains words and their
     *            frequencies
     * @param size
     *            size of tagCloud
     */
    private static void alphabeticalSort(
            Queue<Entry<String, Integer>> tagCloudElements, int size) {
        Queue<Entry<String, Integer>> copy = new LinkedList<>();
        int i = 0;
        // Add the i most frequent words to the copy queue.
        while (i < size) {
            copy.add(tagCloudElements.remove());
            i++;
        }
        tagCloudElements.clear();
        while (copy.size() > 0) {
            /*
             * Loop through each entry to get the one that comes first
             * alphabetically
             */
            Entry<String, Integer> firstAlphabetically = new SimpleEntry<String, Integer>(
                    "ZZZ", -1);
            for (Entry<String, Integer> e : copy) {
                if (e.getKey().toLowerCase().compareTo(
                        firstAlphabetically.getKey().toLowerCase()) < 0) {
                    firstAlphabetically = e;
                }
            }
            /*
             * here, we determine the highest and lowest frequencies of the
             * final tag cloud elements, in order for font size calculation to
             * be efficient.
             */
            if (firstAlphabetically.getValue() > maxFrequency) {
                maxFrequency = firstAlphabetically.getValue();
            }
            if (firstAlphabetically.getValue() < minFrequency) {
                minFrequency = firstAlphabetically.getValue();
            }
            /*
             * Add the first term alphabetically to the tagClouds queue and
             * remove it from the copy queue
             */
            tagCloudElements.add(firstAlphabetically);
            copy.remove(firstAlphabetically);

        }
        /*
         * by this point, tagCloudElements should only contain the number of
         * elements specified by the user, and should have these elements sorted
         * alphabetically.
         */
    }

    /**
     * Adds HTML code to the output file, so that when that file is opened, a
     * working web application with desired functionality is available.
     *
     * @param tagCloudElements
     *            {@code SortingMachine} that contains words and their
     *            frequencies
     * @param inputFileName
     *            name of the file to be read
     * @param outputFile
     *            location of HTML code
     * @param size
     *            size of tagCloud
     */
    private static void outputHTML(
            Queue<Entry<String, Integer>> tagCloudElements,
            String inputFileName, PrintWriter outputFile, int size) {

        /*
         * this header was inspired by the sample website, seen after
         * "View Page Source"
         */
        outputFile.println("<html>\r\n" + "<head>\r\n" + "<title>Top " + size
                + " words in " + inputFileName + "</title>\r\n"
                + "<link href=\"http://web.cse.ohio-state.edu/software/2231/"
                + "web-sw2/assignments/projects/tag-cloud-generator/data/"
                + "tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">\r\n"
                + "<link href=\"tagcloud.css\" rel=\"stylesheet\" type=\"text/css\">\r\n"
                + "</head>\r\n" + "<body>\r\n" + "<h2>Top " + size
                + " words in " + inputFileName + " </h2>\r\n" + "<hr>\r\n"
                + "<div class=\"cdiv\">\r\n" + "<p class=\"cbox\">");
        /*
         * this prints out variable content. For every word in tagCloudElements,
         * a specialized fontSize just for that word is calculated based on its
         * frequency. Viewers of the website can hover over a word to view its
         * frequency.
         */

        while (tagCloudElements.size() > 0) {
            Entry<String, Integer> entry = tagCloudElements.remove();
            outputFile.print("<span style=\"cursor:default\" class=\"f");
            outputFile.print(fontSize(entry.getValue()));
            outputFile.println("\" title=\"count: " + entry.getValue() + "\">"
                    + entry.getKey() + "</span>");
        }

        /*
         * prints footer to output file
         */
        outputFile
                .println("</p>\r\n" + "</div>\r\n" + "</body>\r\n" + "</html>");

    }

    /**
     *
     * @param frequency
     *            the frequency of a word which will be used to decide its
     *            fontSize
     * @return fontSize, the size of the word that will be on the website
     */
    private static int fontSize(int frequency) {
        final int largestFont = 48;
        final int smallestFont = 11;
        int divisor = (maxFrequency - minFrequency)
                / (largestFont - smallestFont);
        if (divisor == 0) {
            divisor++;
        }
        int size = (frequency - minFrequency) / divisor + smallestFont;
        if (size < smallestFont) {
            size = smallestFont;
        } else if (size > largestFont) {
            size = largestFont;
        }
        return size;
    }

    /**
     * Attempts to close given streams.
     *
     * @param inputFileOpened
     *            boolean representing if input file is opened
     * @param outputFileOpened
     *            boolean representing if output file is opened
     * @param fileInput
     *            stream for file input
     * @param fileOutput
     *            stream for file output
     * @param keyboardInput
     *            stream for keyboard input
     */
    public static void closeStreams(boolean inputFileOpened,
            boolean outputFileOpened, BufferedReader fileInput,
            PrintWriter fileOutput, BufferedReader keyboardInput) {
        try {
            if (inputFileOpened) {
                fileInput.close();
            }
            if (outputFileOpened) {
                fileOutput.close();
            }
            keyboardInput.close();
        } catch (IOException e) {
            System.err.println("Error closing streams.");
            return;
        }
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) {
        // Try to open keyboards stream and print error if it fails.
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            System.err.println("Error opening stream from keyboard.");
            return;
        }
        /*
         * Ask user to enter an input file, attempt to open stream to the file
         * and print error if it fails.
         */
        System.out.print("Enter input file (e.g. data/gettysburg.txt): ");
        String inputFileName = "";
        BufferedReader inputFile = null;
        boolean inputFileOpened = false;
        boolean outputFileOpened = false;
        try {
            inputFileName = in.readLine();
            inputFile = new BufferedReader(new FileReader(inputFileName));
            inputFileOpened = true;
        } catch (IOException e) {
            System.err.println("Error opening stream from input.");
            try {
                in.close();
            } catch (IOException e2) {
                System.err.println("Error closing streams.");
                return;
            }
            return;
        }
        /*
         * Asks user for output file name and attempt to open stream to output
         * file, print error if it fails.
         */
        System.out.print(
                "Enter an output file name (e.g. data/gettysburg.html): ");
        String outputFileName;
        PrintWriter outputFile = null;
        try {
            outputFileName = in.readLine();
            outputFile = new PrintWriter(
                    new BufferedWriter(new FileWriter(outputFileName)));
            outputFileOpened = true;
        } catch (IOException e) {
            System.err.println("Error opening stream to output.");
            try {
                if (inputFileOpened) {
                    inputFile.close();
                }
                in.close();
            } catch (IOException e2) {
                System.err.println("Error closing streams.");
                return;
            }
            return;
        }
        /*
         * Ask user to enter value for size of tag cloud, attempt to parse as an
         * integer and print error if it fails.
         */
        System.out.print("Enter the desired size of the tag cloud: ");
        String sizeString;
        int size = 0;
        /*
         * Gets input from user and checks if it input can be read and if it is
         * an integer.
         */
        try {
            sizeString = in.readLine();
            size = Integer.parseInt(sizeString);
        } catch (NumberFormatException n) {
            System.err.println("Error parsing integer.");
            closeStreams(inputFileOpened, outputFileOpened, inputFile,
                    outputFile, in);
            return;
        } catch (IOException e) {
            System.err.println("Error reading input.");
            closeStreams(inputFileOpened, outputFileOpened, inputFile,
                    outputFile, in);
            return;
        }
        /*
         * wordsAndCounts created as a Map that is meant to hold words of the
         * text files along with their frequencies
         */
        Map<String, Integer> wordsAndCounts = new HashMap<>();
        Queue<String> words = new LinkedList<>();
        /*
         * get a Set of separators
         */
        Set<Character> separators = separatorGetter();
        /*
         * Attempts to update the Map with words and their counts
         */
        try {
            wordsAndCountsGetter(wordsAndCounts, words, separators, inputFile);
        } catch (IOException e) {
            System.err.println("Error adding to map from reading input file.");
            closeStreams(inputFileOpened, outputFileOpened, inputFile,
                    outputFile, in);
            return;
        }
        /*
         * creating tagCloudElements, which is the SortingMachine that will hold
         * the contents
         */
        Queue<Entry<String, Integer>> tagCloudElements;
        tagCloudElements = new LinkedList<>();
        /*
         * sorts tagCloudElements by the frequency of each word
         */
        descendingSort(tagCloudElements, wordsAndCounts);
        /*
         * alphabetically sorts tagCloudElements and ensures its contents are
         * only of the user-specified size
         */
        alphabeticalSort(tagCloudElements, size);
        /*
         * calls a method that adds HTML code to the user-specified output file
         * name and location if output file is opened.
         */
        if (outputFileOpened) {
            outputHTML(tagCloudElements, inputFileName, outputFile, size);
        }
        /*
         * Attempt to close input and output streams if opened, print error if
         * closing streams fails.
         */
        closeStreams(inputFileOpened, outputFileOpened, inputFile, outputFile,
                in);
    }

}
