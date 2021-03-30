package Controller;

import NetBeans.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPage {
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

    /**
    * @Description: Login and register check ,check if the input string is correct.
    * @Param:
    * @return:
    * @Author: CloudKing
    * @Date: 2021/3/30
    */
    @FXML
    void onLoginBtnClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        String id = idInput.getText();
        String pw = pwInput.getText();
        if (new ValidChecker().isIDValid(id)) {
            System.out.println("error");

            alert.setTitle("Error");
            alert.setHeaderText("Login failed");
            alert.setContentText("Failed reason : ID is incorrect");
            alert.show();
        }

        if (new ValidChecker().isPwValid(pw)) {

            alert.setTitle("Error");
            alert.setHeaderText("Login failed");
            alert.setContentText("Failed reason : Password is incorrect");
            alert.show();
        }

        // Instantiate a new user, set the id and pw for it.
        User user;

        if (new ValidChecker().isValidAccount(id, pw)) {
            //todo 此处getUser（），从hashmap中获得有特定id的用户。该函数打算写在User类中（或未来新建的ObjectGetter类）
        }


        //todo 此处将此user对象传递给检查存在函数（外部class），看是否存在该用户。不存在则窗口提示，存在则继续

        //todo 存在该用户，跳转到登陆后页面，并且将User对象传递给下一个页面的Controller（外部class，进入下一环节）

    }

    @FXML
    void onRegBtnClicked(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        ValidChecker Checker = new ValidChecker();
        String id = regUserName.getText();
        String pw1 = regPw.getText();
        String pw2 = regPwConfirm.getText();


        //todo 验证id， pw1是否合法（外部class内容，待完成）
        //todo 检查pw1， pw2是否相同
        //todo 合法则存储该对象到User中
        if (Checker.isIDValid(id)) {
            System.out.println("error");

            alert.setTitle("Error");
            alert.setHeaderText("Register failed");
            alert.setContentText("Failed reason : ID is incorrect");
            alert.show();
        }

        else {
            if (Checker.isPwValid(pw1)) {

                alert.setTitle("Error");
                alert.setHeaderText("Register failed");
                alert.setContentText("Failed reason : Password1 is incorrect");
                alert.show();
            }
            else {
                if (Checker.isPwValid(pw2)) {

                    alert.setTitle("Error");
                    alert.setHeaderText("Register failed");
                    alert.setContentText("Failed reason : Password2 is incorrect");
                    alert.show();
                }
                else {
                    if (!Checker.isSameString(pw1, pw2)) {
                        alert.setTitle("Error");
                        alert.setHeaderText("Register failed");
                        alert.setContentText("Failed reason : Password1 and Password2 is not same");
                        alert.show();
                    }
                    else {
                        System.out.println("Register success");
                    }
                }
            }
        }


    }
}
