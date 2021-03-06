package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {


    static Stage primaryStage;


    @Override
    public void start(Stage primaryStage) throws Exception {



        //todo 在窗体刚加载时，就需要读取所有用户的信息了，以便用户输入后比对。
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Login");
        Parent root = FXMLLoader.load(getClass().getResource("../view/LoginPage.fxml"));
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
