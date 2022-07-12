package BankAccountProgram;

public class SavingAccount extends Account implements SavingAccountInterface{
    private double interestRate;

    // Derived constructor using base constructor
    public SavingAccount(String name, double balance, double interestRate){
        super(name, balance);
        setInterestRate(interestRate);
    }

    // Modifiers for interestRate
    public double getInterestRate(){
        return interestRate;
    }
    public void setInterestRate(double interestRate){
        if (0.0 <= interestRate && interestRate <= 1.0){
            this.interestRate = interestRate;
        }
        else{
            throw new ArithmeticException("Exception: Interest rate must be between 0 and 1.");
        }
    }

    // return calculated interest
    public double calculateInterest() {
        return this.balance * this.interestRate;
    }
    public double calculateInterest(double balance) {
        return balance * this.interestRate;
    }

    // Override base toString() method with proper fields
    @Override
    public String toString(){
        return super.toString() + df.format(this.balance + calculateInterest());
    }
}