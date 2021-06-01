package Boundary;

import Controller.InformationReader;
import Controller.GetAllInformation;
import Controller.ToPage;
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

public class UserInfo {
    @FXML
    private TableView<User> table;
    @FXML
    private Text adminMain;

    private InformationReader cr = new InformationReader();
    private GetAllInformation get = new GetAllInformation();

    /**
     * Initial method called when loading the page
     * @throws IOException
     */
    public void init() throws IOException {
        get.GetInfo("userProfile","AllUserInfo.txt");
        readCSV();
    }

    /**
     * set csv to table
     */
    public void readCSV()  {
        ObservableList<User> slist= cr.getUserList();

        table.setItems(slist);
        TableColumn<User, String> table_id= new TableColumn<User, String>("ID");
        TableColumn<User, String> table_name= new TableColumn<User, String>("Name");
        TableColumn<User, Double> table_height= new TableColumn<User, Double>("Height");
        TableColumn<User, Double> table_weight= new TableColumn<User, Double>("Weight");
        TableColumn<User, String> table_gender= new TableColumn<User, String>("Gender");
        TableColumn<User, String> table_trainer= new TableColumn<User, String>("Trainer");
        TableColumn<User, Integer> table_level= new TableColumn<User, Integer>("Level");

        table_name.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
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
        new ToPage().toMainPage(adminMain,0);
    }
}
