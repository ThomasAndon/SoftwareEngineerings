package NetBeans;

import java.time.LocalDate;
import java.util.Date;

public class Session {
    String trainerID;
    String userID;
    String time;
    String target;
    String physicalAbility;
    String note;
    String detail;  // trainer makes plans

    public String getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(String trainerID) {
        this.trainerID = trainerID;
    }

    public String getUserID() { return userID; }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPhysicalAbility() {
        return physicalAbility;
    }

    public void setPhysicalAbility(String physicalAbility) {
        this.physicalAbility = physicalAbility;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
