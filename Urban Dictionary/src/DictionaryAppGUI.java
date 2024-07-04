import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DictionaryAppGUI extends JFrame {
    protected static final Object anchor = null;
    private DictionaryBSTApp kbApp = new DictionaryBSTApp();
    private JComboBox<String> actionComboBox;
    private JTextField fileNameTextField;

    
    /** A graphical user interface programme that uses a binary search tree to manage knowledge bases.
     * Constructs a new GenericsKbBSTAppGUI.
     */

    public DictionaryAppGUI() {

        setTitle("Knowledge Base Application");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel instructionLabel = new JLabel("Choose an action from the menu:");
        actionComboBox = new JComboBox<>(new String[]{"Load a knowledge base from a file",
                "Add a new statement to the knowledge base",
                "Search for an item in the knowledge base by term",
                "Search for an item in the knowledge base by term and sentence",
                "Quit"});
        actionComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUserInput(anchor);
            }
        });
        fileNameTextField = new JTextField(20);
        fileNameTextField.setVisible(false);
        inputPanel.add(instructionLabel);
        inputPanel.add(actionComboBox);
        inputPanel.add(fileNameTextField);

        add(inputPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    /**
     * Responds to user input in accordance with the combo box's selected action.
     *
     * 
     * @param anchor A placeholder parameter
     */
    
    private void handleUserInput(Object anchor) {
        String selectedAction = (String) actionComboBox.getSelectedItem();
        switch (selectedAction) {
            case "Load a knowledge base from a file":
                String fileName = JOptionPane.showInputDialog(this, "Enter file name:");
                kbApp.knowledgeBase(fileName);
                JOptionPane.showMessageDialog(this, "Knowledge base loaded successfully.");
                break;
            case "Add a new statement to the knowledge base":
                String term = JOptionPane.showInputDialog(this, "Enter the term:");
                String statement = JOptionPane.showInputDialog(this, "Enter the statement:");
                double confidenceScore = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter the confidence score:"));
                kbApp.addStatement(term, statement, confidenceScore);
                JOptionPane.showMessageDialog(this, "Statement for term " + term + " has been updated.");
                break;
            case "Search for an item in the knowledge base by term":
                String searchTerm = JOptionPane.showInputDialog(this, "Enter the term to search:");
                String result = kbApp.searchByTerm(searchTerm);
                JOptionPane.showMessageDialog(this, result);
                break;
            case "Search for an item in the knowledge base by term and sentence":
                String searchTermi = JOptionPane.showInputDialog(this, "Enter the term:");
                String searchStatement = JOptionPane.showInputDialog(this, "Enter the statement to search for:");
                String results = kbApp.searchByTermAndSentence(searchTermi, searchStatement);
                JOptionPane.showMessageDialog(this, results);
                break;
            case "Quit":
                System.exit(0);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid choice. Please try again.");
        }
    }
     /**
     * Main method to run the GUI application.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DictionaryAppGUI();
            }
        });
    }
}
