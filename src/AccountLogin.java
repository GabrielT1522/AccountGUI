import DefaultFrame.DefaultFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

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
    private Font headingFont = new Font("Times New Roman", Font.BOLD, 20);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 15);
    private AccountLoginModel accountLoginModel = new AccountLoginModel();


    public AccountLogin() {
        mainPanel = new JPanel();
        pageStartPanel = new JPanel();
        centerPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        centerPanel.setLayout(new GridLayout(0,2, 15, 5));

        userLoginTypeLabel = new JLabel("Login as Admin or User?");
        userLoginTypeLabel.setForeground(Color.BLACK);
        userLoginTypeLabel.setFont(headingFont);
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

        userLoginButton.setFont(normalFont);
        adminLoginButton.setFont(normalFont);

        centerPanel.add(userLoginButton);
        centerPanel.add(adminLoginButton);

        usernameLabel = new JLabel("---");
        usernameLabel.setFont(normalFont);
        centerPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(normalFont);
        centerPanel.add(usernameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setFont(normalFont);
        centerPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(normalFont);
        centerPanel.add(passwordField);

        pageEndPanel = new JPanel();
        loginButton = new JButton("Login");
        loginButton.setFont(normalFont);
        loginButton.addActionListener(e -> {
            accountLogin();
        });

        pageEndPanel.add(loginButton, BorderLayout.PAGE_END);
        loginLabel = new JLabel("");
        loginLabel.setFont(normalFont);
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

    public void accountLogin(){
        String accountID = usernameField.getText();
        String password = passwordField.getText();

        if (accountLoginModel.getUserLoginType() == 1){
            try{
                File dir = new File("/Users/gabrieltorres/Desktop/Java/AccountGUI/SavedAccounts");
                File[] directoryListing = dir.listFiles();
                if (directoryListing != null) {
                    for (File child : directoryListing) {
                        Path accFile = Path.of(child.getAbsolutePath());
                        processLogin(accountID, password, accFile);
                    }
                } else {
                    throw new RuntimeException("No directory found.");
                }
                //Path accFile = Path.of("/Users/gabrieltorres/Desktop/Java/AccountGUI/SavedAccounts/User1234.txt");
                //processLogin(accountID, password, accFile);
            }
            catch(IOException exc){
                throw new RuntimeException();
            }
        }
        else if (accountLoginModel.getUserLoginType() == 2){
            try{
                Path adminFile = Path.of("/Users/gabrieltorres/Desktop/Java/AccountGUI/SavedAccounts/Admin.txt");
                processLogin(accountID, password, adminFile);
            }
            catch(IOException exc){
                throw new RuntimeException();
            }
        }
        else{
            System.out.println("Something went wrong.");
        }
    }

    private void processLogin(String accountID, String password, Path accFile) throws IOException {
        String accountIDLine = Files.readAllLines(accFile).get(1);
        String passwordLine = Files.readAllLines(accFile).get(2);
        if (Objects.equals(accountID, accountIDLine) && Objects.equals(password, passwordLine)){
            System.out.println("Logged in to account #"+accountID);
            loginLabel.setForeground(Color.BLACK);
            loginLabel.setText("You have successfully logged in.");
            accountLoginModel.setLoginAccess(1);
        }
        else{
            System.out.println("Invalid Username or Password");
            loginLabel.setForeground(Color.RED);
            loginLabel.setText("Invalid Username or Password.");
            accountLoginModel.setLoginAccess(2);
        }
    }
}