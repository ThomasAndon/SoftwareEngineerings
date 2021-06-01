package Boundary;

import Controller.OpenAd;
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

/**
 * This is the trainer main interface
 */
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

    /**
     * Initial a trainer object
     * @param trainer
     * @throws IOException
     */
    public void initData(Trainer trainer) throws IOException {
        trainerID.setText(trainer.getTrainerID());
        this.trainer = trainer;
   //     Ad.setText(new OpenAd().selectAd());
    }

    /**
     * go to schedule page
     * @param actionEvent
     * @throws Exception
     */
    public void toSchedule(ActionEvent actionEvent) throws Exception {
        ToPage tp = new ToPage();
        tp.toSchedule2(ScheduleBtn, trainer);
    }

    /**
     * to profile page
     * @param actionEvent
     * @throws IOException
     */
    public void toProfile(ActionEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toCoachProfile(ProfileBtn,trainer);
    }

    /**
     * exit current id
     * @param mouseEvent
     * @throws IOException
     */
    public void exit(MouseEvent mouseEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.exit(Exit);
    }
}
