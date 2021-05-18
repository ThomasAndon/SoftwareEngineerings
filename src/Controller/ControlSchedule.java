package Controller;

import NetBeans.Session;
import javafx.collections.ObservableList;

public interface ControlSchedule<T> {
    public void printSchedule(ObservableList<Session> slist);
    public void setSchedule(MySchedule<T> s) throws Exception;
}
