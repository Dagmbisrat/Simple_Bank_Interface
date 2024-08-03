package coe528.project;

public class SilverLevel extends Level {

    public SilverLevel(Customer c){
        super(c);
    }

    public void deposit(double ammount){

        if(ammount < 0){
            throw new IllegalArgumentException("Cannot add -tiv ammount");
        }else{
            Customer.getAccount1().setBalance(Customer.getBalance() + ammount);
        }

        if(Customer.getBalance() >= 20000){

            //make level to Platnum
            Customer.setLevel(new PlatinumLevel(Customer));

        }
        else if(Customer.getBalance() >= 10000){
            //make level Gold
            Customer.setLevel(new GoldLevel(Customer) );
        }
    }

    public void withdraw(double ammount){

        if(ammount > Customer.getBalance() || ammount < 0){
            throw new IllegalArgumentException("Insufficient balance");
        }else{
            Customer.getAccount1().setBalance(Customer.getBalance() - ammount);
        }
    }

    public void onlinepurchase(double ammount){

        if(ammount < 50){
            throw new IllegalArgumentException("Insufficient balance or ammount");
         }else{
            withdraw(ammount + 20);
        }

        

    }

    @Override
    public String toString(){
        return "Silver";
    }
}
