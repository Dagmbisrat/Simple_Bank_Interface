package coe528.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ManagerPage extends Application {

    private Label messageLabel;
    Manager M = Manager.getInstance();

    @Override
    public void start(Stage primaryStage) {

        // Create buttons
        Button addCustomerButton = new Button("Add Customer");
        addCustomerButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 20px; -fx-text-fill: black;");
        Button deleteCustomerButton = new Button("Delete Customer");
        deleteCustomerButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 20px; -fx-text-fill: black;");
        Button goBackButton = new Button("Logout");
        goBackButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 15px; -fx-text-fill: red;");


        // Set actions for buttons
        addCustomerButton.setOnAction(e -> {
            addCustomerPage();
            primaryStage.close();
        });
        deleteCustomerButton.setOnAction(e -> {
            deleteCustomerPage();
            primaryStage.close();
        });
        goBackButton.setOnAction(e -> {
            goBack();
            primaryStage.close();
        });

        // Layout for buttons
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER_LEFT); // Center align the buttons horizontally
        root.getChildren().addAll(addCustomerButton, deleteCustomerButton);
        root.setPadding(new Insets(20));

        HBox top = new HBox();
        top.setPadding(new Insets(20)); // Set padding around the HBox
        top.setStyle("-fx-padding: 20px; -fx-background-color: #808080;"); // Set background color for the header
        top.setAlignment(Pos.CENTER_RIGHT);
        top.getChildren().add(goBackButton);



        BorderPane Borderpane = new BorderPane();
        Borderpane.setTop(top);
        Borderpane.setLeft(root);


        // Create scene and set on stage
        Scene scene = new Scene(Borderpane, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bank Manager Page");
        primaryStage.show();
    }

    private void addCustomerPage(){
        
        Stage addCustomerStage = new Stage();

        // Create labels, text fields, and password field
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField("ElonMusk");
        Label passwordLabel = new Label("Password:");
        TextField passwordField = new TextField("Neurolink123");

        // Create add and back buttons
        Button addButton = new Button("Add");
        Button backButton = new Button("Back");
        addButton.setOnAction(e -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            var C = new Customer(username, password, 100);
            

            if(M.addCustomer(C)){
                messageLabel.setText("Customer '" + username + "' has been successfuly added.");
            }else{
                App.errorScreen("User: " + username + " may alredy exist. Please try Again.");
            }

        });
        backButton.setOnAction(e -> {

            var Mp = new ManagerPage();
            Mp.start(new Stage());

            addCustomerStage.close();
        });

        // Create layout and add components
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER_LEFT); // Align center horizontally and left vertically
        grid.setPadding(new Insets(10)); // Add padding around the grid
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(addButton, 1, 2);
        grid.add(backButton, 0, 2);

        // Create message label
        messageLabel = new Label("");
        messageLabel.setTextFill(Color.GREEN); // Set text color to red
        grid.add(messageLabel, 0, 3, 2, 1); // span two columns

        // Create a stack pane to center the grid
        StackPane root = new StackPane();
        root.getChildren().add(grid);

        // Create scene and set on stage
        Scene scene = new Scene(root, 600, 250);
        addCustomerStage.setScene(scene);
        addCustomerStage.setTitle("Add Customer Page");
        addCustomerStage.show();

    }
    
    private void deleteCustomerPage(){
        
        Stage RemoveCustomerStage = new Stage();

        // Create labels, text fields, and password field
        Label usernameLabel = new Label("Username:");
        TextField usernameField = new TextField();

        // Create delete and back buttons
        Button deleteButton = new Button("Delete");
        Button backButton = new Button("Go Back");
        deleteButton.setOnAction(e -> {

            String username = usernameField.getText();
        
            if(M.removeCustomer(username)){
             messageLabel.setText("Customer '" + username + "' has been successfuly deleted.");
            }else{
            App.errorScreen("Customer '" + username + "' dose not exsist! deletion unsuccessful.");
       }

        });
        backButton.setOnAction(e -> {

            var Mp = new ManagerPage();
            Mp.start(new Stage());
            RemoveCustomerStage.close();
        });

        // Create layout and add components
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER); // Align center horizontally and vertically
        grid.setPadding(new Insets(10)); // Add padding around the grid
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameField, 1, 0);
        grid.add(deleteButton, 1, 1);
        grid.add(backButton, 0, 1);

        // Create message label
        messageLabel = new Label("");
        messageLabel.setTextFill(Color.RED); // Set text color to red
        grid.add(messageLabel, 0, 2, 2, 1); // span two columns
        
        // Create a stack pane to center the grid
        StackPane root = new StackPane();
        root.getChildren().add(grid);

        // Create scene and set on stage
        Scene scene = new Scene(root, 600, 250);
        RemoveCustomerStage.setScene(scene);
        RemoveCustomerStage.setTitle("Remove Customer Page");
        RemoveCustomerStage.show();
    }

    private void goBack(){
        var App = new App();
        App.start(new Stage());
    }


    public static void main(String[] args) {
        launch(args);
    }
}
