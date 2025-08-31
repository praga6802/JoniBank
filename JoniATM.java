package com.joniatm;
import com.jonibank.*;
import java.util.Scanner;

public class JoniATM extends BankOperations{
    Scanner sc=new Scanner(System.in);

    public JoniATM(String accountHolderName, String accountNumber, double balance,String branch_name, String IFSC_code, String contactno, String atmpin){
        super(accountHolderName,accountNumber,balance,branch_name,IFSC_code,contactno,atmpin);
    }

    //verify atmpin
    public String verifyATMPin(){
        Scanner sc= new Scanner(System.in);
        //for withdrawal option
        System.out.println("Enter your Joni ATM PIN Number:");
        String atmpin=sc.next();
        String atm_pin= getAtmpin(); //from constructor
        if(atmpin.length()==4) {
            if (atmpin.equals(atm_pin)) {
                String ac_name = getAccountHolderName();
                System.out.println("Account Holder: " + ac_name);
            }
        }
        else{
            System.out.println("Invalid ATM PIN");
        }
        return atmpin;
    }
}
