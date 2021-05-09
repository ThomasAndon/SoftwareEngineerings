package Controller;

import NetBeans.Trainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CheckCoachProfile {

    @FXML
    private TextField Sex;

    @FXML
    private TextField Height;

    @FXML
    private TextField Phone;

    @FXML
    private TextField ID;

    @FXML
    private TextField Weight;

    @FXML
    private TextField Name;
    @FXML
    private Text coachMain;
    private Trainer trainer;


    // init a coach information
    //todo 个人信息的我认为使用Textfield比table要好，之后应该修改成为使用Text field来打印信息
    public void init(Trainer trainer) {
        this.trainer= trainer;
        IOClass ioClass=new IOClass();
        readCoachProfile(ioClass.CoachProfilePath,trainer.getTrainerID());
    }

    private void readCoachProfile(String coachProfilePath, String trainerID) {
        File file = new File(coachProfilePath +"/" + trainerID +".txt");
        try{

            BufferedReader textFile = new BufferedReader(new FileReader(file));
            String lineDta = textFile.readLine();
            ID.setText(lineDta.split("#")[0]);
            Sex.setText(lineDta.split("#")[1]);
            Height.setText(lineDta.split("#")[2]);
            Weight.setText(lineDta.split("#")[3]);
            Phone.setText(lineDta.split("#")[4]);
            Name.setText(lineDta.split("#")[5]);


        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }

    }

    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) coachMain.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CoachMain.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();

    }


}
