package Boundary;

import Controller.ToPage;
import Entity.Session;
import Entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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

    @FXML
    /**
    * @Description: interact with the UI, run after the user click book session button
    * @Author: Cui Kening
    */
    void addSession (ActionEvent event) throws Exception{
        Session session = new Session();
        session.setUserID(user.getId());

        user.setTrainerID("8159"); //TODO 教练

        session.setTrainerID(user.getTrainerID());
  //     System.out.println(user.getId());
        String time = sdate.getValue().toString()+" "+stime.getSelectionModel().getSelectedItem().toString();
        if(!checkSchedule(time)){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Book Failed!");
            alert.setHeaderText("This time has already been booked.");
            alert.show();
        }else{
            RadioButton selected1 = (RadioButton) target.getSelectedToggle();
            session.setTarget(selected1.getText());
            RadioButton selected2 = (RadioButton) pability.getSelectedToggle();
            session.setPhysicalAbility(selected2.getText());
            session.setNote(snote.getText());
            session.setTime(time);
            if(writeSession("src//Data//Session//"+session.getUserID()+".csv","src//Data//Schedule.csv",session)){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/BookSuccessful.fxml"));
                Parent root = loader.load();
                UserMain controller = loader.getController();
                controller.getUser(user);
                Stage stage = (Stage) bookBtn.getScene().getWindow();
                stage.close();
                stage.setScene(new Scene(root, 1000, 700));
                stage.show();
            }

        }


    }
    /**
    * @Description: Check if the time chosen by the user is available for trainer
    * @Param:  time is the time chosen by the user while booking a session
    * @return:  whether the time is available for the trainer
    * @Author: Cui Kening
    */
    public Boolean checkSchedule(String time) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader("src//Data//Schedule.csv"));
        while((line=reader.readLine())!=null){
            String[] item = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                if(item[1].equals(user.getTrainerID())&&item[2].equals(time)){
                    return false;
                }
        }
        return true;
    }
    /**
    * @Description:  每个学生写一个session，文件命名就是学生的id,path1是session的路径，path2是schedule路径
    * @Param:
    * @return:
    * @Author: Cui Kening
    * @Date:
    */
    public boolean writeSession(String path1, String path2, Session session) throws Exception {
        File f1 = new File(path1);
        int flag1 = 1, flag2 = 1;
        if(!f1.exists()){ flag1 = 0; }
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f1,true));
        BufferedWriter bw = new BufferedWriter(out);
        if(flag1==0){
            bw.write("userID,trainerID,time,target,physical ability,note,detail\n");
        }
        bw.write(session.getUserID()+","+session.getTrainerID()+","
                + session.getTime()+","+ session.getTarget() +","+session.getPhysicalAbility()+ ","
                + session.getNote()+ ","+ session.getDetail()+ "\n");
        bw.flush();
        bw.close();

        File f2 = new File(path2);
        if(!f2.exists()){ flag2 = 0; }
        OutputStreamWriter out2 = new OutputStreamWriter(new FileOutputStream(f2,true));
        BufferedWriter bw2 = new BufferedWriter(out2);
        if(flag2==0){
            bw2.write("userID,trainerID,time\n");
        }
        bw2.write(session.getUserID() + "," + session.getTrainerID() + "," + session.getTime() + "\n");
        bw2.flush();
        bw2.close();

        return true;
    }


    public void toSchedule(MouseEvent actionEvent) throws Exception {
        ToPage tp = new ToPage();
        tp.toSchedule(viewSchedule, user);
//        Stage stage = (Stage) viewSchedule.getScene().getWindow();
//        stage.close();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("../view/ScheduleUI.fxml"));
//        Parent root = loader.load();
//        ScheduleControl controller = loader.getController();
//        //instantiating a user
//        controller.getUser(user);
//
//        stage.setScene(new Scene(root, 1000, 700));
//        stage.show();
    }

    public void toMainPage(MouseEvent actionEvent) throws IOException {
        ToPage tp = new ToPage();
        tp.toUserMainPage(mainPage, user);
//        Stage stage = (Stage) mainPage.getScene().getWindow();
//        stage.close();
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("../view/UserMainUI.fxml"));
//        Parent root = loader.load();
//        UserMain controller = loader.getController();
//        //instantiating a user
//        controller.initData(user);
//        // stage.setTitle("Hello World");
//        stage.setScene(new Scene(root, 1000, 700));
//        stage.show();
    }
}
