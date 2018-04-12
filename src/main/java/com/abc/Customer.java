package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) {
        accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    
    /**

     * Transfer from one account to another

     * @param from      The account to transfer from

     * @param to     The account to transfer to

     * @param amount    The amount of money to transfer

     */

    public void Transfer(Account from, Account to, double amount) {
    	
               double balance = from.sumTransactions();
               if (amount <= balance) {
                              from.withdraw(amount);
                              to.deposit(amount);
               } else {
                              throw new IllegalArgumentException("Insufficient fund");
               }
    }

 

    /*

     * This will add the interest earned to every account

     */
    public void depositInterestEarned() {
               for (Account acc: accounts) {
                              acc.addInterestEarned();
               }
    }
    
    
    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        String statement = null;
        statement = "Statement for " + name + "\n";
        double total = 0.0;
        for (Account a : accounts) {
            statement += "\n" + statementForAccount(a) + "\n";
            total += a.sumTransactions();
        }
        statement += "\nTotal In All Accounts " + toDollars(total);
        return statement;
    }

    private String statementForAccount(Account a) {
        	StringBuilder s = new StringBuilder();
    	    
       //Translate to pretty account type
        switch(a.getAccountType()){
            case CHECKING:
            	    s.append("Checking Account\n");
                break;
            case SAVINGS:
            	    s.append("Savings Account\n");
                break;
            case MAXI_SAVINGS:
            	    s.append("Maxi Savings Account\n");
                break;
        }

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            s.append("  " + (t.amount < 0 ? "withdrawal" : "deposit") + " " + toDollars(t.amount) + "\n");
            total += t.amount;
        }
        s.append("Total " + toDollars(total));
        return s.toString();
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
}
