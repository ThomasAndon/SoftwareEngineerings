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

public class CoachInfoControl {
    @FXML
    private TableView<Trainer> table;
    @FXML
    private Text adminMain;
    @FXML
    private Text addCoach;


    private InformationReader cr = new InformationReader();
    private GetAllInformation get = new GetAllInformation();

    public void init() throws IOException {
        get.GetInfo("coachProfile","AllCoachInfo.txt");
        addToTable();
    }

    public ObservableList<Trainer> returnCoachList() throws IOException {

        return cr.getCoachList();

    }

    private void addToTable() throws IOException {
        ObservableList<Trainer> slist= returnCoachList();

        table.setItems(slist);//将集合的值 存储到tableView里
        TableColumn<Trainer, String> table_name= new TableColumn<Trainer, String>("Name");//创建TableColumn  列名为序号
        TableColumn<Trainer, String> table_id= new TableColumn<Trainer, String>("ID");
        TableColumn<Trainer, Double> table_height= new TableColumn<Trainer, Double>("Height");
        TableColumn<Trainer, Double> table_weight= new TableColumn<Trainer, Double>("Weight");
        TableColumn<Trainer, String> table_gender= new TableColumn<Trainer, String>("Gender");
        TableColumn<Trainer, Integer> table_phone= new TableColumn<Trainer, Integer>("Phone");
        TableColumn<Trainer, String> table_intro= new TableColumn<Trainer, String>("Introduction");
        /**
         * 反射取值
         */
        table_name.setCellValueFactory(new PropertyValueFactory<Trainer,String>("name"));//相当于getid
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



    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        new ToPage().toMainPage(adminMain,0);
    }

    public void toAddCoach(MouseEvent mouseEvent) throws IOException {
        new ToPage().toAddCoach(addCoach);
    }
}
