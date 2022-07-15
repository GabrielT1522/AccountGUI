public class AccountDriver {

    static AccountLogin accountLogin = new AccountLogin();
    static AccountLoginModel accountLoginModel = new AccountLoginModel();
    static AccountFrame accountFrame = new AccountFrame();
    static AccountModel accountModel = new AccountModel();
    public static void main(String[] args)
    {
        accountLogin.showIt("Account Login");


        if (accountLoginModel.getLoginAccess() == 1) {
            accountFrame.showIt("Create Accounts");
            accountLogin.hideIt();
            System.out.println("Account frame should be open.");
        }

        if (accountModel.getAccountIndex() <= accountModel.getNumOfAccounts()){
            accountFrame.hideIt();
        }
    }
}
