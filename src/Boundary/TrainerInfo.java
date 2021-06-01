package Boundary;

import Controller.InformationReader;
import Controller.GetAllInformation;
import Controller.ToPage;
import Entity.Trainer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.*;

/**
 * Display the coaches information in the UI
 */
public class TrainerInfo {
    @FXML
    private TableView<Trainer> table;
    @FXML
    private Text adminMain;
    @FXML
    private Text addCoach;


    private InformationReader cr = new InformationReader();
    private GetAllInformation get = new GetAllInformation();

    /**
     *Get the coaches information
     * @throws IOException
     */
//    public void init() throws IOException {
//        get.GetInfo("coachProfile","AllCoachInfo.txt");
//        addToTable();
//    }

    public ObservableList<Trainer> returnCoachList() throws IOException {
        return cr.getCoachList();
    }

    /**
     * Pass value to the table
     * @throws IOException
     */
    public void addToTable() throws IOException {
        get.GetInfo("coachProfile","AllCoachInfo.txt");
        ObservableList<Trainer> slist= cr.getCoachList();

        table.setItems(slist);
        TableColumn<Trainer, String> table_name= new TableColumn<Trainer, String>("Name");
        TableColumn<Trainer, String> table_id= new TableColumn<Trainer, String>("ID");
        TableColumn<Trainer, Double> table_height= new TableColumn<Trainer, Double>("Height");
        TableColumn<Trainer, Double> table_weight= new TableColumn<Trainer, Double>("Weight");
        TableColumn<Trainer, String> table_gender= new TableColumn<Trainer, String>("Gender");
        TableColumn<Trainer, Integer> table_phone= new TableColumn<Trainer, Integer>("Phone");
        TableColumn<Trainer, String> table_intro= new TableColumn<Trainer, String>("Introduction");

        table_name.setCellValueFactory(new PropertyValueFactory<Trainer,String>("name"));
        table_id.setCellValueFactory(new PropertyValueFactory<Trainer,String>("trainerID"));
        table_height.setCellValueFactory(new PropertyValueFactory<Trainer,Double>("height"));
        table_weight.setCellValueFactory(new PropertyValueFactory<Trainer,Double>("weight"));
        table_gender.setCellValueFactory(new PropertyValueFactory<Trainer,String>("gender"));
        table_phone.setCellValueFactory(new PropertyValueFactory<Trainer,Integer>("Tel"));
        table_intro.setCellValueFactory(new PropertyValueFactory<Trainer,String>("Intro"));

        table.getColumns().add(table_name);
        table.getColumns().add(table_id);
        table.getColumns().add(table_height);
        table.getColumns().add(table_weight);
        table.getColumns().add(table_gender);
        table.getColumns().add(table_phone);
        table.getColumns().add(table_intro);


    }


    /**
     * go to main page
     * @param mouseEvent
     * @throws IOException
     */
    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        new ToPage().toMainPage(adminMain,0);
    }

    /**
     * go to add coach page
     * @param mouseEvent
     * @throws IOException
     */
    public void toAddCoach(MouseEvent mouseEvent) throws IOException {
        new ToPage().toAddCoach(addCoach);
    }
}
