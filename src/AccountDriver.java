import AccountLogin.AccountLogin;

import java.awt.*;

public class AccountDriver {

    public static void main(String[] args)
    {
        AccountLogin accountLogin = new AccountLogin();
        accountLogin.showIt("Account Login");

        AccountFrame accountFrame = new AccountFrame();
        accountFrame.showIt("Create Accounts");


        AccountModel accountModel = new AccountModel();
        if (accountModel.getAccountIndex() <= accountModel.getNumOfAccounts()){
            accountFrame.hideIt();
        }
    }
}
