package AdminAccount;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;

public class AccountPanel extends JPanel {

    private AccountModel accountModel = new AccountModel();
    private static String ADD = "Add Account";
    private static String REMOVE = "Remove Account";
    private static String SHOW = "Show Account Information";

    // CardLayout configuration
    private JPanel adminCard;
    private AddAccountPanel addAccountPanel;
    private JPanel removeAccountPanel = new JPanel();
    private JPanel showAccountPanel = new JPanel();

    // welcomePanel components
    private JPanel welcomePanel;
    private JPanel welcomeCenterPanel;
    private JPanel welcomeSouthPanel;
    private JLabel welcomeMessage;

    private Font headingFont = new Font("Times New Roman", Font.BOLD, 20);
    private Font normalFont = new Font("Times New Roman", Font.PLAIN, 15);

    public AccountPanel()
    {
        // Create welcomePanel
        welcomePanel = new JPanel();
        welcomeCenterPanel = new JPanel();
        welcomeSouthPanel = new JPanel();

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.setPreferredSize(new Dimension(400, 300));

        welcomeMessage = new JLabel("Hello Admin! What would you like to do?");
        welcomeMessage.setFont(headingFont);

        welcomeCenterPanel.add(welcomeMessage);
        welcomeCenterPanel.setBackground(Color.white);

        adminCard = new JPanel(new CardLayout());
        addAccountPanel = new AddAccountPanel();
        adminCard.add(addAccountPanel, ADD);
        adminCard.add(removeAccountPanel, REMOVE);
        adminCard.add(showAccountPanel, SHOW);

        //Put the JComboBox in a JPanel to get a nicer look.
        JPanel comboBoxPane = new JPanel(); //use FlowLayout
        String[] comboBoxItems = { ADD, REMOVE, SHOW };
        JComboBox cb = new JComboBox(comboBoxItems);
        cb.setEditable(false);
        cb.addItemListener(this::changeCardEvent);
        comboBoxPane.add(cb);

        welcomeCenterPanel.setLayout(new BoxLayout(welcomeCenterPanel, BoxLayout.PAGE_AXIS));
        welcomeCenterPanel.setPreferredSize(new Dimension(350, 400));
        welcomeCenterPanel.add(comboBoxPane);
        welcomeSouthPanel.add(adminCard);

        
        welcomePanel.add(welcomeCenterPanel);
        welcomePanel.add(welcomeSouthPanel);
        this.add(welcomePanel); // Finish welcomePanel
    }

    public void changeCardEvent(ItemEvent evt) {
        CardLayout cl = (CardLayout)(adminCard.getLayout());
        cl.show(adminCard, (String)evt.getItem());
    }


    /*public void processAccount()
    {
        int accType = accountModel.getAccType();
        int alterBalanceType = accountModel.getAlterBalanceType();

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
    }*/
}
