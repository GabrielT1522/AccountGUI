package UserAccount;

import AdminAccount.AccountModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UserAccountPanel extends JPanel
{
    // alterPanel components
    private  JPanel userAccountPanel;
    private JPanel userAccountNorthPanel;
    private JPanel userAccountCenterPanel;
    private JPanel userAccountSouthPanel;
    public JLabel alterBalanceLabel;
    private JRadioButton withdrawButton;
    private JRadioButton depositButton;
    private JTextField alterBalanceField;
    private ButtonGroup userAccountButtonGroup;
    private AccountModel accountModel = new AccountModel();
    public void UserAccountPanel() {
        // Create alterBalancePanel
        userAccountPanel = new JPanel();
        userAccountNorthPanel = new JPanel();
        userAccountCenterPanel = new JPanel();
        userAccountSouthPanel = new JPanel();

        userAccountPanel.setLayout(new BoxLayout(userAccountPanel, BoxLayout.PAGE_AXIS));

        userAccountButtonGroup = new ButtonGroup();
        alterBalanceLabel = new JLabel("Deposit or Withdraw?");
        withdrawButton = new JRadioButton("Withdraw");
        depositButton = new JRadioButton("Deposit");

        userAccountNorthPanel.add(alterBalanceLabel, BorderLayout.PAGE_START);
        userAccountPanel.setPreferredSize(new Dimension(200, 50));
        userAccountButtonGroup.add(withdrawButton);
        userAccountButtonGroup.add(depositButton);
        withdrawButton.addActionListener(this::userAccountActionPerformed);
        depositButton.addActionListener(this::userAccountActionPerformed);
        withdrawButton.setSelected(false);
        depositButton.setSelected(false);

        userAccountPanel.setLayout(new GridLayout(0,1, 15, 5));
        userAccountPanel.add(withdrawButton);
        userAccountPanel.add(depositButton);
        alterBalanceLabel = new JLabel("---");
        alterBalanceField = new JTextField();
        userAccountPanel.add(alterBalanceLabel);
        userAccountPanel.add(alterBalanceField);

        JButton userAccountSubmitButton = new JButton("Submit");
        userAccountSouthPanel.add(userAccountSubmitButton);
        userAccountSubmitButton.addActionListener(this::userAccountSubmitAction);

        userAccountPanel.add(userAccountNorthPanel);
        userAccountPanel.add(userAccountCenterPanel);
        userAccountPanel.add(userAccountSouthPanel);
        //userAccountPanel.add(userAccountPanel);
        //userAccountPanel.add(userAccountPanel);

    }

    public void userAccountActionPerformed(ActionEvent e)
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

    public void userAccountSubmitAction(ActionEvent e)
    {
        String actionCommand = e.getActionCommand();
        processUserAccount();

        if (actionCommand.equals("Submit")) {
            System.out.println("Submit button selected.");

            //accountModel.printReceipt(accountArrayList, accountModel.getWithdraw(), accountModel.getDeposit());
            //accountModel.incrementAccount();
            alterBalanceField.setText("");
        }
    }

    public void processUserAccount()
    {
        int accType = accountModel.getAccType();
        int alterBalanceType = accountModel.getAlterBalanceType();
        double alterBalance = Double.parseDouble(alterBalanceField.getText());

        System.out.println("Account type " + accountModel.getAccType());
        //accountModel.inputAccounts(accountArrayList, firstName, lastName, balance, accType, extraCharge);


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
}
