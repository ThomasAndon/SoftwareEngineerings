package Controller;

import Entity.Trainer;
import Entity.User;

import java.io.*;

public class SetProfile implements profilePath{


    /**
     * This method takes in a user, set up his personal information and return the same user back.
     * @param user this is the user known only the ID and password.
     * @return return the user with his/her full information.
     * @author Thomas Andon
     */
    public User setUserProfile(User user) throws IOException {
        String[] info=readFile("userProfile/", user.getId());
        user.setGender(info[1]);
        user.setHeight(Double.parseDouble(info[2]));
        user.setWeight(Double.parseDouble(info[3]));
        user.setLevel(Integer.parseInt(info[4]));
        user.setName(info[5]);


        return user;
    }

    public Trainer setCoachProfile(Trainer trainer)throws IOException{
        String[] info=readFile("coachProfile/", trainer.getTrainerID());

        trainer.setGender(info[1]);
        trainer.setHeight(Double.parseDouble(info[2]));
        trainer.setWeight(Double.parseDouble(info[3]));
        trainer.setTel(Integer.parseInt(info[4]));
        trainer.setName(info[5]);

        return trainer;
    }

}
