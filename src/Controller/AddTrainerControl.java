package Controller;

import Entity.Trainer;

import java.io.*;

public class AddTrainerControl {

    public void SaveInfo(String name,String id,String pw,String gender,String phone,Double height, Double weight,String intro) throws IOException {
        /**
         * save the id and password in the coach account file
         */
        Trainer trainer = new Trainer();
        File f = new File("src//Data//Account//CoachAccounts.txt");
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f,true));
        BufferedWriter bw = new BufferedWriter(out);
        if(!f.exists()){
            f.createNewFile();
        }
        bw.write(id+ " " + pw+ " # " + "\n");
        bw.flush();
        bw.close();
        System.out.println(name);


        /**
         * Add the information into the coach file
         */
        WriteTrainerProfile writeCoachProfile= new WriteTrainerProfile();
        trainer.setName(name);
        trainer.setTrainerID(id);
        trainer.setGender(gender);
        trainer.setTel(Integer.parseInt(phone));
        trainer.setHeight(height);
        trainer.setWeight(weight);
        trainer.setIntro(intro);

        writeCoachProfile.writeCoachProfile(trainer);


    }
}
