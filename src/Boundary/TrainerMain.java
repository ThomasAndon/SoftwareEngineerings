package Boundary;

import Controller.ReadAd;
import Controller.ToPage;
import Entity.Trainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class TrainerMain {
    @FXML
    private Button SessionBtn;
    @FXML
    private Button ScheduleBtn;
    @FXML
    private Button ProfileBtn;
    @FXML
    private Label trainerID;
    @FXML
    private TextArea Ad;
    @FXML
    private Text Exit;

    private Trainer trainer;
    private ReadAd ra = new ReadAd();


    public void initData(Trainer trainer) throws IOException {
        trainerID.setText(trainer.getTrainerID());
        this.trainer = trainer;
        initAd();
    }

    private void initAd() throws IOException {
        Ad.setText(ra.readAd(ra.AdPath));

    }


    public void toSchedule(ActionEvent actionEvent) throws Exception {
        ToPage tp = new ToPage();
        tp.toSchedule2(ScheduleBtn, trainer);
    }

    public void toProfile(ActionEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toCoachProfile(ProfileBtn,trainer);
    }

    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        /*ToPage tp = new ToPage();
        String s= "LoginPageUI";
        tp.toMainPage(Exit, s);*/
    }
}
