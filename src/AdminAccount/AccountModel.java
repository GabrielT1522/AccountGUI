package AdminAccount;

import BankAccountProgram.*;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AccountModel
{
    private int alterBalanceType;
    private int accType;
    private int numOfAccounts;
    private int accountIndex;
    private String firstName;
    private String lastName;
    private double balance;
    private double interest;
    private double fee;
    private double deposit;
    private double withdraw;

    public AccountModel()
    {
        alterBalanceType = 0;
        accType = 0;
        numOfAccounts = 0;
        accountIndex = 0;
        firstName = "f";
        lastName = "n";
        balance = 0.0;
        interest = 0.0;
        fee = 0.0;
        deposit = 0.0;
        withdraw = 0.0;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName){this.firstName = firstName;}

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName){this.lastName = lastName;}

    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance){this.balance = balance;}

    public double getFee() {
        return fee;
    }
    public void setFee(double fee){this.fee = fee;}

    public double getInterest() {
        return interest;
    }
    public void setInterest(double interest){
        this.interest = interest;}

    public int getAccType() {
        return accType;
    }
    public void setAccType(int accType) {
        this.accType = accType;
    }

    public void incrementAccount(){
        accountIndex++;
    }

    public int getAccountIndex(){return accountIndex+1;}

    public int getAlterBalanceType(){return alterBalanceType;}

    public void setAlterBalanceType(int alterBalanceType) {
        this.alterBalanceType = alterBalanceType;
    }

    public int getNumOfAccounts(){return numOfAccounts;}
    public void setNumOfAccounts(int numOfAccounts){
        this.numOfAccounts = numOfAccounts;
    }

    public double getWithdraw() {
        return withdraw;
    }
    public void setWithdraw(double withdraw) {
        this.withdraw = withdraw;
    }
    public double getDeposit() {
        return deposit;
    }
    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public void inputAccounts(ArrayList<Account> accountArrayList, String firstName, String lastName,
                              double balance, int accType, double extraCharge)
    {
        // Validate initial Balance
        setBalance(balance);
        try {
            if (balance < 0.0)
                System.out.println("Deposit amount must be positive.");
        } catch (Exception e) {
            throw new RuntimeException("Exception: Deposit amount must be positive.");
        }
        System.out.println("\nInitial balance: "+getBalance());
        String fullName = firstName+" "+lastName;


        // Create type of account based on user input
        if (accType == 1) {
            this.fee = extraCharge;
            System.out.println("Fee Amount: "+getFee());
            accountArrayList.add(new CheckingAccount(fullName, balance, extraCharge));
        } else if (accType == 2) {
            this.interest = extraCharge;
            System.out.println("Interest Rate: "+getInterest());
            accountArrayList.add(new SavingAccount(fullName, balance, extraCharge));
        }
    } // END OF inputAccounts

    static final DecimalFormat df = new DecimalFormat("###,###,###.00");
    void printReceipt(ArrayList<Account> accountArrayList, double withdraw, double deposit)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String currentDate = formatter.format(date);
        int i = getAccountIndex();

            try {
                FileWriter newReceipt = new FileWriter("/Users/gabrieltorres/Desktop/Java/AccountGUI/Account Receipts/Account"+i+"Receipt.txt");
                double oldBalance = accountArrayList.get(i-1).getBalance();
                accountArrayList.get(i-1).credit(deposit);
                accountArrayList.get(i-1).debit(withdraw);
                double newBalance = accountArrayList.get(i-1).getBalance();

                newReceipt.write("----------------------------------------\n");
                newReceipt.write("Account "+i+" Receipt\n\n");
                newReceipt.write("Date of transaction: " + currentDate);
                newReceipt.write("\nAccount Holder Name: " + accountArrayList.get(i-1).getName());
                if (getAccType() == 1){
                    newReceipt.write("\nAccount Type: Checking");
                } else if (getAccType() == 2){
                    newReceipt.write("\nAccount Type: Savings");
                } else { newReceipt.write("\nError getting account type.");}
                newReceipt.write("\n\nAmount withdrawn: $"+withdraw);
                newReceipt.write("\nAmount deposited: $"+deposit);
                // try/catch runs only if account is SavingAccount
                try{
                    double interest = ((SavingAccount) accountArrayList.get(i-1)).calculateInterest(oldBalance);
                    newReceipt.write("\nCalculated interest: $" + df.format(interest));
                    newBalance += interest;
                }
                catch (Exception e){
                    newReceipt.write("\nAccount fee: "+getFee());
                    newBalance += getFee();
                }
                newReceipt.write("\n\nPrevious balance: $" + df.format(oldBalance));
                newReceipt.write("\n ---");
                newReceipt.write("\nCurrent balance: $" + df.format(newBalance));
                newReceipt.write("\n----------------------------------------");
                newReceipt.close();
                System.out.println("\nSuccessfully wrote to the file: Account"+i+"Receipt.txt\n");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
    }
}
