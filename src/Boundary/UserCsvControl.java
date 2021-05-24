package Boundary;

import Controller.CsvReader;
import Entity.Trainer;
import Entity.User;
import javafx.collections.FXCollections;
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
import java.util.ArrayList;
import java.util.List;

public class UserCsvControl implements CsvReader {
    @FXML
    private TableView<User> table;
    @FXML
    private Text adminMain;

    public void init(){

        readCSV();
    }
    public void readCSV()  {
        ObservableList<User> slist= getUserList();

        table.setItems(slist);//将集合的值 存储到tableView里
        TableColumn<User, String> table_name= new TableColumn<User, String>("Name");//创建TableColumn  列名为序号
        TableColumn<User, String> table_id= new TableColumn<User, String>("ID");
        TableColumn<User, String> table_pw= new TableColumn<User, String>("Password");
        TableColumn<User, String> table_gender= new TableColumn<User, String>("Gender");
        /**
         * 反射取值
         */
        table_name.setCellValueFactory(new PropertyValueFactory<User,String>("name"));//相当于getid
        table_id.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        table_pw.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        table_gender.setCellValueFactory(new PropertyValueFactory<User,String>("gender"));
        table.getColumns().add(table_name);
        table.getColumns().add(table_id);
        table.getColumns().add(table_pw);
        table.getColumns().add(table_gender);

    }

    public void toMainPage(MouseEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminMain.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/AdminMain.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
}
