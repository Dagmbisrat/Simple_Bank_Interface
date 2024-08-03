package coe528.project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class App extends Application {


    @Override
    public void start(Stage primaryStage) {
        // Create two buttons
        Button CustomerLogin = new Button("CustomerLogin");
        Button ManagerLogin = new Button("ManagerLogin");

        // Set actions 
        CustomerLogin.setOnAction(e -> {
            goCustomerLogin();
            primaryStage.close();
        });
        ManagerLogin.setOnAction(e -> {
            goManagerLogin();
            primaryStage.close();
        });

        // Layout for buttons
        VBox root = new VBox(10);
        root.getChildren().addAll(CustomerLogin, ManagerLogin);

        // Center align buttons vertically
        root.setAlignment(Pos.CENTER);

        // Create the scene
        Scene scene = new Scene(root, 600, 300);

        // Set the scene to the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bank wif Money");
        primaryStage.show();
    }

    public void goCustomerLogin(){

    var CL = new CustomerLogin();
    CL.start(new Stage());


    }

    public void goManagerLogin(){

        var Ml = new ManagerLogin();
        Ml.start(new Stage());
    }

    public static void errorScreen(String messege){

        // Show alert for invalid username or password
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid Input");
        alert.setHeaderText(null);
        alert.setContentText(messege);
        alert.showAndWait();
    }

public static void main(String[] args) {
  launch(args);
}
}