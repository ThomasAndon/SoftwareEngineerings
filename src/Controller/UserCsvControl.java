package Controller;

import NetBeans.Session;
import NetBeans.Trainer;
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

/**
*@Description: Print the all users' information , administrators can view thees information
*@param:
*@return:
*@Author:Jin TianYu
*@Date:2021/4/25
*/
public class UserCsvControl {
    @FXML
    private TableView<User> table;
    @FXML
    private Text adminMain;

    public void init(){

        IOClass ioClass=new IOClass();
        readUserProfile(ioClass.UserProfilePath);
    }
    /**
     *@Description: Read all the user profiles
     *@param: fileDir
     *@return:
     *@Author:Jin TianYu
     *@Date:2021/4/26
     */
    public void readUserProfile(String fileDir){
        File file = new File(fileDir);
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
        for (File f : files) {
            if (f.isFile()) {
                readFile(f.getAbsolutePath());
            }
        }
    }
    public void readFile(String path)  {
        ObservableList<User> slist= FXCollections.observableArrayList();
        List<User> list = new ArrayList<User>();
        //第一步：先获取csv文件的路径，通过BufferedReader类去读该路径中的文件
        File csv = new File(path);//only for test
        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";
            int i=0;
            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            while ((lineDta = textFile.readLine()) != null){
                User s = new User();
                s.setTrainerID(lineDta.split("#")[0]);
                s.setGender(lineDta.split("#")[1]);
                s.setHeight(lineDta.split("#")[2]);
                s.setWeight((lineDta.split("#")[3]));
                s.setLevel((lineDta.split("#")[4]));
                s.setName((lineDta.split("#")[5]));
                list.add(s);

            }
            slist.addAll(list);
            textFile.close();
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }
        table.setItems(slist);//将集合的值 存储到tableView里
        TableColumn<User, String> table_name= new TableColumn<User, String>("Name");//创建TableColumn  列名为序号
        TableColumn<User, String> table_id= new TableColumn<User, String>("ID");
        TableColumn<User, String> table_height= new TableColumn<User, String>("Height");
        TableColumn<User, String> table_weight= new TableColumn<User, String>("Weight");
        TableColumn<User, String> table_gender= new TableColumn<User, String>("Gender");
        TableColumn<User, String> table_level= new TableColumn<User, String>("Vip Level");
        /**
         * 反射取值
         */
        table_name.setCellValueFactory(new PropertyValueFactory<User,String>("name"));//相当于getid
        table_id.setCellValueFactory(new PropertyValueFactory<User,String>("id"));
        table_height.setCellValueFactory(new PropertyValueFactory<User,String>("height"));
        table_weight.setCellValueFactory(new PropertyValueFactory<User,String>("weight"));
        table_gender.setCellValueFactory(new PropertyValueFactory<User,String>("gender"));
        table_level.setCellValueFactory(new PropertyValueFactory<User,String>("level"));

        table.getColumns().add(table_name);
        table.getColumns().add(table_id);
        table.getColumns().add(table_height);
        table.getColumns().add(table_weight);
        table.getColumns().add(table_gender);
        table.getColumns().add(table_level);

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
