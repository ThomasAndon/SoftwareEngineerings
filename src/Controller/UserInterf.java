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
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UserInterf {
    @FXML
    private Text viewSchedule;

    @FXML
    private Text Back;
    @FXML
    private Text mainPage;
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

    private IOClass ioClass=new IOClass();

    @FXML
    private TextArea Advertisement;

    private User user;

    public void initData(User user) {
        userID.setText(user.getId());
        this.user = user;
        try {
            initAdvertisemnet(ioClass.AdPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(user.getLevel()==0){
            SessionBtn.setDisable(true);
            ScheduleBtn.setDisable(true);
        }
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


    public void toProfile(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) SessionBtn.getScene().getWindow();
//        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ProfileInfo.fxml"));
        Parent root = loader.load();
        ProfileInfo controller = loader.getController();
        //instantiating a user
        controller.initData(user);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toSchedule1(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ScheduleBtn.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ScheduleUI.fxml"));
        Parent root = loader.load();
        ScheduleController controller = loader.getController();
        //instantiating a user
        controller.getUser(user);
        controller.userSchedule();
     //   controller.printSchedule(controller.readCSV(user.getId()),true);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toSchedule(MouseEvent actionEvent) throws Exception {
        Stage stage = (Stage) viewSchedule.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ScheduleUI.fxml"));
        Parent root = loader.load();
        ScheduleController controller = loader.getController();
        System.out.println(user);
        //instantiating a user
        controller.getUser(user);
        controller.userSchedule();
       // controller.printSchedule(controller.readCSV(user.getId()),true);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toMainPage(MouseEvent actionEvent) throws IOException {
        Stage stage = (Stage) mainPage.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/UserInterf.fxml"));
        Parent root = loader.load();
        UserInterf controller = loader.getController();
        //instantiating a user
        controller.initData(user);
        // stage.setTitle("Hello World");
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
