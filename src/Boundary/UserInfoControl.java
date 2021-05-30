package Boundary;

import Controller.InformationReader;
import Controller.GetAllInformation;
import Entity.User;
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

public class UserInfoControl {
    @FXML
    private TableView<User> table;
    @FXML
    private Text adminMain;


    private InformationReader cr = new InformationReader();
    private GetAllInformation get = new GetAllInformation();

    public void init() throws IOException {
        get.GetInfo("userProfile","AllUserInfo.txt");
        readCSV();
    }
    public void readCSV()  {
        ObservableList<User> slist= cr.getUserList();

        table.setItems(slist);//将集合的值 存储到tableView里
        TableColumn<User, String> table_id= new TableColumn<User, String>("ID");
        TableColumn<User, String> table_name= new TableColumn<User, String>("Name");//创建TableColumn  列名为序号
        TableColumn<User, Double> table_height= new TableColumn<User, Double>("Height");
        TableColumn<User, Double> table_weight= new TableColumn<User, Double>("Weight");
        TableColumn<User, String> table_gender= new TableColumn<User, String>("Gender");
        TableColumn<User, String> table_trainer= new TableColumn<User, String>("Trainer");
        TableColumn<User, Integer> table_level= new TableColumn<User, Integer>("Level");
        /**
         * 反射取值
         */
        table_name.setCellValueFactory(new PropertyValueFactory<User,String>("name"));//相当于getid
        table_id.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        table_height.setCellValueFactory(new PropertyValueFactory<User,Double>("height"));
        table_weight.setCellValueFactory(new PropertyValueFactory<User,Double>("weight"));
        table_gender.setCellValueFactory(new PropertyValueFactory<User,String>("gender"));
        table_level.setCellValueFactory(new PropertyValueFactory<User,Integer>("level"));
        table_trainer.setCellValueFactory(new PropertyValueFactory<User,String>("trainerID"));

        table.getColumns().add(table_name);
        table.getColumns().add(table_id);
        table.getColumns().add(table_height);
        table.getColumns().add(table_weight);
        table.getColumns().add(table_gender);
        table.getColumns().add(table_level);
        table.getColumns().add(table_trainer);

    }

    public void toMainPage(MouseEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminMain.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/AdminMainUI.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
}
