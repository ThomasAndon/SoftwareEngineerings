package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
*@Description: Main page of the admin
*@param:
*@return:
*@Author:Jin TianYu
*@Date:2021/4/25
*/
public class AdminMain {

    @FXML
    private Button sessionInfo;

    @FXML
    private Button add;

    @FXML
    private Button userInfo;

    @FXML
    private Button edit;

    @FXML
    private Button coachInfo;

    @FXML
    private Text Back;


    public void toUserInfo(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) userInfo.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/UserCsv.fxml"));
        Parent root = loader.load();
        UserCsvControl controller = loader.getController();
        //instantiating a user
        controller.init();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toCoachInfo(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) coachInfo.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CoachCsv.fxml"));
        Parent root = loader.load();
        CoachCsvControl controller = loader.getController();
        //instantiating a user
        controller.init();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toSessionInfo(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) sessionInfo.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/SessionCsv.fxml"));
        Parent root = loader.load();
        SessionCsvControl controller = loader.getController();
        //instantiating a user
        controller.init();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
    //Go to edit page
    public void editAd(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) edit.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/EditAd.fxml"));
        Parent root = loader.load();
        EditAd controller = loader.getController();
        controller.init();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
    //Back to the login page
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
