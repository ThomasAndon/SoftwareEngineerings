package Boundary;

import Boundary.ScheduleControl;
import Controller.CsvReader;
import Entity.Session;
import Entity.Trainer;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SessionCsvControl implements Comparator< Session >, CsvReader {

    @FXML
    private TableView<Session> table;

    @FXML
    private Text adminMain;

    public void init() {
        readCSV();
    }

    public void readCSV() {
        ObservableList<Session> slist= getSessionList();
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
    @Override
    public int compare(Session o1, Session o2) {
        if ( o1.equals( o2 ) || o1.getTime().equals(o2.getTime()) ) {
            return 0;
        }
        else if ( o1.getTime().compareTo(o2.getTime())>0 ) {
            return 1;
        }
        else {
            return -1;
        }
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
}

