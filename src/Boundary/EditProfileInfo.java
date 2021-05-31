package Boundary;

import Controller.*;
import Entity.Trainer;
import Entity.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditProfileInfo implements Initializable {
    @FXML
    public Text adminMain;
    User currentUser;
    Trainer chosenTrainer = new Trainer();
    String selectedTrainerID;
    ArrayList<String> students = new ArrayList<>();
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
    @FXML
    private ChoiceBox<String> CoachChoiceBox;
    @FXML
    private Button selectCoachBtn;


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

        TrainerInfo ccc = new TrainerInfo();
        ObservableList<Trainer> coachList = null;
        try {
            coachList = ccc.returnCoachList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0;i < coachList.size();i++) {
            CoachChoiceBox.getItems().add(coachList.get(i).getName()+'-'+coachList.get(i).getTrainerID());
            if(user.getTrainerID().equals(coachList.get(i).getTrainerID())) {
                CoachChoiceBox.getSelectionModel().select(i);
            }
        }


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

        currentUser.setWeight(Double.parseDouble(weightInput.getText()));
        currentUser.setHeight(Double.parseDouble(heightInput.getText()));
        currentUser.setGender(gender);
        currentUser.setName(inputName);


        if (currentUser.getLevel()!=0) {
            try {
                String selectedTrainerID = (String) CoachChoiceBox.getSelectionModel().getSelectedItem().split("-")[1];
                currentUser.setTrainerID(selectedTrainerID);
                chosenTrainer.setTrainerID(selectedTrainerID);
                students.add(currentUser.getId());
                chosenTrainer = new SetTrainerProfile().setProfile(chosenTrainer);
                chosenTrainer.setStudents(students);
           } catch (Exception e) {
                System.out.println("Trainer not selected");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Error");
                alert.setContentText("Coach not selected.");
                alert.show();
                return;
            }
        }

        try {
            new WriteUserProfile().writeUserProfile(currentUser);
            new WriteTrainerProfile().writeCoachProfile(chosenTrainer);
        } catch (IOException e) {
            System.out.println("Error occurs when writing user profile");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Error occurs when writing user profile");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @FXML
    /** This method is invocated whenever the user wants to upgrade
     * @author Thomas Andon
     */
    void onUpgradeClicked(ActionEvent event) {

        if (CoachChoiceBox.getValue()==null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("You should first choose a coach to become a vip.");
            alert.show();
            return;
        }


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

        String selectedTrainerID = (String) CoachChoiceBox.getSelectionModel().getSelectedItem().split("-")[1];
        currentUser.setTrainerID(selectedTrainerID);
        currentUser.setLevel(currentUser.getLevel()+1);
        VIPLevelHolder.setText(String.valueOf(currentUser.getLevel()));
        try {
            new WriteUserProfile().writeUserProfile(currentUser);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void onSelectCoachBtnClicked(ActionEvent event) {
        try {
            String selectedTrainerID = (String) CoachChoiceBox.getSelectionModel().getSelectedItem().split("-")[1];
            this.selectedTrainerID = selectedTrainerID;
            onSaveBtnClicked(event);
        } catch (Exception e) {
            System.out.println("Trainer not selected");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("Coach not selected.");
            alert.show();
            return;

        }

    }




    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        new ToPage().toMainPage(mainPage,currentUser);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
