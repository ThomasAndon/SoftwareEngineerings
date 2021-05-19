package Boundary;

import Controller.ReadAd;
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

    public ReadAd ra = new ReadAd();

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
        Ad.setText(ra.readAd(ra.AdPath));

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
//        Stage stage = (Stage) SessionBtn.getScene().getWindow();
//        stage.close();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("../view/BookSessionUI.fxml"));
//        Parent root = loader.load();
//        BookSession controller = loader.getController();
//        //instantiating a user
//        controller.getUser(user);
//        stage.setScene(new Scene(root, 1000, 700));
//        stage.show();
    }


    public void toProfile(ActionEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toProfile(ProfileBtn, user);

    }


    public void toSchedule(MouseEvent actionEvent) throws Exception {
        ToPage tp = new ToPage();
        tp.toSchedule2(ScheduleBtn, user);

    }

    public void toMainPage(MouseEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toUserMainPage(mainPage, user);

    }

}
