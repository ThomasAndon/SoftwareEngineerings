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

public class ToPage  {

    public<T>void toMainPage(Text mainPage, T o) throws IOException {
        Stage stage = (Stage) mainPage.getScene().getWindow();
        stage.close();
        Parent root = null;
        if (User.class.isInstance(o)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/UserMainUI.fxml"));
            root = loader.load();
            UserMain controller = loader.getController();
            User user = (User) o;
            controller.initData(user);

        } else if(Trainer.class.isInstance(o)){
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/TrainerMainUI.fxml"));
            root = loader.load();
            TrainerMain controller = loader.getController();
            Trainer trainer = (Trainer) o;
            controller.initData(trainer);
        }else{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/AdminMainUI.fxml"));
            root = loader.load();
        }
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void toWorkout(Button WorkoutBtn, User user) throws IOException {
        Stage stage = (Stage) WorkoutBtn.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/WorkOutUI.fxml"));
        Parent root = loader.load();
        UserWatchVideo controller = loader.getController();
        controller.getUser(user);
        stage.setScene(new Scene(root));
        stage.show();

    }
    public void toSchedule(Text viewSchedule,User user) throws Exception {
        Stage stage = (Stage) viewSchedule.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/UserScheduleUI.fxml"));
        Parent root = loader.load();
        UserControlSchedule controller = loader.getController();
        controller.getUser(user);
        controller.showSchedule();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public<T>void toSchedule2(Button ScheduleBtn,T o) throws Exception {
        Stage stage = (Stage) ScheduleBtn.getScene().getWindow();
        stage.close();
        Parent root;
        if (User.class.isInstance(o)) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/UserScheduleUI.fxml"));
            root = loader.load();
            UserControlSchedule controller = loader.getController();
            User user = (User) o;
            controller.getUser(user);
            controller.showSchedule();
        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../view/TrainerScheduleUI.fxml"));
            root = loader.load();
            TrainerControlSchedule controller = loader.getController();
            Trainer trainer = (Trainer) o;
            controller.getTrainer(trainer);
            controller.showSchedule();
        }
        stage.setScene(new Scene(root));
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
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void toProfile(Button ProfileBtn, User user) throws IOException {
        Stage stage = (Stage) ProfileBtn.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/ProfileInfo.fxml"));
        Parent root = loader.load();
        EditProfileInfo controller = loader.getController();
        controller.initData(user);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void toCoachProfile(Button Btn, Trainer trainer) throws IOException {
        Stage stage = (Stage) Btn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CoachProfileUI.fxml"));
        Parent root = loader.load();
        CoachProfile controller = loader.getController();
        controller.initData(trainer);
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void toBookSuccessfulPage(Button bookBtn, User user) throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/BookSuccessful.fxml"));
        Parent root = loader.load();
        UserMain controller = loader.getController();
        controller.getUser(user);
        Stage stage = (Stage) bookBtn.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void toCoachCSVPage(Text text) throws IOException{
        Stage stage = (Stage) text.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CoachInformationUI.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void toCoachCSVPage2(Button btn) throws IOException{
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CoachInformationUI.fxml"));
        Parent root = loader.load();
        CoachInfoControl controller = loader.getController();
        //instantiating a user
        controller.init();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void toUserCSVPage(Button btn) throws IOException{
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/UserInformationUI.fxml"));
        Parent root = loader.load();
        UserInfoControl controller = loader.getController();
        //instantiating a user
        controller.init();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void toSessionCSVPage(Button btn) throws IOException{
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/SessionCsv.fxml"));
        Parent root = loader.load();
        SessionCsvControl controller = loader.getController();
        //instantiating a user
        controller.init();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void toAd(Button btn) throws IOException{
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/EditAdUI.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void toUploadVideo(Button btn) throws IOException{
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/VideoUploaderUI.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void toAddCoach(Text text) throws IOException{
        Stage stage = (Stage) text.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/AddCoachUI.fxml"));
        Parent root = loader.load();
      //  AddCoach controller = loader.getController();
     //   controller.init();
        stage.setScene(new Scene(root));
        stage.show();
    }
    public void exit(Text text) throws IOException{
        Stage stage = (Stage) text.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/LoginPageUI.fxml"));
        Parent root = loader.load();
        //  AddCoach controller = loader.getController();
        //   controller.init();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
