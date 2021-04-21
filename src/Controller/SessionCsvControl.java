package Controller;

import NetBeans.Session;
import NetBeans.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SessionCsvControl {

    @FXML
    private TableView<Session> table;

    public void init() {
        readCSV();
    }

    public void readCSV() {
        ObservableList<Session> slist = FXCollections.observableArrayList();
        List<Session> list = new ArrayList<Session>();
        //第一步：先获取csv文件的路径，通过BufferedReader类去读该路径中的文件
        File csv = new File("src//Data//Schedule.csv");
        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";
            textFile.readLine();
            int i=0;
            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            while ((lineDta = textFile.readLine()) != null){
                Session s = new Session();
                s.setUserID(lineDta.split(",")[0]);
                s.setTrainerID(lineDta.split(",")[1]);
                s.setTime((lineDta.split(",")[2]));
                list.add(s);
            }
            ScheduleController comparator = new ScheduleController();
            list.sort( comparator );
            slist.addAll(list);
            //    Arrays.sort(slist);


            textFile.close();
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }
        TableColumn<Session, String> table_user_id= new TableColumn<Session, String>("UserId");
        TableColumn<Session, String> table_trainer_id= new TableColumn<Session, String>("TrainerId");
        TableColumn<Session, LocalDate> table_date= new TableColumn<Session, LocalDate>("Time");//创建TableColumn  列名为序号

        /**
         * 反射取值
         */
        table_user_id.setCellValueFactory(new PropertyValueFactory<Session, String>("userID"));//getName
        table_trainer_id.setCellValueFactory(new PropertyValueFactory<Session, String>("trainerID"));//getName
        table_date.setCellValueFactory(new PropertyValueFactory<Session, LocalDate>("time"));//相当于getid

        table.getColumns().add(table_user_id);
        table.getColumns().add(table_trainer_id);
        table.getColumns().add(table_date);

    }
}

