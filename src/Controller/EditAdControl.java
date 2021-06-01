package Controller;

import java.io.*;

/**
 * controller of adding ad
 */
public class EditAdControl {

    /**
     *@Description: write an new advertisement into the file
     *@param: path,content
     *@return:
     *@Author:Jin TianYu
     *@Date:2021/4/26
     */
    private String path ="src/Data/Advertisement/";

    public void writeAd(String name,String content) throws IOException {
        File ad = new File(path + name +".txt");
        if(!ad.exists()){
            ad.createNewFile();
        }
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(ad,true));
        BufferedWriter bw = new BufferedWriter(out);
        bw.write(content);
        bw.flush();
        bw.close();
    }

    /**
     * after adding ad, save it
     * @param name
     * @param txt
     * @throws IOException
     */
    public void saveAd(String name, String txt)throws IOException{
        EditAdControl wa = new EditAdControl();
        wa.writeAd(name,txt);
        //todo 实现一个AD写入一个文件当中
    }
}
