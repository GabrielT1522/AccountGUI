public class AccountDriver {

    public static void main(String[] args)
    {
        AccountFrame accountFrame = new AccountFrame();
        accountFrame.showIt("Account");
        AccountModel accountModel = new AccountModel();
        if (accountModel.getAccountIndex() <= accountModel.getNumOfAccounts()){
            accountFrame.hideIt();
        }
    }
}
