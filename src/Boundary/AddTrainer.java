package Boundary;

import Controller.AddTrainerControl;
import Controller.ToPage;
import Controller.ValidChecker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddTrainer implements Initializable {

    @FXML
    private TextField CoachPw;
    @FXML
    private TextField CoachName;
    @FXML
    private ChoiceBox<String> genderSelection;
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


    private final static String path = "src/Data/Account/CoachAccounts.txt";

    /**
     * Interface for the admin to add a new coach and save coach information
     * @param actionEvent
     * @throws IOException
     */
    public void SaveInfo(ActionEvent actionEvent) throws Exception {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ValidChecker vc= new ValidChecker();

        if(vc.isNameValid(CoachName.getText()) && !vc.isInvalidID(CoachId.getText()) && !vc.isInvalidID(CoachPw.getText()) && !CoachPhone.getText().equals("")
                && !genderSelection.getValue().equals("") && vc.isValidDouble(CoachHeight.getText())
                && vc.isValidDouble(CoachWeight.getText()) && !CoachIntro.getText().equals("")){

            String name=CoachName.getText();
            String id=CoachId.getText();
            String pw=CoachPw.getText();
            String phone=CoachPhone.getText();
            String gender=genderSelection.getValue();
            Double height=Double.parseDouble(CoachHeight.getText());
            Double weight=Double.parseDouble(CoachWeight.getText());
            String intro=CoachIntro.getText();

            alert.setTitle("Success");
            alert.setContentText("Add coach success");
            alert.show();
            AddTrainerControl c = new AddTrainerControl();
            c.SaveInfo(name, id, pw, gender, phone, height, weight, intro);
        }
        else if(vc.isAccountExists(path,CoachId.getText(),CoachPw.getText())){
            alert.setTitle("Failed");
            alert.setContentText("Failed reason :ID has already exist.");
            alert.show();
        }
        else {
            alert.setTitle("Error");
            alert.setHeaderText("Add coach failed");
            alert.setContentText("Failed reason : The information is wrong.\n"+
                    "Please check :\n" +
                    "1.if your password length is between 4-15\n"+
                    "2.if you select gender\n"+
                    "3.if your input phone number's length is 11\n"+
                    "4.if you input correct height or weight (only number)\n"+
                    "5.if you add self introduction");
            alert.show();

        }
    }


    public void toLastPage(MouseEvent mouseEvent) throws IOException {
        new ToPage().toMainPage(backBt,0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genderSelection.getItems().addAll("Male", "Female");
    }
}
