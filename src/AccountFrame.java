import DefaultFrame.DefaultFrame;

import java.awt.*;

public class AccountFrame extends DefaultFrame {
    public AccountFrame(){
        AccountPanel accountPanel = new AccountPanel();
        this.getContentPane().add(accountPanel);
        this.setSize(400, 250);
    }
}
