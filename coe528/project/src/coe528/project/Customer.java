package coe528.project;


/** Overview:
 * Customer class represents a customer of a bank. It holds information about the customer's username, 
 * password, bank account, and membership level. Customers can perform actions like withdrawing, depositing, 
 * and making online purchases. The class is mutable as it allows modification of the customer's 
 * account details.
 */


 /**
 * Abstraction Function:
 * - Represents a customer with a username, password, bank account, and membership level.
 * 
 * Rep Invariant:
 * - Username and password must not be null.
 * - Balance of the bank account must be non-negative.
 * - The level of the Customer must be:
 *      -Silver when balance in the back account is less than 10000
 *      -Gold when balance in the back account is less than 20000
 *      -Platnim when balance in the back account is >= 20000
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;



public class Customer {
    
    private String username;
    private String password;
    private BankAccount account1;
    private final String ROLE = "Customer";
    private Level lvl = new SilverLevel(this);

    private final String CUSTOMER_FILE_DIRECTORY = System.getProperty("user.dir").concat("/coe528/project/src/CustomerFiles/");
    

    public Customer(String usr,String pass,double balance){

        if(usr == null || pass == null || balance < 0 ){
            throw new IllegalArgumentException("Illegal Arg");
        }else{

            username = usr;
            password = pass;
            this.account1 = new BankAccount(balance);
            if(!repOk())
                throw new IllegalArgumentException("Illigal rep");

            
        }
    }

    /**
    * Effectd:Returns the username of the customer.
    * Modifies: dosent modify anything
    * Requires: dose not require anything
    *
    * @return username.
    */
    public String getUsername(){

        return username;
    }

    /**
    * Effects: Returns the password of the customer.
    * Modifies: dosent modify anything
    * Requires: dose not require anything
    *
    * @return  password.
    */
    public String getPassword(){
        return password;
    }

    /**
    * Modifies: Returns the role of the customer.
    * Modifies: dosent modify anything
    * Requires: dose not require anything 
    *
    * @return ROLE.
    */
    public String getRole(){
        return ROLE;
    }

    /**
    * Effects: Returns the balance of the customer's bank account.
    * Modifies: dosent modify anything
    * Requires: dose not require anything

    * @return account1.getBalance().
    */
    public double getBalance(){
        return account1.getBalance();
    }

    /**
    * Effects: Returns the customer's bank account.
    * Modifies: dosent modify anything
    * Requires: dose not require anything
    *
    * @return accoun1.
    */
    public BankAccount getAccount1(){

        return account1;

    }
    /**
    * Effects: Changes the customer's bank account to the given account.
    * Modifies: Sets the customer's bank account.
    * Requires: A BankAccount to set it to
    *
    * @param account The bank account to set.
    */
    public void setAccount(BankAccount account){
        account1 = account;
    }

    /**
    * Effects: Returns the membership level of the customer.
    * Modifies: dosent modify anything
    * Requires: dose not require anything 
    
    * @return membership level of the customer.
    */
    public Level getLevel(){
        return lvl;
    }

    /**
    * Effects: Changes the customer's membership level to the given level.
    * Modifies: Sets the membership level of the customer.
    * Requires: a Level to set it to 
    *
    * @param lvl The membership level to set.
    */
    public void setLevel(Level lvl){
        this.lvl = lvl;
    }

    /**
    * Effects: Returns the membership level from the string rep.
    * Modifies: dosent modify anything
    * Requires: @param s must be 'Gold','Silver',or 'Platinum' or else 
    * IllegalArgumentException is thrown.
    *
    * @param s The string representing the membership level.
    * @return The membership level corresponding to the given string.
    * @throws IllegalArgumentException If the given string does not match any membership level.
    */
    public Level getLevel(String s){

        if(s.equals("Silver"))
            return new SilverLevel(this);
        if(s.equals("Gold"))
            return new GoldLevel(this);
        if(s.equals("Platinum"))
            return new PlatinumLevel(this);
        else{
            throw new IllegalArgumentException("Dose not recognize the Level");
        }
    }
    /**
    * Effects: Returns the directory path for storing customer files.
    * Modifies: dosent modify anything
    * Requires: dose not require anything 
    *
    * @return CUSTOMER_FILE_DIRECTORY.
    */
    public String getdirectoryPath(){
        return CUSTOMER_FILE_DIRECTORY;
    }

    /**
    * Effects: Allows the customer to make an withdraw by calling 
    *          the withdraw method from the Level of the customer.
    * Modifies: Changes the balance of the customers BankAccount(account1)
    * Requires: a double ammount to withdraw
    *
    * @param pay The amount to withdraw.
    */
    public void withdraw(double pay){
        
        lvl.withdraw(pay);
        updateInfo();
    }
    /**
    * Effects: Allows the customer to make an deposit by calling 
    *          the deposit method from the Level of the customer.
    * Modifies: Changes the balance of the customers BankAccount(account1)
    * Requires: a double ammount to deposit
    *
    * @param ammount The amount to deposit.
    */
    public void deposit(double ammount){
        
        lvl.deposit(ammount);
        updateInfo();
    }

    /**
    * Effects: Allows the customer to make an online purchase by calling 
    *          the onlinepurchase method from the Level of the customer.
    * Modifies: Changes the balance of the customers BankAccount(account1)
    * Requires: a double ammount to online purchase  
    *
    * @param ammount The amount of the purchase.
    */
    public void onlinepurchase(double ammount){

        lvl.onlinepurchase(ammount);
        updateInfo();
    }

    /**
    * Effects: Updates the customer's information file in the CustomersFiles direct.
    * Modifies: Changes the customer's information file
    * Requires: no  Requirements 
    *
    * @return True if the update is successful, false otherwise.
    */
    private boolean updateInfo(){

        try {

            // Create a File object with the specified directory and file name
            File directory = new File(getdirectoryPath());

            // Create a File object
            File file = new File(directory,getUsername());

            // Create FileWriter object with append mode as false (to overwrite existing content)
            FileWriter writer = new FileWriter(file);

            // Write content to the file
            writer.write(toString());

            // Close the writer
            writer.close();

            System.out.println("File updated successfully!");
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while updating the file: " + e.getMessage());
            return false;
        }

    }

    
    /**
    * Effects: Returns a string representation of the customer.
    * Modifies: no Modification
    * Requires: no Requirements 
    * 
    * @return A string representation of the customer.
    */
    @Override
    public String toString(){
        return username + "\n" + password + "\n" + account1.toString() + "\n" + lvl.toString() + "\n" + getRole();
    }
    /**
    * Effetcs: Checks if this customer is equal to the given object.
    * Modifies: no Modification
    * Requires: an Object for commpairason
    *
    * @param obj The object to compare with.
    * @return True if this customer is equal to the given object, false otherwise.
    */
    @Override
    public boolean equals(Object obj) {

        if (this.getClass() == obj.getClass()) {

            Customer c = (Customer)obj;
            return this.username.equalsIgnoreCase(c.username);

        }else{
            return false;
        }
    }

    /**
    * Effects: Checks if the representation invariant holds for the Customer object.
    * Modifies: no Modification
    * Requires: no Requirements 
    *
    * @return True if the representation invariant holds, false otherwise.
    */
    private boolean repOk() {

        if( username != null && password != null && account1.getBalance() >= 0){
            if(getAccount1().getBalance() < 10000 &&  lvl.toString().equals("Silver"))
                return true;
            if(getAccount1().getBalance() < 20000 && getAccount1().getBalance() >= 10000 && lvl.toString().equals("Gold"))
                return true;
            if(getAccount1().getBalance() >= 20000 && lvl.toString().equals("Platinum"))
                return true;
            else
                return false;
        }else 
            return false;

    }
    

    public static void main(String[] args) {

        var d = new Customer("dagm", "Showtime", 100);
        System.out.println(d.repOk());
    
       
    }
}

