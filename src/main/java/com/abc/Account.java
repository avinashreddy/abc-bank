package com.abc;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

public class Account {

    private final Type accountType;
    public List<Transaction> transactions;

    public Account(Type accountType) {
        Preconditions.checkArgument(accountType != null, "accountType is null");
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        Preconditions.checkArgument(amount > 0, "amount must be greater than zero");
        transactions.add(new Transaction(amount));
    }

    public void withdraw(double amount) {
        Preconditions.checkArgument(amount > 0, "amount must be greater than zero");
        transactions.add(new Transaction(-amount));
    }

    public double interestEarned() {
        double amount = sumTransactions();
        switch (accountType) {
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount - 1000) * 0.002;
            case MAXI_SAVINGS:
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount - 1000) * 0.05;
                return 70 + (amount - 2000) * 0.1;
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t : transactions)
            amount += t.getAmount();
        return amount;
    }

    public Type getAccountType() {
        return accountType;
    }

    public enum Type {
        CHECKING,
        SAVINGS,
        MAXI_SAVINGS
    }

}
