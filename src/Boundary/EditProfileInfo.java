package Boundary;

import Controller.IOClass;
import Controller.ValidChecker;
import Entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditProfileInfo implements Initializable, ValidChecker {

    User currentUser;

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
    private ChoiceBox<String> genderSelection;

    @FXML
    private Button saveBtn;

    @FXML
    private Label VIPLevelHolder;

    private User user;

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
        this.user = user;

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
        if (!isNameValid(inputName)) {
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

        //todo 成功，存储信息 (内存、永存、显示）
        currentUser.setWeight(Double.parseDouble(weightInput.getText()));
        currentUser.setHeight(Double.parseDouble(heightInput.getText()));
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


    @FXML
    /** This method is invocated whenever the user wants to upgrade
     * @author Thomas Andon
     */
    void onUpgradeClicked(ActionEvent event) {

        //todo 以后如果加入了最高会员等级，在这里加一个ifelse判断，不符合就弹窗以及return来跳出此函数
        if (currentUser.getLevel()>= currentUser.MAX_VIP_LEVEL) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You've reached the highest level of VIP now.");
            alert.show();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Confirmation");
        alert.setContentText("You are now level " + (currentUser.getLevel()+1) + " now");
        alert.show();
        //内存里的用户对象数据更改；显示的等级数字更改；永存里文件数据也要改。
        currentUser.setLevel(currentUser.getLevel()+1);
        VIPLevelHolder.setText(String.valueOf(currentUser.getLevel()));
        try {
            new IOClass().writeUserProfile(currentUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) mainPage.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/UserMainUI.fxml"));
        Parent root = loader.load();
        UserMain controller = loader.getController();
        //instantiating a user
        controller.initData(user);
        // stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
        //todo 这个函数要和接口集成一下
    }
}
