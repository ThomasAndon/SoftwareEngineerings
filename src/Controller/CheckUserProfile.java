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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CheckUserProfile {

    @FXML
    private TextField Sex;

    @FXML
    private TextField Height;

    @FXML
    private TextArea Level;

    @FXML
    private TextField ID;

    @FXML
    private Text userMain;

    @FXML
    private TextField Weight;

    @FXML
    private TextField Name;


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
        //第一步：先获取csv文件的路径，通过BufferedReader类去读该路径中的文件
        File file = new File(userProfilePath +"/" + id +".txt");//only for test
        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(file));
            String lineDta = textFile.readLine();
            ID.setText(lineDta.split("#")[0]);
            Sex.setText(lineDta.split("#")[1]);
            Height.setText(lineDta.split("#")[2]);
            Weight.setText(lineDta.split("#")[3]);
            Level.setText(lineDta.split("#")[4]);
            Name.setText(lineDta.split("#")[5]);

        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }


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
