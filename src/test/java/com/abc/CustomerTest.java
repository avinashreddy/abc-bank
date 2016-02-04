package com.abc;

import lombok.NonNull;
import org.junit.Ignore;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;


public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        Account checkingAccount = new CheckingAccount();
        Account savingsAccount = new SavingAccount();

        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);

        checkingAccount.deposit(100.0);
        savingsAccount.deposit(4000.0);
        savingsAccount.withdraw(200.0);

        assertThat(henry.getStatement()).isEqualTo(
                "Statement for Henry\n" +
                        "\n" +
                        "Checking Account\n" +
                        "  deposit $100.00\n" +
                        "Total $100.00\n" +
                        "\n" +
                        "Savings Account\n" +
                        "  deposit $4,000.00\n" +
                        "  withdrawal $200.00\n" +
                        "Total $3,800.00\n" +
                        "\n" +
                        "Total In All Accounts $3,900.00");
    }

    @Test
    public void testOneAccount() {
        Customer oscar = new Customer("Oscar").openAccount(new SavingAccount());
        assertThat(oscar.getNumberOfAccounts()).isEqualTo(1);
    }

    @Test
    public void testTwoAccount() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount());
        oscar.openAccount(new CheckingAccount());
        assertThat(oscar.getNumberOfAccounts()).isEqualTo(2);

    }

    @Test
    public void interestEarned() {
        Customer oscar = new Customer("Oscar")
                .openAccount(new SavingAccount() {
                    @Override
                    public double interestEarned() {
                        return 100;
                    }
                }).openAccount(new SavingAccount() {
                    @Override
                    public double interestEarned() {
                        return 200;
                    }
                });

        assertThat(oscar.totalInterestEarned()).isEqualTo(300);


    }
}
