package Controller;

public class WatchVideoControl {
    private String osName = System.getProperty("os.name", "");
    public void watchVideo(String url){
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
