package Controller;

import NetBeans.Trainer;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;


/**
*@Description: Main page for the coaches
*@param:
*@return:
*@Author:Jin TianYu
*@Date:2021/4/25
*/
public class CoachMain {
    @FXML
    private Button schedule;

    @FXML
    private Button EditClass;

    @FXML
    private Label CoachID;

    @FXML
    private Button uploadVideo;

    @FXML
    private Button Profile;

    @FXML
    private TextArea Advertisement;
    @FXML
    private Text Back;

    private Trainer trainer;

    private IOClass ioClass=new IOClass();

    public void initData(Trainer trainer) throws IOException {
        CoachID.setText(trainer.getTrainerID());
        this.trainer = trainer;
        initAdvertisement(ioClass.AdPath);
    }

    private void initAdvertisement(String adPath) throws IOException {
        String s=ioClass.readAd(adPath);
        Advertisement.setText(s);
    }


    public void toUploadVideo(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) uploadVideo.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/UploadVideoUI.fxml"));
        Parent root = loader.load();
        //UploadViedo controller = loader.getController();

        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toSchedule(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) schedule.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CoachSchedule.fxml"));
        Parent root = loader.load();
        CoachSchedule controller = loader.getController();
        controller.init(trainer);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toCoachProfile(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) Profile.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CoachProfile.fxml"));
        Parent root = loader.load();
        CheckCoachProfile controller = loader.getController();
        controller.init(trainer);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();

    }

    public void editClass(ActionEvent actionEvent) {
    }



    public void toLoginPage(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) Back.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/LoginPage.fxml"));
        Parent root = loader.load();
        stage.setTitle("Login page");
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }
}
