package com.abc;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    public List<Transaction> transactions;

    public Account() {
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
        return sumTransactions() * 0.001;
    }

    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t : transactions)
            amount += t.getAmount();
        return amount;
    }

    public abstract String getAccountType();

}
