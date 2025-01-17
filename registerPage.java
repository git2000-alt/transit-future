import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class registerPage extends JPanel implements ActionListener { // Main Class
    public static void main(String[] args) { // Constructor Method
        JFrame frame = new JFrame("TransitFuture");
        frame.setSize(250, 400); // Fixed size setting
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new registerPage();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Global Variables
    JLabel promptText;
    JTextField usernameField;
    JTextField passwordField; 
    JTextField accountType; 
    JLabel invalidResponse;

    public registerPage() { // Primary method
        promptText = new JLabel("Register for Account");
        promptText.setFont(new Font("Arial", Font.BOLD, 20));
        invalidResponse = new JLabel("Account Status Will Display Here");
        JLabel usernameText = new JLabel("Username");
        usernameField = new JTextField(15);  // Use the global variable
        JLabel passwordText = new JLabel("Password");
        passwordField = new JTextField(15);  // Use the global variable
        JLabel accountTypeText = new JLabel("Account Type");
        accountType = new JTextField(15);  // Use the global variable

        JButton submitButton = new JButton("Submit");
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.addActionListener(this);
        submitButton.setActionCommand("submit");
        submitButton.setPreferredSize(new Dimension(95, 35));
        submitButton.setBackground(Color.pink);
        submitButton.setForeground(Color.black);

        // Adding components to the panel
        add(promptText);
        add(usernameText);
        add(usernameField);
        add(passwordText);
        add(passwordField);
        add(accountTypeText);
        add(accountType);
        add(submitButton);
        add(invalidResponse);
    }

    public void actionPerformed(ActionEvent e) { // Action Listening
        if (e.getActionCommand().equals("submit")) {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty() || accountType.getText().isEmpty()) { // if fields are empty
                invalidResponse.setForeground(Color.red);
                invalidResponse.setText("One or more text fields are empty.");
            } else if (passwordField.getText().length() <= 8) { // if password is too short
                invalidResponse.setForeground(Color.red);
                invalidResponse.setText("Password is less than 8 characters.");
            } else if (!accountType.getText().equalsIgnoreCase("Customer") && !accountType.getText().equalsIgnoreCase("Driver")) { // if account type is wrong
                invalidResponse.setForeground(Color.red);
                invalidResponse.setText("Account type is invalid.");
            } else if (usernameExists(usernameField.getText())) { // if username already exists
                invalidResponse.setForeground(Color.red);
                invalidResponse.setText("Account name already exists.");
            } else { // if everything checks out
                // if all fields are valid, save to the text file
                saveToFile(usernameField.getText(), passwordField.getText(), accountType.getText());

                invalidResponse.setForeground(Color.green);
                invalidResponse.setText("Account has been created.");
                loginPage.main(null);
            }
        }
    }

    private void saveToFile(String username, String password, String accountType) { // saves to separate file
        try {
            // Create or append to a file called "user_data.txt"
            BufferedWriter writer = new BufferedWriter(new FileWriter("accountInfo.txt", true));  // 'true' for append mode
            
            // Write the user data in a comma-separated format
            writer.write(username + "," + password + "," + accountType + "\n");
            
            // Close the writer
            writer.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
            invalidResponse.setText("Error saving the account data.");
        }
    }

    private ArrayList<ArrayList<String>> loadFromFile() {
        ArrayList<ArrayList<String>> userData = new ArrayList<>();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("accountInfo.txt")); // File reading variable
            String line;
            
            while ((line = reader.readLine()) != null) { // Read each line from file
                String[] userInfo = line.split(","); // Split line by commas
                
                ArrayList<String> user = new ArrayList<>(); // Create an ArrayList to hold the individual user data
                user.add(userInfo[0]);  // username
                user.add(userInfo[1]);  // password
                user.add(userInfo[2]);  // accountType
                
                // Add the user data to the main list
                userData.add(user);
            }
            
            // Close the reader
            reader.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
            invalidResponse.setText("Error loading the account data.");
        }
        
        return userData;
    }

    private boolean usernameExists(String username) {
        ArrayList<ArrayList<String>> userData = loadFromFile(); // Load user data from file
    
        for (ArrayList<String> user : userData) { // Iterate through user data and check if username exists
            if (user.get(0).equals(username)) {  // user.get(0) is the username
                return true;  // Username exists
            }
        }
        
        return false;  // Username does not exist
    }
}

// Harsimran S. (754047)