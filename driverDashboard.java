import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class driverDashboard extends JPanel implements ActionListener {
    public static void main(String[] args) { // Constructor Method
        JFrame frame = new JFrame("TransitFuture");
        frame.setSize(300, 500); // Fixed size setting
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new driverDashboard();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    JLabel promptText;

    public driverDashboard() { // Primary method
        promptText = new JLabel("Driver Dashboard"); // Title Text
        promptText.setFont(new Font("Arial", Font.BOLD, 21));
        JLabel map = new JLabel(createImageIcon("mapImage.png")); // Main map picture

        JLabel labelText = new JLabel("Transit Map");
        labelText.setFont(new Font("Arial", Font.BOLD, 21));
        JLabel subtitle = new JLabel("Including Brampton Transit and ZÜM routes"); // Body Text
        JLabel bodyText = new JLabel("See live map of buses in Brampton.");
        JButton viewButton = new JButton("View"); // View Button
        viewButton.setOpaque(true);
        viewButton.setBorderPainted(false);
        viewButton.setPreferredSize(new Dimension(95, 35));
        viewButton.setBackground(Color.pink);
        viewButton.setForeground(Color.black);
        JButton alertButton = new JButton("See Alerts (1)"); // See alerts button
        alertButton.setOpaque(true);
        alertButton.setBorderPainted(false);
        alertButton.setPreferredSize(new Dimension(225, 35));
        alertButton.setBackground(Color.red);
        alertButton.setForeground(Color.white);
        JButton notifButton = new JButton("See Notifications (1)"); // See notifications button
        notifButton.setOpaque(true);
        notifButton.setBorderPainted(false);
        notifButton.addActionListener(this);
        notifButton.setActionCommand("notif");
        notifButton.setPreferredSize(new Dimension(225, 35));
        notifButton.setBackground(Color.white);

        add(promptText);
        add(alertButton);
        add(notifButton);
        add(map);
        add(labelText);
        add(subtitle);
        add(bodyText);
        add(viewButton);
    }

    protected static ImageIcon createImageIcon (String path) // Create map image
    {
        java.net.URL imgURL = customerDashboard.class.getResource(path);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("notif")) {
            feedbackPage.main(null);
        }
    } 
}

// Harsimran S. (754047)