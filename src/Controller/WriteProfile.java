package Controller;

import Entity.Trainer;
import Entity.User;

import java.io.*;

public class WriteProfile implements ProfileWriter{


    public boolean writeUserProfile(User user) throws IOException {

        String info = user.getId() + "#" + user.getGender() + "#" + user.getHeight()
                + "#" + user.getWeight() + "#" + user.getLevel() +"#" + user.getName() ;

        FileWriter(info, user.getId());
        return true;
    }
    public boolean writeCoachProfile(Trainer trainer) throws IOException {
        String info = trainer.getTrainerID() + "#" + trainer.getGender() + "#" + trainer.getHeight()
                + "#" + trainer.getWeight() + "#" + trainer.getTel() +"#" + trainer.getName() ;

        FileWriter(info,trainer.getTrainerID());
        return true;
    }
}
