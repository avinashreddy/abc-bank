package com.abc;

import com.google.common.collect.Lists;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class MaxiSavingAccount extends Account {

    public double interestEarned() {
        Transaction lastWithdrawal = lastWithdrawal();
        if (lastWithdrawal == null || olderThanDays(lastWithdrawal, 10)) {
            return sumTransactions() * 0.05;
        }
        return sumTransactions() * 0.001;
    }

    private boolean olderThanDays(Transaction lastWithdrawal, int days) {
        Duration duration = Duration.between(LocalDateTime.now(), lastWithdrawal.getTransactionDate());
        return duration.minusDays(days).get(ChronoUnit.SECONDS) > 0;
    }

    Transaction lastWithdrawal() {
        for (Transaction transaction : Lists.reverse(transactions)) {
            if (transaction.isWithdrawal()) {
                return transaction;
            }
        }
        return null;
    }

    @Override
    public String getAccountType() {
        return "Maxi Savings Account";
    }
}
