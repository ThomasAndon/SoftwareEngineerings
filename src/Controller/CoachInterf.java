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

public class CoachInterf {
    @FXML
    private Button SessionBtn;
    @FXML
    private Button ScheduleBtn;
    @FXML
    private Button ProfileBtn;
    @FXML
    private Label trainerID;
    private Trainer trainer;

    public void initData(Trainer trainer) {
        trainerID.setText(trainer.getTrainerID());
        this.trainer = trainer;
    }

    public void toMyStudents(ActionEvent actionEvent) {
    }

    public void toSchedule(ActionEvent actionEvent) throws Exception {
        Stage stage = (Stage) ScheduleBtn.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ScheduleUI.fxml"));
        Parent root = loader.load();
        ScheduleController controller = loader.getController();
        //instantiating a user
        controller.getTrainer(trainer);
        controller.trainerSchedule();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toProfile(ActionEvent actionEvent) {

    }
}
