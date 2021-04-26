package Controller;

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

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
*@Description: Read the Coach Profile from the existing files and print the information in a table
*@param:
*@return:
*@Author:Jin TianYu
*@Date:2021/4/25
*/
public class CoachCsvControl {
    @FXML
    private TableView<Trainer> table;
    @FXML
    private Text adminMain;

    @FXML
    private Text addCoach;

    public void init(){

        IOClass ioClass=new IOClass();
        readCoachProfile(ioClass.CoachProfilePath);
    }
   /**
    *@Description: Read all the coach profiles
    *@param: fileDir
    *@return:
    *@Author:Jin TianYu
    *@Date:2021/4/26
    */

    public void readCoachProfile(String fileDir){
        File file = new File(fileDir);
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
        for (File f : files) {
            if (f.isFile()) {
                readFile(f.getAbsolutePath());
            }
        }
    }

    private void readFile(String path) {

        ObservableList<Trainer> slist= FXCollections.observableArrayList();
        List<Trainer> list = new ArrayList<Trainer>();

        File csv = new File(path);//todo this csv file is only for test
        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";
            int i=0;
            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            while ((lineDta = textFile.readLine()) != null){
                Trainer s = new Trainer();
                s.setTrainerID(lineDta.split("#")[0]);
                s.setGender(lineDta.split("#")[1]);
                s.setHeight(lineDta.split("#")[2]);
                s.setWeight((lineDta.split("#")[3]));
                s.setTel((lineDta.split("#")[4]));
                s.setName((lineDta.split("#")[5]));
                list.add(s);
            }
            slist.addAll(list);
            System.out.println(list.toString());
            textFile.close();
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }
        table.setItems(slist);//将集合的值 存储到tableView里
        TableColumn<Trainer, String> table_name= new TableColumn<Trainer, String>("Name");//创建TableColumn  列名为序号
        TableColumn<Trainer, String> table_id= new TableColumn<Trainer, String>("ID");
        TableColumn<Trainer, String> table_height= new TableColumn<Trainer, String>("Height");
        TableColumn<Trainer, String> table_weight= new TableColumn<Trainer, String>("Weight");
        TableColumn<Trainer, String> table_gender= new TableColumn<Trainer, String>("Gender");
        TableColumn<Trainer, String> table_phone= new TableColumn<Trainer, String>("Phone");
        /**
         * 反射取值
         */
        table_name.setCellValueFactory(new PropertyValueFactory<Trainer,String>("name"));//相当于getid
        table_id.setCellValueFactory(new PropertyValueFactory<Trainer,String>("trainerID"));
        table_height.setCellValueFactory(new PropertyValueFactory<Trainer,String>("height"));
        table_weight.setCellValueFactory(new PropertyValueFactory<Trainer,String>("weight"));
        table_gender.setCellValueFactory(new PropertyValueFactory<Trainer,String>("gender"));
        table_phone.setCellValueFactory(new PropertyValueFactory<Trainer,String>("Tel"));
        table.getColumns().add(table_name);
        table.getColumns().add(table_id);
        table.getColumns().add(table_height);
        table.getColumns().add(table_weight);
        table.getColumns().add(table_gender);
        table.getColumns().add(table_phone);

    }



    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) adminMain.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/AdminMain.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }


    public void toAddCoach(MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) addCoach.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/AddCoach.fxml"));
        Parent root = loader.load();
        AddCoach controller = loader.getController();
        //instantiating a user
        controller.init();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();
    }
}
