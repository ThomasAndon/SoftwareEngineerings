package Controller;

import NetBeans.User;
import NetBeans.Video;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.HashMap;
// test2

public class IOClass {

    // This is the path where the test files are.
    String userAccountFilePath = "src/Data/Account/account.txt";
    String coachAccountFilePath = "src/Data/Account/CoachAccounts.txt";
    String adminAccountFilePath = "src/Data/Account/AdminAccounts.txt";
    String profileInfoFolderPath = "src/Data/ProfileInfo/";
    String videoMapperFilePath = "src/Data/VideoMapper.txt";

    /**
     * This method reads id and password files and parse them.
     *
     * @return An array list with all members ID and passwords.
     * @throws Exception
     * @author Thomas Andon
     */
    public HashMap readAllAccount() throws Exception {

        return getHashMap(userAccountFilePath);
    }

    public HashMap readAllAccount(String path) throws Exception{

        return getHashMap(path);
    }

    /**
     * Encapsulated in readAllAccount() methods, return the Hashmap with dictated files.
     * @param path
     * @return A HashMap with all user accounts and corresponding passwords.
     * @throws IOException
     */
    public HashMap getHashMap(String path) throws IOException {
        HashMap<String, String> account = new HashMap<>();

        File f = new File(path);
        if (!(f.isFile() && f.exists())) {
            System.out.println("Reading - Account file doesn't exist");
            return null;
        }
        InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        while ((line = br.readLine()) != null) {
            // This is where we process each String line
            String[] res = line.split("\\s+");
            System.out.println(res[0]+" "+res[1]);
            account.put(res[0], res[1]);
        }
        br.close();

        return account;
    }


    /**
     * This methods write new account into the file
     * @param id user id
     * @param pw user password
     * @author Thomas Andon
     * @return false if exists, true if OK.
     * @throws IOException
     */
    public boolean writeNewUser(String id, String pw) throws Exception {
/*        File f = new File(accountFilePath);
        if (!(f.isFile() && f.exists())) {
            System.out.println("Writing - Account file doesn't exist, new one created");
        }

        if (new ValidChecker().checkIDExists(id)) {
            return false;
        }

        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f,true));
        BufferedWriter bw = new BufferedWriter(out);
        out.write(id+" " + pw + " " + "#\n");
        bw.close();
        return true;*/

        return writeAccounts(userAccountFilePath, id, pw);

    }

    public boolean writeNewUser(String path, String id, String pw) throws Exception {
        return writeAccounts(path, id, pw);
    }


    /**
     * Encapsulated in writeAccounts() methods.
     * @param path filepath
     * @param id coach/admin ID
     * @param pw coach/admin pw
     * @return  whether writing succeeded.
     * @throws Exception
     */
    public boolean writeAccounts(String path, String id, String pw) throws Exception {
        File f = new File(path);
        if (!(f.isFile() && f.exists())) {
            System.out.println("Writing - Account file doesn't exist, new one created");
        }

        if (new ValidChecker().checkIDExists(id)) {
            return false;
        }

        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f,true));
        BufferedWriter bw = new BufferedWriter(out);
        bw.write(id+" " + pw + " " + "#\n");
        bw.close();
        return true;
    }


    /**
     * This method takes in a user, set up his personal information and return the same user back.
     * @param user this is the user known only the ID and password.
     * @return return the user with his/her full information.
     * @author Thomas Andon
     */
    public User setUserProfile(User user) throws IOException {

        File f = new File(profileInfoFolderPath + user.getId() + ".txt");

        if (!(f.isFile() && f.exists())) {
            System.out.println(user.getId() + "profile txt does not exist.");
            return null;
        }

        InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
        BufferedReader in = new BufferedReader(isr);

        String line = in.readLine();
        String[] info = parseProfileString(line);
        user.setGender(info[1]);
        user.setHeight(Double.parseDouble(info[2]));
        user.setWeight(Double.parseDouble(info[3]));
        user.setLevel(Integer.parseInt(info[4]));
        user.setName(info[5]);
        //todo 此处新加入项目


        return user;
    }


    /**
     * This method parses the in-coming String and returns a string array.
     * @param info
     * @return
     */
    public String[] parseProfileString(String info) {

        String[] res = info.split("#");
        return res;
    }




    public boolean writeUserProfile(User user) throws IOException {
        String info = user.getId() + "#" + user.getGender() + "#" + user.getHeight()
            + "#" + user.getWeight() + "#" + user.getLevel() +"#" + user.getName() ;
        // todo 此处新加项目

        File f = new File(profileInfoFolderPath+user.getId()+".txt");

        if (!(f.isFile() && f.exists())) {
            System.out.println("Writing - user profile doesn't exist, new one created-"+user.getId());
            try {
                f.createNewFile();

            } catch(Exception e) {
                System.out.println("Error occurs when creating files. (path not exists)");
                return false;
            }


            //todo 不存在的文件会抛异常
        }

        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f));
        BufferedWriter bw = new BufferedWriter(out);

        bw.write(info);
        bw.close();
        return true;
    }


    /**
     * This class writes the video mapping info to the local file.
     * @param data
     */
    public void writeVideoMapping(ObservableList<Video> data) throws Exception {

        File f = new File(videoMapperFilePath);
        if (!(f.isFile() && f.exists())) {
            System.out.println("Writing - video mapper file doesn't exist, new one created");
        }



        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f,false));
        BufferedWriter bw = new BufferedWriter(out);


        for (int i = 0;i < data.size();i++) {
            String title = data.get(i).getTitle();
            String type = data.get(i).getType();
            String path = data.get(i).getPath();

            bw.write(title + "###" + path + "###" + type + "\n");
        }
        bw.close();
    }

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






    public static void main(String[] args) throws Exception {
        User a = new User();
        a.setId("123454321");
        a.setGender("male");
        System.out.println(new IOClass().writeUserProfile(a));;
        System.out.println("-------------");
        new IOClass().parseProfileString("#123454321#male#0.0#0.0#0");
    }
}
