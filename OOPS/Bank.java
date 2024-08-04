import java.util.Scanner;
import java.util.ArrayList;

interface Transaction{
    void addTransactions(String decription,double amount);
    void displayTransactions();
}

class Account implements Transaction{
    private String account_number;
    private String account_holder;
    protected double balance;
    private ArrayList<String> transactions;

    public Account(String account_number,String account_holder){
        this.account_number = account_number;
        this.account_holder = account_holder;
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }
}
