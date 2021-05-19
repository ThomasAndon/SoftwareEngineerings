package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public interface OpenAd {
     String AdPath="src/Data/Advertisement/Ad.txt";

    default String readAd() throws IOException {

        File ad=new File(AdPath);
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
