package Controller;

import Entity.User;

import java.io.*;

public class WriteUserProfile{

    /**
     * Write the user information into a text file
     * @param user
     * @return
     * @throws IOException
     */
    public boolean writeUserProfile(User user) throws IOException {

        String str = "userProfile";
        String info = user.getId() + "#" + user.getGender() + "#" + user.getHeight()
                + "#" + user.getWeight() + "#" + user.getLevel() +"#" + user.getName()+"#"+user.getTrainerID()+'#' ;

        new ProfileWriter().FileWriter(info, str , user.getId());
        return true;
    }

}
