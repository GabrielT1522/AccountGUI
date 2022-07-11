package BankAccountProgram;

public interface CheckingAccountInterface extends AccountInterface{

    // Modifiers for fee
    double getFee();
    void setFee(double fee);

    // Override base credit() method to add fee
    @Override
    void credit(double balance);

    // Override base debit() method to add fee
    @Override
    void debit(double balance);

    // Override base toString() method with proper fields
    @Override
    String toString();
}