package AdminAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class AddAccountPanel extends JPanel
{

    private final AccountModel accountModel = new AccountModel();
    private JLabel accountsToBeCreatedLabel;
    private JLabel loadLabel;
    private JButton welcomeButton;
    private JTextField noOfAccountsField;

    // inputPanel components
    //private Timer inputTimer;
    private JPanel masterPanel;
    private JPanel inputPanel;
    private JPanel inputNorthPanel;
    private JPanel inputCenterPanel;
    private GridLayout gridLayout;
    private JLabel filler;
    private JLabel accountNumLabel;
    private JLabel fNameLabel;
    private JTextField fNameField;
    private ButtonGroup inputButtonGroup;
    private JLabel accTypeLabel;
    private JRadioButton savingsButton;
    private JRadioButton checkingButton;
    private JLabel lNameLabel;
    private JTextField lNameField;
    private JLabel extraChargeLabel;
    private JTextField extraChargeField;
    private JLabel inBalanceLabel;
    private JTextField inBalanceField;
    private JLabel accountIDLabel;
    private JTextField accountIDField;
    private JLabel passwordLabel;
    private JTextField passwordField;

    private Font headingFont = new Font("Times New Roman", Font.BOLD, 20);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 15);

    public void AddAccountPanel()
    {
        masterPanel = new JPanel();
        noOfAccountsField = new JTextField();
        noOfAccountsField.setPreferredSize(new Dimension(50, 35));
        noOfAccountsField.setFont(normalFont);
        accountsToBeCreatedLabel = new JLabel("---");
        welcomeButton = new JButton("Add Account(s)");
        welcomeButton.setFont(normalFont);

        loadLabel = new JLabel();
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
        //welcomeSouthPanel.setBackground(Color.lightGray);
        masterPanel.add(noOfAccountsField);
        masterPanel.add(welcomeButton);
        masterPanel.add(accountsToBeCreatedLabel);
        masterPanel.add(loadLabel);

        // Auto-update timer
        int delay = 1000; //milliseconds
        ActionListener taskPerformer = evt -> {
            if (Objects.equals(noOfAccountsField.getText(), "0")) {
                accountsToBeCreatedLabel.setText("You can not create 0 accounts.");
                accountsToBeCreatedLabel.setForeground(Color.RED);
            }
            else{
                accountsToBeCreatedLabel.setForeground(Color.BLACK);
                accountsToBeCreatedLabel.setText("---");
            }
        };
        Timer welcomeTimer = new Timer(delay, taskPerformer);
        welcomeTimer.start();

        welcomeButton.addActionListener(evt -> {
            String actionCommand = evt.getActionCommand();
            if (actionCommand.equals("Create Accounts")) {

                welcomeTimer.stop();
                int numOfAccounts = Integer.parseInt(noOfAccountsField.getText());
                accountModel.setNumOfAccounts(numOfAccounts);

                if (accountModel.getNumOfAccounts() == 1) {
                    accountsToBeCreatedLabel.setText(numOfAccounts + " account will be created.");
                    System.out.println(numOfAccounts + " account will be created.\n");
                } else if (numOfAccounts > 1) {
                    accountsToBeCreatedLabel.setText(numOfAccounts + " accounts will be created.");
                    System.out.println(numOfAccounts + " accounts will be created.\n");
                }

                setAccountNumLabel(numOfAccounts);
            }
        });
        // Create inputPanel
        inputPanel = new JPanel();
        inputNorthPanel = new JPanel();
        inputCenterPanel = new JPanel();

        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
        inputPanel.setPreferredSize(new Dimension(400, 200));
        gridLayout = new GridLayout(0,2, 10, 5);
        filler = new JLabel(" ");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        accountNumLabel = new JLabel("Account 1");
        inputNorthPanel.add(accountNumLabel);

        accountIDLabel = new JLabel("Account ID:");
        accountIDField = new JTextField();

        passwordLabel = new JLabel("Password:");
        passwordField = new JTextField();

        fNameLabel = new JLabel("First Name: ");
        fNameField = new JTextField();

        lNameLabel = new JLabel("Last Name: ");
        lNameField = new JTextField();

        inputButtonGroup = new ButtonGroup();
        accTypeLabel = new JLabel("Type of Account? ");
        savingsButton = new JRadioButton("Savings");
        checkingButton = new JRadioButton("Checking");

        extraChargeLabel = new JLabel("---");
        extraChargeField = new JTextField("");

        inBalanceLabel = new JLabel("Initial Balance: $");
        inBalanceField = new JTextField();

        inputCenterPanel.setSize(new Dimension(100, 350));
        inputCenterPanel.setLayout(gridLayout);

        inputCenterPanel.add(accountIDLabel);
        inputCenterPanel.add(accountIDField);

        inputCenterPanel.add(passwordLabel);
        inputCenterPanel.add(passwordField);

        inputCenterPanel.add(fNameLabel);
        inputCenterPanel.add(fNameField);

        inputCenterPanel.add(lNameLabel);
        inputCenterPanel.add(lNameField);

        inputCenterPanel.add(accTypeLabel);
        inputCenterPanel.add(filler);
        inputButtonGroup.add(savingsButton);
        inputButtonGroup.add(checkingButton);
        savingsButton.addActionListener(this::accTypeActionPerformed);
        checkingButton.addActionListener(this::accTypeActionPerformed);
        savingsButton.setSelected(false);
        checkingButton.setSelected(false);

        inputCenterPanel.add(savingsButton);
        inputCenterPanel.add(checkingButton);

        inputCenterPanel.add(extraChargeLabel);
        inputCenterPanel.add(extraChargeField);

        inputCenterPanel.add(inBalanceLabel);
        inputCenterPanel.add(inBalanceField);

        JButton inputSubmitButton = new JButton("Submit");
        inputNorthPanel.add(inputSubmitButton);
        inputSubmitButton.addActionListener(evt -> {
            String actionCommand = evt.getActionCommand();

            if (actionCommand.equals("Submit")) {
                System.out.println("Submit button selected.");

                //accountModel.printReceipt(accountArrayList, accountModel.getWithdraw(), accountModel.getDeposit());
                accountModel.incrementAccount();

                // Reset and Update Fields
                setAccountNumLabel(accountModel.getNumOfAccounts());
                fNameField.setText("");
                lNameField.setText("");
                savingsButton.setSelected(false);
                checkingButton.setSelected(false);
                extraChargeField.setText("");
                inBalanceField.setText("");
                if (accountModel.getAccountIndex()-1 >= accountModel.getNumOfAccounts()){
                    accountsToBeCreatedLabel.setText(accountModel.getNumOfAccounts() + " accounts have been created.");
                    loadLabel.setText("Open \"File -> Load\" to access account receipts.");
                }
            }
        });

        this.setBackground(Color.darkGray);
        inputPanel.add(inputNorthPanel);
        inputPanel.add(inputCenterPanel);
        this.add(inputPanel);
    }

    public void accTypeActionPerformed(ActionEvent e)
    {
        if (e.getSource() == checkingButton)
        {
            accountModel.setAccType(1);
            extraChargeLabel.setText("Enter the fee: $");
            System.out.println("Checking button selected.");
        }

        if (e.getSource() == savingsButton)
        {
            accountModel.setAccType(2);
            extraChargeLabel.setText("Enter the interest rate: ");
            System.out.println("Savings button selected.");
        }
    }

    public void setAccountNumLabel(int numOfAccounts){
        String accountIndex = String.valueOf(accountModel.getAccountIndex());

        if (accountModel.getAccountIndex() <= numOfAccounts) {
            accountNumLabel.setText("Account" + " " + accountIndex + " out of " + numOfAccounts);
            System.out.println("Account" + " " + accountIndex + " out of " + numOfAccounts);
        }
    }

}
