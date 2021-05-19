package Boundary;

import Controller.IOClass;
import Controller.ToPage;
import Controller.ValidChecker;
import Entity.Trainer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;

public class CoachProfile implements ValidChecker {
    Trainer currentTrainer;
    @FXML
    public Text mainPage;
    @FXML
    private Label IDHolder;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField heightInput;

    @FXML
    private TextField weightInput;
    @FXML
    private TextField Phone;

    @FXML
    private ChoiceBox<String> genderSelection;

    @FXML
    private Button saveBtn;

    private Trainer trainer;

    public void initData(Trainer trainer) {
        currentTrainer = trainer;
        IDHolder.setText(trainer.getTrainerID());
        nameInput.setText(trainer.getName());
        heightInput.setText(String.valueOf(trainer.getHeight()));
        weightInput.setText(String.valueOf(trainer.getWeight()));
        Phone.setText(String.valueOf(trainer.getTel()));


        genderSelection.getItems().addAll("Male", "Female");
        if (trainer.getGender().equals("Male")) {
            genderSelection.getSelectionModel().select(0);
        } else {
            if(trainer.getGender().equals("Female")) {
                genderSelection.getSelectionModel().select(1);
            }
        }

        this.trainer = trainer;

    }
    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        ToPage toPage = new ToPage();
        String s= "TrainerMainUI";
        toPage.toMainPage(mainPage,s, trainer);
    }

    public void onSaveBtnClicked(ActionEvent actionEvent) {


        //Following part checks the height and weight.
        try {
            double height = Double.parseDouble(heightInput.getText());
            double weight = Double.parseDouble(weightInput.getText());

            if (height < 0 || weight < 0) {
                throw new Exception();
            }

        } catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Height and weight must be numbers bigger than 0");
            alert.show();
            return;

        }

        String inputName = nameInput.getText();

        if (!isNameValid(inputName)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Username must be 4-15 chars");
            alert.show();
            return;
        }
        System.out.println("Info Accepted");

        String gender = genderSelection.getValue();
        if (gender == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Error");
            a.setContentText("Please choose your gender");
            a.show();
            return;
        }

        int inputPhone = Integer.parseInt(Phone.getText());


        currentTrainer.setWeight(Double.parseDouble(weightInput.getText()));
        currentTrainer.setHeight(Double.parseDouble(heightInput.getText()));
        currentTrainer.setGender(gender);
        currentTrainer.setName(inputName);
        currentTrainer.setTel(inputPhone);
        try {
            new IOClass().writeCoachProfile(currentTrainer);
        } catch (IOException e) {
            System.out.println("Error occurs when writing coach profile");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Error occurs when writing coach profile");
        }
    }
}
