package Controller;

import java.io.*;

public interface profilePath {
     String profileInfoFolderPath = "src/Data/ProfileInfo/";

    default String[] readFile(String kind, String id) throws IOException {
        File f = new File(profileInfoFolderPath + kind + id + ".txt");

        if (!(f.isFile() && f.exists())) {
        System.out.println(id + "profile txt does not exist.");
        return null;
        }
        InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
        BufferedReader in = new BufferedReader(isr);

        String line = in.readLine();
        String[] info = parseProfileString(line);
        return info;
    }


    default String[] parseProfileString(String info) {

        String[] res = info.split("#");
        return res;
    }


}
