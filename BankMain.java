package com.jonibank;
import com.joniatm.*;
import java.util.Scanner;

public class BankMain {
    static BankOperations bank;
    static ATMOperations atm;
    static double amount;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Account Holder Name: ");
        String name = sc.nextLine();
        String ac_no;
        System.out.println("Enter 16-Digit Account Number: ");
        ac_no = sc.nextLine();
        if (ac_no.length() != 16) {
            System.out.println("Length of Account Number is Invalid");
            return;
        }
        System.out.println("Enter the initial Balance: ");
        amount = sc.nextDouble();
        sc.nextLine();

        System.out.println("Enter the branch Name: ");
        String branch_name = sc.nextLine();

        System.out.println("Enter the IFSC Code: ");
        String ifsc_code = sc.nextLine();

        System.out.println("Enter your mobile Number");
        String contactno= sc.nextLine();
        if (contactno.length() != 10) {
            System.out.println("Length of Mobile Number is Invalid");
            return;
        }

        System.out.println("Select where you want to Access Accounts:");
        System.out.println("Press 1 --> Bank Branch, Press 2 --> Bank ATM");
        int option = sc.nextInt();
        sc.nextLine();

        switch (option) {
            //case Bank Branch
            case 1:
                System.out.println("Select Account Type : 1.Savings Account, 2.Current Account");
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1: //savings account
                        System.out.println("You entered into Savings Account");
                        bank = new SavingsAccount(name, ac_no, amount, branch_name, ifsc_code,contactno);
                        bank.bankOperations();
                        break;
                    case 2: //current account
                        System.out.println("You entered into Current Account");
                        bank = new CurrentAccount(name, ac_no, amount, branch_name, ifsc_code,contactno);
                        bank.bankOperations();
                        break;
                    default:
                        System.out.println("Invalid Option! Enter the correct option");
                }
            break;

            // case  Bank ATM
            case 2:
                System.out.println("Welcome to Joni Bank ATM");
                System.out.println("Please Insert you ATM Card..");
                System.out.println("Enter 4-digit ATM Pin");
                String atm_pin = sc.next();

                System.out.println("Select Account Type : 1.Savings Account, 2.Current Account");
                int atmchoice = sc.nextInt();
                sc.nextLine();
                switch (atmchoice) {
                    case 1:
                        System.out.println("You entered into Savings Account");
                        SavingsAccount sa= new SavingsAccount(name, ac_no, amount, branch_name, ifsc_code, contactno,atm_pin);
                        atm = new ATMOperations(sa);
                        atm.atmOperations();
                        break;
                    case 2:
                        System.out.println("You entered into Current Account");
                        CurrentAccount ca= new CurrentAccount(name, ac_no, amount, branch_name, ifsc_code,contactno, atm_pin);
                        atm = new ATMOperations(ca);
                        atm.atmOperations();
                        break;
                    default:
                        System.out.println("Invalid Option! Enter the correct Option!");
                }
                break;
            default:
                System.out.println("Invalid Option! Enter the correct Option!");
            }
        sc.close();
    }
}
