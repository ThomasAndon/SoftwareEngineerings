package Controller;


import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: This class contains all methods that checks the validation.
 * @Date: 2021/3/29
 */
public class ValidChecker {
    //todo 所有需要检查的方法都写在这里，比如检查用户名、密码是否符合规范，等等。


    /**
     * @Description: check the validation of the input Id and Password
     * @return: boolean
     * @Author: ThomasAndon
     * @Date: 2021/3/30
     */
    public boolean isInvalidID(String id) {

        int length = id.length();
        if (length < 4 || length > 15) {
            return true;
        }
        for (int i = 0;i<length;i++) {
            char a = id.charAt(i);
            if ((a<'A'||a>'Z') && (a<'a' || a>'z') && ((a<'0')||(a>'9'))) {
                return true;
            }
        }

        return false;
    }

    public boolean isInvalidPw(String pw) {
        return isInvalidID(pw);
    }


    public boolean isSameString(String s1, String s2) {
        return s1.equals(s2);
    }


    /**
     * This method checks whether the account belongs to a valid user.
     *
     * @param id User ID
     * @param pw User password
     * @return Whether this account is valid
     * @throws Exception
     */
    public boolean isValidAccount(String id, String pw) throws Exception {

        return isAccountExists(new MatchAccount().userAccountFilePath, id, pw);
    }


    public boolean isValidAccount(String path, String id, String pw) throws Exception {
        return isAccountExists(path, id, pw);
    }



    public boolean isAccountExists(String path, String id, String pw) throws Exception {

        // Instantiate an all-accounts hashmap, it contains all accounts.
        HashMap accounts = new MatchAccount().readAllAccount(path);


        // if there is no such ID, then false is returned.
        if (!accounts.containsKey(id)) {
            return false;
        }

        // there is this ID, but the pw is wrong. false is returned.
        if (!pw.equals(accounts.get(id))) {
            return false;
        }

        return true;
    }

    /**
     * This method checks whether this ID has already exists.
     *
     * @param id
     * @return Whether this is an existed ID.
     * @throws Exception
     * @author Thomas Andon
     */
    public boolean checkIDExists(String id) throws Exception {
        HashMap accounts = new MatchAccount().readAllAccount();
        if (accounts.containsKey(id)) {
            return true;
        }
        return false;
    }

    public boolean checkIDExists(String path, String id) throws Exception {
        HashMap accounts = new MatchAccount().readAllAccount(path);
        if (accounts.containsKey(id)) {
            return true;
        }
        return false;
    }

    public boolean isNameValid(String name) {

        int length = name.length();
        if (length < 4 || length > 15) {
            return false;
        }
        for (int i = 0;i<length;i++) {
            char a = name.charAt(i);
            if ((a<'A'||a>'Z') && (a<'a' || a>'z')) {
                return false;
            }
        }

        return true;

    }



}


