package Boundary;

import Controller.*;
import Entity.Session;
import Entity.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserControlSchedule implements ControlSchedule<User> {
    @FXML
    private TableColumn<Session, LocalDate> timeCol;
    @FXML
    private TableColumn<Session,String> idCol,targetCol,aimCol,noteCol,cancelCol;
    @FXML
    private TableView<Session> table;
    @FXML
    private Text mainPage;
    private User user;

    CancelScheduleControl usc = new CancelScheduleControl();
    public void getUser(User user) throws Exception {
        this.user = user;
    }

    /**
     * @description show the user's schedule on the interface, add the "cancel" function.
     * @param slist
     */
    @Override
    public void printSchedule(ObservableList<Session> slist) {
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        targetCol.setCellValueFactory(new PropertyValueFactory<>("target"));
        aimCol.setCellValueFactory(new PropertyValueFactory<>("PhysicalAbility"));
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
        idCol.setCellValueFactory(new PropertyValueFactory<>("trainerID"));
        File csv = new File("src//Data//Session//" + user.getId() + ".csv");
        Callback<TableColumn<Session, String>, TableCell<Session, String>> DcellFactory
                = new Callback<>() {
            @Override
            public TableCell call(final TableColumn<Session, String> param) {
                final TableCell<Session, String> cell = new TableCell<Session, String>() {
                    final Button btn = new Button("Cancel");
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            String[] l = getTableView().getItems().get(getIndex()).getTime().split(" ");
                            for(int j=0;j<l.length; ){
                                //check: only the session that have not yet been carried out can be cancelled
                                LocalDate date = LocalDate.parse(l[j], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                if (date.isBefore(LocalDate.now())){
                                    break;
                                }
                                btn.setOnAction(event -> {
                                    Session s =getTableView().getItems().get(getIndex());
                                    String i = s.getTime();
                                    slist.remove(s);
                                    try {
                                        if(usc.cancelSession(csv, i, user.getId())){
                                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                            alert.setTitle("Cancel successful!");
                                            alert.setHeaderText("Session on "+i+" was canceled Successful!");
                                            alert.show();
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                });
                                setGraphic(btn);
                                setText(null);
                                j = j + 2;
                            }
                        }
                    }
                };
                return cell;
            }
        };
        cancelCol.setCellFactory(DcellFactory);
        table.setItems(slist);
    }

    @Override
    public void setSchedule(MySchedule<User> us) throws Exception {
        printSchedule(us.mySchedule(user));
    }

    @Override
    public void showSchedule() throws Exception {
        UserSchedule us = new UserSchedule();
        setSchedule(us);
    }

    /**
     * to main page
     * @param mouseEvent
     * @throws IOException
     */
    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toMainPage(mainPage, user);
    }
}
