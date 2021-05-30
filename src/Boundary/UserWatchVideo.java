package Boundary;

import Entity.User;

import Controller.ParseVideoMapper;
import Controller.ToPage;
import Controller.WriteVideoMapping;
import Entity.Video;
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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * The interface for user to watch the video
 */
public class UserWatchVideo implements Initializable {


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


    @FXML
    private Text exitText;


    private String temporaryFilePath;
    private ObservableList<Video> data;

     User currentuser;

    public void initdata(User user) {
        currentuser = user;
    }

    @FXML
    void onChoosePathClicked(ActionEvent event) {
        if (temporaryFilePath == null) {
            Stage s = (Stage) videoTable.getScene().getWindow();
            FileChooser fc = new FileChooser();
            fc.setTitle("Select Video to Update:");
            File f = fc.showOpenDialog(s);
            if (f != null) {
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
        for (int i = 0; i < data.size(); i++) {
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

        // if no type specified, terminated.
        if (typeChoice.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Please specify the file type");
            alert.show();
            return;
        }


        Video temp = new Video(titleInput.getText(), temporaryFilePath, typeChoice.getValue());
        data.add(temp);
        try {
            new WriteVideoMapping().writeVideoMapping(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        temporaryFilePath = null;
        titleInput.clear();
        pathBtn.setText("Path...");
        typeChoice.setValue(null);
    }

    @FXML
    void onDeletedSelected(ActionEvent event) {
        Video video = videoTable.getSelectionModel().getSelectedItem();
        for (int i = 0; i < data.size(); i++) {
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

    @FXML
    void onOpenClicked(ActionEvent event) {

        Video video = videoTable.getSelectionModel().getSelectedItem();
        String url = video.getPath();
        String osName = System.getProperty("os.name", "");
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        typeChoice.getItems().addAll("type1", "type2", "type3");

        try {
            data = new ParseVideoMapper().parseVideoMapper();
        } catch (Exception e) {
            try {
                new WriteVideoMapping().writeVideoMapping(data);
            } catch (Exception ee) {
                System.out.println("error when writing video mapper");
            }
        }
        videoTable.setItems(data);
        titleCol.setCellValueFactory(new PropertyValueFactory<Video, String>("title"));
        typeCol.setCellValueFactory(new PropertyValueFactory<Video, String>("type"));
    }

    @FXML
    public void exit(MouseEvent actionEvent) throws IOException {
        new ToPage().toMainPage(exitText, currentuser);
    }
}
