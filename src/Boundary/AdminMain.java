package Boundary;

import Controller.ToPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMain {
    public Button uploadVideo;
    @FXML
    private Button sessionInfo;
    @FXML
    private Button userInfo;
    @FXML
    private Button edit;
    @FXML
    private Button coachInfo;

    public void toUserInfo(ActionEvent actionEvent) throws IOException {
        new ToPage().toUserCSVPage(userInfo);
    }

    public void toCoachInfo(ActionEvent actionEvent) throws IOException {
        new ToPage().toCoachCSVPage2(coachInfo);
    }

    public void toSessionInfo(ActionEvent actionEvent) throws IOException {
        new ToPage().toSessionCSVPage(sessionInfo);
    }

    public void editAd(ActionEvent actionEvent) throws IOException {
        new ToPage().toAd(edit);
    }

    @FXML
    /** This function is invocated when the admin tries to upload a video
     * @author Thomas Andon
     */
    void onUploadVideoClicked(ActionEvent event) throws IOException {
        new ToPage().toUploadVideo(uploadVideo);
    }

}
