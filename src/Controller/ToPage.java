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

public class ToPage {


    public void toUserMainPage(Text mainPage, User user) throws IOException {
        Stage stage = (Stage) mainPage.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/UserMainUI.fxml"));
        Parent root = loader.load();
        UserMain controller = loader.getController();
        controller.initData(user);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
    
    public void toTrainerMainPage(Text mainPage, Trainer trainer) throws IOException {
        Stage stage = (Stage) mainPage.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/TrainerMainUI.fxml"));
        Parent root = loader.load();
        TrainerMain controller = loader.getController();
        controller.initData(trainer);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toWorkout(Button WorkoutBtn, User user) throws IOException{

    }

    public void toSchedule(Text viewSchedule,User user) throws Exception {
        Stage stage = (Stage) viewSchedule.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/UserScheduleUI.fxml"));
        Parent root = loader.load();
       // ScheduleControl controller = loader.getController();
        UserControlSchedule controller = loader.getController();
        controller.getUser(user);
        controller.showSchedule();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public<T> void toSchedule2(Button ScheduleBtn,T o) throws Exception {
        Stage stage = (Stage) ScheduleBtn.getScene().getWindow();
        stage.close();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("../view/UserScheduleUI.fxml"));
        Parent root;
        // ScheduleControl controller = loader.getController();
        if(User.class.isInstance(o)){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/UserScheduleUI.fxml"));
            root = loader.load();
            UserControlSchedule controller = loader.getController();
            User user = (User) o;
            controller.getUser(user);
            controller.showSchedule();
        }else{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/TrainerScheduleUI.fxml"));
            root = loader.load();
            TrainerControlSchedule controller = loader.getController();
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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/BookSessionUI.fxml"));
        Parent root = loader.load();
        BookSession controller = loader.getController();
        //instantiating a user
        controller.getUser(user);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toProfile(Button ProfileBtn, User user) throws IOException {
        Stage stage = (Stage) ProfileBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ProfileInfo.fxml"));
        Parent root = loader.load();
        EditProfileInfo controller = loader.getController();
        //instantiating a user
        controller.initData(user);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

    public void toCoachProfile(Button Btn, Trainer trainer) throws IOException {
        Stage stage = (Stage) Btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CoachProfileUI.fxml"));
        Parent root = loader.load();
        CoachProfile controller = loader.getController();
        //instantiating a user
        controller.initData(trainer);
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
    public void toMainPage(Text text, String  s,Trainer trainer) throws IOException {
        Stage stage = (Stage) text.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/" + s +".fxml"));
        Parent root = loader.load();
        TrainerMain controller = loader.getController();
        controller.initData(trainer);
        stage.setScene(new Scene(root, 800, 600));
        stage.show();
    }

}
