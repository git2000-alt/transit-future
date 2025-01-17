import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class loginPage extends JPanel implements ActionListener { // Main Class
    public static void main(String[] args) { // Constructor Method
        JFrame frame = new JFrame("TransitFuture");
        frame.setSize(250, 400); // Fixed size setting
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new loginPage();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Global Variables
    JLabel promptText;
    JTextField usernameField;
    JTextField passwordField; 
    JLabel invalidResponse;
    String accountType;

    public loginPage() { // Primary method
        promptText = new JLabel("Login to Existing Account");
        promptText.setFont(new Font("Arial", Font.BOLD, 19));
        invalidResponse = new JLabel("Account Status Will Display Here");
        JLabel usernameText = new JLabel("Username");
        usernameField = new JTextField(15);  // Use the global variable
        JLabel passwordText = new JLabel("Password");
        passwordField = new JTextField(15);  // Use the global variable

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
        add(submitButton);
        add(invalidResponse);
    }

    public void actionPerformed(ActionEvent e) { // Action Listening
        if (e.getActionCommand().equals("submit")) {
            if (usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
                invalidResponse.setForeground(Color.red);
                invalidResponse.setText("One or more text fields are empty.");
            } else if (!usernameExists(usernameField.getText(), passwordField.getText())) {
                invalidResponse.setForeground(Color.red);
                invalidResponse.setText("Account name or password is incorrect.");
            } else {
                // If all fields are valid, save to the text file
                invalidResponse.setForeground(Color.green);
                invalidResponse.setText("Login has been successful.");

                if (accountType.equalsIgnoreCase("Driver")) {
                    driverDashboard.main(null);
                } else {
                    customerDashboard.main(null);
                }
            }
        }
    }

    public ArrayList<ArrayList<String>> loadFromFile() { // turn file info into arrayList
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
            
            reader.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
            invalidResponse.setText("Error loading the account data.");
        }
        
        return userData;
    }

    public boolean usernameExists(String username, String password) {
        ArrayList<ArrayList<String>> userData = loadFromFile(); // Load user data from file
    
        for (ArrayList<String> user : userData) { // Iterate through user data and check if username and password exists
            if (user.get(0).equals(username)) {  // get username
                if (user.get(1).equals(password)) { // get password
                    accountType = user.get(2); 
                    return true;
                }
                else 
                    return false;
            }
        }
        return false;  // Username does not exist
    }
}

// Harsimran S. (754047)