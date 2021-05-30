package Controller;

import Entity.Trainer;

import java.io.IOException;



public class SetCoachProfile{
    /**
     * This method takes in a user, set up his personal information and return the same user back.
     * @param trainer this is the user known only the ID and password.
     * @return return the user with his/her full information.
     * @author Thomas Andon
     */
    public Trainer setCoachProfile(Trainer trainer)throws IOException {
        String[] info=new profilePath().readFile("coachProfile/", trainer.getTrainerID());

        trainer.setGender(info[1]);
        trainer.setHeight(Double.parseDouble(info[2]));
        trainer.setWeight(Double.parseDouble(info[3]));
        trainer.setTel(Integer.parseInt(info[4]));
        trainer.setName(info[5]);

        return trainer;
    }
}
