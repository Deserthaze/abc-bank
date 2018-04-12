package com.abc;

import org.junit.Test;
import com.abc.Account.AccountType;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        john.openAccount(new Account(AccountType.CHECKING));
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.CHECKING);
        Customer bill = new Customer("Bill").openAccount(checkingAccount);
        bank.addCustomer(bill);

        checkingAccount.deposit(100.0);

        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(1500.0);

        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount = new Account(AccountType.MAXI_SAVINGS);
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
        checkingAccount.deposit(3000.0);
        assertEquals(171.0, bank.totalInterestPaid(), DOUBLE_DELTA);
    }
    
    @Test

    public void test_accrue_daily() {
       Bank bank = new Bank();
       Account checkingAccount = new Account(AccountType.CHECKING);
       Customer c = new Customer("Max");
       bank.addCustomer(c.openAccount(checkingAccount));
       checkingAccount.deposit(1000);
       bank.depositInterestEarned();
       //0.0027* 356 we be equal to 1
       assertEquals(1000.0027397260274, checkingAccount.sumTransactions(), DOUBLE_DELTA);       
    }

}
