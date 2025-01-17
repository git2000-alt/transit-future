import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class customerDashboard extends JPanel implements ActionListener {
    public static void main(String[] args) { // Constructor Method
        JFrame frame = new JFrame("TransitFuture");
        frame.setSize(300, 400); // Fixed size setting
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane = new customerDashboard();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    JLabel promptText;

    public customerDashboard() { // Primary method
        promptText = new JLabel("Customer Dashboard");
        promptText.setFont(new Font("Arial", Font.BOLD, 21));
        JLabel map = new JLabel(createImageIcon("mapImage.png"));

        JLabel labelText = new JLabel("Transit Map");
        labelText.setFont(new Font("Arial", Font.BOLD, 21));
        JLabel subtitle = new JLabel("Including Brampton Transit and ZÜM routes");
        JLabel bodyText = new JLabel("See live map of buses in Brampton.");
        JLabel bodyText2 = new JLabel("Customize visible routes & buses.");
        JButton viewButton = new JButton("View");
        viewButton.setOpaque(true);
        viewButton.setBorderPainted(false);
        viewButton.setPreferredSize(new Dimension(95, 35));
        viewButton.setBackground(Color.pink);
        viewButton.setForeground(Color.black);

        add(promptText);
        add(map);
        add(labelText);
        add(subtitle);
        add(bodyText);
        add(bodyText2);
        add(viewButton);
    }

    protected static ImageIcon createImageIcon (String path)
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    } 
}

// Harsimran S. (754047)