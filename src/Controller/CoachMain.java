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
import javafx.stage.Stage;

import java.io.IOException;

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

    private Trainer trainer;

    public void initData(Trainer coach) {
        CoachID.setText(coach.getTrainerID());
        this.trainer = coach;
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
        //CoachSchedule controller = loader.getController();

        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toCoachProfile(ActionEvent actionEvent) {
    }

    public void editClass(ActionEvent actionEvent) {
    }
}
