package NetBeans;

import java.net.URI;

public class Video {

    private String title;
    private String type;
    private String path;

    public Video() {

    }

    public Video(String title, String path, String type) {
        this.title = title;
        this.path = path;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
