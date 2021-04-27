package Controller;

import NetBeans.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// test2

public class IOClass {

    // This is the path where the test files are.
    String userAccountFilePath = "src/Data/Account/account.txt";
    String coachAccountFilePath = "src/Data/Account/CoachAccounts.txt";
    String adminAccountFilePath = "src/Data/Account/AdminAccounts.txt";

    String AdPath="src/Data/Advertisement/Ad.txt";

    String CoachProfilePath="src/Data/ProfileInfo/CoachProfile";
    String UserProfilePath="src/Data/ProfileInfo/UserProfile";
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

        File f = new File( UserProfilePath+ user.getId() + ".txt");

        if (!(f.isFile() && f.exists())) {
            System.out.println(user.getId() + "profile txt does not exist.");
            return null;
        }

        InputStreamReader isr = new InputStreamReader(new FileInputStream(f));
        BufferedReader in = new BufferedReader(isr);

        String line = in.readLine();
        String[] info = parseProfileString(line);
        user.setGender(info[1]);
        /*user.setHeight(Double.parseDouble(info[2]));
        user.setWeight(Double.parseDouble(info[3]));
        user.setLevel(Integer.parseInt(info[4]));*/
        user.setHeight(info[2]);
        user.setHeight(info[3]);
        user.setHeight(info[4]);


        user.setName(info[5]);


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
            + "#" + user.getWeight() + "#" + user.getLevel() +"#" + user.getName()+'\n';

        File f = new File(UserProfilePath+user.getId()+".txt");

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




    public static void main(String[] args) throws Exception {
        User a = new User();
        a.setId("123454321");
        a.setGender("male");
        System.out.println(new IOClass().writeUserProfile(a));;
        System.out.println("-------------");
        new IOClass().parseProfileString("#123454321#male#0.0#0.0#0");
    }
}
