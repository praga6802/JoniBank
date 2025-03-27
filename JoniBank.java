package com.jonibank;
import javax.swing.*;
import java.util.Scanner;
public class JoniBank extends BankAdapter {
    //data hiding
    protected final static String bank_name = "Joni Bank";
    protected String accountHolderName;
    protected String accountNumber;
    protected double balance;
    protected String IFSC_code;
    protected String branch_name;
    protected String atmpin;
    protected String contactno;
    Scanner sc = new Scanner(System.in);

    //Constructor - Bank
    public JoniBank(String accountHolderName, String accountNumber, double balance, String branch_name, String IFSC_code, String contactno) {
        this.accountHolderName = accountHolderName;
        this.branch_name = branch_name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.IFSC_code = IFSC_code;
        this.contactno = contactno;
    }

    //constructor - ATM
    public JoniBank(String accountHolderName, String accountNumber, double balance, String branch_name, String IFSC_code, String contactno, String atmpin) {
        this.accountHolderName = accountHolderName;
        this.branch_name = branch_name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.IFSC_code = IFSC_code;
        this.contactno = contactno;
        this.atmpin = atmpin;
    }

    //Encapsulation - getter() and setter() methods
    //getters()
    public String getAccountHolderName() {
        return accountHolderName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getIFSC_code() {
        return IFSC_code;
    }

    public String getBranch_name() {
        return branch_name;
    }

    public String getContactno() {
        return contactno;
    }

    public String getAtmpin() {
        return atmpin;
    }

    //setters()
    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setIFS_Code(String IFSC_code) {
        this.IFSC_code = IFSC_code;
    }

    public void setBranch_name(String branch_name) {
        this.branch_name = branch_name;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public void setAtmPin(String atmPin) {
        this.atmpin = atmpin;
    }

    //verify details()
    public String verifyAccountNumber() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your Joni Bank Account Number:");
        String acno = sc.nextLine();
        String ac_no = getAccountNumber();
        if (acno.length() == 16) {
            if (acno.equals(ac_no)) {
                String ac_name = getAccountHolderName();
                System.out.println("Account Holder: " + ac_name);
            }
        } else {
            System.out.println("Length of Account Number is Invalid");
        }
        return acno;
    }

    public void changePhoneNumber() {
        final int MAX_ATTEMPTS = 3;
        int attempts = 1;
        System.out.println("To change Phone Number..");
        System.out.println("Account Number verifying...");
        while (attempts <= MAX_ATTEMPTS) {
            String verify_no = verifyAccountNumber();
            if (verify_no.equals(accountNumber)) {
                System.out.println("Enter your New Mobile Number: ");
                String contact_no = sc.next();
                System.out.println("Re-Enter your New Mobile Number: ");
                String rcontact_no = sc.next();
                if (contact_no.length() == 10) {
                    if (contact_no.equals(contactno)) {
                        System.out.println("Already registered with \"" + contact_no + "\" mobile number");
                    } else {
                        if (rcontact_no.equals(contact_no)) {
                            System.out.println("Mobile Number has been changed from \"" + contactno + "\" to \"" + contact_no + "\"");
                            contactno = contact_no;
                            break;
                        } else {
                            System.out.println("Re- Entered Mobile number is not same..");
                        }
                    }
                }
                else {
                    System.out.println("Length of Mobile Number is Invalid!");
                }
            }
            else {
                System.out.println("Invalid Account Number");
                System.out.println("Attempts Left: " + (MAX_ATTEMPTS - attempts));
                attempts++;
            }
        }
        if(attempts>=MAX_ATTEMPTS){
            System.out.println("Maximum limit reached! Please try again later..");
        }
    }
}
