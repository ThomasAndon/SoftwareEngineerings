package Controller;

import NetBeans.Session;
import NetBeans.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserCsvControl {
    @FXML
    private TableView<User> table;
    @FXML
    private Text adminMain;

    public void init(){

        readCSV();
    }
    public void readCSV()  {
        ObservableList<User> slist= FXCollections.observableArrayList();
        List<User> list = new ArrayList<User>();
        //第一步：先获取csv文件的路径，通过BufferedReader类去读该路径中的文件
        File csv = new File("src//Data//Account//User.csv");//only for test
        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";
            textFile.readLine();
            int i=0;
            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            while ((lineDta = textFile.readLine()) != null){
                User s = new User();
                s.setName(lineDta.split(",")[0]);
                s.setId(lineDta.split(",")[1]);
                s.setPassword((lineDta.split(",")[2]));
                s.setGender(lineDta.split(",")[3]);
                list.add(s);
            }
            slist.addAll(list);
            //    Arrays.sort(slist);


            textFile.close();
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }
        table.setItems(slist);//将集合的值 存储到tableView里
        TableColumn<User, String> table_name= new TableColumn<User, String>("Name");//创建TableColumn  列名为序号
        TableColumn<User, String> table_id= new TableColumn<User, String>("ID");
        TableColumn<User, String> table_pw= new TableColumn<User, String>("Password");
        TableColumn<User, String> table_gender= new TableColumn<User, String>("Gender");
        /**
         * 反射取值
         */
        table_name.setCellValueFactory(new PropertyValueFactory<User,String>("name"));//相当于getid
        table_id.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        table_pw.setCellValueFactory(new PropertyValueFactory<User,String>("password"));
        table_gender.setCellValueFactory(new PropertyValueFactory<User,String>("gender"));
        table.getColumns().add(table_name);
        table.getColumns().add(table_id);
        table.getColumns().add(table_pw);
        table.getColumns().add(table_gender);

    }

    public void toMainPage(MouseEvent actionEvent) throws IOException {
        Stage stage = (Stage) adminMain.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/AdminMain.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
}
