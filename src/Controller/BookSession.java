package Controller;

import NetBeans.Session;
import NetBeans.User;
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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.HijrahChronology;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class BookSession implements Initializable{
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

    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
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

        StringConverter converter = new StringConverter<LocalDate>(){
            @Override
            public String toString(LocalDate date){
                if(date != null) return dateFormatter.format(date);
                else return "";
            }

            @Override
            public LocalDate fromString(String string) {
                if(string != null && !string.isEmpty()) {
                    LocalDate date = LocalDate.parse(string, dateFormatter);
                    if(date.isBefore(LocalDate.now()) || date.isAfter(LocalDate.now().plusYears(1))) {
                        return sdate.getValue();
                    }
                    else return date;
                }
                return null;
            }
        };
        sdate.setDayCellFactory(dayCellFactory);
//        sdate.setConverter(converter);
//        sdate.setPromptText("dd/MM/yyyy");
        sdate.setValue(LocalDate.now());
    }

    @FXML
    void addSession (ActionEvent event) throws Exception{
        Session session = new Session();
        session.setStudentID(user.getId());

        user.setTrainerID("8159"); //just for test!

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
            if(writeSession("src//Data//Session//"+session.getStudentID()+".csv","src//Data//Schedule.csv",session)){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../view/BookSuccessful.fxml"));
                Parent root = loader.load();
                UserInterf controller = loader.getController();
                controller.getUser(user);
                Stage stage = (Stage) bookBtn.getScene().getWindow();
                stage.close();
                stage.setScene(new Scene(root, 1000, 700));
                stage.show();
            }

        }


    }

    public Boolean checkSchedule(String time) throws IOException {
        String line = null;
        int index=0;
        BufferedReader reader = new BufferedReader(new FileReader("src//Data//Schedule.csv"));
        while((line=reader.readLine())!=null){
            String[] item = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                if(item[1].equals(user.getTrainerID())&&item[2].equals(time)){
                    return false;
                }
            index++;
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
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f1,true));
        BufferedWriter bw = new BufferedWriter(out);
        if(!f1.exists()){
            f1.createNewFile();
            System.out.println("creat success");
            bw.write("userID,trainerID,time,target,physical ability,note,detail\n");
        }else {
            bw.write(session.getStudentID()+","+session.getTrainerID()+","
                    + session.getTime()+","+ session.getTarget() +","+session.getPhysicalAbility()+ ","
                    + session.getNote()+ ","+ session.getDetail()+ "\n");
            bw.flush();
//        bw.write(session.getStudentID()+"\t"+session.getTrainerID()+"\t"+session.getDate()+ "\t"
//                + session.getTime()+"\t"+ session.getTarget() +"\t"+session.getpAbility()+ "\t"
//                + session.getNote()+ "\t"+ session.getDetail()+ "#\n");
            bw.close();
        }


        File f2 = new File(path2);
        OutputStreamWriter out2 = new OutputStreamWriter(new FileOutputStream(f2,true));
        BufferedWriter bw2 = new BufferedWriter(out2);
        if(!f2.exists()){
            f2.createNewFile();
            bw2.write("userID,trainerID,time\n");
        }else {
            bw2.write(session.getStudentID() + "," + session.getTrainerID() + "," + session.getTime() + "\n");
            bw2.flush();
            bw2.close();

        }
        return true;
    }


    public void toSchedule(MouseEvent mouseEvent) {
    }
}
