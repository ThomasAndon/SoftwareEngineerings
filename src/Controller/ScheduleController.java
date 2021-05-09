package Controller;

import NetBeans.Session;
import NetBeans.Trainer;
import NetBeans.User;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ScheduleController implements Comparator< Session > {
    @FXML
    private TableColumn<Session,LocalDate> timeCol;
    @FXML
    private TableColumn<Session,String> idCol,targetCol,aimCol,noteCol,cancelCol;
    @FXML
    private TableView<Session> table;
    @FXML
    private Text mainPage;
    private User user;
    private Trainer trainer;

    public void getUser(User user) throws Exception {
        this.user = user;
      //  printSchedule(readCSV(user.getId()));
    }
    public void getTrainer(Trainer trainer) throws Exception {
        this.trainer = trainer;
    }
    public void userSchedule() throws Exception {
        ObservableList<Session> ulist = FXCollections.observableArrayList();
        ScheduleController comparator = new ScheduleController();
        List<Session> list = readCSV(user.getId());
        list.sort(comparator);
        ulist.addAll(list);
        printSchedule(ulist,true);
    }
    public void trainerSchedule() throws Exception {
        //todo 读取教练的学生 这里我自己设置的
        trainer.setStudents(new ArrayList<String>(Arrays.asList("1234", "12345")));
        ObservableList<Session> tlist = FXCollections.observableArrayList();
        List<Session> list = readCSV(trainer.getStudents().get(0));
        for(int i =1; i<trainer.getStudents().size();i++){
            list.addAll(readCSV(trainer.getStudents().get(i)));
        }
        ScheduleController comparator = new ScheduleController();
        list.sort(comparator);
        tlist.addAll(list);
        printSchedule(tlist,false);

    }

    public List<Session> readCSV (String userID) throws Exception {
        ObservableList<Session> slist = FXCollections.observableArrayList();
        List<Session> list = new ArrayList<Session>();
        File csv = new File("src//Data//Session//" + userID + ".csv");
        try {
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";
            textFile.readLine();
            while ((lineDta = textFile.readLine()) != null) {
                Session s = new Session();
                s.setUserID(lineDta.split(",")[0]);
                s.setTrainerID(lineDta.split(",")[1]);
                s.setTime(lineDta.split(",")[2]);
                s.setTarget(lineDta.split(",")[3]);
                s.setPhysicalAbility((lineDta.split(",")[4]));
                s.setNote(lineDta.split(",")[5]);
                list.add(s);
            }
  //          ScheduleController comparator = new ScheduleController();
//            list.sort(comparator);
//            slist.addAll(list);
            textFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        return list;
    }
    /**
    * @Description:
    * @Param:  boolean who:user是true,教练是false，操作稍有不同
    * @return:
    * @Author: Cui Kening
    * @Date:
    */
    public void printSchedule(ObservableList<Session> slist, boolean who){
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        targetCol.setCellValueFactory(new PropertyValueFactory<>("target"));
        aimCol.setCellValueFactory(new PropertyValueFactory<>("PhysicalAbility"));
        noteCol.setCellValueFactory(new PropertyValueFactory<>("note"));
        if(who){
            idCol.setText("Trainer ID");
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
                                //System.out.println(item.split(",")[1]);
                                setGraphic(null);
                                setText(null);
                            } else {
                                String[] l = getTableView().getItems().get(getIndex()).getTime().split(" ");
                                for(int j=0;j<l.length; ){
                                    LocalDate date = LocalDate.parse(l[j], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                                    if (date.isBefore(LocalDate.now())){
                                        break;
                                    }
                                    btn.setOnAction(event -> {
                                        Session s =getTableView().getItems().get(getIndex());
                                        String i = s.getTime();
                                        slist.remove(s);
                                        try {
                                            if(cancelSession(csv, i, user.getId())){
                                                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                                alert.setTitle("Cancel successful!");
                                                alert.setHeaderText("You have already canceled your session on "+i);
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
        }
        else{
            idCol.setText("Student ID");
            idCol.setCellValueFactory(new PropertyValueFactory<>("userID"));
            //todo 教练可以添加训练的备注
            cancelCol.setText("Note");
        }

        table.setItems(slist);
    }

    public boolean cancelSession(File file, String i, String userID)throws Exception {
        File temp = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        File temp2 = null;
        BufferedReader br2 = null;
        PrintWriter pw2 = null;
        File file2 = new File("src//Data//Schedule.csv");
        try {
            temp = File.createTempFile("temp", "temp");
            pw = new PrintWriter(temp);
            br = new BufferedReader(new FileReader(file));
            while (br.ready()) {
                String line = br.readLine();
                if (line.contains(i)) {
                    continue;
                }
                pw.write(line + "\n");
            }
                pw.flush();
            temp2 = File.createTempFile("temp2", "temp2");
            pw2 = new PrintWriter(temp2);
            br2 = new BufferedReader(new FileReader(file2));
            while (br2.ready()) {
                String line2 = br2.readLine();
                if (line2.contains(i) && line2.contains(userID)) {
                    continue;
                }
                pw2.write(line2 + "\n");
            }
            pw2.flush();
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        } finally {
            pw.close();
            br.close();
            pw2.close();
            br2.close();
            if (temp != null) {
                file.delete();
                temp.renameTo(file);
            }
            if (temp2 != null) {
                file2.delete();
                temp2.renameTo(file2);
            }
        }
        return true;
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

    public void toMainPage(MouseEvent actionEvent) throws IOException {
        Stage stage = (Stage) mainPage.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        Parent root;
        if(user!=null){
            loader.setLocation(getClass().getResource("../view/UserInterf.fxml"));
            root = loader.load();
            UserInterf controller = loader.getController();
            //instantiating a user
            controller.initData(user);
        }else{
            loader.setLocation(getClass().getResource("../view/CoachInterf.fxml"));
            root = loader.load();
            CoachInterf controller = loader.getController();
            //instantiating a user
            controller.initData(trainer);
        }

        // stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
}
