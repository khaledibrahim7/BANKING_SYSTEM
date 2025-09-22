package org.example;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.io.FileDescriptor.in;


public class Bank implements Serializable {

    public void addCustomer(Customer customer) {
        String sql = "INSERT INTO Customers (phoneNumber, name) VALUES (?, ?)";
        ConnectionDataBase.executeQ(sql, customer.getPhoneNumber(), customer.getName());
        System.out.println("\nYou have been added to the bank as a customer: " + customer);
    }

    public void removeCustomer(Customer customer) {
        String sql = "DELETE FROM Customers WHERE phoneNumber = ?";
        ConnectionDataBase.executeQ(sql, customer.getPhoneNumber());
        System.out.println("Removing customer: " + customer);
    }

    public void addAccount(Account account) {
        String sql = "INSERT INTO Accounts (id, balance) VALUES (?, ?)";
        ConnectionDataBase.executeQ(sql, account.getId(), account.getBalance());
        System.out.println("Account added: " + account);
    }

    public void removeAccount(Account account) {
        String sql = "DELETE FROM Accounts WHERE id = ?";
        ConnectionDataBase.executeQ(sql, account.getId());
        System.out.println("Removing account: " + account);
    }

    public void displayCustomer() {
        String sql = "SELECT * FROM Customers";
        ConnectionDataBase.runSelect(sql);
    }

    public void displayAccount() {
        String sql = "SELECT * FROM Accounts";
        ConnectionDataBase.runSelect(sql);
    }

    public void saveData(String fileName){
        try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(fileName))) {
            ous.writeObject(this);
            System.out.println("Bank data saved successfully ✅");
        } catch (IOException e) {
        }
    }

    public  static Bank loadData(String fileName) {
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Bank) ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return new Bank();
        }
    }
 }
