package AdminAccount;

import BankAccountProgram.Account;
import DefaultFrame.DefaultFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

public class AccountPanel extends JPanel {

    private final AccountModel accountModel = new AccountModel();
    static ArrayList<Account> accountArrayList = new ArrayList<>();

    // welcomePanel components
    private Timer welcomeTimer;
    private JPanel welcomePanel;
    private JPanel welcomeCenterPanel;
    private JPanel welcomeSouthPanel;
    private JLabel welcomeMessage;
    private JLabel numOfAccountsLabel;
    private JLabel accountsToBeCreatedLabel;
    private JLabel loadLabel;
    private JButton welcomeButton;
    private JTextField noOfAccountsField;

    // inputPanel components
    //private Timer inputTimer;
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

    // alterPanel components
    private  JPanel alterBalancePanel;
    private JPanel alterBalanceNorthPanel;
    private JPanel alterBalanceCenterPanel;
    private JPanel alterBalanceSouthPanel;
    public JLabel alterBalanceLabel;
    private JRadioButton withdrawButton;
    private JRadioButton depositButton;
    private JTextField alterBalanceField;
    private ButtonGroup alterBalanceButtonGroup;

    public AccountPanel()
    {
        // Create welcomePanel
        welcomePanel = new JPanel();
        welcomeCenterPanel = new JPanel();
        welcomeSouthPanel = new JPanel();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(400, 300));

        welcomeMessage = new JLabel("Hello! Welcome to my Bank Application");
        numOfAccountsLabel = new JLabel("Enter the number of accounts you want to create.");
        accountsToBeCreatedLabel = new JLabel("---");
        welcomeCenterPanel.add(welcomeMessage);
        welcomeCenterPanel.setBackground(Color.white);

        welcomeCenterPanel.add(numOfAccountsLabel);

        //welcomeCenterPanel.setLayout(new BoxLayout(welcomeCenterPanel, BoxLayout.PAGE_AXIS));
        welcomeCenterPanel.setPreferredSize(new Dimension(350, 50));

        noOfAccountsField = new JTextField();
        noOfAccountsField.setPreferredSize(new Dimension(50, 35));
        welcomeButton = new JButton("Create Accounts");

        loadLabel = new JLabel();
        welcomeSouthPanel.setLayout(new BoxLayout(welcomeSouthPanel, BoxLayout.Y_AXIS));
        //welcomeSouthPanel.setBackground(Color.lightGray);
        welcomeSouthPanel.add(noOfAccountsField);
        welcomeSouthPanel.add(welcomeButton);
        welcomeSouthPanel.add(accountsToBeCreatedLabel);
        welcomeSouthPanel.add(loadLabel);

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
        welcomeTimer = new Timer(delay, taskPerformer);
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

