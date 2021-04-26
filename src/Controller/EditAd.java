package Controller;

import NetBeans.Advertisement;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import java.io.*;

/**
*@Description: Used for the admin to edit the advertisement displayed in the main page
*@param:
*@return:
*@Author:Jin TianYu
*@Date:2021/4/25
*/
public class EditAd {
    @FXML
    private Text adminMain;


    @FXML
    private TextArea Ad;




    public void init() { }

    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) adminMain.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/AdminMain.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
    /**
    *@Description: Save advertisement
    *@param: actionEvent
    *@return:
    *@Author:Jin TianYu
    *@Date:2021/4/26
    */
    public void saveAd(ActionEvent actionEvent) throws IOException {
        String s=Ad.getText();
        System.out.println(s);
        IOClass ioClass=new IOClass();
        ioClass. writeAd(ioClass.AdPath,s);


    }


}
