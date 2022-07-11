import DefaultFrame.DefaultFrame;

public class AccountFrame extends DefaultFrame {
    private AccountPanel accountPanel;

    public AccountFrame(){
        accountPanel = new AccountPanel();
        this.getContentPane().add(accountPanel);
        this.pack();
    }
}
