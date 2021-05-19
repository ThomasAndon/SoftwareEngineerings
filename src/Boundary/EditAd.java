package Boundary;

import Controller.WriteAd;
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

    public void saveAd(ActionEvent actionEvent) throws IOException {
        String s = Ad.getText();
        WriteAd wa = new WriteAd();
        wa.writeAd("src//Data//Advertisement//Ad.txt",s);
        //todo 实现一个AD写入一个文件当中

    }


}
