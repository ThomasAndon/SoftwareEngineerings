package Controller;

import Entity.Video;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class WriteVideoMapping {

    public String videoMapperFilePath = "src/Data/VideoMapper.txt";
    /**
     * Write the video mapping into a text file
     * @param data
     * @throws Exception
     */

    public void writeVideoMapping(ObservableList<Video> data) throws Exception {

        File f = new File(videoMapperFilePath);
        if (!(f.isFile() && f.exists())) {
            System.out.println("Writing - video mapper file doesn't exist, new one created");
        }



        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f,false));
        BufferedWriter bw = new BufferedWriter(out);
        for (int i = 0;i < data.size();i++) {
            String title = data.get(i).getTitle();
            String type = data.get(i).getType();
            String path = data.get(i).getPath();

            bw.write(title + "###" + path + "###" + type + "\n");
        }
        bw.close();
    }
}
