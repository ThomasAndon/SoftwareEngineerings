package Controller;

import Entity.Trainer;
import Entity.User;

import java.io.*;

public class WriteProfile {

    public String profileInfoFolderPath = "src/Data/ProfileInfo/";

    public boolean writeUserProfile(User user) throws IOException {

        String info = user.getId() + "#" + user.getGender() + "#" + user.getHeight()
                + "#" + user.getWeight() + "#" + user.getLevel() +"#" + user.getName() ;
        // todo 此处新加项目

        File f = new File(profileInfoFolderPath+user.getId()+".txt");

        if (!(f.isFile() && f.exists())) {
            System.out.println("Writing - user profile doesn't exist, new one created-"+user.getId());
            try {
                f.createNewFile();

            } catch(Exception e) {
                System.out.println("Error occurs when creating files. (path not exists)");
                return false;
            }


            //todo 不存在的文件会抛异常
        }

        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f));
        BufferedWriter bw = new BufferedWriter(out);

        bw.write(info);
        bw.close();
        return true;
    }
    public boolean writeCoachProfile(Trainer trainer) throws IOException {
        String info = trainer.getTrainerID() + "#" + trainer.getGender() + "#" + trainer.getHeight()
                + "#" + trainer.getWeight() + "#" + trainer.getTel() +"#" + trainer.getName() ;
        // todo 此处新加项目

        File f = new File(profileInfoFolderPath+trainer.getTrainerID()+".txt");

        if (!(f.isFile() && f.exists())) {
            try {
                f.createNewFile();

            } catch(Exception e) {
                System.out.println("Error occurs when creating files. (path not exists)");
                return false;
            }


            //todo 不存在的文件会抛异常
        }

        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f));
        BufferedWriter bw = new BufferedWriter(out);

        bw.write(info);
        bw.close();
        return true;
    }
}
