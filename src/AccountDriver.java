import AccountLogin.AccountLoginFrame;
import AdminAccount.AccountFrame;
import AdminAccount.AccountModel;

public class AccountDriver {
    public static void main(String[] args)
    {
        AccountLoginFrame accountLoginFrame = new AccountLoginFrame();
        AccountFrame accountFrame = new AccountFrame();
        AccountModel accountModel = new AccountModel();

        accountLoginFrame.showIt("Account Login");
        if (accountModel.getAccountIndex() <= accountModel.getNumOfAccounts()){
            accountFrame.hideIt();
        }
    }
}
