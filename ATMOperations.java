package com.joniatm;
import com.jonibank.CurrentAccount;
import com.jonibank.SavingsAccount;
import com.jonibank.JoniBank;

import javax.swing.*;
import java.util.Scanner;


public class ATMOperations extends JoniATM{
    Scanner sc= new Scanner(System.in);
    CurrentAccount ca;
    SavingsAccount sa;
    JoniBank j;

    private static final int MAX_ATTEMPTS=3;
    String lastOperation="No Operation";

    public ATMOperations(CurrentAccount ca){
        super(ca.getAccountHolderName(), ca.getAccountNumber(), ca.getBalance(),ca.getBranch_name(),ca.getIFSC_code(), ca.getContactno(), ca. getAtmpin());
        this.ca=ca;
    }

    public ATMOperations(SavingsAccount sa){
        super(sa.getAccountHolderName(), sa.getAccountNumber(), sa.getBalance(),sa.getBranch_name(),sa.getIFSC_code(), sa.getContactno(), sa. getAtmpin());
        this.sa=sa;
    }

    //withdraw amount for ATM
    public void withdrawAmount(double amount) {
        System.out.println("PIN number verifying..");
        String verify_atmpin = super.verifyATMPin(); //storing atmpin from verifyatmpin()
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

    //change ATM Pin in ATM @override changeATMPin() in ATMOperations()
    public void changeATMPin() {
        System.out.println("To change ATM Pin..");
        int attempts = 1;
        //Enter accno and ifsc code
        while (attempts <= MAX_ATTEMPTS) {
            System.out.println("Enter your JONI Bank Account Number: ");
            String acno = sc.next();  // Read account number
            System.out.println("Enter your Mobile Number: ");
            String mob_no = sc.next();

            //checking if the both acno and ifsc code is correct or not
            if (acno.equals(getAccountNumber()) && mob_no.equals(getContactno())) {
                System.out.println("Enter your Existing ATM Pin: ");
                String epin = sc.next(); //getting from the user
                sc.nextLine();
                String atm_pin = getAtmpin(); //exisiting atmpin

                //exisiting pin and saved pin is same or not
                if (epin.equals(atm_pin)) {
                    System.out.println("Enter your New ATM Pin: ");
                    String npin = sc.next().trim();
                    System.out.println("Re Enter your New ATM Pin: ");
                    String rnpin=sc.next().trim();
                    //checking the length of new ATM Pin
                    if (npin.length() == 4) {

                        //checking the new pin is matched with existing pin
                        if (epin.equals(npin)) {
                            System.out.println("Previous Pin should not matched with New Pin");
                        }
                        else {
                            if (npin.equals(rnpin)) {
                                atm_pin = npin;
                                System.out.println("ATM Pin has been successfully changed..");
                                break;
                            }
                            else {
                                System.out.println("Re-Enter ATM Pin is not matching");
                            }
                        }
                    }
                    else {
                        System.out.println("Length of ATM Pin is Invalid!");
                    }
                } else {
                    System.out.println("Incorrect ATM Pin!");
                }
            }
            else{
                System.out.println("Please Enter correct credentials!");
                System.out.println("Attempts left: "+(MAX_ATTEMPTS-attempts));
                attempts++;
            }
        }
        if (attempts >= MAX_ATTEMPTS) {
            System.out.println("Maximum Attempts reached..Please try again later!");
            return;
        }
    }


    int decision;
    double damount;
    double wamount;
        //ATM related operations
    public void atmOperations() {
        boolean exit=false;
            char con=' ';
            do{
                System.out.println("-- JONI ATM Operations --");
                System.out.println("Press 1->> Deposit Amount, Press 2--> WithDraw Amount, Press 3--> Check Balance, Press 4 --> Calculate Interest, Press 5 --> Account Details, Press 6 --> Change Phone Number, Press 7 --> Change ATM Pin , Press 8 --> Exit ");
                decision = sc.nextInt();
                switch (decision) {
                    case 1:
                        System.out.println("Enter the amount to be deposit: ");
                        damount = sc.nextDouble();
                        super.depositAmount(damount);
                        lastOperation="Deposit Amount";
                        break;

                    case 2:
                        if(ca instanceof CurrentAccount) {
                            if (super.getBalance() <= CurrentAccount.MIN_BALANCE && wamount <= CurrentAccount.MIN_BALANCE) {
                                System.out.println("Initial balance is already LOW! You can't withdraw. Deposit some amount to continue withdrawal.");
                            }
                            else {
                                if (balance > 0) {
                                    System.out.println("Enter the amount to be withdraw: ");
                                    double wamount = sc.nextDouble();
                                    ca.withdrawAmount(wamount);
                                    lastOperation = "WithDraw Amount";
                                }
                                else {
                                    System.out.println("InSufficient balance. Deposit Some Amount to continue withdraw");
                                }
                            }
                        }
                        else{
                            if (balance > 0) {
                                System.out.println("Enter the amount to be withdraw: ");
                                double wamount = sc.nextDouble();
                                withdrawAmount(wamount);
                                lastOperation = "WithDraw Amount";
                            }
                            else {
                                System.out.println("InSufficient balance. Deposit Some Amount to continue withdraw");
                            }
                        }
                        break;
                    case 3:
                        if(ca instanceof CurrentAccount){
                            ca.checkBalance();
                            lastOperation="Check Balance";
                        }
                        else{
                            sa.checkBalance();
                            lastOperation="Check Balance";
                        }
                        break;

                    case 4:
                        if(ca instanceof CurrentAccount) {
                            ca.calculateInterestRate();
                            lastOperation="Calculate Interest";
                        }
                        else{
                            sa.calculateInterestRate();
                            lastOperation="Calculate Interest";
                        }
                        break;

                    case 5:
                        displayAccountDetails();
                        lastOperation="Display Account Details";
                        break;

                    case 6:
                        changePhoneNumber();
                        lastOperation="Change Mobile Number";
                        break;

                    case 7:
                        changeATMPin();
                        lastOperation="Change ATM Pin";
                        break;

                    case 8:
                        exit=true;
                        System.out.println("You have choose Exit Option!");
                        return;

                    default:
                        System.out.println("Invalid Option.. Please try again");
                }
                System.out.println("Do you want to continue.. Press (Y)--> Yes and Press (N)--> No");
                con = sc.next().charAt(0);
            } while (con == 'Y' || con == 'y');

            char receipt=' ';
            System.out.println("Do you want Transaction Receipt? Press (Y)--> Yes and Press (N)--> No");
            receipt=sc.next().charAt(0);
            if(receipt=='y' || receipt=='Y'){
                returnReceipt();
            }
            else{
                System.out.println("Thank You for using JONI ATM!");
            }
        }

    public void returnReceipt(){
            System.out.println("Welcome to Joni Bank ATM");
            System.out.println("=================================");
            System.out.println("Bank Name: "+bank_name);
            System.out.println("Account Holder Name: "+super.accountHolderName);
            System.out.println("Account Number: "+super.accountNumber);
            if(lastOperation.equals("WithDraw Amount")){
                System.out.println("Operation You Have Done: "+lastOperation);
                System.out.println("Withdrawn Amount: "+wamount);
            }
            else if(lastOperation.equals("Deposit Amount")){
                System.out.println("Operation You Have Done: "+lastOperation);
                System.out.println("Deposited Amount: "+damount);
            }
            else{
                System.out.println("Operation You Have Done: "+lastOperation);
            }

            System.out.println("Current Balance= "+balance);
            System.out.println("==================================");
            System.out.println("THANK YOU!");
        }
}

