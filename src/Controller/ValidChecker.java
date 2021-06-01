package Controller;


import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: This class contains all methods that checks the validation.
 * @Date: 2021/3/29
 */
public class ValidChecker {

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
     * check if the name is valid
     * @param name input name
     * @return whether name is valid
     */
    public boolean isNameValid(String name) {

        int length = name.length();
        if (length < 2 || length > 15) {
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

    /**
     * check if its a valid double
     */
    public boolean isValidDouble(String str){
        try{
            Double d= Double.parseDouble(str);
            System.out.println(d);
            return true;
        }catch (NumberFormatException e){
            System.out.println("wrong");
            return false;
        }

    }


}


