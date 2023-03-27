import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.AttributeSet;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.*;


public class Register extends JFrame {
    private JPanel registerPanel;
    private JLabel nameLabel;
    private JTextField nameField;
    private JLabel regNoLabel;
    private JTextField regNoField;

    private JLabel phoneNoLabel;
    private JTextField phoneNoField;
    private JLabel passwrdLabel;
    private JPasswordField passwrdField;
    private JButton registerButton;

    public Register(){
        setTitle("Register");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));

        nameLabel = new JLabel("Enter Name");
        nameLabel.setIcon(new ImageIcon("Icons/user.png"));
        nameField = new JTextField(20);
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        registerPanel.add(nameLabel);
        registerPanel.add(nameField);

        regNoLabel = new JLabel("Enter Registration No:");
        regNoLabel.setIcon(new ImageIcon("Icons/regNo.png"));
        regNoField = new JTextField(20);
        JPanel regNoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        registerPanel.add(regNoLabel);
        registerPanel.add(regNoField);

        phoneNoLabel = new JLabel("Phone Number:");
        phoneNoLabel.setIcon(new ImageIcon("Icons/phone.png"));
        phoneNoField = new JTextField(10);
        JPanel phoneNoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        registerPanel.add(phoneNoLabel);
        registerPanel.add(phoneNoField);

        passwrdLabel = new JLabel("Password");
        passwrdLabel.setIcon(new ImageIcon("Icons/key.png"));
        passwrdField = new JPasswordField(20);
        JPanel passwrdPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        registerPanel.add(passwrdLabel);
        registerPanel.add(passwrdField);

        registerButton = new JButton("Register");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        registerPanel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String regNo = regNoField.getText();
                String phoneNo = phoneNoField.getText();
                char[] passwrdChars = passwrdField.getPassword();
                String passwrd = new String(passwrdChars);

                if (registerUser(name, regNo, phoneNo, passwrd)){
                    JOptionPane.showMessageDialog(Register.this, "Registration successful");
                    dispose();
                }else {
                    JOptionPane.showMessageDialog(Register.this, "Failed to register person");
                }
            }
        });






        add(registerPanel);
        pack();
        setVisible(true);



    }
private boolean registerUser(String name, String regNo, String phoneNo, String passwrd){
        boolean isSuccess = false;
        try {
            Connection conn = new JDBC().connection();

            PreparedStatement stmt = conn.prepareStatement("INSERT INTO student (name, regNo, phoneNo, password) VALUES (?, ?, ?, ?)");
            stmt.setString(1, name);
            stmt.setString(2, regNo);
            stmt.setString(3, phoneNo);
            stmt.setString(4, passwrd);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1){
                isSuccess = true;
            }

            stmt.close();
            conn.close();
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return isSuccess;
}
}
