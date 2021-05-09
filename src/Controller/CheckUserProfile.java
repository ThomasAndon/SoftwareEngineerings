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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CheckUserProfile {

    @FXML
    private TableView<User> table;

    @FXML
    private Text userMain;
    private User user;
    /**
    *@Description: User can check their information in this window
    *@param:
    *@return:
    *@Author:Jin TianYu
    *@Date:2021/5/9
    */
    public void getUser(User user) throws Exception {
        this.user = user;
        IOClass ioClass=new IOClass();
        readUserProfile(ioClass.UserProfilePath,user.getId());

    }

    private void readUserProfile(String userProfilePath, String id) {
        ObservableList<User> slist= FXCollections.observableArrayList();
        List<User> list = new ArrayList<User>();
        //第一步：先获取csv文件的路径，通过BufferedReader类去读该路径中的文件
        File file = new File(userProfilePath +"/" + id +".txt");//only for test
        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(file));
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

    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) userMain.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/UserInterf.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
}
