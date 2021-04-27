package Controller;

import NetBeans.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class UserInterf {
    @FXML
    private Text viewSchedule;
    @FXML
    private Text Back;
    @FXML
    private AnchorPane window;
    @FXML
    private Button SessionBtn;
    @FXML
    private Button ScheduleBtn;
    @FXML
    private Button UserProfile;
    @FXML
    private Label userID;
    @FXML
    private TextArea Advertisement;

    private User user;

    private IOClass ioClass=new IOClass();

    public void initData(User user) throws IOException {

        userID.setText(user.getId());
        this.user = user;
        initAdvertisemnet(ioClass.AdPath);
    }

    /**
    *@Description: Initialize advertisement and display
    *@param:
    *@return:
    *@Author:Jin TianYu
    *@Date:2021/4/27
    */

    private void initAdvertisemnet(String path) throws IOException {
        String s=ioClass.readAd(path);
        Advertisement.setText(s);
    }

    public void getUser(User user) {

        this.user = user;
    }
    public void toWorkout(ActionEvent actionEvent) {


    }

    public void toBookSession(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) SessionBtn.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/BookSessionUI.fxml"));
        Parent root = loader.load();
        BookSession controller = loader.getController();
        //instantiating a user
        controller.getUser(user);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }


    public void toProfile(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) UserProfile.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/PersonalProfile.fxml"));
        Parent root = loader.load();
        CheckUserProfile controller = loader.getController();

        //instantiating a user
        controller.getUser(user);


        stage.setScene(new Scene(root, 1000, 700));
        stage.show();

    }

    public void toSchedule(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ScheduleBtn.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ScheduleUI.fxml"));
        Parent root = loader.load();
        ScheduleController controller = loader.getController();
        System.out.println(user);
        //instantiating a user
        controller.getUser(user);
        System.out.println("successful!");

        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }


    public void toLoginPage(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) Back.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/LoginPage.fxml"));
        Parent root = loader.load();
        stage.setTitle("Login Page");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
}
