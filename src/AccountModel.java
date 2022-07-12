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

    public String getFullName(){
        return firstName + " " + lastName;
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
        do {
            System.out.print("Enter the initial balance: ");
            this.setBalance(balance);
            try {
                if (this.balance < 0.0)
                    System.out.println("Deposit amount must be positive.");
            } catch (Exception e) {
                System.out.println("Exception: Deposit amount must be positive.");
            }
        } while (getBalance() < 0.0);

        String fullName = firstName+" "+lastName;
        // Create type of account based on user input
        if (accType == 1) {
            System.out.print("Enter the fee: ");
            this.fee = extraCharge;
            accountArrayList.add(new CheckingAccount(fullName, balance, extraCharge));
        } else if (accType == 2) {
            System.out.print("Enter the interest rate: ");
            this.interest = extraCharge;
            accountArrayList.add(new SavingAccount(fullName, balance, extraCharge));
        }
    } // END OF inputAccounts

    static final DecimalFormat df = new DecimalFormat("###,###,###.00");
    void printReceipt(ArrayList<Account> accountArrayList, double withdraw, double deposit)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String currentDate = formatter.format(date);

        int counter = 1;
        for (Account account : accountArrayList) {
            try {
                FileWriter newReceipt = new FileWriter("Account" + counter + "Receipt.txt");
                double newBalance;
                newBalance = getBalance() + deposit;
                newBalance -= withdraw;

                newReceipt.write("----------------------------------------\n");
                newReceipt.write("Account "+counter+" Receipt\n\n");
                newReceipt.write("Date of transaction: " + currentDate);
                newReceipt.write("\nAccount Holder Name: " + account.getName());
                newReceipt.write("\n\nAmount withdrawn: $"+withdraw);
                newReceipt.write("\nAmount deposited: $"+deposit);
                newReceipt.write("\n\nPrevious balance: $" + df.format(account.getBalance()));
                newReceipt.write("\n ---");
                newReceipt.write("\nCurrent balance: $" + df.format(newBalance));
                newReceipt.write("\n----------------------------------------");
                newReceipt.close();
                System.out.println("\n\nSuccessfully wrote to the file: Account"+counter+"Receipt.txt");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            counter++;
        }
    }
}
