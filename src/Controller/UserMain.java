package Controller;

import NetBeans.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
    private User user;

    public void initData(User user) {
        userID.setText(user.getId());
        this.user = user;
        if(user.getLevel()==0){
            SessionBtn.setDisable(true);
            ScheduleBtn.setDisable(true);
        }
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
//        Stage stage = (Stage) ProfileBtn.getScene().getWindow();
////        stage.close();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("../view/ProfileInfo.fxml"));
//        Parent root = loader.load();
//        EditProfileInfo controller = loader.getController();
//        //instantiating a user
//        controller.initData(user);
//        stage.setScene(new Scene(root, 1000, 700));
//        stage.show();
    }

    public void toSchedule1(ActionEvent actionEvent) throws Exception {
        ToPage tp = new ToPage();
       // tp.toSchedule(viewSchedule, user);
        Stage stage = (Stage) ScheduleBtn.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/UserScheduleUI.fxml"));
        Parent root = loader.load();
        UserControlSchedule controller = loader.getController();
        //instantiating a user
        controller.getUser(user);
        controller.showSchedule();
     //   controller.printSchedule(controller.readCSV(user.getId()),true);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toSchedule(MouseEvent actionEvent) throws Exception {
        ToPage tp = new ToPage();
        tp.toSchedule2(ScheduleBtn, user);
//        Stage stage = (Stage) viewSchedule.getScene().getWindow();
//        stage.close();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("../view/ScheduleUI.fxml"));
//        Parent root = loader.load();
//        ScheduleControl controller = loader.getController();
//        //instantiating a user
//        controller.getUser(user);
//        controller.userSchedule();
//       // controller.printSchedule(controller.readCSV(user.getId()),true);
//        stage.setScene(new Scene(root, 1000, 700));
//        stage.show();
    }

    public void toMainPage(MouseEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toUserMainPage(mainPage, user);
//        Stage stage = (Stage) mainPage.getScene().getWindow();
//        stage.close();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("../view/UserMainUI.fxml"));
//        Parent root = loader.load();
//        UserMain controller = loader.getController();
//        //instantiating a user
//        controller.initData(user);
//        // stage.setTitle("Hello World");
//        stage.setScene(new Scene(root, 1000, 700));
//        stage.show();
    }

}
