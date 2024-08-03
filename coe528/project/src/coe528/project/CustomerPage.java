package coe528.project;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CustomerPage extends Application {

    private Customer loogedinCustomer;

    public CustomerPage(Customer c){
        loogedinCustomer = c;
    }

    @Override
    public void start(Stage primaryStage) {

        
        Label Hlable = new Label("Welcome!");
        Hlable.setStyle("-fx-font-size: 20px; -fx-fill: white;"); // Set font size and text color

        StackPane Wraper = new StackPane();
        
        

        // Create buttons
        Button depositButton = new Button("Deposit Money");
        depositButton.setStyle("-fx-background-color: transparent; -fx-border-color:  -fx-font-size: 20px; transparent; -fx-text-fill: black;");
        Button withdrawButton = new Button("Withdraw Money");
        withdrawButton.setStyle("-fx-background-color: transparent; -fx-border-color:  -fx-font-size: 20px; transparent; -fx-text-fill: black;");
        Button getBalanceButton = new Button("Get Balance");
        getBalanceButton.setStyle("-fx-background-color: transparent; -fx-border-color:  -fx-font-size: 20px; transparent; -fx-text-fill: black;");
        Button onlinePurchaseButton = new Button("Make an Online Purchase");
        onlinePurchaseButton.setStyle("-fx-background-color: transparent; -fx-border-color:  -fx-font-size: 20px; transparent; -fx-text-fill: black;");
        Button Logout = new Button("Logout");
        Logout.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: red;");
        Wraper.getChildren().add(Logout);

        // Set action handlers for each button
        depositButton.setOnAction(e -> {
            depositMoney();
            primaryStage.close();
        });
        withdrawButton.setOnAction(e -> {
            withdrawMoney();
            primaryStage.close();
        });
        getBalanceButton.setOnAction(e -> {
            getBalance();
            primaryStage.close();
        });
        onlinePurchaseButton.setOnAction(e -> {
            doOnlinePurchase();
            primaryStage.close();
        });
        Logout.setOnAction(e -> {
            var App = new App();
            App.start(new Stage());
            primaryStage.close();
        });

        // Createing left
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(depositButton, withdrawButton, getBalanceButton, onlinePurchaseButton);

        //Creating the header
        HBox top = new HBox();
        top.setStyle("-fx-padding: 20px; -fx-background-color: #808080;"); // Set background color for the header
        top.getChildren().addAll(Hlable,Wraper);
        top.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        HBox.setHgrow(Wraper, javafx.scene.layout.Priority.ALWAYS); // Allow right label to grow horizontally
        top.setSpacing(300); // Set spacing between nodes
        top.setPadding(new Insets(20)); // Set padding around the HBox


        BorderPane Borderpane = new BorderPane();
        Borderpane.setTop(top);
        Borderpane.setLeft(root);

        // Create scene and set on stage
        Scene scene = new Scene(Borderpane, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bank wif Money");
        primaryStage.show();
    }

    // Functions to handle button actions
    private void depositMoney() {
        
        Stage depositStage = new Stage();
        depositStage.setTitle("Deposit Money");
        
        Label messageLabel;
        messageLabel = new Label("");
        messageLabel.setTextFill(Color.GREEN); // Set text color to Green


        // Create components for deposit window
        Label amountLabel = new Label("Enter amount to deposit:");
        TextField amountField = new TextField();
        Button depositConfirmButton = new Button("Deposit");
        depositConfirmButton.setOnAction(e -> {

            String s  = amountField.getText();  

            try{

                double amount = Double.parseDouble(amountField.getText());
                loogedinCustomer.deposit(amount);
                messageLabel.setText("Deposit of " + amount + " was successfull");

            }catch(NumberFormatException Ne){
                App.errorScreen(s + " not a valid number. Please try again!");
            }catch(IllegalArgumentException Ie){
                App.errorScreen("Cannot add -tiv ammount. Please try again");
            }


           
        });
        Button back = new Button("<--");
            back.setOnAction(e -> {
            start(depositStage);
        });
        back.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 15px; -fx-text-fill: black;");

        // Create layout for deposit window
        VBox depositLayout = new VBox(10);
        // depositLayout.setAlignment(Pos.CENTER);
        depositLayout.setPadding(new Insets(20));
        depositLayout.getChildren().addAll(amountLabel, amountField, depositConfirmButton,messageLabel,back);

        // Create scene for deposit window and set on stage
        Scene depositScene = new Scene(depositLayout, 600, 250);
        depositStage.setScene(depositScene);
        depositStage.show();
    }

    private void withdrawMoney() {
         
        Stage depositStage = new Stage();
        depositStage.setTitle("Withdraw Money");
        
        Label messageLabel;
        messageLabel = new Label("");
        messageLabel.setTextFill(Color.GREEN); // Set text color to Green


        // Create components for deposit window
        Label amountLabel = new Label("Enter amount to Withdraw:");
        TextField amountField = new TextField();
        Button depositConfirmButton = new Button("Withdraw");
        depositConfirmButton.setOnAction(e -> {

            String s  = amountField.getText();  

            try{

                double amount = Double.parseDouble(amountField.getText());
                loogedinCustomer.withdraw(amount);
                messageLabel.setText("Withdraw of " + amount + " was successfull");

            }catch(NumberFormatException Ne){
                App.errorScreen(s + " not a valid number. Please try again!");
            }catch(IllegalArgumentException Ie){
                App.errorScreen("Cannot Withdraw -tiv ammount. Please try again");
            }
           
        });
        Button back = new Button("<--");
            back.setOnAction(e -> {
            start(depositStage);
        });
        back.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 15px; -fx-text-fill: black;");

        // Create layout for deposit window
        VBox depositLayout = new VBox(10);
        depositLayout.setAlignment(Pos.CENTER);
        depositLayout.setPadding(new Insets(20));
        depositLayout.getChildren().addAll(amountLabel, amountField, depositConfirmButton,messageLabel,back);

        // Create scene for deposit window and set on stage
        Scene depositScene = new Scene(depositLayout, 600, 250);
        depositStage.setScene(depositScene);
        depositStage.show();
    }

    private void getBalance() {
        Stage S = new Stage();
        S.setTitle("Account Balance");

        StackPane testWrap = new StackPane();
        testWrap.setPadding(new Insets(20));

        Text message = new Text("You have $" + loogedinCustomer.getBalance() + " in your account");
        message.setFont(Font.font("Serif Fonts", FontWeight.MEDIUM ,20)); // Set font family, weight, and size
        message.setFill(Color.GREEN);
       
        testWrap.getChildren().addAll(message);
        
        Button back = new Button("<--");
        back.setOnAction(e -> {
        start(S);
        });
        back.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 15px; -fx-text-fill: black;");

        // Create a StackPane layout and add the message to it
        VBox root = new VBox();
        root.getChildren().addAll(testWrap,back);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));

        // Create a Scene with the layout and set it on the stage
        Scene scene = new Scene(root, 600, 200);
        S.setScene(scene);

        S.show();
    }

    private void doOnlinePurchase() {
        Stage depositStage = new Stage();
        depositStage.setTitle("Online Purchase");
        
        Label messageLabel;
        messageLabel = new Label("");
        messageLabel.setTextFill(Color.GREEN); // Set text color to Green


        // Create components for deposit window
        Label amountLabel = new Label("Enter amount:");
        TextField amountField = new TextField();
        Button depositConfirmButton = new Button("pay");
        depositConfirmButton.setOnAction(e -> {

            String s  = amountField.getText();  

            try{
                double amount = Double.parseDouble(amountField.getText());
                loogedinCustomer.onlinepurchase(amount);
                messageLabel.setText("Online Purchase of " + amount + " was successfull");

            }catch(NumberFormatException Ne){
                App.errorScreen(s + " not a valid number. Please try again!");
            }catch(IllegalArgumentException Ie){
                App.errorScreen(Ie.getMessage());
            }
           
        });
        Button back = new Button("<--");
            back.setOnAction(e -> {
            start(depositStage);
        });
        back.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 15px; -fx-text-fill: black;");

        // Create layout for deposit window
        VBox depositLayout = new VBox(10);
        depositLayout.setPadding(new Insets(20));
        depositLayout.getChildren().addAll(amountLabel, amountField, depositConfirmButton,messageLabel,back);

        // Create scene for deposit window and set on stage
        Scene depositScene = new Scene(depositLayout, 600, 250);
        depositStage.setScene(depositScene);
        depositStage.show();
    }
    
    public static boolean isValidDoouble(String str) {
        // Regular expression to match digits and "."
        String regex = "[0-9.]+";
        return str.matches(regex);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
