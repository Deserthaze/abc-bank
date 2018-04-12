package com.abc;

import java.util.ArrayList;

import java.time.LocalDate;

import java.time.temporal.ChronoUnit;

import java.util.ArrayList;

import java.util.List;


public class Account {

	  enum AccountType {
          CHECKING,
          SAVINGS,
          MAXI_SAVINGS
    }

    private final AccountType accountType;
    public List<Transaction> transactions;
    LocalDate lastWithdraw;

    public Account(AccountType accountType) {
        this.accountType = accountType;
        this.transactions = new ArrayList<Transaction>();
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            transactions.add(new Transaction(amount));
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
        } else {
        transactions.add(new Transaction(-amount));
        lastWithdraw = DateProvider.getInstance().localNow();
        }
    }


    /**

     * Calculate the interest rate. It should be called daily using the task in the bank

     * to calculate the interest earned and then deposit that money

    */

    public void addInterestEarned() {

        double interestRate = getInterestRate();

        double amount = sumTransactions();

        double interestRateDaily = interestRate / 365;

        double interestEarnedForDay = amount * interestRateDaily;

        deposit(interestEarnedForDay);
    }

    public double interestEarned() {
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
            case MAXI_SAVINGS:
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;
            default:
                return amount * 0.001;
        }
    }

    public double sumTransactions() {
       return checkIfTransactionsExist(true);
    }

    private double checkIfTransactionsExist(boolean checkAll) {
        double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    public AccountType getAccountType() {
        return accountType;
    }
    
    
    
    /**

    * Helper function to calculate the number of days between two dates.

    *

     * @param date1   first date

    * @param date2       second date

    * @return difference between the two dates in days

    */

    public static long getDateDiff(LocalDate date1, LocalDate date2) {

          return ChronoUnit.DAYS.between(date1, date2);

    }  
    /**
    * @return the interest rate based on the account type, amount and last withdraw date
    */

    private double getInterestRate() {
          double amount = sumTransactions();
          switch (accountType) {
          case SAVINGS:
                 if (amount <= 1000)
                        return 0.001;
                 else
                        return 0.002;
          case MAXI_SAVINGS:
                 if (lastWithdraw != null) {
                        LocalDate now = DateProvider.getInstance().localNow();
                        long days = getDateDiff(now, lastWithdraw);
                        if (days <= 10) {
                              return 0.001;
                        }
                 }
          default:
                 return 0.001;
          }
    }

}
