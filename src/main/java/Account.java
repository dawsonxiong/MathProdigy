/**
 * Authors: Dawson Xiong, Gary Wang
 * Date completed: 28 January 2022
 * Program description: This is a class that is used specifically for methods relating to user's data (balance, skins, username, etc.)
 */
import java.io.*;

public class Account {
    // Account's username
    private static String username;

    /**
     * This method will find the amount of money the current user has
     * @return the amount of money user has
     */
    public static int getMoney() {
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("data\\usersInfo.txt"));
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                if (words[0].equals(username)) {
                    return Integer.parseInt(words[1]);
                }
            }
        } catch (IOException iox) {
            iox.printStackTrace();
        }
        return 0;
    }

    /**
     * This method will find the skins that user has (in a String array)
     * @return a string array containing all skins user has (yes/no)
     */
    public static String[] getSkins() {
        String[] skins = new String[6];
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader("data\\usersInfo.txt"));
            while ((line = br.readLine()) != null) {
                String[] words = line.split(" ");
                if (words[0].equals(username)) {
                    System.arraycopy(words, 2, skins, 0, 6);
                }
            }
        } catch (IOException iox) {
            iox.printStackTrace();
        }
        return skins;
    }

    /**
     * This method will clear usersInfo.txt for data transfer w/temp.txt
     */
    public static void clearInfo() {
        try {
            FileWriter fwOb = new FileWriter("data\\usersInfo.txt", false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }

    /**
     * This method is made to transfer data from temp.txt to usersInfo.txt
     */
    public static void transferFile() {
        try {
            FileReader br = new FileReader("data\\temp.txt");
            FileWriter bw = new FileWriter("data\\usersInfo.txt", true);
            Account.clearInfo();
            int c;
            while ((c = br.read()) != -1) {
                bw.write(c);
            }
            br.close();
            bw.close();
        } catch (IOException iox) {
            iox.printStackTrace();
        }
    }


    public static void setUsername(String str) {
        username = str;
    }
    public static String getUsername() {
        return username;
    }
}
