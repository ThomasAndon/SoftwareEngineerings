package Boundary;

import Controller.InformationReader;
import Controller.ToPage;
import Entity.Session;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.*;
import java.time.LocalDate;

/**
 * Admin can see the Session table form this interface
 */
public class SessionInfo {

    @FXML
    private TableView<Session> table;

    @FXML
    private Text adminMain;

    /**
     * paarse csv and print in on the table
     */
    public void readCSV() {
        ObservableList<Session> slist= new InformationReader().getSessionList();
        table.setItems(slist);
        TableColumn<Session, String> table_user_id= new TableColumn<Session, String>("UserId");
        TableColumn<Session, String> table_trainer_id= new TableColumn<Session, String>("TrainerId");
        TableColumn<Session, LocalDate> table_date= new TableColumn<Session, LocalDate>("Time");

        table_user_id.setCellValueFactory(new PropertyValueFactory<Session, String>("userID"));
        table_trainer_id.setCellValueFactory(new PropertyValueFactory<Session, String>("trainerID"));
        table_date.setCellValueFactory(new PropertyValueFactory<Session, LocalDate>("time"));

        table.getColumns().add(table_user_id);
        table.getColumns().add(table_trainer_id);
        table.getColumns().add(table_date);

    }

    /**
     * go to main page
     * @param mouseEvent
     * @throws IOException
     */
    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        new ToPage().toMainPage(adminMain,0);
    }
}

