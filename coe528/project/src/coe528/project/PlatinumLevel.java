package coe528.project;

public class PlatinumLevel extends Level{


    public PlatinumLevel(Customer c){
        super(c);
    }

    public void deposit(double ammount){

        if(ammount < 0){
            throw new IllegalArgumentException("Cannot add -tiv ammount");
        }else{
            Customer.getAccount1().setBalance(Customer.getBalance() + ammount);
        }
    }

    public void withdraw(double ammount){

        if(ammount > Customer.getBalance() || ammount < 0){
            throw new IllegalArgumentException("Insufficient balance");
        }else{
            Customer.getAccount1().setBalance(Customer.getBalance() - ammount);
        }

        if(Customer.getBalance() < 20000){

            //make level to Gold
            Customer.setLevel(new GoldLevel(Customer));
        }
        if(Customer.getBalance() < 10000){

            //make level to Gold
            Customer.setLevel(new SilverLevel(Customer));
        }
    }

    public void onlinepurchase(double ammount){

        if(ammount < 50){
            throw new IllegalArgumentException("Insufficient balance or ammount");
         }else{
            withdraw(ammount);
        }

    }

    @Override
    public String toString(){
        return "Platinum";
    }
    
}
