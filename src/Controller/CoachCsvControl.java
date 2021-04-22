package Controller;

import NetBeans.Trainer;
import NetBeans.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class CoachCsvControl {
    @FXML
    private TableView<Trainer> table;
    @FXML
    private Text adminMain;

    @FXML
    private Text addCoach;

    public void init(){
        readCSV();
    }

    private void readCSV() {
    }



    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) adminMain.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/AdminMain.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }


    public void toAddCoach(MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) addCoach.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/AddCoach.fxml"));
        Parent root = loader.load();
        AddCoach controller = loader.getController();
        //instantiating a user
        controller.init();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
}
