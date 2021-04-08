package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GoPage {


    private static FXMLLoader getFxmlLoader(String str) throws IOException {
        Stage Scene = new Stage();
        FXMLLoader loader = new FXMLLoader();


        Parent root = loader.load(GoPage.class.getResource("../view/"+str+".fxml"));

        Scene.setTitle("User");
        Scene.setScene(new Scene(root, 800, 600));
        Scene.show();
        return loader;
    }


    public static FXMLLoader go_to_Userpage() throws IOException {

        return getFxmlLoader("ProfileInfo");
    }

    public static FXMLLoader go_to_Coachpage() throws IOException {
        return getFxmlLoader("CoachInfo");

    }


}
