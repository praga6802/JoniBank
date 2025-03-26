package com.jonibank;
import com.joniatm.ATMOperations;

public class CurrentAccount extends BankOperations {

    // IMPORTANT FEATURE: MINIMUM BALANCE IS REQUIRED IN CURRENT ACCOUNT

    public final static double MIN_BALANCE=5000;

    //current account constructor for bank branch
    public CurrentAccount(String accountHolderName, String accountNumber, double balance, String branch_name, String IFSC_code, String contactno) {
        super(accountHolderName,accountNumber, balance, branch_name, IFSC_code, contactno);
    }

    //current account constructor for bank atm
    public CurrentAccount(String accountHolderName, String accountNumber, double balance, String branch_name, String IFSC_code,String contactno, String atmpin) {
        super(accountHolderName,accountNumber, balance, branch_name, IFSC_code,contactno, atmpin);
    }

    //overriding withdrawn method for current account
    public void withdrawAmount(double amount) {
        if(balance-amount <= MIN_BALANCE) {
            System.out.println("Cannot withdraw Amount -- Maintain Minimum Balance: ₹"+MIN_BALANCE+" is required for current account");
            System.out.println("Current Balance="+balance);
        }
        else {
            super.withdrawAmount(amount);
        }
    }

    public void calculateInterestRate() {
        System.out.println("No Interest Rate for Current Account Users");
    }

}
