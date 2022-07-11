import BankAccountProgram.*;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class InputAccountModel
{
    private int accType;
    private int noOfAccounts;
    private int accountIndex;
    private String firstName;
    private String lastName;
    private double balance;
    private double interest;
    private double fee;

    public InputAccountModel()
    {
        accType = 0;
        noOfAccounts = 0;
        accountIndex = 0;
        firstName = "f";
        lastName = "n";
        balance = 0.0;
        interest = 0.0;
        fee = 0.0;
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
    public int getNoOfAccounts(){return noOfAccounts;}



    public void inputAccounts(ArrayList<Account> accountArrayList, String firstName, String lastName, double balance, int accType, double extraCharge)
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

        String fullName = firstName + " " + lastName;
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

    public int counter = 1;
    public void outputAccounts(ArrayList<Account> accountArrayList){
        for (Account account : accountArrayList) {

            System.out.println("Account(" + (counter) + ")");
            System.out.println("Bank client: " + account.getName() + " has $" + df.format(account.getBalance()));

            System.out.print("Updated Account(" + (counter) + ") ");
            System.out.println(account);
            System.out.println();
            counter++;
        }
    } // END OF outputAccounts

}
