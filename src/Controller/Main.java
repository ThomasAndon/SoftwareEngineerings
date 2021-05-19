package Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;


public class Main extends Application {


    static Stage primaryStage;






    @Override


    public void start(Stage primaryStage) throws Exception {


        //todo 在窗体刚加载时，就需要读取所有用户的信息了，以便用户输入后比对。
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Login");
        Parent root = FXMLLoader.load(getClass().getResource("../View/LoginPageUI.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("../view/UserMainUI.fxml"));
        //todo 在这里读取所有表格

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        // todo delete-test
        new MatchAccount().readAllAccount();
    }



    public static void main(String[] args) {
        launch(args);
    }

}
