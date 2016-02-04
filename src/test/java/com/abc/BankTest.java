package com.abc;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class BankTest {

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new CheckingAccount());
        bank.addCustomer(john);

        assertThat(bank.customerSummary()).isEqualTo("Customer Summary\n - John (1 account)");
    }

    @Test
    public void totalInterestPaid() {
        Bank bank = new Bank();
        bank.addCustomer(new Customer("bill") {
            @Override
            public double totalInterestEarned() {
                return 100;
            }
        });

        bank.addCustomer(new Customer("Jack") {
            @Override
            public double totalInterestEarned() {
                return 200;
            }
        });

        assertThat(bank.totalInterestPaid()).isEqualTo(300);
    }

}
