package com.abc;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.fest.assertions.Assertions.assertThat;

public class AccountTest {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void transfer() {
        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingAccount();

        new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(100.0);

        checkingAccount.transferTo(savingsAccount, 50);

        assertThat(checkingAccount.sumTransactions()).isEqualTo(50);
        assertThat(savingsAccount.sumTransactions()).isEqualTo(150);
    }

    @Test
    public void transferBetweenAccountsOfDifferentCustomersNotAllowed() {

        Account checkingAccount = new CheckingAccount();
        new Customer("Henry").openAccount(checkingAccount);

        Account savingsAccount = new SavingAccount();
        new Customer("Tom").openAccount(savingsAccount);

        expectedEx.expect(RuntimeException.class);
        expectedEx.expectMessage("Cannot perform transfer. Both accounts must belong to same customer");

        checkingAccount.transferTo(savingsAccount, 50);
    }
}
