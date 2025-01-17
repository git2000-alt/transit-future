import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

public class feedbackPage extends JPanel implements ActionListener {
    public static void main(String[] args) { // Constructor Method
        JFrame frame = new JFrame("TransitFuture");
        frame.setSize(300, 400); // Fixed size setting
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new feedbackPage();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    JLabel promptText;

    public feedbackPage() { // Primary method
        promptText = new JLabel("Feedback");
        promptText.setFont(new Font("Arial", Font.BOLD, 21));
        JLabel map = new JLabel(createImageIcon("mapImage.png"));
        ArrayList<ArrayList<String>> userData = loadFromFile();
        add(promptText);
        add(map);
        for (ArrayList<String> user : userData) { // Iterate through user data
            JLabel issuesLabel = new JLabel("Issue: " + user.get(0));
            JLabel changesLabel = new JLabel("Change: " + user.get(1));
            JLabel ratingLabel = new JLabel("Rating: " + user.get(2));

            add(issuesLabel);
            add(changesLabel);
            add(ratingLabel);
        }
    }

    protected static ImageIcon createImageIcon (String path)
    {
        java.net.URL imgURL = feedbackPage.class.getResource(path);
        if (imgURL != null)
        {
        return new ImageIcon (imgURL);
        }
        else
        {
            System.err.println ("Couldn't find file: " + path);
            return null;
        }
    }

    private ArrayList<ArrayList<String>> loadFromFile() {
        ArrayList<ArrayList<String>> userData = new ArrayList<>();
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader("submitFeedbackInfo.txt")); // File reading variable
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
        }
        return userData;
    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}

// Harsimran S. (754047)