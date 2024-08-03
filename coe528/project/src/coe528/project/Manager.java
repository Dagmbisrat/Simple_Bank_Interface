package coe528.project;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class Manager {
    
    private final String USERNAME = "admin";
    private final String PASSWORD = "admin";
    private final String ROLE = "manager";
    private static ArrayList<Customer> accounts = new ArrayList<>();

    private static Manager manager;


    private Manager(){ 

        String directoryPath = System.getProperty("user.dir").concat("/coe528/project/src/CustomerFiles/");
        File directory = new File(directoryPath);

        // Check if the directory exists
        if (directory.exists() && directory.isDirectory()) {

            // Get an array of all the files in the directory
            File[] files = directory.listFiles();
            
            // Iterate over the array of files and print out their names
            if (files != null) {
                for (File file : files) {

                    //for mac users
                    if (!file.getName().equals(".DS_Store")) {
                        
                        String usr,pass;
                        double cash;
    
                        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                            
                            usr = reader.readLine();
                            pass = reader.readLine();
                            cash = Double.parseDouble(reader.readLine());
                        
    
                            var c = new Customer(usr,pass,cash);
                            c.setLevel(c.getLevel(reader.readLine()));
                            accounts.add(c);

    
                        } catch (IOException e) {
                            e.printStackTrace(); // Handle file reading error
                            System.out.println("didnt work");
                        }catch(IllegalArgumentException e){
                            e.toString();
                        }
                    
                    }

                
                    
                }
            }
        } else {
            System.out.println("Directory does not exist or is not a directory.");
        }

    }

    public static Manager getInstance() {
        
        // Create the instance if it doesn't exist
        if (manager == null) {
            manager = new Manager();
        }
        return manager;
    }

    public String getUsername(){
        return USERNAME;
    }
    public String getPassword(){
        return PASSWORD;
    }
    public String getRole(){
        return ROLE;
    }
    public ArrayList<Customer> getAccounts(){
        return accounts;
    }
    public boolean authenthicate(String usr, String pass){
        return (usr.equals(USERNAME) && pass.equals(PASSWORD));
    } 
    public boolean addCustomer(Customer c){

       if(!accounts.contains(c)){
        try {

            // Create a File object with the specified directory and file name
            File directory = new File(c.getdirectoryPath());

            // Create a File object
            File file = new File(directory,c.getUsername());

            // Create FileWriter object with append mode as false (to overwrite existing content)
            FileWriter writer = new FileWriter(file);

            // Write content to the file
            writer.write(c.toString());

            // Close the writer
            writer.close();

            System.out.println("File created successfully!");
            accounts.add(c);
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file: " + e.getMessage());
            return false;
        }
        }else{
            return false;
        }
    }
    public boolean removeCustomer(String username_){

        int i = accounts.indexOf(new Customer(username_, username_, 1));
        
        if( i > -1){
            String filePath = accounts.get(i).getdirectoryPath()+ accounts.get(i).getUsername();
            try {
                // Create a File object for the file to be deleted
                File file = new File(filePath);
    
                // Check if the file exists
                if (file.exists()) {
                    // Attempt to delete the file
                    if (file.delete()) {
                        accounts.remove(i);
                        System.out.println("File deleted successfully!");
                        return true;
                    } else {
                        System.out.println("Failed to delete the file: "+ accounts.get(i).getdirectoryPath());
                        return false;
                    }
                } else {
                    System.out.println("File does not exist!");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("An error occurred while deleting the file: " + e.getMessage());
                return false;
            }

        }else {
            System.out.println("Customer does not exist!");
            return false;
        }
    
    }
    

    
    
    public static void main(String[] args) {

        var M = getInstance();
        var jay = new Customer("dagm", "urMOM", 100);
        
        M.addCustomer(jay);
        jay.deposit(9900);
        jay.deposit(10000);

        jay.withdraw(10001);


        jay.onlinepurchase(50);


        M.removeCustomer("dagm");
        M.removeCustomer("josh");

        
       
        
    }

}
