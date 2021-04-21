package Controller;

import NetBeans.Session;
import NetBeans.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ScheduleController implements Comparator< Session > {
    @FXML
    private TableView<Session> table;
    @FXML
    private TextArea schedule;
    private User user;

    public void getUser(User user) throws Exception {
        this.user = user;
        readCSV(user.getId());
    }


    public void readCSV(String userID) throws Exception {
        ObservableList<Session> slist=FXCollections.observableArrayList();
        List<Session> list = new ArrayList<Session>();
        //第一步：先获取csv文件的路径，通过BufferedReader类去读该路径中的文件
        File csv = new File("src//Data//Session//"+userID+".csv");
        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";
            textFile.readLine();
            int i=0;
            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            while ((lineDta = textFile.readLine()) != null){
                schedule.appendText(lineDta);
                schedule.appendText("\n");
                Session s = new Session();
                s.setTime(lineDta.split(",")[2]);
                s.setTarget(lineDta.split(",")[3]);
                s.setPhysicalAbility((lineDta.split(",")[4]));
                s.setNote(lineDta.split(",")[5]);
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
        table.setItems(slist);//将集合的值 存储到tableView里
        TableColumn<Session, LocalDate> table_date= new TableColumn<Session, LocalDate>("Time");//创建TableColumn  列名为序号
        TableColumn<Session, String> table_target=new TableColumn<Session, String>("Target");
        TableColumn<Session, String> table_pAbility=new TableColumn<Session, String>("Aim");
        TableColumn<Session, String> table_note=new TableColumn<Session, String>("Note");
        /**
         * 反射取值
         */
        table_date.setCellValueFactory(new PropertyValueFactory<Session, LocalDate>("time"));//相当于getid
        table_target.setCellValueFactory(new PropertyValueFactory<Session, String>("target"));//getName
        table_pAbility.setCellValueFactory(new PropertyValueFactory<Session, String>("PhysicalAbility"));//getAge
        table_note.setCellValueFactory(new PropertyValueFactory<Session, String>("note"));
        table.getColumns().add(table_date);
        table.getColumns().add(table_target);
        table.getColumns().add(table_pAbility);

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
}
