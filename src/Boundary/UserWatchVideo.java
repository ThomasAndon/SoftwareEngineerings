package Boundary;

import Controller.*;
import Entity.User;
import Entity.Video;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * This class is an interface for user to watch the video
 */
public class UserWatchVideo implements Initializable {

    @FXML
    private Text mainPage;
    @FXML
    private TableView<Video> videoTable;
    @FXML
    private TableColumn<Video, String> titleCol;
    @FXML
    private TableColumn<Video, String> typeCol;

    private User user;
    private ObservableList<Video> data;

    @FXML
    void onOpenClicked(ActionEvent event) {

        Video video = videoTable.getSelectionModel().getSelectedItem();
        String url = video.getPath();
        new WatchVideoControl().watchVideo(url);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            data = new ParseVideoMapper().parseVideoMapper();
        } catch (Exception e) {

            try {

              //  new WriteVideoMapping().writeVideoMapping(data);
                new VideoUploaderControl().write(data);
            } catch (Exception ee) {
                System.out.println("error when writing video mapper");
            }
        }
        videoTable.setItems(data);
        titleCol.setCellValueFactory(new PropertyValueFactory<Video,String>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Video,String>("type"));
    }
    public void getUser(User user) throws IOException {
        this.user = user;
    }
    public void toMainPage(MouseEvent mouseEvent) throws IOException {
        new ToPage().toMainPage(mainPage,user);
    }
}
