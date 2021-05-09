package Controller;

import NetBeans.Trainer;
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

public class CheckCoachProfile {

    @FXML
    private TableView<Trainer> table;
    @FXML
    private Text coachMain;
    private Trainer trainer;


    // init a coach information
    //todo 个人信息的我认为使用Textfield比table要好，之后应该修改成为使用Text field来打印信息
    public void init(Trainer trainer) {
        this.trainer= trainer;
        IOClass ioClass=new IOClass();
        readCoachProfile(ioClass.CoachProfilePath,trainer.getTrainerID());
    }

    private void readCoachProfile(String coachProfilePath, String trainerID) {
        ObservableList<Trainer> clist= FXCollections.observableArrayList();
        List<Trainer> list = new ArrayList<Trainer>();
        File file = new File(coachProfilePath +"/" + trainerID +".txt");
        try{

            BufferedReader textFile = new BufferedReader(new FileReader(file));
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
            clist.addAll(list);
            textFile.close();
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }
        table.setItems(clist);//将集合的值 存储到tableView里
        TableColumn<Trainer, String> table_name= new TableColumn<Trainer, String>("Name");//创建TableColumn  列名为序号
        TableColumn<Trainer, String> table_id= new TableColumn<Trainer, String>("ID");
        TableColumn<Trainer, String> table_height= new TableColumn<Trainer, String>("Height");
        TableColumn<Trainer, String> table_weight= new TableColumn<Trainer, String>("Weight");
        TableColumn<Trainer, String> table_gender= new TableColumn<Trainer, String>("Gender");
        TableColumn<Trainer, String> table_phone= new TableColumn<Trainer, String>("Phone");
        /**
         * 反射取值
         */
        table_name.setCellValueFactory(new PropertyValueFactory<Trainer,String>("name"));
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
        Stage stage = (Stage) coachMain.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/CoachMain.fxml"));
        Parent root = loader.load();
        stage.setScene(new Scene(root, 1000, 700));
        stage.show();

    }


}
