package coe528.project;

public class BankAccount {

    private double balance;
    private Customer customer;

    public BankAccount(double ammount){

        if(ammount < 0){
            throw new IllegalArgumentException("Illegal Arg");
        }else{
            balance = ammount;
        }
    }
    public BankAccount(){

        balance = 100;

    }

    public double getBalance(){

        return balance;
    }

    public void setBalance(double ammount){

        if(ammount < 0){
            throw new IllegalArgumentException("Illegal Arg");
        }else{
            balance = ammount;
        }

    }

    public Customer getCustomer(){
        return customer;
    }

    public void withdraw(double pay){
        
        customer.getLevel().withdraw(pay);
       
    }

    public void deposit(double ammount){
        
        customer.getLevel().deposit(ammount);
        
    }

    public void onlinepurchase(double ammount){

        customer.getLevel().onlinepurchase(ammount);
        
    }

    @Override
    public String toString(){
        return Double.toString(balance);
    }
    
}
