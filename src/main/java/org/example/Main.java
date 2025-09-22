package org.example;

import org.example.exception.InvaliDepositAmount;
import org.example.exception.InvalidWithdrawAmount;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws InvalidWithdrawAmount {


        Account account1 = new Account(1000, 1);
        Account account2 = new Account(1500, 2);
        Account account3 = new Account(14000, 3);
        Account account4 = new Account(1500000, 4);


        System.out.println(account1.getBalance());

        Customer customer = new Customer("01023456874", "khaled");
        Customer customer1 = new Customer("0111023515581", "Asmaa");
        Customer customer2 = new Customer("010602534153", "Sarah");
        Customer customer3 = new Customer("0111140003152", "Amira");

        customer1.addAccount(account2);
        customer1.addAccount(account4);
        customer2.addAccount(account3);
        customer3.addAccount(account1);

        Bank bank = new Bank();
        bank.addAccount(account4);
        bank.addAccount(account1);
        ;
        bank.addAccount(account2);
        bank.addAccount(account3);

        bank.addCustomer(customer);
        bank.addCustomer(customer1);
        bank.addCustomer(customer2);
        bank.addCustomer(customer3);

        try {
            account1.deposit(-1);

        } catch (Exception e) {
            System.out.println(e);

        }

        try {
            account2.withdraw(200);
        } catch (Exception e) {
            System.out.println(e);
        }

        System.out.println("\n\n");
        bank.displayAccount();
        bank.displayCustomer();
        bank.removeAccount(account1);
        bank.removeCustomer(customer3);

        bank.saveData("Bank.dat");

        Bank loadedBank = Bank.loadData("Bank.dat");
        System.out.println("\nAfter Loading:");
        loadedBank.displayCustomer();
        loadedBank.displayAccount();

        System.out.println("\n\ntransfer To Account");

        try {
            account2.transfer(account3, 500);
            account1.transfer(account2 , 600);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\n\n  Multithreading Test:");

        Runnable task3 = () -> {
            try {
                account2.withdraw(300);
            } catch (InvalidWithdrawAmount e) {
                System.out.println(e.getMessage());
            }
        };

        Runnable task1 = () -> {
            try {
                account2.deposit(500);
            } catch (InvaliDepositAmount e) {
                System.out.println(e.getMessage());
            }
        };
        Runnable task2 = () -> {
            try {
                account2.deposit(3000);
            } catch (InvaliDepositAmount e) {
                System.out.println(e.getMessage());
            }
        };


        Runnable task4 = () -> {
            try {
                account2.withdraw(200);
            } catch (InvalidWithdrawAmount e) {
                System.out.println(e.getMessage());
            }
        };

        Thread t1 = new Thread(task1, "Thread-Deposit1");
        Thread t2 = new Thread(task2, "Thread-Withdraw1");
        Thread t3 = new Thread(task3, "Thread-Withdraw2");
        Thread t4 = new Thread(task4, "Thread-Deposit1");
        t1.start();
        t2.start();
        t3.start();
        t4.start();



    }

}