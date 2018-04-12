package com.abc;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Transaction {
    public final double amount;

    private LocalDate transactionDate;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionDate = DateProvider.getInstance().localNow();
    }

}
