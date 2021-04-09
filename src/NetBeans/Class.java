package NetBeans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Class {
    String name;
    String id;
    String coachID;
    String studentID;
    String date;
    String weekday;
    int session;
    String note;
    //String time;
    //11

    public Class(String time){
        //this.setId(id);
        //this.setName(name);
        this.setTime(time);
    }
    private StringProperty time;


    public void setTime(String value) {
        timeProperty().set(value);

    }
    public String getTime() {
        return timeProperty().get();
    }

    public StringProperty timeProperty(){
        time = new SimpleStringProperty(this, "time");
        return time;
    }
     /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/






}
