package AccountLogin;

public class AccountLoginModel
{

    private int userLoginType;
    public AccountLoginModel()
    {
        userLoginType = 0;
    }

    public void setUserLoginType(int userLoginType){
        this.userLoginType = userLoginType;
    }

    public int getUserLoginType() {
        return userLoginType;
    }
}
