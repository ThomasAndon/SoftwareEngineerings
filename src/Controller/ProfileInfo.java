package Controller;

import NetBeans.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfileInfo implements Initializable {
    @FXML
    private Label IDHolder;

    @FXML
    private TextField nameInput;

    @FXML
    private TextField heightInput;

    @FXML
    private TextField weightInput;

    @FXML
    private ChoiceBox<String> genderSelection = new ChoiceBox<String>(FXCollections.observableArrayList("Male","Female"));;

    @FXML
    private Button saveBtn;

    @FXML
    private Label VIPLevelHolder;


    /**
     * This is the method called after login, and the page needs to be filled with the user's data. Set
     * it up within this function.
     * @param user
     */
    public void initData(User user) {
        IDHolder.setText(user.getId());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void onSaveBtnClicked(ActionEvent event) {

    }
}
