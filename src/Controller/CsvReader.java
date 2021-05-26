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

public class CsvReader {

    public ObservableList<Trainer> getCoachList(String path) {
        ObservableList<Trainer> slist= FXCollections.observableArrayList();
        List<Trainer> list = new ArrayList<Trainer>();
        //第一步：先获取csv文件的路径，通过BufferedReader类去读该路径中的文件
        File csv = new File(path);//todo this csv file is only for test
        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";
            int i=0;

            while ((lineDta = textFile.readLine()) != null) {
                Trainer s = new Trainer();
                s.setName(lineDta.split(",")[0]);
                s.setTrainerID(lineDta.split(",")[1]);
                s.setTrainerPw((lineDta.split(",")[2]));
                s.setGender(lineDta.split(",")[3]);
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
        File csv = new File("src//Data//Account//User.csv");//only for test
        try{
            //第二步：从字符输入流读取文本，缓冲各个字符，从而实现字符、数组和行（文本的行数通过回车符来进行判定）的高效读取。
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";
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
