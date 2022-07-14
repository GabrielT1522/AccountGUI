package AccountLogin;

import DefaultFrame.DefaultFrame;

import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

public class AccountLogin extends DefaultFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JLabel loginLabel;
    private JPanel pageStartPanel;
    private JPanel centerPanel;
    private JPanel pageEndPanel;
    private JPanel mainPanel;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    private JLabel userLoginTypeLabel;
    private JRadioButton adminLoginButton;
    private JRadioButton userLoginButton;
    private ButtonGroup loginButtonGroup;
    private JButton loginButton;
    private AccountLoginModel accountLoginModel = new AccountLoginModel();


    public AccountLogin() {
        mainPanel = new JPanel();
        pageStartPanel = new JPanel();
        centerPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setLayout(new GridLayout(0,2, 15, 5));

        userLoginTypeLabel = new JLabel("Login as Admin or User?");
        userLoginTypeLabel.setForeground(Color.BLACK);
        userLoginTypeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        pageStartPanel.add(userLoginTypeLabel, BorderLayout.PAGE_START);

        loginButtonGroup = new ButtonGroup();
        userLoginButton = new JRadioButton("User");
        adminLoginButton = new JRadioButton("Admin");
        loginButtonGroup.add(userLoginButton);
        loginButtonGroup.add(adminLoginButton);
        userLoginButton.addActionListener(this::userLoginActionPerformed);
        adminLoginButton.addActionListener(this::userLoginActionPerformed);
        userLoginButton.setSelected(false);
        adminLoginButton.setSelected(false);

        centerPanel.add(userLoginButton);
        centerPanel.add(adminLoginButton);

        usernameLabel = new JLabel("---");
        //usernameLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        centerPanel.add(usernameLabel);

        usernameField = new JTextField();
        //usernameField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        centerPanel.add(usernameField);

        passwordLabel = new JLabel("Password");
        //passwordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 20));
        centerPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        //passwordField.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        centerPanel.add(passwordField);

        pageEndPanel = new JPanel();
        loginButton = new JButton("Login");
        //loginButton.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        loginButton.addActionListener(e -> {
            String accountID = usernameField.getText();
            String password = passwordField.getText();

            // Add Account login button logic
        });

        pageEndPanel.add(loginButton, BorderLayout.PAGE_END);
        loginLabel = new JLabel("test");
        pageEndPanel.add(loginLabel);
        mainPanel.add(pageStartPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(pageEndPanel);
        this.add(mainPanel);
    }

    public void userLoginActionPerformed(ActionEvent e)
    {
        if (e.getSource() == userLoginButton)
        {
            accountLoginModel.setUserLoginType(1);
            userLoginTypeLabel.setText("User Login");
            usernameLabel.setText("Account Pin");
            System.out.println("User button selected.");
        }

        if (e.getSource() == adminLoginButton)
        {
            accountLoginModel.setUserLoginType(2);
            userLoginTypeLabel.setText("Admin Login");
            usernameLabel.setText("Admin Username");
            System.out.println("Admin button selected.");
        }

    }
}