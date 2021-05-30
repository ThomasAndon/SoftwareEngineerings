package Controller;

import Entity.Session;
import Entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UserSchedule extends MySchedule<User> {
    /**
     * @description get the schedule of a particular user.
     * @return user's schedule
     * @throws Exception
     */
    @Override
    public ObservableList<Session> mySchedule(User u) throws Exception {
        ObservableList<Session> ulist = FXCollections.observableArrayList();
        Sort comparator = new Sort();
        List<Session> list = readCSV(u.getId());
        list.sort(comparator);
        ulist.addAll(list);
        return ulist;
    }

}
