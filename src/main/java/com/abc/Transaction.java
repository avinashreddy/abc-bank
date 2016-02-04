package com.abc;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
public class Transaction {

    @Getter
    private final double amount;

    @Getter
    private final LocalDateTime transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = LocalDateTime.now();
    }

    public boolean isWithdrawal() {
        return amount < 0;
    }
}
