package Controller;


import NetBeans.User;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description: This class contains all methods that checks the validation.
 * @Date: 2021/3/29
 */
public class ValidChecker {
    //todo 所有需要检查的方法都写在这里，比如检查用户名、密码是否符合规范，等等。



    public boolean isIDValid(String id) {
        //todo 实现id检查逻辑，只能包括大小写字母和数字，长度在4-15位。
        if(id.length()>=4 && id.length()<=15) {
            String regex = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！ @#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
            Pattern pattern = Pattern.compile(regex);
            Matcher hasSpecial = pattern.matcher(id);
            if(hasSpecial.find()==false){
                return hasSpecial.find();
            }
            else {
                return hasSpecial.find();
            }
        }
        else { return  true; }
    }

    public boolean isPwValid(String pw) {
        if(pw.length()>=4 && pw.length()<=15) {
            String regex = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！ @#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
            Pattern pattern = Pattern.compile(regex);
            Matcher hasSpecial = pattern.matcher(pw);
            if(hasSpecial.find()==false){
                return hasSpecial.find();
            }
            else {
                return hasSpecial.find();
            }
        }
        else { return  true; }
    }

    

    public boolean isSameString (String s1, String s2) {
        return s1.equals(s2);
    }

    public boolean isValidAccount (String s1, String s2) {
        //todo 如果有这个user就true
        return false;
    }
    


}
