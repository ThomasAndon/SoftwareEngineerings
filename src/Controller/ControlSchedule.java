package Controller;

import Entity.Session;
import javafx.collections.ObservableList;

/**
 * containning methods of controlling scedule
 * @param <T>
 */
public interface ControlSchedule<T> {
    public void printSchedule(ObservableList<Session> slist);
    public void setSchedule(MySchedule<T> s) throws Exception;
    public void showSchedule() throws Exception;
}
