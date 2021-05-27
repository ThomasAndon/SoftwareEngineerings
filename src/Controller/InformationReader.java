package Controller;

import Boundary.TrainerControlSchedule;
import Entity.Session;
import Entity.Trainer;
import Entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InformationReader {
    private String path1="src/Data/ProfileInfo/AllCoachInfo.txt";
    private String path2="src/Data/ProfileInfo/AllUserInfo.txt";

    public ObservableList<Trainer> getCoachList() {
        ObservableList<Trainer> slist= FXCollections.observableArrayList();
        List<Trainer> list = new ArrayList<Trainer>();


        File AllInfo = new File(path1);

        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(AllInfo));
            String lineDta = "";
            int i=0;

            while ((lineDta = textFile.readLine()) != null) {
                Trainer s = new Trainer();
                s.setTrainerID(lineDta.split("#")[0]);
                s.setGender(lineDta.split("#")[1]);
                s.setHeight(Double.parseDouble(lineDta.split("#")[2]));
                s.setWeight(Double.parseDouble(lineDta.split("#")[3]));
                s.setTel(Integer.parseInt(lineDta.split("#")[4]));
                s.setName(lineDta.split("#")[5]);
                s.setIntro(lineDta.split("#")[6]);
                list.add(s);
                }

            slist.addAll(list);
            textFile.close();
        }catch (FileNotFoundException e){
            System.out.println("Fail to find the file.");
        }catch (IOException e){
            System.out.println("IO Exception.");
        }

        return slist;

    }

    public ObservableList<User> getUserList() {
        ObservableList<User> slist= FXCollections.observableArrayList();
        List<User> list = new ArrayList<User>();
        File file = new File(path2);
        try{
            BufferedReader textFile = new BufferedReader(new FileReader(file));
            String lineDta = "";
            while ((lineDta = textFile.readLine()) != null){
                User s = new User();
                s.setId(lineDta.split("#")[0]);
                s.setGender(lineDta.split("#")[1]);
                s.setHeight(Double.parseDouble(lineDta.split("#")[2]));
                s.setWeight(Double.parseDouble(lineDta.split("#")[3]));
                s.setLevel(Integer.parseInt(lineDta.split("#")[4]));
                s.setName(lineDta.split("#")[5]);
                s.setTrainerID(lineDta.split("#")[6]);
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

        return slist;

    }

    public ObservableList<Session> getSessionList(){
        ObservableList<Session> slist = FXCollections.observableArrayList();
        List<Session> list = new ArrayList<Session>();
        //第一步：先获取csv文件的路径，通过BufferedReader类去读该路径中的文件
        File csv = new File("src//Data//Schedule.csv");
        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";
            int i=0;
            //第三步：将文档的下一行数据赋值给lineData，并判断是否为空，若不为空则输出
            while ((lineDta = textFile.readLine()) != null){
                System.out.println(lineDta);
                Session s = new Session();
                s.setUserID(lineDta.split(",")[0]);
                s.setTrainerID(lineDta.split(",")[1]);
                s.setTime((lineDta.split(",")[2]));
                list.add(s);

            }
            Sort comparator = new Sort();
            list.sort(comparator);
            slist.addAll(list);
            textFile.close();
        }catch (FileNotFoundException e){
            System.out.println("没有找到指定文件");
        }catch (IOException e){
            System.out.println("文件读写出错");
        }

        return slist;
    }
}