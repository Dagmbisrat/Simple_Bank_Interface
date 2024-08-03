package coe528.project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class ManagerLogin extends Application {
    @Override
    public void start(Stage primaryStage) {

        // Create labels, text fields, and password field
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField("ad...");
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("password");


     
        

        // Create buttons
        Button loginButton = new Button("Login");
        loginButton.setOnAction(e -> {
            if(login(usernameField.getText(), passwordField.getText())){
                goManagerPage();
                primaryStage.close();
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
        primaryStage.setTitle("Manager Login");
        primaryStage.show();
    }

    // Method to handle login button action
    private boolean login(String username, String password) {
        
        var m = Manager.getInstance();
        
        if(m.authenthicate(username, password)){
            return true;
        }
            
        else{

            App.errorScreen("Invalid username or password. Please try again.");
            return false;
        }
            
    }
    private void goAppPage(){
        var App = new App();
        App.start(new Stage());
    }

    private void goManagerPage(){
        var Mp = new ManagerPage();
        Mp.start(new Stage());
        System.out.println("Sucsessful login!");
        
    }

    

    public static void main(String[] args) {
        launch(args);
    }
    
}
