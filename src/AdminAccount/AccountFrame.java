package AdminAccount;

import DefaultFrame.DefaultFrame;

public class AccountFrame extends DefaultFrame {
    public AccountFrame(){
        AccountPanel accountPanel = new AccountPanel();
        this.getContentPane().add(accountPanel);
        //this.setSize(450, 250);
        this.pack();
    }
}
