package Controller;

import Boundary.TrainerControlSchedule;
import Entity.Session;
import Entity.Trainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainerSchedule extends MySchedule<Trainer>{
    /**
     * @description get the schedule of a particular trainer.
     * @return trainer's schedule
     * @throws Exception
     */
    @Override
    public ObservableList<Session> mySchedule(Trainer trainer) throws Exception {
        //todo 读取教练的学生 这里我自己设置的
        trainer.setStudents(new ArrayList<String>(Arrays.asList("1234", "12345")));
        ObservableList<Session> tlist = FXCollections.observableArrayList();
        List<Session> list = readCSV(trainer.getStudents().get(0));
        for(int i =1; i<trainer.getStudents().size();i++){
            list.addAll(readCSV(trainer.getStudents().get(i)));
        }
        Sort comparator = new Sort();
        list.sort(comparator);
        tlist.addAll(list);
        return tlist;
    }

}
