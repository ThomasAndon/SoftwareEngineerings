package Controller;


import java.io.*;

/**
 * This class is used to write a user/coach information into a text tile
 */
public class ProfileWriter {
     String profileInfoFolderPath = "src/Data/ProfileInfo/";

    public boolean FileWriter(String s,String type, String id) throws IOException {
        File f = new File(profileInfoFolderPath+ type + "/"+ id +".txt");

        if (!(f.isFile() && f.exists())) {
            System.out.println("Writing - user profile doesn't exist, new one created-"+id);
            try {
                f.createNewFile();

            } catch(Exception e) {
                System.out.println("Error occurs when creating files. (path not exists)");
                return false;
            }
        }

        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f));
        BufferedWriter bw = new BufferedWriter(out);

        bw.write(s);
        bw.close();
        return true;
    }
}
