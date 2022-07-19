package AccountLogin;

import DefaultFrame.DefaultFrame;


public class AccountLoginFrame extends DefaultFrame {

    public AccountLoginFrame(){
        AccountLoginPanel accountLoginPanel = new AccountLoginPanel();
        this.getContentPane().add(accountLoginPanel);
        this.setSize(400, 250);
    }
}
