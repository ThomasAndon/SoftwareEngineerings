package Controller;

import Entity.Trainer;
import Entity.User;
import Entity.Video;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// test2

public class IOClass {

    // This is the path where the test files are.
    public String userAccountFilePath = "src/Data/Account/account.txt";
    public String coachAccountFilePath = "src/Data/Account/CoachAccounts.txt";
    public String adminAccountFilePath = "src/Data/Account/AdminAccounts.txt";
    public String profileInfoFolderPath = "src/Data/ProfileInfo/";
    public String videoMapperFilePath = "src/Data/VideoMapper.txt";

    public String AdPath="src/Data/Advertisement/Ad.txt";

    public String CoachProfilePath="src/Data/ProfileInfo/CoachProfile";
    public String UserProfilePath="src/Data/ProfileInfo/UserProfile";
    public String SchedulePath="src/Data/Schedule.csv";
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

        /*if (new ValidChecker().checkIDExists(id)) {
            return false;
        }*/

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

        File f = new File(profileInfoFolderPath + "userProfile/" +user.getId() + ".txt");

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

    public Trainer setCoachProfile(Trainer trainer)throws IOException{
        File f = new File(profileInfoFolderPath + "coachProfile/" + trainer.getTrainerID() + ".txt");

        if (!(f.isFile() && f.exists())) {
            System.out.println(trainer.getTrainerID() + "profile txt does not exist.");
            return null;
        }

        InputStreamReader ISR = new InputStreamReader(new FileInputStream(f));
        BufferedReader in = new BufferedReader(ISR);

        String line = in.readLine();
        String[] info = parseProfileString(line);
        trainer.setGender(info[1]);
        trainer.setHeight(Double.parseDouble(info[2]));
        trainer.setWeight(Double.parseDouble(info[3]));
        trainer.setTel(Integer.parseInt(info[4]));
        trainer.setName(info[5]);

        return trainer;
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
    *@Description: write an new advertisement into the file
    *@param: path,content
    *@return:
    *@Author:Jin TianYu
    *@Date:2021/4/26
    */

    public void writeAd(String path,String content) throws IOException {
        File ad = new File(path);
        if(!ad.exists()){
            ad.createNewFile();
        }
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(ad,true));
        BufferedWriter bw = new BufferedWriter(out);
        bw.write(content);
        bw.flush();
        bw.close();
    }
    /**
    *@Description:Read an advertisement from the file12
    *@param: Ad path
    *@return: String
    *@Author:Jin TianYu
    *@Date:2021/4/27
    */
    public String readAd(String path) throws IOException{
        File ad=new File(path);
        BufferedReader textFile = new BufferedReader(new FileReader(ad));
        String lineDta = "";
        StringBuffer stringBuffer=new StringBuffer();
        lineDta=textFile.readLine();
        while (lineDta != null){
                stringBuffer.append(lineDta+"\n");
                lineDta=textFile.readLine();
        }
        return  stringBuffer.toString();
    }

    /**
    *@Description: Read all of the user profiles
    *@param:
    *@return:
    *@Author:Jin TianYu
    *@Date:2021/4/26
    */
    private static List<File> readFile(String fileDir) {
        List<File> fileList = new ArrayList<File>();
        File file = new File(fileDir);
        File[] files = file.listFiles();// 获取目录下的所有文件或文件夹
        if (files == null) {// 如果目录为空，直接退出
            return null;
        }
        for (File f : files) {
            if (f.isFile()) {
                fileList.add(f);
            } else if (f.isDirectory()) {
                System.out.println(f.getAbsolutePath());
                readFile(f.getAbsolutePath());
            }
        }

        for (File f1 : fileList) {
            System.out.println(f1.getName());
        }
        return fileList;

    }
    /**
    *@Description: Get lessons information from the Schedule file
    *@param:
    *@return:
    *@Author:Jin TianYu
    *@Date:2021/5/9
    */
    public String readLesson(String id,String path) throws IOException {
        File lesson = new File(path);
        StringBuilder l= new StringBuilder();
        String lineDta="";
        try {
            BufferedReader textFile = new BufferedReader(new FileReader(lesson));

            while ((lineDta = textFile.readLine()) != null){
                String s= lineDta.split(",")[1];
                if(s.equals(id)){
                    String date=lineDta.split(",")[2];

                    l.append(date).append("\n");

                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return l.toString();
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

    public boolean writeCoachProfile(Trainer trainer) throws IOException {
        String info = trainer.getTrainerID() + "#" + trainer.getGender() + "#" + trainer.getHeight()
                + "#" + trainer.getWeight() + "#" + trainer.getTel() +"#" + trainer.getName() ;
        // todo 此处新加项目

        File f = new File(profileInfoFolderPath+trainer.getTrainerID()+".txt");

        if (!(f.isFile() && f.exists())) {
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
}
