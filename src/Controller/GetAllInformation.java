package Controller;

import java.io.*;

/**
 * Get all of the users/coaches information into a file
 */
public class GetAllInformation {

    private String path = "src/Data/ProfileInfo/";

    public void GetInfo(String s1, String s2) throws IOException {
        String Data ="";
        File AllInfo = new File(path + s2);
        File file = new File(path+s1);
        File[] fs = file.listFiles();
        for(File f:fs){
            File f1 = new File(String.valueOf(f));
            BufferedReader textFile = new BufferedReader(new FileReader(f1));
            Data = Data+textFile.readLine()+"\n";
        }
        BufferedWriter bw = new BufferedWriter(new FileWriter(AllInfo));
        bw.flush();
        bw.write(Data);
        bw.close();

    }

}
