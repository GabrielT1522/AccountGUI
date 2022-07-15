public class AccountLoginModel
{
    private int userLoginType;
    private int loginAccess;
    public AccountLoginModel()
    {
        userLoginType = 0;
        loginAccess = 0;
    }

    public void setUserLoginType(int userLoginType){
        this.userLoginType = userLoginType;
    }
    public int getUserLoginType() {
        return userLoginType;
    }
    public void setLoginAccess(int loginAccess) {
        this.loginAccess = loginAccess;
    }
    public int getLoginAccess(){
        return loginAccess;
    }
}
