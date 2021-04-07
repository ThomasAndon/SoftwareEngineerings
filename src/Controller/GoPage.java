package Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GoPage {
    private static FXMLLoader loader;
    private static Parent root;

    public static void setLoader(FXMLLoader loader) {
        GoPage.loader = loader;
    }

    private static FXMLLoader getFxmlLoader(String str) {
        Stage Scene = new Stage();

        try {

            root = loader.load(GoPage.class.getResource("../view/"+str+".fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene.setTitle("User");
        Scene.setScene(new Scene(root, 800, 600));
        Scene.show();
        return loader;
    }


    public static FXMLLoader go_to_Userpage() {

        return getFxmlLoader("ProfileInfo");
    }

    public static FXMLLoader go_to_Coachpage() {
        return getFxmlLoader("CoachInfo");

    }


}
