package Controller;

import NetBeans.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileInfo implements Initializable {



    User currentUser;


    @FXML
    private Label IDHolder;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField heightInput;

    @FXML
    private TextField weightInput;

    @FXML
    private ChoiceBox<String> genderSelection;//= new ChoiceBox<String>(FXCollections.observableArrayList("Male","Female"));;

    @FXML
    private Button saveBtn;

    @FXML
    private Label VIPLevelHolder;

    @FXML
    private Label expDateHolder;


    /**
     * This is the method called after login, and the page needs to be filled with the user's data. Set
     * it up within this function.
     * @param user
     */
    public void initData(User user) {
        currentUser = user;

        IDHolder.setText(user.getId());
        nameInput.setText(user.getName());
        heightInput.setText(String.valueOf(user.getHeight()));
        weightInput.setText(String.valueOf(user.getWeight()));

        genderSelection.getItems().addAll("Male", "Female");
        if (user.getGender().equals("Male")) {
            genderSelection.getSelectionModel().select(0);
        } else {
            if(user.getGender().equals("Female")) {
                genderSelection.getSelectionModel().select(1);
            }
        }

        VIPLevelHolder.setText(String.valueOf(user.getLevel()));


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    /** This method is called whenever the save button of the profile Info panel is clicked.
     * @author Thomas Andon
     */
    void onSaveBtnClicked(ActionEvent event) {
        String inputName = nameInput.getText();

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

        //Following part prompts the username.
        if (!new ValidChecker().isNameValid(inputName)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Username must be 4-15 chars");
            alert.show();
            return;
        }
        System.out.println("Info Accepted");


        //Following part prompts the gender selection.
        String gender = genderSelection.getValue();
        if (gender == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText("Error");
            a.setContentText("Please choose your gender");
            a.show();
            return;
        }

        //todo 成功，存储信息
        //currentUser.setWeight(Double.parseDouble(weightInput.getText()));
        //currentUser.setHeight(Double.parseDouble(heightInput.getText()));
        currentUser.setHeight(heightInput.getText());
        currentUser.setWeight(weightInput.getText());
        currentUser.setGender(gender);
        currentUser.setName(inputName);
        try {
            new IOClass().writeUserProfile(currentUser);
        } catch (IOException e) {
            System.out.println("Error occurs when writing user profile");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Error occurs when writing user profile");
        }


    }
}
