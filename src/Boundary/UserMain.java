package Boundary;

import Controller.OpenAd;
import Controller.ToPage;
import Entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class UserMain {
    @FXML
    public Button WorkoutBtn;
    @FXML
    public Button ProfileBtn;
    public Text viewSchedule;
    public Text Exit;

    @FXML
    private Text mainPage;

    @FXML
    private Button SessionBtn;
    @FXML
    private Button ScheduleBtn;
    @FXML
    private Label userID;
    @FXML
    private TextArea Ad;

    private User user;

    public void initData(User user) throws IOException {
        userID.setText(user.getId());
        this.user = user;
        if(user.getLevel()==0){
            SessionBtn.setDisable(true);
            ScheduleBtn.setDisable(true);
        }
        initAd();
    }

    private void initAd() throws IOException {
        Ad.setText(new OpenAd().selectAd());

    }

    public void getUser(User user) {
        this.user = user;
    }

    public void toWorkout(ActionEvent actionEvent) throws IOException{
        ToPage tp = new ToPage();
        tp.toWorkout(WorkoutBtn, user);
    }

    public void toBookSession(ActionEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toBookSession(SessionBtn, user);
    }


    public void toProfile(ActionEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toProfile(ProfileBtn, user);

    }


    public void toSchedule(MouseEvent actionEvent) throws Exception {
        ToPage tp = new ToPage();
        tp.toSchedule2(ScheduleBtn, user);

    }

    public void toSchedule2(MouseEvent actionEvent) throws Exception {
        ToPage tp = new ToPage();
        tp.toSchedule(viewSchedule, user);
    }

    public void toMainPage(MouseEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toMainPage(mainPage, user);

    }
    public void exit(MouseEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
       tp.exit(Exit);
    }

}
