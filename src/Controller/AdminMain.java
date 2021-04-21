package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

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

    public void toCoachInfo(ActionEvent actionEvent) {
    }

    public void toSessionInfo(ActionEvent actionEvent) {
    }

    public void addCoach(ActionEvent actionEvent) {
    }

    public void editAd(ActionEvent actionEvent) {
    }
}