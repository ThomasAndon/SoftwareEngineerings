package Boundary;

import Controller.BookSessionControl;
import Controller.ToPage;
import Entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Callback;
import java.io.*;
import java.time.LocalDate;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import java.net.URL;
import java.util.ResourceBundle;


public class BookSession implements Initializable{
    @FXML
    public Text mainPage;
    @FXML
    public Text viewSchedule;
    @FXML
    private Button bookBtn;
    @FXML
    private ToggleGroup target;
    @FXML
    private ToggleGroup pability;
    @FXML
    private DatePicker sdate;
    @FXML
    private ComboBox stime;
    @FXML
    private TextField snote;

    private User user;

    public void getUser(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell(){
            @Override
            public void updateItem(LocalDate item, boolean empty){
                super.updateItem(item, empty);
                if(item.isBefore(LocalDate.now()) || item.isAfter(LocalDate.now().plusYears(1))){
                    setStyle("-fx-background-color: #ffc0cb; -fx-text-fill: darkgray;");
                    setDisable(true);
                }
            }
        };

        sdate.setDayCellFactory(dayCellFactory);
        sdate.setValue(LocalDate.now());
    }

    /**
     * Control the "Book NOW" button.
     */
    @FXML
    void addSession (ActionEvent event) throws Exception{
        BookSessionControl bsc = new BookSessionControl();
        String time = sdate.getValue().toString()+" "+stime.getSelectionModel().getSelectedItem().toString();
        if(!bsc.checkSchedule(user, time)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Book Failed!");
            alert.setHeaderText("This time has already been booked.");
            alert.show();
        }else{
            RadioButton selected1 = (RadioButton) target.getSelectedToggle();
            RadioButton selected2 = (RadioButton) pability.getSelectedToggle();
            if(bsc.addSession(user,selected1.getText(),selected2.getText(),snote.getText(),time)){
                new ToPage().toBookSuccessfulPage(bookBtn,user);
            }
        }
    }

    public void toSchedule(MouseEvent actionEvent) throws Exception {
        ToPage tp = new ToPage();
        tp.toSchedule(viewSchedule, user);
    }

    public void toMainPage(MouseEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toMainPage(mainPage, user);
    }
}
