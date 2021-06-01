package Controller;

import java.io.*;

/**
 * Read the personal profile information
 */
public class profilePath {
     String profileInfoFolderPath = "src/Data/ProfileInfo/";

    /**
     * read data from file
     * @param kind type of user
     * @param id if of user
     */
    public String[] readFile(String kind, String id) throws IOException {
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

    /**
     * parse profile info string
     * @param info
     * @return
     */
    public String[] parseProfileString(String info) {
        String[] res = info.split("#");
        return res;
    }


}
