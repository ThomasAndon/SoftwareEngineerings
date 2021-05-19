package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadAd {

    public String AdPath="src/Data/Advertisement/Ad.txt";
    /**
     *@Description:Read an advertisement from the file12
     *@param: Ad path
     *@return: String
     *@Author:Jin TianYu
     *@Date:2021/4/27
     */
    public String readAd(String path) throws IOException {
        File ad=new File(path);
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
