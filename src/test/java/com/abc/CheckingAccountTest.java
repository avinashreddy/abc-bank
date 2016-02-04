package com.abc;

import org.junit.Test;
import static org.fest.assertions.Assertions.assertThat;

public class CheckingAccountTest {

    Account account = new CheckingAccount();

    @Test
    public void interestEarned() {
        account.deposit(1000.00);
        account.withdraw(50.50);
        assertThat(account.interestEarned()).isEqualTo(0.9495);
    }


}
