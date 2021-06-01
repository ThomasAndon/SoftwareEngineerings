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
/**
 * This class is the user main interface
 */
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
    private Label Ad;

    private User user;
    /**
     * Initial a user object
     * @param user
     * @throws IOException
     */
    public void initData(User user) throws IOException {
        userID.setText(user.getId());
        this.user = user;
        if(user.getLevel()==0){
            SessionBtn.setDisable(true);
            ScheduleBtn.setDisable(true);
        }
        Ad.setText(new OpenAd().selectAd());
    }

    public void getUser(User user) {
        this.user = user;
    }

    /**
     * go to workout page
     * @param actionEvent
     * @throws IOException
     */
    public void toWorkout(ActionEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toWorkout(WorkoutBtn, user);
    }

    /**
     * to book session page
     * @param actionEvent
     * @throws IOException
     */
    public void toBookSession(ActionEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toBookSession(SessionBtn, user);
    }

    /**
     * to profile page
     * @param actionEvent
     * @throws IOException
     */
    public void toProfile(ActionEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toProfile(ProfileBtn, user);

    }

    /**
     * to schedule page
     * @param actionEvent
     * @throws Exception
     */
    public void toSchedule(MouseEvent actionEvent) throws Exception {
        ToPage tp = new ToPage();
        tp.toSchedule2(ScheduleBtn, user);

    }

    public void toSchedule2(MouseEvent actionEvent) throws Exception {
        ToPage tp = new ToPage();
        tp.toSchedule(viewSchedule, user);
    }

    /**
     * to main page
     * @param actionEvent
     * @throws IOException
     */
    public void toMainPage(MouseEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toMainPage(mainPage, user);

    }

    /**
     * exit current id
     * @param actionEvent
     * @throws IOException
     */
    public void exit(MouseEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
       tp.exit(Exit);
    }

}
