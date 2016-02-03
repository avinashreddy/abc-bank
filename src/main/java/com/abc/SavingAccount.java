package com.abc;

public class SavingAccount extends Account {

    public double interestEarned() {
        double amount = sumTransactions();
        if (amount <= 1000)
            return amount * 0.001;
        else
            return 1 + (amount - 1000) * 0.002;
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }
}
