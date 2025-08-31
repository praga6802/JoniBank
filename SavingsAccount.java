package com.jonibank;

import com.joniatm.ATMOperations;

//inheritance
public class SavingsAccount extends BankOperations {
    // IMPORTANT FEATURE:  INTEREST RATE IS CALCULATED IN SAVINGS ACCOUNT
    private final static double INTEREST_RATE=4.0;

    //savings account constructor for bank branch
    public SavingsAccount(String accountHolderName, String accountNumber, double balance, String branch_name, String IFSC_code, String contactno) {
        super(accountHolderName,accountNumber, balance, branch_name, IFSC_code,contactno);
    }

    //savings account constructor for bank atm
    public SavingsAccount(String accountHolderName, String accountNumber, double balance, String branch_name, String IFSC_code,String contactno, String atmpin) {
        super(accountHolderName,accountNumber, balance, branch_name, IFSC_code, contactno,atmpin);
    }

    //polymorphism- method overriding
    public void calculateInterestRate() {
        double interest =(balance * INTEREST_RATE)/100;
        balance=balance + interest;
        System.out.println("Interest of ₹ "+interest+" added to savings account");
        System.out.println("Current Balance: "+balance);
    }

}
