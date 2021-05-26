package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

public class AddCoachControl {

    @FXML
    private TextField CoachPw;

    @FXML
    private TextField CoachName;

    @FXML
    private TextField CoachGender;

    @FXML
    private TextField CoachId;

    @FXML
    private TextField CoachPhone;

    @FXML
    private Text backBt;

    public void SaveInfo(String name,String id,String pw,String gender,String phone) throws IOException {

        File f = new File("src//Data//Account//CoachAccounts.txt");
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f,true));
        BufferedWriter bw = new BufferedWriter(out);
        if(!f.exists()){
            f.createNewFile();
        }
        bw.write(id+ " " + pw+ " # " + "\n");
        bw.flush();
        bw.close();
        System.out.println(name);

    }

    public void toLastPage(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) backBt.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CoachCsv.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
}
