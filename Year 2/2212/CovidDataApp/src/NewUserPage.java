/**
 * @authors Matteo Tanzi <mtanzi@uwo.ca>, Matthew Liu <mliu493@uwo.ca>, Sahebjot Bal <sbal7@uwo.ca>
 * 
 * This class is the creating a new user window 
 */

/**
 * imported libraries used
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;


public class NewUserPage implements ActionListener {
//class body
	
	/**
	 * Initializing variables
	 */
    private static JLabel newUserLabel;
    private static JTextField newUserTxt;
    private static JLabel newPassLabel;
    private static JPasswordField newPassTxt;
    private static JButton okButton;
    private static JButton resetButton;
    private static JLabel msg;
    HashMap<String, String> loginInfo = new HashMap<String, String>();
    JFrame frame = new JFrame("Create New User Covid-19 App");
    JPanel panel = new JPanel();

    /**
     * Creates a new user page UI
     * @param loginInfoNew new account information
     */
    NewUserPage(HashMap<String, String> loginInfoNew) {

        loginInfo = loginInfoNew;

        // Set window size
        frame.setSize(450, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        panel.setLayout(null);

        //  New user-name Label
        newUserLabel = new JLabel("New Username:");
        newUserLabel.setBounds(10, 20, 100, 25);
        panel.add(newUserLabel);

        // New user-name text field
        newUserTxt = new JTextField(20);
        newUserTxt.setBounds(110, 20, 300, 25);
        panel.add(newUserTxt);

        // New password Label
        newPassLabel = new JLabel("New Password:");
        newPassLabel.setBounds(10, 50, 100, 25);
        panel.add(newPassLabel);

        // new password text field (with privacy dots)
        newPassTxt = new JPasswordField(20);
        newPassTxt.setBounds(110, 50, 300, 25);
        panel.add(newPassTxt);

        // Create new user button
        okButton = new JButton("Submit");
        okButton.setBounds(110, 80, 130, 25);
        okButton.addActionListener(this);
        panel.add(okButton);

        // Reset button
        resetButton = new JButton("Reset");
        resetButton.setBounds(278, 80, 130, 25);
        resetButton.addActionListener(this);
        panel.add(resetButton);

        // Display message
        msg = new JLabel("");
        msg.setBounds(10, 110, 300, 25);
        panel.add(msg);

        frame.setVisible(true);
    }

    /**
     * This method is used to call what happens when a user presses a button on the GUI
     */
    public void actionPerformed(ActionEvent act) {
        // Action performed if reset button is pressed
        if (act.getSource() == resetButton) {
            resetButtonAction();
        }
        // Action performed if create button is pressed
        if (act.getSource() == okButton) {
            okButtonAction();
        }
    }
    
    /**
     * Clears all fields when reset button pressed
     */
    private void resetButtonAction() {
        newUserTxt.setText(""); // empty text field
        newPassTxt.setText(""); // empty text field
    }

    /**
     * checks if user account information is valid and creates a new user then returns to the main login UI
     */
    private void okButtonAction() {
        // Get user-name and password
        String user = newUserTxt.getText();
        String pass = String.valueOf(newPassTxt.getPassword());

        // If user-name or password field is empty
        if (user.isEmpty() || pass.isEmpty()) {
            msg.setForeground(Color.red); // Color of message (Red)
            msg.setText("Fill All Fields"); // Display message
        }
        // Check if user-name already exists in database
        else if (loginInfo.containsKey(user)) {
            msg.setForeground(Color.red);
            msg.setText("Username Already Exists"); // User-name exists display message
        }
        // Add new user to the database and close create new user page
        else {
            loginInfo.put(user, pass);
            try {
                //Create a text file in "users"
                File file = new File("users//", user + ".txt");
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(pass);
                fileWriter.close();
                System.out.println("The User " + user + " has been entered");
            } catch (IOException e) {
                System.out.println("Cannot Write to File");
            }

            msg.setForeground(Color.green);
            msg.setText("User Created!");
            Login newLogin = new Login(loginInfo);
            frame.dispose();
        }
    }
}
