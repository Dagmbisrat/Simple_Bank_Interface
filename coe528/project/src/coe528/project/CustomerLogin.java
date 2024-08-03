package coe528.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CustomerLogin extends Application {
    @Override
    public void start(Stage primaryStage) {

        // Create labels, text fields, and password field
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField("dagm");
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");

        // Create buttons
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            var c = login(usernameField.getText(), passwordField.getText());
            if( c != null ){
                primaryStage.close();
                goCustomerPage(c);
            }
            
        });
        Button Back = new Button("<--");
        Back.setOnAction(e -> {
            primaryStage.close();
            goAppPage();
        });
        Back.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 15px; -fx-text-fill: black;");

        // Create layout and add components
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(loginButton, 1, 2);
        grid.add(Back,2,2);

        // Center align buttons vertically
        grid.setAlignment(Pos.CENTER);

        // Create scene and set on stage
        Scene scene = new Scene(grid, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Customer Login");
        primaryStage.show();
    }

    // Method to handle login button action
    private Customer login(String username, String password) {
        
        var m = Manager.getInstance();
        System.out.println(m.getAccounts()); //check------
        var i = m.getAccounts().indexOf(new Customer(username, password, 0));
       
        //if the user exsist autenticate
        if(i != -1){
            
            //grab the user
            var c = m.getAccounts().get(i);
            String filepath = c.getdirectoryPath() + username;
    
            //go the the file
            try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
                
                //get the 2nd line
                line = reader.readLine();
                line = reader.readLine();

                //authenticate password
                if(line.equals(password)){
                    
                    return c;
                    
                }else{
                   
                    App.errorScreen("Invalid username or password. Please try again.");
                    return null;
                }
            } catch (IOException e) {
                System.err.println("Error reading file: " + e.getMessage());
                App.errorScreen("User file dose not exsist. ");
                return null;
            }
            
        }else{
            
            App.errorScreen("User dose not exsist");
            return null;
        }
    }
    private void goAppPage(){
        var App = new App();
        App.start(new Stage());
    }

    private void goCustomerPage(Customer c){
        var Cp = new CustomerPage(c);
        Cp.start(new Stage());
        System.out.println("Sucsessful login!");
        
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
