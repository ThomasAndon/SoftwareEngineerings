package Controller;


import java.io.*;

public interface ProfileWriter {
     String profileInfoFolderPath = "src/Data/ProfileInfo/";

    default boolean FileWriter(String s,String id) throws IOException {
        File f = new File(profileInfoFolderPath+ id +".txt");

        if (!(f.isFile() && f.exists())) {
            System.out.println("Writing - user profile doesn't exist, new one created-"+id);
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

        bw.write(s);
        bw.close();
        return true;
    }
}
