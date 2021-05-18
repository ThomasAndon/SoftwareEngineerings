package Controller;

import NetBeans.Session;
import NetBeans.Trainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainerSchedule extends MySchedule<Trainer> {
    @Override
    public ObservableList<Session> mySchedule(Trainer trainer) throws Exception {
        //todo 读取教练的学生 这里我自己设置的

        trainer.setStudents(new ArrayList<String>(Arrays.asList("1234", "12345")));
        ObservableList<Session> tlist = FXCollections.observableArrayList();
        List<Session> list = readCSV(trainer.getStudents().get(0));
        for(int i =1; i<trainer.getStudents().size();i++){
            list.addAll(readCSV(trainer.getStudents().get(i)));
        }
        ScheduleControl comparator = new ScheduleControl();
        list.sort(comparator);
        tlist.addAll(list);
        return tlist;
    }

}
