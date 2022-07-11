import BankAccountProgram.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AccountPanel extends JPanel {

    private WelcomeModel welcomeModel = new WelcomeModel();
    private InputAccountModel inputAccountModel = new InputAccountModel();

    // welcomePanel components
    private JPanel welcomePanel;
    private JPanel welcomeCenterPanel;
    private JPanel welcomeSouthPanel;
    private JLabel welcomeMessage;
    private JLabel numOfAccountsLabel;
    private JLabel accountsToBeCreatedLabel;
    private JButton welcomeSubmitButton;
    private JTextField noOfAccountsField;

    // inputPanel components
    private JPanel inputPanel;
    private JPanel inputNorthPanel;
    private JPanel inputCenterPanel;
    private JPanel inputSouthPanel;
    private GridLayout gridLayout;
    private JLabel filler;
    private JLabel accountNumLabel;
    private JLabel fNameLabel;
    private JTextField fNameField;
    private ButtonGroup group;
    private JLabel accTypeLabel;
    private JRadioButton savingsButton;
    private JRadioButton checkingButton;
    private JLabel lNameLabel;
    private JTextField lNameField;
    private JLabel extraChargeLabel;
    private JTextField extraChargeField;
    private JLabel inBalanceLabel;
    private JTextField inBalanceField;

    public AccountPanel()
    {
        // Create welcomePanel
        welcomePanel = new JPanel();
        welcomeCenterPanel = new JPanel();
        welcomeSouthPanel = new JPanel();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(400, 350));

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
        welcomeSubmitButton = new JButton("Submit");

        //welcomeSouthPanel.setLayout(new BoxLayout(welcomeSouthPanel, BoxLayout.Y_AXIS));
        welcomeSouthPanel.setBackground(Color.lightGray);
        welcomeSouthPanel.add(noOfAccountsField);
        welcomeSouthPanel.add(welcomeSubmitButton);
        welcomeSouthPanel.add(accountsToBeCreatedLabel);

        welcomeSubmitButton.addActionListener(evt -> {
            String actionCommand = evt.getActionCommand();
            if (actionCommand.equals("Submit")) {
                System.out.println("Num of accounts: " + welcomeModel.getNumOfAccounts());
                System.out.println("Submit button selected.");

                int numOfAccounts = Integer.parseInt(noOfAccountsField.getText());
                welcomeModel.setNumOfAccounts(numOfAccounts);
                setAccountNumLabel(numOfAccounts);

                if (actionCommand.equals("Submit")) {
                    System.out.println("Submit button selected.");

                    if (welcomeModel.getNumOfAccounts() == 1) {
                        accountsToBeCreatedLabel.setText(numOfAccounts + " account will be created.");
                        System.out.println(numOfAccounts + " account will be created.");
                    } else if (numOfAccounts > 1) {
                        accountsToBeCreatedLabel.setText(numOfAccounts + " accounts will be created.");
                        System.out.println(numOfAccounts + " accounts will be created.");
                    }
                }
            }
        });

        welcomePanel.add(welcomeCenterPanel);
        welcomePanel.add(welcomeSouthPanel);
        this.add(welcomePanel); // Finish welcomePanel

        // Create inputPanel
        inputPanel = new JPanel();
        inputNorthPanel = new JPanel();
        inputCenterPanel = new JPanel();
        inputSouthPanel = new JPanel();

        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.PAGE_AXIS));
        inputPanel.setPreferredSize(new Dimension(400, 200));
        gridLayout = new GridLayout(0,2, 10, 5);
        filler = new JLabel(" ");

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        accountNumLabel = new JLabel("Account 1");
        inputNorthPanel.add(accountNumLabel);

        fNameLabel = new JLabel("First Name: ");
        fNameField = new JTextField();

        group = new ButtonGroup();
        accTypeLabel = new JLabel("Type of Account? ");
        savingsButton = new JRadioButton("Savings");
        checkingButton = new JRadioButton("Checking");

        lNameLabel = new JLabel("Last Name: ");
        lNameField = new JTextField();

        extraChargeLabel = new JLabel("---");
        extraChargeField = new JTextField();

        inBalanceLabel = new JLabel("Initial Balance: $");
        inBalanceField = new JTextField();

        inputCenterPanel.setSize(new Dimension(100, 250));
        inputCenterPanel.setLayout(gridLayout);

        inputCenterPanel.add(fNameLabel);
        inputCenterPanel.add(fNameField);

        inputCenterPanel.add(lNameLabel);
        inputCenterPanel.add(lNameField);

        inputCenterPanel.add(accTypeLabel);
        inputCenterPanel.add(filler);
        group.add(savingsButton);
        group.add(checkingButton);
        savingsButton.addActionListener(this::accTypeAction);
        checkingButton.addActionListener(this::accTypeAction);
        savingsButton.setSelected(false);
        checkingButton.setSelected(false);

        inputCenterPanel.add(savingsButton);
        inputCenterPanel.add(checkingButton);

        inputCenterPanel.add(extraChargeLabel);
        inputCenterPanel.add(extraChargeField);

        inputCenterPanel.add(inBalanceLabel);
        inputCenterPanel.add(inBalanceField);


        JButton inputSubmitButton = new JButton("Submit");
        inputSouthPanel.add(inputSubmitButton);
        inputSubmitButton.addActionListener(evt -> {
            String actionCommand = evt.getActionCommand();
            processAccount();

            if (actionCommand.equals("Submit")) {
                System.out.println("Num of accounts: "+welcomeModel.getNumOfAccounts());
                System.out.println("Submit button selected.");
            }
        });


        inputPanel.add(inputNorthPanel);
        inputPanel.add(inputCenterPanel);
        inputPanel.add(inputSouthPanel);
        this.add(inputPanel);

    }

    public void accTypeAction(ActionEvent e)
    {
        if (e.getSource() == checkingButton)
        {
            inputAccountModel.setAccType(1);
            extraChargeLabel.setText("Enter the fee: $");
            System.out.println("Checking button selected.");
        }

        if (e.getSource() == savingsButton)
        {
            inputAccountModel.setAccType(2);
            extraChargeLabel.setText("Enter the interest rate: $");
            System.out.println("Savings button selected.");
        }
    }

    public void processAccount() {
        int arraySize = welcomeModel.getNumOfAccounts();
        int accType = inputAccountModel.getAccType();
        String firstName = fNameField.getText();
        String lastName = lNameField.getText();
        double balance = Double.parseDouble(inBalanceField.getText());
        double extraCharge = Double.parseDouble(extraChargeField.getText());

        //Account[] accountArrayList = new Account[welcomePanel.getNumOfAccounts()];
        ArrayList<Account> accountArrayList = new ArrayList<>();

        System.out.println("Account type " + inputAccountModel.getAccType());
        for (int i = 0; i < arraySize; i++) {
            inputAccountModel.inputAccounts(accountArrayList, firstName, lastName, balance, accType, extraCharge);
        }
        inputAccountModel.outputAccounts(accountArrayList);

        if (accType == 1) {
            inputAccountModel.setFee(extraCharge);
        } else if (accType == 2) {
            inputAccountModel.setInterest(extraCharge);
        } else {
            System.out.println("Error setting accType.");
        }
    }

    public void setAccountNumLabel(int numOfAccounts){
        String accountIndex = String.valueOf(inputAccountModel.getAccountIndex());

        accountNumLabel.setText("Account" + " " + accountIndex+" out of "+numOfAccounts);
        System.out.println("Account" + " " + accountIndex+" out of "+numOfAccounts);
    }

}
