package Controller;

import NetBeans.Session;
import NetBeans.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UserSchedule extends MySchedule<User> {
    @Override
    public ObservableList<Session> mySchedule(User u) throws Exception {
        ObservableList<Session> ulist = FXCollections.observableArrayList();
        ScheduleControl comparator = new ScheduleControl();
        List<Session> list = readCSV(u.getId());
        list.sort(comparator);
        ulist.addAll(list);
        return ulist;
    }

}
