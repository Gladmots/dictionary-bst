import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;
import java.io.*;

/**
 *a straightforward application that uses a generic array to manage a knowledge base.
 */

public class DictionaryArrayApp {
    private Statement[] knowledgeBase;
    private int size;

    /**
     * Builds a fresh GenericsKbArrayApp with the provided size.
     * 
     * @param capacity The initial capacity of the knowledge base.
     */

    public DictionaryArrayApp(int capacity) {
        knowledgeBase = new Statement[capacity];
        size = 0;
    }
    /**
     * gives an explanation from the knowledge base.
     */
    private static class Statement {
        String term;
        String sentence;
        double confidenceScore;

        /**
         * Creates a new Statement object.
         * 
         * @param term            The term of the statement.
         * @param sentence        The sentence of the statement.
         * @param confidenceScore The confidence score of the statement.
         */

        Statement(String term, String sentence, double confidenceScore) {
            this.term = term;
            this.sentence = sentence;
            this.confidenceScore = confidenceScore;
        }
    }
     /**
     * Main method to run the application.
     * 
     */
    public static void main(String[] args) {
        DictionaryArrayApp kbApp = new DictionaryArrayApp(100000); 
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an action from the menu:\n" +
                    "1. Load a knowledge base from a file\n" +
                    "2. Add a new statement to the knowledge base\n" +
                    "3. Search for an item in the knowledge base by term\n" +
                    "4. Search for an item in the knowledge base by term and sentence\n" +
                    "5. Quit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter file name: ");
                    String fileName = scanner.nextLine();
                    kbApp.loadKnowledgeBase(fileName);
                    System.out.println("Knowledge base loaded successfully.");
                    break;
                case 2:
                    System.out.print("Enter the term: ");
                    String term = scanner.nextLine();
                    System.out.print("Enter the statement: ");
                    String statement = scanner.nextLine();
                    System.out.print("Enter the confidence score: ");
                    double confidenceScore = scanner.nextDouble();
                    kbApp.addStatement(term, statement, confidenceScore);
                    break;
                case 3:
                    System.out.print("Enter the term to search: ");
                    String searchTerm = scanner.nextLine();
                    String result = kbApp.searchTerm(searchTerm);
                    System.out.println(result);
                    break;
                case 4:
                    System.out.print("Enter the term: ");
                    String searchTermi = scanner.nextLine();
                    System.out.print("Enter the statement to search for: ");
                    String searchStatement = scanner.nextLine();
                    String results = kbApp.search(searchTermi, searchStatement);
                    System.out.println(results);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }

    public void loadKnowledgeBase(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t");
                String term = parts[0];
                String sentence = parts[1];
                double confidenceScore = Double.parseDouble(parts[2]);
                knowledgeBase[size++] = new Statement(term, sentence, confidenceScore);
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        }
    }

    public void addStatement(String term, String sentence, double confidenceScore) {
        Statement newStatement = new Statement(term, sentence, confidenceScore);
        knowledgeBase[size++] = newStatement;
        Arrays.sort(knowledgeBase, 0, size, Comparator.comparingDouble((Statement s) -> s.confidenceScore).reversed());
    }

    public String searchTerm(String term) {
        for (int i = 0; i < size; i++) {
            if (knowledgeBase[i].term.equalsIgnoreCase(term)) {
                return "Statement found: " + knowledgeBase[i].sentence + " (Confidence score: " + knowledgeBase[i].confidenceScore + ")";
            }
        }
        return "Statement not found for term: " + term;
    }

    public String search(String term, String sentence) {    

        for (int i = 0; i < size; i++) {
            String s = removePunctuation(sentence);
            String r = removePunctuation(knowledgeBase[i].sentence);

            if (knowledgeBase[i].term.equalsIgnoreCase(term) && r.equalsIgnoreCase(s)) {
                return "Statement found: " + r + " (Confidence score: " + knowledgeBase[i].confidenceScore + ")";
            }
        }
        return "Statement not found for term: " + term + " and sentence: " + sentence;
    }
    private static String removePunctuation(String s) {
        String regex = "[\\p{Punct}\\s]"; 
        return s.replaceAll(regex, "");
    }

}
