package Boundary;

import Controller.AddCoachControl;
import Controller.ToPage;
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

    public void SaveInfo(ActionEvent actionEvent) throws IOException {
        String name=CoachName.getText();
        String id=CoachId.getText();
        String pw=CoachPw.getText();
        String gender=CoachGender.getText();
        String phone=CoachPhone.getText();
        AddCoachControl c = new AddCoachControl();
        c.SaveInfo(name,id,pw,gender,phone);
    }

    public void toLastPage(MouseEvent mouseEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toCoachCSVPage(backBt);//TODO 检查一下需不需要init，如果需要在ToPage的方法里改
    }
}
