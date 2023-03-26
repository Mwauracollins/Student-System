
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Login extends JFrame {
    private JPanel loginPanel;
    private JLabel regNoLabel;
    private JTextField regNoField;
    private JLabel passwrdLabel;
    private JPasswordField passwrdField;
    private JButton loginButton;
    private JLabel registerLabel;
    private JButton registerButton;

    public Login(){
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);

        //login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        //Add regfiels and label
        regNoLabel = new JLabel("Registration number");
        regNoLabel.setIcon(new ImageIcon("Icons/regNo.png"));
        loginPanel.add(regNoLabel);
        regNoField = new JTextField(20);
        loginPanel.add(regNoField);

        //Add passwrd fields and labels
        passwrdLabel = new JLabel("Password:");
        passwrdLabel.setIcon(new ImageIcon("Icons/key.png"));
        loginPanel.add(passwrdLabel);
        passwrdField = new JPasswordField(20);
        loginPanel.add(passwrdField);

        //login button
        loginButton = new JButton("Login");
        loginPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String regNo = regNoField.getText();
                String passwrd = passwrdField.getText();

                //check if details are correct/valid
                if (authenticateUser(regNo, passwrd)){
                    Homepage homepagewindow = new Homepage();
                    homepagewindow.setVisible(true);
                    dispose();  //close login
                } else {
                    JOptionPane.showMessageDialog(Login.this, "Invalid regNo or Password");
                }
            }
        });

        //register option
        registerLabel = new JLabel("Don't have an account?");
        loginPanel.add(registerLabel);
        registerButton = new JButton("Register");
        loginPanel.add(registerButton);
        registerButton.addActionListener(e -> {
            Register registerwindow = new Register();
            registerwindow.setVisible(true);

        });

        add(loginPanel);
        pack();
        setVisible(true);
    }

    // Method to authenticate user
    private boolean authenticateUser(String regNo, String passwrd) {
        boolean isValid = false;
        try {
            Connection conn = new JDBC().connection();

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM student WHERE regNo = ? AND password = ?");
            stmt.setString(1, regNo);
            stmt.setString(2, passwrd);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                isValid = true;
            }

            else{
                JOptionPane.showMessageDialog(null,"Invalid Credentials!");
                System.exit(0);            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return isValid;
    }

