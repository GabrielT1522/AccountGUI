package BankAccountProgram;

public interface AccountInterface{

    // Modifiers for name and balance
    String getName();
    double getBalance();

    void setName(String name);
    void setBalance(double balance);

    // Credit method that validates positive input
    void credit(double balance);
    // Debit method that validates proper withdraw
    void debit(double balance);

    @Override
    // Default toString method
    String toString();
}