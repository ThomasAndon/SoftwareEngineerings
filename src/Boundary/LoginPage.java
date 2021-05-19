package Boundary;

import Controller.MatchAccount;
import Controller.SetProfile;
import Controller.ValidChecker;
import Controller.WriteUserAccounts;
import Entity.Trainer;
import Entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginPage implements ValidChecker {
    @FXML
    private TextField idInput;

    @FXML
    private PasswordField pwInput;


    @FXML
    private TextField regUserName;

    @FXML
    private PasswordField regPw;

    @FXML
    private PasswordField regPwConfirm;

    @FXML
    private Button loginBtn;

    @FXML
    private Button regBtn;


    @FXML
    private Label CALogin;


    @FXML
    private Label adminLogin;


    /**
     * @Description: Login and register check, check if the input string is correct.
     * @Param:
     * @return:
     * @Author: CloudKing
     * @Date: 2021/3/30
     */
    @FXML
    void onLoginBtnClicked(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Error");
        alert.setHeaderText("Login failed");


        //ValidChecker Checker = new ValidChecker();
        String id = idInput.getText();
        String pw = pwInput.getText();
        if (isInvalidID(id)) {
            System.out.println("error");
            alert.setContentText("Failed reason : ID is invalid");
            alert.show();
            return;
        } else {

            if (isInvalidPw(pw)) {
                alert.setContentText("Failed reason : Password is invalid");
                alert.show();
                return;
            }

        }
        //todo 此处是格式通过验证
        System.out.println("OK");

        // If ID not exists or not matching pw, error occurs.
        if(!isValidAccount(id,pw)){
            alert.setContentText("Failed reason : Unidentified user");
            alert.show();
            return;
        }

        System.out.println("Login success");

        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("../view/UserMainUI.fxml"));
        Parent root = loader.load();
        //  ProfileInfo controller = loader.getController();
        UserMain controller = loader.getController();
        //instantiating a user
        User user = new User(id,pw);

        try {
            User user1 = new SetProfile().setUserProfile(user);
            controller.initData(user1);
        } catch (Exception e) {
            System.out.println("Login Exception Caught");
            controller.initData(user);
        }
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
        //todo 此处将此user对象传递给检查存在函数（外部class），看是否存在该用户。不存在则窗口提示，存在则继续

        //todo 存在该用户，跳转到登陆后页面，并且将User对象传递给下一个页面的Controller（外部class，进入下一环节）

    }

    @FXML
    void onRegBtnClicked(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Error");
        alert.setHeaderText("Register failed");


        //ValidChecker Checker = new ValidChecker();
        String id = regUserName.getText();
        String pw1 = regPw.getText();
        String pw2 = regPwConfirm.getText();
        if (isInvalidID(id)) {
            System.out.println("error");
            alert.setContentText("Failed reason : ID is invalid");
            alert.show();
            return;
        } else {
            if (isInvalidPw(pw1)) {

                alert.setContentText("Failed reason : Password1 is invalid");
                alert.show();
                return;
            } else {
                if (isInvalidPw(pw2)) {

                    alert.setContentText("Failed reason : Password2 is invalid");
                    alert.show();
                    return;
                } else {
                    if (!isSameString(pw1, pw2)) {
                        alert.setContentText("Failed reason : Password1 and Password2 is different");
                        alert.show();
                        return;
                    }
                }
            }
        }
        // end if-else
        //todo 字符串格式通过
        System.out.println("OK");
        if(!new WriteUserAccounts().writeNewUser(id, pw1)) {
            alert.setContentText("Failed reason : ID already exists.");
            alert.show();
            return;
        }

        //todo 弹出成功窗口，注册成功
        Alert affir = new Alert(Alert.AlertType.CONFIRMATION);
        affir.setTitle("Register Succeeded");
        affir.setContentText("Done! Your ID is \"" + id+"\"");
        affir.show();

    }




    @FXML
    /**
     * This is the function when coach login is pressed
     */
    void onCALoginClicked(MouseEvent event) throws Exception {
        String id = idInput.getText();
        String pw = pwInput.getText();
       // ValidChecker vc = new ValidChecker();
        MatchAccount io = new MatchAccount();

        if ((isInvalidID(id)) || isInvalidPw(pw) || !isValidAccount(io.coachAccountFilePath,id,pw)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setHeaderText("Coach Login Error");
            alert.setContentText("Wrong input. Try again.");
            alert.show();
            return;

        }

        // todo 此处是登录成功
        System.out.println("Coach login OK");
        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();

        //Below is instantiating the user and passing it to the next window.
        FXMLLoader loader = new FXMLLoader();
        //   loader.setLocation(getClass().getResource("../view/ProfileInfo.fxml"));
        loader.setLocation(getClass().getResource("../view/TrainerMainUI.fxml"));
        Parent root = loader.load();
        //  ProfileInfo controller = loader.getController();
        TrainerMain controller = loader.getController();
        //instantiating a user
        Trainer trainer = new Trainer(id,pw);

        try {
            Trainer trainer1 = new SetProfile().setCoachProfile(trainer);
            controller.initData(trainer1);
        } catch (Exception e) {
            System.out.println("Login Exception Caught");
            controller.initData(trainer);
        }

        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }



    @FXML
    void onAdminLoginClicked(MouseEvent event) throws Exception {
        String id = idInput.getText();
        String pw = pwInput.getText();

      //  ValidChecker vc = new ValidChecker();
        MatchAccount io = new MatchAccount();


        if ((isInvalidID(id)) || isInvalidPw(pw) || !isValidAccount(io.adminAccountFilePath,id,pw)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login error");
            alert.setHeaderText("Admin Login Error");
            alert.setContentText("Wrong input. Try again.");
            alert.show();
            return;

        }

        // todo 此处是登录成功
        System.out.println("Admin Login OK");

        Stage stage = (Stage) loginBtn.getScene().getWindow();
        stage.close();

        //Below is instantiating the user and passing it to the next window.
        FXMLLoader loader = new FXMLLoader();;
        loader.setLocation(getClass().getResource("../view/AdminMain.fxml"));
        Parent root = loader.load();
        //  ProfileInfo controller = loader.getController();
        AdminMain controller = loader.getController();

        stage.setTitle("Hello admin");
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }

}
