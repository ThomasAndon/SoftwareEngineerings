package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Random select an advertisement from the text files
 */
public class OpenAd {


    private final String AdPath="src/Data/Advertisement/";


    ArrayList<String> fileName= new ArrayList<String>();

    /**
     * invoked when ad selected
     * @return
     * @throws IOException
     */
    public String selectAd() throws IOException {
        Random i=new Random();
        int con = 0;
        File file = new File(AdPath);
        File[] fs = file.listFiles();

        if(fs.length== 0){
            return "None";
        }
        else {
            for (File f : fs) {
                con = con + 1;

                fileName.add(f.getName());
            }

            int a = i.nextInt(con);
            return readAd(fileName.get(a));
        }
    }

    /**
     * read ad from file
     * @param name
     * @return
     * @throws IOException
     */
    public String readAd(String name) throws IOException {

        File ad=new File(AdPath+name);
        BufferedReader textFile = new BufferedReader(new FileReader(ad));
        String lineDta = "";
        StringBuffer stringBuffer=new StringBuffer();
        lineDta=textFile.readLine();
        while (lineDta != null){
            stringBuffer.append(lineDta+"\n");
            lineDta=textFile.readLine();
        }
        return  stringBuffer.toString();
    }
}