                openInput();
                setAccountNumLabel(numOfAccounts);
            }
        });
        welcomePanel.add(welcomeCenterPanel);
        welcomePanel.add(welcomeSouthPanel);
        this.add(welcomePanel); // Finish welcomePanel
    }

    public void openInput()
    {
        DefaultFrame inputFrame = new DefaultFrame();
        JPanel masterPanel = new JPanel();
        inputFrame.setTitle("Input Account Information");
        inputFrame.setSize(400,500);
        inputFrame.setLocation(400,500);
        inputFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
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

        // Create alterBalancePanel
        alterBalancePanel = new JPanel();
        alterBalanceNorthPanel = new JPanel();
        alterBalanceCenterPanel = new JPanel();
        alterBalanceSouthPanel = new JPanel();

        alterBalancePanel.setLayout(new BoxLayout(alterBalancePanel, BoxLayout.PAGE_AXIS));

        alterBalanceButtonGroup = new ButtonGroup();
        alterBalanceLabel = new JLabel("Deposit or Withdraw?");
        withdrawButton = new JRadioButton("Withdraw");
        depositButton = new JRadioButton("Deposit");

        alterBalanceNorthPanel.add(alterBalanceLabel, BorderLayout.PAGE_START);
        alterBalanceNorthPanel.setPreferredSize(new Dimension(200, 50));
        alterBalanceButtonGroup.add(withdrawButton);
        alterBalanceButtonGroup.add(depositButton);
        withdrawButton.addActionListener(this::alterBalanceActionPerformed);
        depositButton.addActionListener(this::alterBalanceActionPerformed);
        withdrawButton.setSelected(false);
        depositButton.setSelected(false);

        alterBalanceCenterPanel.setLayout(new GridLayout(0,1, 15, 5));
        alterBalanceCenterPanel.add(withdrawButton);
        alterBalanceCenterPanel.add(depositButton);
        alterBalanceLabel = new JLabel("---");
        alterBalanceField = new JTextField();
        alterBalanceCenterPanel.add(alterBalanceLabel);
        alterBalanceCenterPanel.add(alterBalanceField);

        /* Auto-update timer
        int delay = 1000; //milliseconds
        ActionListener taskPerformer = evt -> {
            //if (Objects.equals(extraChargeField.getText(), "0"))
            if (extraCharge < 0.00 || extraCharge > 1.00){
                extraChargeLabel.setText("Interest must be between 0 and 1.");
                extraChargeLabel.setForeground(Color.RED);
            }
            else{
                extraChargeLabel.setForeground(Color.BLACK);
                extraChargeLabel.setText("---");
            }
        };
        inputTimer = new Timer(delay, taskPerformer);
        inputTimer.start();*/

        JButton inputSubmitButton = new JButton("Submit");
        alterBalanceSouthPanel.add(inputSubmitButton);
        inputSubmitButton.addActionListener(evt -> {
            String actionCommand = evt.getActionCommand();
            processAccount();

            if (actionCommand.equals("Submit")) {
                System.out.println("Submit button selected.");

                accountModel.printReceipt(accountArrayList, accountModel.getWithdraw(), accountModel.getDeposit());
                accountModel.incrementAccount();

                // Reset and Update Fields
                setAccountNumLabel(accountModel.getNumOfAccounts());
                fNameField.setText("");
                lNameField.setText("");
                savingsButton.setSelected(false);
                checkingButton.setSelected(false);
                extraChargeField.setText("");
                inBalanceField.setText("");
                withdrawButton.setSelected(false);
                depositButton.setSelected(false);
                alterBalanceField.setText("");

                if (accountModel.getAccountIndex()-1 >= accountModel.getNumOfAccounts()){
                    inputFrame.setVisible(false);
                    accountsToBeCreatedLabel.setText(accountModel.getNumOfAccounts() + " accounts have been created.");
                    loadLabel.setText("Open \"File -> Load\" to access account receipts.");
                }
            }
        });


        masterPanel.setBackground(Color.darkGray);
        inputPanel.add(inputNorthPanel);
        inputPanel.add(inputCenterPanel);
        alterBalancePanel.add(alterBalanceNorthPanel);
        alterBalancePanel.add(alterBalanceCenterPanel);
        alterBalancePanel.add(alterBalanceSouthPanel);
        masterPanel.add(inputPanel);
        masterPanel.add(alterBalancePanel);
        //masterPanel.add(alterBalancePanel);
        inputFrame.getContentPane().add(masterPanel);
        inputFrame.setVisible(true);
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

    public void alterBalanceActionPerformed(ActionEvent e)
    {
        if (e.getSource() == withdrawButton)
        {
            accountModel.setAlterBalanceType(1);
            alterBalanceLabel.setText("Enter amount to withdraw: $");
            System.out.println("Withdraw button selected.");
        }

        if (e.getSource() == depositButton)
        {
            accountModel.setAlterBalanceType(2);
            alterBalanceLabel.setText("Enter amount to deposit: $");
            System.out.println("Deposit button selected.");
        }
    }

    public void processAccount() {
        int accType = accountModel.getAccType();
        int alterBalanceType = accountModel.getAlterBalanceType();
        String firstName = fNameField.getText();
        String lastName = lNameField.getText();
        double balance = Double.parseDouble(inBalanceField.getText());
        double extraCharge = Double.parseDouble(extraChargeField.getText());
        double alterBalance = Double.parseDouble(alterBalanceField.getText());

        System.out.println("Account type " + accountModel.getAccType());
        accountModel.inputAccounts(accountArrayList, firstName, lastName, balance, accType, extraCharge);

        if (accType == 1) {
            accountModel.setFee(extraCharge);
        } else if (accType == 2) {
            accountModel.setInterest(extraCharge);
        } else {
            System.out.println("Error setting accType.");
        }

        if (alterBalanceType == 1) {
            accountModel.setWithdraw(alterBalance);
            accountModel.setDeposit(0.00);
        } else if (alterBalanceType == 2) {
            accountModel.setDeposit(alterBalance);
            accountModel.setWithdraw(0.00);
        } else {
            System.out.println("Error setting alterBalanceType.");
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
