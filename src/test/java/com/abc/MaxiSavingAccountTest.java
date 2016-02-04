package com.abc;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class MaxiSavingAccountTest {

    Account account = new MaxiSavingAccount();

    @Test
    public void interest_earned_with_no_withdrawals_in_last_10_days() {
        account.deposit(1000.00);
        assertThat(account.interestEarned()).isEqualTo(50);
    }

    @Test
    public void interest_earned_with_withdrawals_in_last_10_days() {
        account.deposit(1000.00);
        account.withdraw(50.50);
        account.deposit(50.50);
        assertThat(account.interestEarned()).isEqualTo(1);
    }



}
