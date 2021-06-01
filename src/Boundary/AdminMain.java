package Boundary;

import Controller.ToPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import java.io.IOException;

/**
 * This class contains the control methods of admin page
 */
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
    @FXML
    private Text Exit;

    /**
     * go to userinfo page
     * @param actionEvent
     * @throws IOException
     */
    public void toUserInfo(ActionEvent actionEvent) throws IOException {
        new ToPage().toUserCSVPage(userInfo);
    }

    /**
     * go to coach info page
     * @param actionEvent
     * @throws IOException
     */
    public void toCoachInfo(ActionEvent actionEvent) throws IOException {
        new ToPage().toCoachCSVPage2(coachInfo);
    }

    /**
     * go to session info page
     * @param actionEvent
     * @throws IOException
     */
    public void toSessionInfo(ActionEvent actionEvent) throws IOException {
        new ToPage().toSessionCSVPage(sessionInfo);
    }

    /**
     * go to edit ad page
     * @param actionEvent
     * @throws IOException
     */
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


    /**
     * User exit the admin page
     * @param mouseEvent
     * @throws IOException
     */
    public void exit(MouseEvent mouseEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.exit(Exit);
    }
}
