package Controller;

import Entity.Session;
import Entity.User;
import java.io.*;

public class BookSessionControl {
    /**
     * @description  add a new living session for a particular user.
     * @param user
     * @param target
     * @param pAbility
     * @param note
     * @param time
     * @return if add session successful, return true; otherwise, return false.
     * @throws Exception
     */
    public boolean addSession (User user, String target, String pAbility, String note, String time) throws Exception{
        Session session = new Session();
        session.setUserID(user.getId());
        user.setTrainerID("8159"); //TODO 教练
        session.setTrainerID(user.getTrainerID());
        session.setTarget(target);
        session.setPhysicalAbility(pAbility);
        session.setNote(note);
        session.setTime(time);
        if(writeSession("src//Data//Session//"+session.getUserID()+".csv","src//Data//Schedule.csv",session)){
            return true;
        }
        return false;
    }


    /**
     * @Description: Check if the time chosen by the user is available for trainer
     * @Param:  time is the time chosen by the user while booking a session
     * @return:  whether the time is available for the trainer
     */
    public boolean checkSchedule(User user,String time) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader("src//Data//Schedule.csv"));
        while((line=reader.readLine())!=null){
            String[] item = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
            if(item[1].equals(user.getTrainerID())&&item[2].equals(time)){
                return false;
            }
        }
        return true;
    }
    /**
     * @Description: Save the information of user's session and schedule.
     * @Param:
     * @return: return true when write successful
     */
    public boolean writeSession(String path1, String path2, Session session) throws Exception {
        File f1 = new File(path1);
        int flag1 = 1, flag2 = 1;
        if(!f1.exists()){ flag1 = 0; }
        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(f1,true));
        BufferedWriter bw = new BufferedWriter(out);
        if(flag1==0){
            bw.write("userID,trainerID,time,target,physical ability,note,detail\n");
        }
        bw.write(session.getUserID()+","+session.getTrainerID()+","
                + session.getTime()+","+ session.getTarget() +","+session.getPhysicalAbility()+ ","
                + session.getNote()+ ","+ session.getDetail()+ "\n");
        bw.flush();
        bw.close();

        File f2 = new File(path2);
        if(!f2.exists()){ flag2 = 0; }
        OutputStreamWriter out2 = new OutputStreamWriter(new FileOutputStream(f2,true));
        BufferedWriter bw2 = new BufferedWriter(out2);
        if(flag2==0){
            bw2.write("userID,trainerID,time\n");
        }
        bw2.write(session.getUserID() + "," + session.getTrainerID() + "," + session.getTime() + "\n");
        bw2.flush();
        bw2.close();
        return true;
    }
}
