import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountPanel extends JPanel {
    private JPanel welcomeCenterPanel;
    private JPanel welcomeSouthPanel;
    private JLabel welcomeMessage;
    public JLabel numOfAccountsLabel;
    public JLabel accountsToBeCreatedLabel;
    private JButton welcomeSubmitButton;
    public JTextField noOfAccountsField;
    private AccountModel accountModel;

    public AccountPanel()
    {
        welcomeCenterPanel = new JPanel();
        welcomeSouthPanel = new JPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(400, 100));

        welcomeMessage = new JLabel("Hello! Welcome to my Bank Application");
        numOfAccountsLabel = new JLabel("Enter the number of accounts you want to create.");
        accountsToBeCreatedLabel = new JLabel("---");
        welcomeCenterPanel.add(welcomeMessage);
        welcomeCenterPanel.setBackground(Color.white);

        welcomeCenterPanel.add(numOfAccountsLabel);

        //centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
        welcomeCenterPanel.setPreferredSize(new Dimension(350, 50));

        noOfAccountsField = new JTextField();
        noOfAccountsField.setPreferredSize(new Dimension(100, 35));
        welcomeSubmitButton = new JButton("Submit");

        //southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.Y_AXIS));
        welcomeSouthPanel.setBackground(Color.lightGray);
        welcomeSouthPanel.add(noOfAccountsField);
        welcomeSouthPanel.add(welcomeSubmitButton);
        welcomeSouthPanel.add(accountsToBeCreatedLabel);

        welcomeSubmitButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent evt) {
            String actionCommand = evt.getActionCommand();
            //accounrPanel.processAccount();
            if (actionCommand.equals("Submit")) {
                System.out.println("Num of accounts: " + accountModel.getNumOfAccounts());
                System.out.println("Submit button selected.");
            }
        }
        });

        this.add(welcomeCenterPanel);
        this.add(welcomeSouthPanel);
    }

}
