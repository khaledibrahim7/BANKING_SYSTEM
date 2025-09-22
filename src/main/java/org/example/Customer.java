package org.example;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

public class Customer implements Serializable{

    private String name ;
    private String phoneNumber ;
    private Set<Account> accounts = new LinkedHashSet<>();

    public Customer(String phoneNumber, String name) {
        this.phoneNumber = phoneNumber;
        this.name = name;
    }


    public void addAccount(Account account){
        accounts.add(account);

    }

    public void removeAccount(Account account){
        accounts.remove(account);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", accounts=" + accounts +
                '}';
    }
}
