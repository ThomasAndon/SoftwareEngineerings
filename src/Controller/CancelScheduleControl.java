package Controller;

import java.io.*;

/**
 * controller of cancelling schedule
 */
public class CancelScheduleControl {
    /**
     * @descriotion user cancels the booked living session.
     * @return return ture if cancel the session successfully
     * @throws Exception
     */

    public boolean cancelSession(File file, String i, String userID)throws Exception {
        File temp = null;
        BufferedReader br = null;
        PrintWriter pw = null;
        File temp2 = null;
        BufferedReader br2 = null;
        PrintWriter pw2 = null;
        File file2 = new File("src//Data//Schedule.csv");
        try {
            temp = File.createTempFile("temp", "temp");
            pw = new PrintWriter(temp);
            br = new BufferedReader(new FileReader(file));
            while (br.ready()) {
                String line = br.readLine();
                if (line.contains(i)) {
                    continue;
                }
                pw.write(line + "\n");
            }
            pw.flush();
            temp2 = File.createTempFile("temp2", "temp2");
            pw2 = new PrintWriter(temp2);
            br2 = new BufferedReader(new FileReader(file2));
            while (br2.ready()) {
                String line2 = br2.readLine();
                if (line2.contains(i) && line2.contains(userID)) {
                    continue;
                }
                pw2.write(line2 + "\n");
            }
            pw2.flush();
        }catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Exception");
        } finally {
            pw.close();
            br.close();
            pw2.close();
            br2.close();
            if (temp != null) {
                file.delete();
                temp.renameTo(file);
            }
            if (temp2 != null) {
                file2.delete();
                temp2.renameTo(file2);
            }
        }
        return true;
    }
}
