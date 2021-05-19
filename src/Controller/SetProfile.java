package Controller;

import Entity.Trainer;
import Entity.User;

import java.io.*;

public class SetProfile {

    public String profileInfoFolderPath = "src/Data/ProfileInfo/";
    /**
     * This method takes in a user, set up his personal information and return the same user back.
     * @param user this is the user known only the ID and password.
     * @return return the user with his/her full information.
     * @author Thomas Andon
     */
    public User setUserProfile(User user) throws IOException {



        File f = new File(profileInfoFolderPath + "userProfile/" +user.getId() + ".txt");

        if (!(f.isFile() && f.exists())) {
            System.out.println(user.getId() + "profile txt does not exist.");
            return null;
        }

        InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
        BufferedReader in = new BufferedReader(isr);

        String line = in.readLine();
        String[] info = parseProfileString(line);
        user.setGender(info[1]);
        user.setHeight(Double.parseDouble(info[2]));
        user.setWeight(Double.parseDouble(info[3]));
        user.setLevel(Integer.parseInt(info[4]));
        user.setName(info[5]);
        //todo 此处新加入项目


        return user;
    }

    public Trainer setCoachProfile(Trainer trainer)throws IOException{
        File f = new File(profileInfoFolderPath + "coachProfile/" + trainer.getTrainerID() + ".txt");

        if (!(f.isFile() && f.exists())) {
            System.out.println(trainer.getTrainerID() + "profile txt does not exist.");
            return null;
        }

        InputStreamReader ISR = new InputStreamReader(new FileInputStream(f));
        BufferedReader in = new BufferedReader(ISR);

        String line = in.readLine();
        String[] info = parseProfileString(line);
        trainer.setGender(info[1]);
        trainer.setHeight(Double.parseDouble(info[2]));
        trainer.setWeight(Double.parseDouble(info[3]));
        trainer.setTel(Integer.parseInt(info[4]));
        trainer.setName(info[5]);

        return trainer;
    }


    /**
     * This method parses the in-coming String and returns a string array.
     * @param info
     * @return
     */
    public String[] parseProfileString(String info) {

        String[] res = info.split("#");
        return res;
    }

}
