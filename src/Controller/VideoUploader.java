package Controller;

import NetBeans.Video;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class VideoUploader implements Initializable {


    @FXML
    private TableView<Video> videoTable;

    @FXML
    private TextField titleInput;

    @FXML
    private ChoiceBox<String> typeChoice;


    @FXML
    private TableColumn<Video, String> titleCol;

    @FXML
    private TableColumn<Video, String> typeCol;

    @FXML
    private Button pathBtn;


    private String temporaryFilePath;
    private ObservableList<Video> data; /*= FXCollections.observableArrayList(
        new Video("title1", "path1","type1"),
        new Video("title2", "path2","type2")
    );*/

    @FXML
    void onChoosePathClicked(ActionEvent event) {
        if(temporaryFilePath == null) {
            Stage s = (Stage) videoTable.getScene().getWindow();
            FileChooser fc = new FileChooser();
            fc.setTitle("Select Video to Update:");
            File f =  fc.showOpenDialog(s);
            if(f != null) {
                pathBtn.setText("Clear");
                temporaryFilePath = String.valueOf(f.toPath());
                System.out.println(temporaryFilePath);
            }
        } else {
            temporaryFilePath = null;
            pathBtn.setText("Path...");
        }

    }

    @FXML
    void onAddBtnClicked(ActionEvent event) {
        String title = titleInput.getText();

        // if the title already exists, terminated.
        for (int i = 0;i < data.size();i++) {
            if (data.get(i).getTitle().equals(title)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Title already exists. Use another one.");
                alert.show();
                return;
            }
        }


        // if the title is empty, adding terminated.
        if (title.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please specify the Title");
            alert.show();

        }

        // if no file path is indicated, terminated.
        if (temporaryFilePath == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please specify the file path");
            alert.show();

        }

/*        if (title.contains("#")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Title should not contain char '#'");
            alert.show();
            return;
        }*/

        // if no type specified, terminated.
        if (typeChoice.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please specify the file type");
            alert.show();
            return;
        }




        Video temp = new Video(titleInput.getText(),temporaryFilePath,typeChoice.getValue());
        data.add(temp);
        try {
            new IOClass().writeVideoMapping(data);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //写入文件

        //收尾工作，临时变量置空，清空输入框内容
        temporaryFilePath = null;
        titleInput.clear();
        pathBtn.setText("Path...");
        typeChoice.setValue(null);
    }

    @FXML
    void onDeletedSelected(ActionEvent event) {

    }

    @FXML
    void onOpenClicked(ActionEvent event) {

        Video video = videoTable.getSelectionModel().getSelectedItem();
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




/*        String osName = System.getProperty("os.name", "");// 获取操作系统的名字
        try {
            if (osName.startsWith("Windows")) {// windows
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (osName.startsWith("Mac OS")) {// Mac
                Class fileMgr = Class.forName("com.apple.eio.FileManager");
                Method openURL = fileMgr.getDeclaredMethod("openURL", String.class);
                openURL.invoke(null, url);
            }
            System.out.println("#########"+url);

        } catch(Exception e) {
            System.out.println(url);
        }*/



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeChoice.getItems().addAll("type1", "type2", "type3");
        try {
            data = new IOClass().parseVideoMapper();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Error occurs when parsing the video mapper file.");
            alert.show();
        }
        videoTable.setItems(data);
        titleCol.setCellValueFactory(new PropertyValueFactory<Video,String>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Video,String>("type"));
    }
}
