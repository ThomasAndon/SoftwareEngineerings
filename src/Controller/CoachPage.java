package Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import NetBeans.Class;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
public class CoachPage {

    @FXML
    private TextField weightInput;

    @FXML
    private TableView<Class> routing;

    @FXML
    private TableColumn<Class, String > timeColumn;

    @FXML
    private TextField heightInput;

    @FXML
    private CheckBox female;

    @FXML
    private Label VIPLevelHolder;

    @FXML
    private TextField nameInput;

    @FXML
    private Label IDHolder;

    @FXML
    private CheckBox male;

    @FXML
    private Button saveBtn;
   //11

    /*@FXML
    void SelectM(ActionEvent event) {

    }

    @FXML
    void SelectFM(ActionEvent event) {

    }

    @FXML
    void onSaveBtnClicked(ActionEvent event) {

    }*/
    private ObservableList<Class> RoutingData = FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        timeColumn.setCellValueFactory(cellData -> cellData.getValue().timeProperty());


        //绑定数据到TableView
        routing.setItems(RoutingData);

        //添加数据到RoutingData，TableView会自动更新
        RoutingData.add( new Class("8:00-10:00"));
        RoutingData.add( new Class("10:00-12:00"));
        RoutingData.add( new Class("12:00-14:00"));
        RoutingData.add( new Class("14:00-16:00"));
        RoutingData.add( new Class("16:00-18:00"));
    }


}