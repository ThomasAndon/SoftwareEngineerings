package Controller;

import Boundary.LoginPage;

import java.io.*;
public class WriteUserAccounts {
    /**
     * This methods write new account into the file
     * @param id user id
     * @param pw user password
     * @author Thomas Andon
     * @return false if exists, true if OK.
     * @throws IOException
     */
    public String userAccountFilePath = "src/Data/Account/account.txt";

    public boolean writeNewUser(String id, String pw) throws Exception {

        return writeAccounts(userAccountFilePath, id, pw);

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
}
