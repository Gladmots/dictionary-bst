import java.util.Scanner;
import java.io.*;

/**
 * A basic binary search tree application for knowledge base management.
 */

public class DictionaryBSTApp {
    private Node root;
    /**
     * Represents a node in the binary search tree.
     */
    private class Node {
        String term;
        String sentence;
        double confidenceScore;
        Node left, right;

         /**
         * Constructs a new Node object.
         * 
         * @param term            The term of the statement.
         * @param sentence        The sentence of the statement.
         * @param confidenceScore The confidence score of the statement.
         */

        Node(String term, String sentence, double confidenceScore) {
            this.term = term;
            this.sentence = sentence;
            this.confidenceScore = confidenceScore;
            left = right = null;
        }
    }
 /**
     * Main method to run the application.
     * 
     */
public static void main(String[] args) {
    DictionaryBSTApp kbApp = new DictionaryBSTApp();
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
                kbApp.knowledgeBase(fileName);
                System.out.println("Knowledge base loaded successfully.");
                break;
            case 2:
                System.out.print("Enter the term: ");
                String term = scanner.nextLine();
                System.out.print("Enter the statement: ");
                String statement = scanner.nextLine();
                System.out.print("Enter the confidence score: ");
                double confidenceScore = scanner.nextDouble();
                System.out.println("Statement for term " + term + " has been updated.");
                kbApp.addStatement(term, statement, confidenceScore);
                break;
            case 3:
                System.out.print("Enter the term to search: ");
                String searchTerm = scanner.nextLine();
                String result = kbApp.searchByTerm(searchTerm);
                System.out.println(result);
                break;
            case 4:
                System.out.print("Enter the term: ");
                String searchTermi = scanner.nextLine();
                System.out.print("Enter the statement to search for: ");
                String searchStatement = scanner.nextLine();
                String results = kbApp.searchByTermAndSentence(searchTermi, searchStatement);
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

    public void knowledgeBase(String fileName) {
        File file = new File(fileName);
        try (Scanner scanner = new Scanner(file)){
           
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\t"); 
                String term = parts[0];
                String sentence = parts[1];
                double confidenceScore = Double.parseDouble(parts[2]);
                insert(term, sentence, confidenceScore);
            }
          
        } catch (FileNotFoundException e) {
             System.err.println("File not found: " + fileName);
        }
    }

    private void insert(String term, String sentence, double confidenceScore) {
        root = bstInsert(root, term, sentence, confidenceScore);
    }

    private Node bstInsert(Node root, String term, String sentence, double confidenceScore) {
 
        if (root == null) {
            return new Node(term, sentence, confidenceScore);
        }

        if (term.compareToIgnoreCase(root.term) < 0) {
            root.left = bstInsert(root.left, term, sentence, confidenceScore);
        } else if (term.compareToIgnoreCase(root.term) > 0) {
            root.right = bstInsert(root.right, term, sentence, confidenceScore);
        } else {
  
            if (confidenceScore > root.confidenceScore) {
                root.sentence = sentence;
                root.confidenceScore = confidenceScore;
            }
        }

        return root;
    }

    public void addStatement(String term, String sentence, double confidenceScore) {
    root = bstInsert(root, term, sentence, confidenceScore);
}

public String searchByTerm(String term) {
    Node result = bstTerm(root, term);
    if (result != null) {
        return "Statement found: " + result.sentence + " (Confidence score: " + result.confidenceScore + ")";
    } else {
        return "Statement not found for term: " + term;
    }
}

private Node bstTerm(Node root, String term) {
    if (root == null || root.term.equalsIgnoreCase(term)) {
        return root;
    }
    if (term.compareToIgnoreCase(root.term) < 0) {
        return bstTerm(root.left, term);
    }
    return bstTerm(root.right, term);
}


public String searchByTermAndSentence(String term, String sentence) {
    Node result = bstTermAndSentence(root, term, sentence);
    if (result != null) {
        return "Statement found: " + result.sentence + " (Confidence score: " + result.confidenceScore + ")";
    } else {
        return "Statement not found for term: " + term + " and sentence: " + sentence;
    }
}
private Node bstTermAndSentence(Node root, String term, String sentence) {   
    
    if (root == null){
        return null;
    }
    String r = removePunctuation(root.sentence);
    String s = removePunctuation(sentence);


    if (root.term.equalsIgnoreCase(term) && r.equalsIgnoreCase(s)) {
        return root;
    }
    if (term.compareToIgnoreCase(root.term) < 0) {
        return bstTermAndSentence(root.left, term, sentence);
    }
    return bstTermAndSentence(root.right, term, sentence);
}

private static String removePunctuation(String s) {
    String regex = "[\\p{Punct}\\s]"; 
    return s.replaceAll(regex, "");
}
}