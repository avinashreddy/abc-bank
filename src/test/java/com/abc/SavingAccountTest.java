package com.abc;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class SavingAccountTest {

    Account account = new SavingAccount();

    @Test
    public void interest_earned_when_amount_not_greater_than_1000() {
        account.deposit(1000.00);
        account.withdraw(50.50);
        assertThat(account.interestEarned()).isEqualTo(0.9495);
    }

    @Test
    public void interest_earned_when_amount_greater_than_1000() {
        account.deposit(1000.00);
        account.withdraw(50.50);
        account.deposit(50.50);
        account.deposit(100.00);
        assertThat(account.interestEarned()).isEqualTo(1.2);
    }

}
