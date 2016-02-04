package com.abc;

import com.google.common.base.Preconditions;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public abstract class Account {

    protected final List<Transaction> transactions;

    //TODO: Find a way to make this final.
    private Customer customer;

    public Account() {
        this.transactions = new ArrayList<>();
    }

    public void setCustomer(@NonNull Customer customer) {
        Preconditions.checkState(this.customer == null, "Cannot change customer!");
        this.customer = customer;
    }

    public void deposit(double amount) {
        Preconditions.checkArgument(amount > 0, "amount must be greater than zero");
        transactions.add(new Transaction(amount));
    }

    public void withdraw(double amount) {
        Preconditions.checkArgument(amount > 0, "amount must be greater than zero");
        transactions.add(new Transaction(-amount));
    }

    public abstract double interestEarned();

    public double sumTransactions() {
        double amount = 0.0;
        for (Transaction t : transactions)
            amount += t.getAmount();
        return amount;
    }

    public String getStatement() {
        String s = getAccountType() + System.lineSeparator();

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : transactions) {
            s += "  " + (t.getAmount() < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.getAmount()) + "\n";
            total += t.getAmount();
        }
        s += "Total " + toDollars(total);
        return s;
    }

    public void transferTo(@NonNull Account to, double amount) {
        Preconditions.checkState(this.customer != null, "From Account is not associated with a customer. Cannot perform transfer.");
        Preconditions.checkState(to.customer != null, "To Account is not associated with a customer. Cannot perform transfer.");
        Preconditions.checkState(this.customer.equals(to.customer), "Cannot perform transfer. Both accounts must belong to same customer.");
        Preconditions.checkArgument(amount > 0, "amount must be greater than zero");

        this.withdraw(amount);
        to.deposit(amount);
    }

    public static String toDollars(double d) {
        return String.format("$%,.2f", abs(d));
    }


    /**
     * @return The display/print name of the type of account.
     */
    public abstract String getAccountType();

}
