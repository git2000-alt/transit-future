import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class userFeedbackPage extends JPanel implements ActionListener {
    public static void main(String[] args) { // Constructor Method
        JFrame frame = new JFrame("TransitFuture");
        frame.setSize(250, 400); // Fixed size setting
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new userFeedbackPage();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    JLabel promptText;
    JTextField issuesText;
    JTextField changesText;
    JTextField ratingInt;
    JLabel invalidResponse;

    public userFeedbackPage() {
        promptText = new JLabel("Submit User Feedback");
        promptText.setFont(new Font("Arial", Font.BOLD, 20));

        JLabel issues = new JLabel("Explain the issues with the route.");
        issuesText = new JTextField(15);
        JLabel changes = new JLabel("Describe the changes you'd like to see.");
        changesText = new JTextField(15);
        JLabel rating = new JLabel("Rate this route from 0 - 10.");
        ratingInt = new JTextField(2);
        invalidResponse = new JLabel("Feedback Status Will Display Here");

        JButton submitButton = new JButton("Submit");
        submitButton.setOpaque(true);
        submitButton.setBorderPainted(false);
        submitButton.addActionListener(this);
        submitButton.setActionCommand("submit");
        submitButton.setPreferredSize(new Dimension(95, 35));
        submitButton.setBackground(Color.pink);
        submitButton.setForeground(Color.black);

        add(promptText);
        add(issues);
        add(issuesText);
        add(changes);
        add(changesText);
        add(rating);
        add(ratingInt);
        add(submitButton);
        add(invalidResponse);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("submit")) {
            if (issuesText.getText().isEmpty() || changesText.getText().isEmpty() || ratingInt.getText().isEmpty()) {
                invalidResponse.setForeground(Color.red);
                invalidResponse.setText("One or more text fields are empty.");
            } else if (!ratingInt.getText().matches("^[0-9]$|^10$")) {
                invalidResponse.setForeground(Color.red);
                invalidResponse.setText("Rating given must be from 0 - 10.");
            } else {
                invalidResponse.setForeground(Color.green);
                invalidResponse.setText("Feedback has been submitted.");
                saveToFile(issuesText.getText(), changesText.getText(), ratingInt.getText());
            }
        }
    }

    private void saveToFile(String issues, String changes, String rating) { // saves to separate file
        try {
            // Create or append to a file called "user_data.txt"
            BufferedWriter writer = new BufferedWriter(new FileWriter("submitFeedbackInfo.txt", true));
            
            // Write the user data in a comma-separated format
            writer.write(issues + "," + changes + "," + rating + "\n");
            
            // Close the writer
            writer.close();
            
        } catch (IOException ex) {
            ex.printStackTrace();
            invalidResponse.setText("Error saving the feedback data.");
        }
    }
}
