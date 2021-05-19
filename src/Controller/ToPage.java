package Controller;

import Boundary.*;
import Entity.Trainer;
import Entity.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class ToPage implements GoPage {


    public void toUserMainPage(Text mainPage, User user) throws IOException {
        Stage stage = (Stage) mainPage.getScene().getWindow();
        stage.close();
        Parent root = PageChange("UserMainUI").load();
        UserMain controller = PageChange("UserMainUI").getController();
        controller.initData(user);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
    
    public void toTrainerMainPage(Text mainPage, Trainer trainer) throws IOException {
        Stage stage = (Stage) mainPage.getScene().getWindow();
        stage.close();
        Parent root = PageChange("TrainerMainUI").load();
        TrainerMain controller = PageChange("TrainerMainUI").getController();
        controller.initData(trainer);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toWorkout(Button WorkoutBtn, User user) throws IOException{

    }

    public void toSchedule(Text viewSchedule,User user) throws Exception {
        Stage stage = (Stage) viewSchedule.getScene().getWindow();
        stage.close();
        Parent root = PageChange("UserScheduleUI").load();
        UserControlSchedule controller = PageChange("UserScheduleUI").getController();
        controller.getUser(user);
        controller.showSchedule();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public<T>void toSchedule2(Button ScheduleBtn,T o) throws Exception {
        Stage stage = (Stage) ScheduleBtn.getScene().getWindow();
        stage.close();
        Parent root;


        if (User.class.isInstance(o)) {

            root = PageChange("UserScheduleUI").load();
            UserControlSchedule controller = PageChange("UserScheduleUI").getController();
            User user = (User) o;
            controller.getUser(user);
            controller.showSchedule();
        } else {
            root = PageChange("TrainerScheduleUI").load();
            TrainerControlSchedule controller = PageChange("TrainerScheduleUI").getController();
            Trainer trainer = (Trainer) o;
            controller.getTrainer(trainer);
            controller.showSchedule();
        }
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toBookSession(Button SessionBtn, User user) throws IOException {
        Stage stage = (Stage) SessionBtn.getScene().getWindow();
        stage.close();
        Parent root = PageChange("BookSessionUI").load();
        BookSession controller = PageChange("BookSessionUI").getController();
        //instantiating a user
        controller.getUser(user);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toProfile(Button ProfileBtn, User user) throws IOException {
        Stage stage = (Stage) ProfileBtn.getScene().getWindow();
        Parent root = PageChange("ProfileInfo").load();
        EditProfileInfo controller = PageChange("ProfileInfo").getController();
        controller.initData(user);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toCoachProfile(Button Btn, Trainer trainer) throws IOException {
        Stage stage = (Stage) Btn.getScene().getWindow();
        Parent root = PageChange("CoachProfileUI").load();
        CoachProfile controller = PageChange("CoachProfileUI").getController();
        //instantiating a user
        controller.initData(trainer);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
    public void toMainPage(Text text, String  s,Trainer trainer) throws IOException {
        Stage stage = (Stage) text.getScene().getWindow();
        Parent root = PageChange(s).load();
        TrainerMain controller = PageChange(s).getController();
        controller.initData(trainer);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

}
