package BankAccountProgram;

public class CheckingAccount extends Account implements CheckingAccountInterface{
    private double fee;

    // Derived constructor using base constructor
    public CheckingAccount(String name, double balance, double fee) {
        super(name, balance);
        setFee(fee);
    }

    // Modifiers for fee
    public double getFee(){
        return fee;
    }
    public void setFee(double fee){
        this.fee = fee;
    }

    // Override base credit() method to add fee
    @Override
    public void credit(double balance){
        if (balance < 0.0){
            throw new ArithmeticException("Exception: Deposit amount must be positive.");
        }
        else{
            this.balance = (this.balance + balance) - fee;
        }
    }
    // Override base debit() method to add fee
    @Override
    public void debit(double balance){
        if (balance > this.balance){
            System.out.println("Debit amount exceeded account balance.");
        }
        else{
            this.balance = (this.balance - balance) - fee;
        }
    }

    // Override base toString() method with proper fields
    @Override
    public String toString(){
        return super.toString() + df.format(balance);
    }
}