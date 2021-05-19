package Boundary;

import Controller.ControlSchedule;
import Controller.MySchedule;
import Controller.ToPage;
import Controller.TrainerSchedule;
import Entity.Session;
import Entity.Trainer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.time.LocalDate;

public class TrainerControlSchedule implements ControlSchedule<Trainer> {
    @FXML
    private TableColumn<Session, LocalDate> timeCol;
    @FXML
    private TableColumn<Session,String> idCol,targetCol,aimCol,noteCol,cancelCol;
    @FXML
    private TableView<Session> table;
    @FXML
    private Text mainPage;
    private Trainer trainer;
    ObservableList<Session> list;

    public void getTrainer(Trainer trainer) throws Exception {
        this.trainer = trainer;
    }

    @Override
    public void printSchedule(ObservableList<Session> slist) {
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        targetCol.setCellValueFactory(new PropertyValueFactory<>("target"));
        aimCol.setCellValueFactory(new PropertyValueFactory<>("PhysicalAbility"));
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
        idCol.setText("Student ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
        //todo 教练可以添加训练的备注
        cancelCol.setText("Note");
        table.setItems(slist);
    }

    @Override
    public void setSchedule(MySchedule<Trainer> ts) throws Exception {
        printSchedule(ts.mySchedule(trainer));
    }

    public void showSchedule() throws Exception {
        TrainerSchedule ts = new TrainerSchedule();
        setSchedule(ts);
    }

    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toTrainerMainPage(mainPage, trainer);
    }
}
