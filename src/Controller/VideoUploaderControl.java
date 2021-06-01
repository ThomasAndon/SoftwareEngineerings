package Controller;

import Entity.Video;
import javafx.collections.ObservableList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/**
 * controller of video uploader
 */
public class VideoUploaderControl {
    public String videoMapperFilePath = "src/Data/VideoMapper.txt";
    public void delete(Video video, ObservableList<Video> data) {
        for(int i = 0; i<data.size();i++) {
            if (data.get(i).getTitle().equals(video.getTitle())) {
                data.remove(i);
            }
        }

        try {
            write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * open a video
     * @param video
     */
    public void open(Video video){
        String url = video.getPath();
        String osName = System.getProperty("os.name", "");// 获取操作系统的名字

        try {
            if (osName.startsWith("Mac OS")) {
                Runtime.getRuntime().exec("open " + url);
            } else {
                Runtime.getRuntime().exec("cmd /c start " + url);
            }

        } catch (Exception e) {
            System.out.println("Error occurs opening.");
        }
    }
    /**
     * Write the video mapping into a text file
     * @param data
     * @throws Exception
     */
    public void write(ObservableList<Video> data) throws Exception{

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
