import DefaultFrame.DefaultFrame;

public class AccountFrame extends DefaultFrame {
    public AccountFrame(){
        AccountPanel accountPanel = new AccountPanel();
        this.getContentPane().add(accountPanel);
        this.setSize(400, 850);
    }
}
