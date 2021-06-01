package Controller;

import Entity.Trainer;
import Entity.User;

import java.io.*;

/**
 * controller of setting user profilie
 */
public class SetUserProfile implements SetProfile<User>{

    @Override
    /**
     * This method takes in a user, set up his personal information and return the same user back.
     * @param user this is the user known only the ID and password.
     * @return return the user with his/her full information.
     * @author Thomas Andon
     */
    public User setProfile(User user) throws Exception {
        String[] info=new profilePath().readFile("userProfile/", user.getId());
        user.setGender(info[1]);
        user.setHeight(Double.parseDouble(info[2]));
        user.setWeight(Double.parseDouble(info[3]));
        user.setLevel(Integer.parseInt(info[4]));
        user.setName(info[5]);
        user.setTrainerID(info[6]);

        return user;
    }
}
