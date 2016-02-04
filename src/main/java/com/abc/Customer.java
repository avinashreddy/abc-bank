package com.abc;

import com.google.common.base.Preconditions;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(of = "name")
@ToString(of = "name")
public class Customer {
    private final String name;
    private final List<Account> accounts;

    public Customer(String name) {
        Preconditions.checkArgument(StringUtils.hasText(name), "name is null/empty");
        this.name = name;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    //TODO: Can a customer have more than one account of the same type?
    public Customer openAccount(@NonNull Account account) {
        accounts.add(account);
        account.setCustomer(this);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        StringBuilder statement = new StringBuilder("Statement for ").append(name).append(System.lineSeparator());
        double total = 0.0;
        for (Account a : accounts) {
            statement.append(System.lineSeparator()).append(a.getStatement()).append(System.lineSeparator());
            total += a.sumTransactions();
        }
        statement.append(System.lineSeparator()).append("Total In All Accounts ").append(Account.toDollars(total));
        return statement.toString();
    }
}
