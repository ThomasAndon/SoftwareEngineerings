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

    public void saveAd(ActionEvent actionEvent) throws IOException {

        writeAD("src//Data//Advertisement//Ad.txt");
        //todo 实现一个AD写入一个文件当中

    }

    public void writeAD(String path) throws IOException {
        String s=Ad.getText();
        System.out.println(s);
        File f = new File(path);
        if(!f.exists()){
            f.createNewFile();
        }
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f,true));
        BufferedWriter bw = new BufferedWriter(out);
        bw.write(s);
        bw.flush();
        bw.close();

    }
}
