package Controller;

import Entity.Trainer;

import java.io.IOException;
import java.util.ArrayList;

public class WriteTrainerProfile {
    String str = "coachProfile";
    String students;
    ArrayList<String> list;
    String info;
    public boolean writeCoachProfile(Trainer trainer) throws IOException {

        if(trainer.getStudents()!=null){
            list = trainer.getStudents();
            students = list.get(0);
            for(int i =1;i<list.size();i++){
                students.concat(",");
                students.concat(list.get(i));
            }
            info = trainer.getTrainerID() + "#" + trainer.getGender() + "#" + trainer.getHeight()
                    + "#" + trainer.getWeight() + "#" + trainer.getTel() +"#" + trainer.getName() + "#" + trainer.getIntro()
                    +"#" + students;
        }else {
            info = trainer.getTrainerID() + "#" + trainer.getGender() + "#" + trainer.getHeight()
                    + "#" + trainer.getWeight() + "#" + trainer.getTel() + "#" + trainer.getName() + "#" + trainer.getIntro();
        }
        new ProfileWriter().FileWriter(info, str, trainer.getTrainerID());
        return true;
    }


}
