
package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginPage {

    @FXML
    private PasswordField pwInput;

    @FXML
    private Button loginBtn;

    @FXML
    private Button loginBtn1;

    @FXML
    private TextField idInput;

    @FXML
    private TextField regUserName;

    @FXML
    private TextField idInput3;

    @FXML
    private TextField idInput2;

    @FXML
    void onLoginBtnClicked(ActionEvent event) {


    }
    /**
    * @Description:  用来储存用户信息
    * @Param:
    * @return:
    * @Author: CloudKing
    * @Date:
    */
    @FXML
    void onLoginBtn1Clicked(ActionEvent event) throws IOException {

        String username= regUserName.getText();
        String pwd=idInput2.getText();

        System.out.println(username);
        System.out.println(pwd);
        userpagecontrol.go_to_userpage();





    }

}

























