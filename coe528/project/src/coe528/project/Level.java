package coe528.project;


public abstract class Level {

    protected Customer Customer;
    
    public Level(Customer c){

        Customer = c;
    }

    public abstract void withdraw(double ammount);
    public abstract void deposit(double ammount);
    public abstract void onlinepurchase(double ammount);

    
    
}
