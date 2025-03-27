package com.jonibank;
import com.joniatm.ATMOperations;
import com.jonibank.JoniBank;

import static java.beans.Beans.isInstanceOf;

public class BankOperations extends JoniBank{
    //Bank Operations Constructor- bank branch
    public BankOperations(String accountHolderName, String accountNumber, double balance,String branch_name, String IFSC_code, String contactno){
        super(accountHolderName,accountNumber,balance,branch_name,IFSC_code,contactno);
    }

    //Bank Operations Constructor- bank atm
    public BankOperations(String accountHolderName, String accountNumber, double balance,String branch_name, String IFSC_code, String contactno, String atmpin){
        super(accountHolderName,accountNumber,balance,branch_name,IFSC_code,contactno);
        this.atmpin=atmpin;
    }

    public void depositAmount(double amount) {

        System.out.println("Account Number verifying...");
        String verify_no=super.verifyAccountNumber();
        if(verify_no.equals(accountNumber)) {
            if (amount > 0) {
                balance = balance + amount;
                System.out.println("₹" + amount + " has been deposited successfully");
                System.out.println("Current Balance="+balance);

            } else {
                System.out.println("Please Enter Valid Amount!");
            }
        }
        else{
            System.out.println("Invalid Account Number! Please Re-Enter Again..");
        }
    }

    //withdraw method() savings account
    public void withdrawAmount(double amount){
        System.out.println("Account Number verifying...");
        String verify_no=super.verifyAccountNumber();
        if(verify_no.equals(accountNumber)) {
            if (amount <= 0) {
                System.out.println("Please Enter Valid Amount!");
            }
            else if (amount > 0 && amount <= balance) {
                balance = balance - amount;
                System.out.println("₹" + amount + " withdrawn successfully");
                System.out.println("Current Balance=" + balance);
            }
            else {
                System.out.println("Insufficient balance!");
            }
        }
        else{
            System.out.println("Invalid Account Number! Please Re-Enter Again..");
        }
    }

    public void checkBalance(){
        System.out.println("Current Balance="+balance);
    }

    //display AccountDetails
    public void displayAccountDetails() {
        System.out.println("\n--Account Details--");
        System.out.println("Bank Name: "+bank_name);
        System.out.println("Branch Name: "+branch_name);
        System.out.println("Account Holder Name: "+accountHolderName);
        System.out.println("Account Number: "+accountNumber);

        System.out.println("IFSC Code: "+IFSC_code);
        System.out.println("Mobile Number: "+contactno);
        System.out.println("Current Balance: "+"₹"+balance);
    }

    //view mini statement
   /* public void viewMiniStatement(){
        System.out.println("------- MINI STATEMENT ------");
        System.out.println("Account Holder Name: "+accountHolderName);
        System.out.println("Account Number: "+accountNumber);
        if(balance>=0){
            System.out.println("")
        }
    }*/

    CurrentAccount ca;
    BankMain j;
    //bank related operations
    public void bankOperations() {
        boolean exit = false;
        char con;
        do{
            System.out.println("-- JONI Banking Operations --");
            System.out.println("Press 1->> Deposit Amount, Press 2--> WithDraw Amount, Press 3--> Check Balance, Press 4 --> Calculate Interest, Press 5 --> Account Details, Press 6 --> Change Phone Number, Press 7--> Exit");
            System.out.println("Enter your option: ");
            int boption = sc.nextInt();
            switch(boption) {
                case 1:
                    System.out.println("Enter the amount to be deposit: ");
                    double damount = sc.nextDouble();
                    depositAmount(damount);
                    break;

                case 2:
                    //suitable only for current account - checking the initial balance is less than minimum balance or not
                    if ((this instanceof CurrentAccount) && j.amount <= CurrentAccount.MIN_BALANCE  && this.balance <= CurrentAccount.MIN_BALANCE)
                        System.out.println("Initial balance is already LOW! You can't withdraw. Deposit some amount to continue withdrawal.");
                    else{
                        if(balance > 0) {
                            System.out.println("Enter the amount to be withdraw: ");
                            double wamount = sc.nextDouble();
                            withdrawAmount(wamount);
                        }
                        else{
                            System.out.println("InSufficient balance. Deposit Some Amount to continue withdraw");
                        }
                    }
                    break;

                case 3:
                    checkBalance();
                    break;

                case 4:
                    calculateInterestRate();
                    break;

                case 5:
                    displayAccountDetails();
                    break;

                case 6:
                    changePhoneNumber();
                    break;

                case 7:
                    exit=true;
                    System.out.println("You have choose Exit Option!");
                    break;

                default:
                    System.out.println("Invalid Option! Please try Again!");
            }
            System.out.println("Do you want to continue.. Press (Y)--> Yes and Press (N)--> No");
            con = sc.next().charAt(0);
        } while (con == 'Y' || con == 'y');
        System.out.println("Thank You for Banking with us!");
    }
}
