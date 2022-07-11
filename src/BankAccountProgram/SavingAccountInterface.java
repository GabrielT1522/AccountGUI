package BankAccountProgram;

public interface SavingAccountInterface extends AccountInterface {
    // Modifiers for interestRate
    double getInterestRate();
    void setInterestRate(double interestRate);

    // return calculated interest
    double calculateInterest();

    // Override base toString() method with proper fields
    @Override
    String toString();
}
