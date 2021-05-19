package Controller;

import javafx.fxml.FXMLLoader;

public interface GoPage {
    default FXMLLoader PageChange(String s)  {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../view/" + s + ".fxml"));
        return loader;
    }
}
