package Boundary;

import Controller.CsvReader;
import Controller.ToPage;
import Entity.Session;
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
import java.time.LocalDate;
import java.util.Comparator;

public class SessionCsvControl{

    @FXML
    private TableView<Session> table;

    @FXML
    private Text adminMain;

    public void init() {
        readCSV();
    }

    public void readCSV() {
        ObservableList<Session> slist= new CsvReader().getSessionList();
        table.setItems(slist);
        TableColumn<Session, String> table_user_id= new TableColumn<Session, String>("UserId");
        TableColumn<Session, String> table_trainer_id= new TableColumn<Session, String>("TrainerId");
        TableColumn<Session, LocalDate> table_date= new TableColumn<Session, LocalDate>("Time");//创建TableColumn  列名为序号

        /**
         * 反射取值
         */
        table_user_id.setCellValueFactory(new PropertyValueFactory<Session, String>("userID"));//getName
        table_trainer_id.setCellValueFactory(new PropertyValueFactory<Session, String>("trainerID"));//getName
        table_date.setCellValueFactory(new PropertyValueFactory<Session, LocalDate>("time"));//相当于getid

        table.getColumns().add(table_user_id);
        table.getColumns().add(table_trainer_id);
        table.getColumns().add(table_date);

    }

    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        new ToPage().toMainPage(adminMain,0);
    }
}

