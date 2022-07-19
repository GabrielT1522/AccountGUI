package AccountLogin;

public class AccountLoginModel
{
    private static AccountLoginModel instance = null;
    private int userLoginType;
    private boolean loginAccess;
    private String accountFileName;
    public AccountLoginModel()
    {
        userLoginType = 0;
        loginAccess = false;
        accountFileName = "FILE NAME ERROR";
    }

    public static AccountLoginModel getInstance() {
        if(instance == null)
            instance = new AccountLoginModel();
        return instance;
    }
    public void setUserLoginType(int userLoginType){
        this.userLoginType = userLoginType;
    }
    public int getUserLoginType() {
        return userLoginType;
    }
    public void setLoginAccess(boolean loginAccess) {
        this.loginAccess = loginAccess;
    }
    public boolean getLoginAccess(){
        return loginAccess;
    }
    public void setAccountFileName(String accountFileName) {
        this.accountFileName = accountFileName;
    }

    public String getAccountFileName() {
        return accountFileName;
    }
}
