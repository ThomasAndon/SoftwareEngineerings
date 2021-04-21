package NetBeans;

import java.util.ArrayList;
import java.util.Date;

public class Trainer {
    String trainerID;
    String name;
    Date DoB;
    String gender;
    String Tel;
    String experience;
    String Intro;
    ArrayList<String>students;

    public String getTrainerID() {
        return trainerID;
    }

    public void setTrainerID(String trainerID) {
        this.trainerID = trainerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDoB() {
        return DoB;
    }

    public void setDoB(Date doB) {
        DoB = doB;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
    }


    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }

    public ArrayList<String> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<String> students) {
        this.students = students;
    }




}
