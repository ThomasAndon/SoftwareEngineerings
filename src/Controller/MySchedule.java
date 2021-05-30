package Controller;

import Entity.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MySchedule<T>{

    public abstract ObservableList<Session> mySchedule(T o) throws Exception;

    /**
     * @description read the schedule information of a particular user.
     * @param userID
     * @return
     * @throws Exception
     */
    protected List<Session> readCSV(String userID) throws Exception {
        ObservableList<Session> slist = FXCollections.observableArrayList();
        List<Session> list = new ArrayList<Session>();
        File csv = new File("src//Data//Session//" + userID + ".csv");
        try {
            BufferedReader textFile = new BufferedReader(new FileReader(csv));
            String lineDta = "";
            textFile.readLine();
            while ((lineDta = textFile.readLine()) != null) {
                Session s = new Session();
                s.setUserID(lineDta.split(",")[0]);
                s.setTrainerID(lineDta.split(",")[1]);
                s.setTime(lineDta.split(",")[2]);
                s.setTarget(lineDta.split(",")[3]);
                s.setPhysicalAbility((lineDta.split(",")[4]));
                s.setNote(lineDta.split(",")[5]);
                list.add(s);
            }
            textFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        return list;
    }

}
