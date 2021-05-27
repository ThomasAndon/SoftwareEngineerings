package Boundary;

import Controller.EditAdControl;
import Controller.ToPage;
import Entity.Advertisement;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;

import java.io.*;

public class EditAd {
    @FXML
    private Text adminMain;
    @FXML
    private TextArea Ad;
    @FXML
    private TextField AdName;

    private Advertisement advertisement = new Advertisement();

    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        new ToPage().toMainPage(adminMain,0);
    }

    public void saveAd(ActionEvent actionEvent) throws IOException {
        advertisement.setName(AdName.getText());

        new EditAdControl().saveAd(advertisement.getName(), Ad.getText());//todo 去这个方法里写你没写完的方法

    }


}
