package Controller;

import NetBeans.Trainer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
*@Description: Can print the coach schedule in the table
*@param:
*@return:
*@Author:Jin TianYu
*@Date:2021/4/25
*/
public class CoachSchedule {

    @FXML
    private Text Back;

    public void init(Trainer trainer) {

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
