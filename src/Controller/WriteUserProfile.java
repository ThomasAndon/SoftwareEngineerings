package Controller;


import Entity.User;

import java.io.*;

/**
 * controller of writing user profile
 */
public class WriteUserProfile{
    private String str;
    private String info;

    /**
     * writing user profile
     */
    public boolean writeUserProfile(User user) throws IOException {
        str = "userProfile";
        info = user.getId() + "#" + user.getGender() + "#" + user.getHeight()
                + "#" + user.getWeight() + "#" + user.getLevel() +"#" + user.getName()+"#"+user.getTrainerID()+'#' ;

        new ProfileWriter().FileWriter(info, str , user.getId());
        return true;
    }
}
