package Controller;

import Entity.Trainer;
import Entity.User;

import java.io.*;

public class WriteUserProfile implements ProfileWriter{


    public boolean writeUserProfile(User user) throws IOException {

        String str = "userProfile";
        String info = user.getId() + "#" + user.getGender() + "#" + user.getHeight()
                + "#" + user.getWeight() + "#" + user.getLevel() +"#" + user.getName()+"#"+user.getTrainerID()+'#' ;

        FileWriter(info, str , user.getId());
        return true;
    }

}
