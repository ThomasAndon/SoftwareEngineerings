package Boundary;

import Controller.AddTrainerControl;
import Controller.ToPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

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
    private TextField CoachHeight;
    @FXML
    private TextField CoachWeight;
    @FXML
    private TextArea CoachIntro;

    @FXML
    private Text backBt;

    /**
     * Interface for the admin to add a new coach and save coach information
     * @param actionEvent
     * @throws IOException
     */
    public void SaveInfo(ActionEvent actionEvent) throws IOException {
        String name=CoachName.getText();
        String id=CoachId.getText();
        String pw=CoachPw.getText();
        String gender=CoachGender.getText();
        String phone=CoachPhone.getText();
        Double height=Double.parseDouble(CoachHeight.getText());
        Double weight=Double.parseDouble(CoachWeight.getText());
        String intro=CoachIntro.getText();


        AddTrainerControl c = new AddTrainerControl();
        c.SaveInfo(name,id,pw,gender,phone,height,weight,intro);
    }

    /**
     * Click and change page
     * @param mouseEvent
     * @throws IOException
     */
    public void toLastPage(MouseEvent mouseEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toCoachCSVPage(backBt);//TODO 检查一下需不需要init，如果需要在ToPage的方法里改
    }
}
