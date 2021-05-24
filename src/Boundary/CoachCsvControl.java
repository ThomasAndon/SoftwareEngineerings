package Boundary;

import Controller.CsvReader;
import Entity.Trainer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;

public class CoachCsvControl implements CsvReader {
    @FXML
    private TableView<Trainer> table;
    @FXML
    private Text adminMain;

    @FXML
    private Text addCoach;



    public void init(){
        addToTable();
    }

    public ObservableList<Trainer> returnCoachList() {
        String str = "src//Data//Account//Trainer.csv";

        return getCoachList(str);

    }

    private void addToTable() {
        ObservableList<Trainer> slist= returnCoachList();

        table.setItems(slist);//将集合的值 存储到tableView里
        TableColumn<Trainer, String> table_name= new TableColumn<Trainer, String>("Name");//创建TableColumn  列名为序号
        TableColumn<Trainer, String> table_id= new TableColumn<Trainer, String>("ID");
        TableColumn<Trainer, String> table_pw= new TableColumn<Trainer, String>("Password");
        TableColumn<Trainer, String> table_gender= new TableColumn<Trainer, String>("Gender");
        /**
         * 反射取值
         */
        table_name.setCellValueFactory(new PropertyValueFactory<Trainer,String>("name"));//相当于getid
        table_id.setCellValueFactory(new PropertyValueFactory<Trainer,String>("trainerID"));
        table_pw.setCellValueFactory(new PropertyValueFactory<Trainer,String>("trainerPw"));
        table_gender.setCellValueFactory(new PropertyValueFactory<Trainer,String>("gender"));
        table.getColumns().add(table_name);
        table.getColumns().add(table_id);
        table.getColumns().add(table_pw);
        table.getColumns().add(table_gender);



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
        loader.setLocation(getClass().getResource("../view/AddCoachUI.fxml"));
        Parent root = loader.load();
        AddCoach controller = loader.getController();
        controller.init();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
}
