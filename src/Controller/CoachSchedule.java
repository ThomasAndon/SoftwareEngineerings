package Controller;

import NetBeans.Trainer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
*@Description: Can print the coach schedule in the table, all of the lessons
*@param:
*@return:
*@Author:Jin TianYu
*@Date:2021/4/25
*/
public class CoachSchedule {
    @FXML
    private TextArea Lessons;
    @FXML
    private Text Back;

    private IOClass ioClass=new IOClass();
    public void init(Trainer trainer) throws IOException {
        String trainerID = trainer.getTrainerID();
        initLessons(trainerID,ioClass.SchedulePath);

    }

    private void initLessons(String id,String path) throws IOException {

        String lesson=ioClass.readLesson(id,path);
        //System.out.println(lesson);
        Lessons.setText(lesson);
    }


    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) Back.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CoachMain.fxml"));
        Parent root = loader.load();
        stage.setTitle("Coach account");
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();

    }


}
