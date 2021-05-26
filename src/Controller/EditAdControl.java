package Controller;

import java.io.*;

public class EditAdControl {
    /**
     *@Description: write an new advertisement into the file
     *@param: path,content
     *@return:
     *@Author:Jin TianYu
     *@Date:2021/4/26
     */

    public void writeAd(String path,String content) throws IOException {
        File ad = new File(path);
        if(!ad.exists()){
            ad.createNewFile();
        }
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(ad,true));
        BufferedWriter bw = new BufferedWriter(out);
        bw.write(content);
        bw.flush();
        bw.close();
    }

    public void saveAd(String Add)throws IOException{
        EditAdControl wa = new EditAdControl();
        wa.writeAd("src//Data//Advertisement//Ad.txt",Add);
        //todo 实现一个AD写入一个文件当中
    }
}
