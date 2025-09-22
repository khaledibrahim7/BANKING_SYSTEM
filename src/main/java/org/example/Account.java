package org.example;

import org.example.exception.InvaliDepositAmount;
import org.example.exception.InvalidWithdrawAmount;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;

public class Account implements Serializable {
    private  int id ;
    private double balance ;

    private final ReentrantLock lock = new ReentrantLock();

    public Account(double balance, int id) {
        this.balance = balance;
        this.id = id;
    }

    public int getId() {

        return id;
    }

    public double getBalance() {
        lock.lock();
        try {

            return balance;

        }finally {
            lock.unlock();
        }

    }

   public void deposit(double amount) throws InvaliDepositAmount {
       try {
           lock.lock();

           if (amount > 0) {
               balance += amount;
               System.out.println("Successfully deposit: " + amount + "  balance With id: " +id +" "+ balance);
           } else
               throw new InvaliDepositAmount("Invalid deposit amount");
       }finally {
           lock.unlock();
       }
   }
   public void withdraw(double amount)throws InvalidWithdrawAmount {
       try {

           lock.lock();
           if (balance < amount) {
               throw new InvalidWithdrawAmount("Invalid withdraw amount!");
           } else
               balance -= amount;
           System.out.println("Successfully withdraw " + amount + "  Balance With id: " +id +" " + balance);
       }finally {
           lock.unlock();
       }
   }

   public void transfer(Account account , double amount) throws InvalidWithdrawAmount, InvaliDepositAmount {

           Account firstLock = this.id < account.id ? this : account;
           Account secondLock = this.id < account.id ? account : this;
try {


    firstLock.lock.lock();
    secondLock.lock.lock();

    this.withdraw(amount);
    this.deposit(amount);

    System.out.println("Successfully transferred " + amount +
            " from Account " + this.id +
            " to Account " + account.id +
            ". New balance: " + this.balance);
}finally {
    firstLock.lock.unlock();
    secondLock.lock.unlock();
}
   }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
