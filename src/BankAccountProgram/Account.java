package BankAccountProgram;

import java.text.DecimalFormat;
public class Account implements AccountInterface{

    protected static final DecimalFormat df = new DecimalFormat("###,###,###.00");
    protected String name;
    protected double balance;

    // Constructor that validates initial valance
    Account(String name, double balance){
        setName(name);
        setBalance(balance);
    }

    // Modifiers for name and balance
    public String getName(){
        return name;
    }
    public double getBalance(){
        return balance;
    }
    public void setName(String name){
        this.name = name;
    }

    public void setBalance(double balance){
        if (balance >= 0.0){
            this.balance = balance;
        }
        else{
            setBalance(0.0);
            throw new ArithmeticException("Exception: Initial balance was invalid.");
        }
    }

    // Credit method that validates positive input
    public void credit(double balance){
        if (balance < 0.0){
            throw new ArithmeticException("Exception: Deposit amount must be positive.");
        }
        else{
            this.balance += balance;
        }
    }

    // Debit method that validates proper withdraw
    public void debit(double balance){
        if (balance > this.balance){
            System.out.println("Debit amount exceeded account balance.");
        }
        else{
            this.balance -= balance;
        }
    }

    // Default toString method overrides original toString
    @Override
    public String toString(){
        return "Name: " + this.name + " Balance: $";
    }
}
