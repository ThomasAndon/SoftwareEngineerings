package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

/**
*@Description: For the admin to add a coach
*@param:
*@return:
*@Author:Jin TianYu
*@Date:2021/4/25
*/
public class AddCoach {

    @FXML
    private TextField CoachPw;

    @FXML
    private TextField CoachName;

    @FXML
    private TextField CoachGender;

    @FXML
    private TextField CoachId;

    @FXML
    private TextField CoachPhone;

    @FXML
    private Text backBt;

    public void init() {

    }

    public void AddCoach(ActionEvent actionEvent) throws Exception {

        String id=CoachId.getText();
        String pw=CoachPw.getText();


        IOClass ioClass=new IOClass();
        ioClass.writeAccounts(ioClass.coachAccountFilePath,id,pw);


    }

    public void toLastPage(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) backBt.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CoachCsv.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
}
