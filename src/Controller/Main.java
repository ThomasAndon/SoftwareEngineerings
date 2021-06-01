package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

/**
 * Load the main page
 */
public class Main extends Application {

    static Stage primaryStage;

    @Override

    /**
     * main starter of the page
     */
    public void start(Stage primaryStage) throws Exception {

        this.primaryStage = primaryStage;
        primaryStage.setTitle("Login");
        Parent root = FXMLLoader.load(getClass().getResource("../View/LoginPageUI.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        new MatchAccount().readAllAccount();
    }



    public static void main(String[] args) {
        launch(args);
    }

}
