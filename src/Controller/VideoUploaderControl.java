package Controller;

import Entity.Video;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;

public class VideoUploaderControl {
    public void delete(Video video, ObservableList<Video> data) {
        for(int i = 0; i<data.size();i++) {
            if (data.get(i).getTitle().equals(video.getTitle())) {
                data.remove(i);
            }
        }

        try {
            new WriteVideoMapping().writeVideoMapping(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void open(Video video){
        String url = video.getPath();
        String osName = System.getProperty("os.name", "");// 获取操作系统的名字

        try {
            if (osName.startsWith("Mac OS")) {
                Runtime.getRuntime().exec("open \"" + url + "\"");
            } else {
                Runtime.getRuntime().exec("cmd /c start " + url);
            }

        } catch (Exception e) {
            System.out.println("Error occurs opening.");
        }
    }

}
