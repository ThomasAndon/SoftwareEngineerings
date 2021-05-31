package Controller;

import Entity.Trainer;

import java.io.IOException;
import java.util.ArrayList;


public class SetTrainerProfile implements SetProfile<Trainer>{
    String[] info;
    ArrayList<String> students=new ArrayList<>();
    /**
     * This method takes in a trainer, set up his personal information and return the same trainer back.
     * @param trainer this is the user known only the ID and password.
     * @return return the user with his/her full information.
     * @author Thomas Andon
     */
    @Override
    public Trainer setProfile(Trainer trainer) throws Exception {
        info=new profilePath().readFile("coachProfile/", trainer.getTrainerID());

        trainer.setGender(info[1]);
        trainer.setHeight(Double.parseDouble(info[2]));
        trainer.setWeight(Double.parseDouble(info[3]));
        trainer.setTel(info[4]);
        trainer.setName(info[5]);
        System.out.println(info[7]);
        if(info[7]!=null){
            for(int i = 0; i< info[7].split(",").length; i++){
                students.add(info[7].split(",")[i]);
            }
            trainer.setStudents(students);
        }
        return trainer;
    }
}
