package Controller;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// test2

public class MatchAccount {

    // This is the path where the test files are.
    public String userAccountFilePath = "src/Data/Account/account.txt";
    public String coachAccountFilePath = "src/Data/Account/CoachAccounts.txt";
    public String adminAccountFilePath = "src/Data/Account/AdminAccounts.txt";
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


}
