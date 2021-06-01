package Controller;

import Entity.Video;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;


/**
 * Read the VideoMapper text file and parse data
 */
public class ParseVideoMapper {


    public String videoMapperFilePath = "src/Data/VideoMapper.txt";

    /**
     * retur a video list
     * @return
     * @throws Exception
     */
    public ObservableList<Video> parseVideoMapper() throws Exception {

        File f = new File(videoMapperFilePath);
        if (!(f.isFile() && f.exists())) {
            System.out.println("Reading - cannot find video mapping file.");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Can't find the mapping file");
            alert.show();
//            return null;
        }

        ObservableList<Video> data =  FXCollections.observableArrayList();


        InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while((line = br.readLine()) != null) {
            String[] info = line.split("###");
            Video video = new Video(info[0],info[1],info[2]);
            data.add(video);
        }

        br.close();

        return data;
    }
}
