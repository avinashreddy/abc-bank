package com.abc;

public class CheckingAccount extends Account {

    @Override
    public String getAccountType() {
        return "Checking Account";
    }

    @Override
    public double interestEarned() {
        return sumTransactions() * 0.001;
    }
}
