/**
 * @authors Matteo Tanzi <mtanzi@uwo.ca>, Matthew Liu <mliu493@uwo.ca>, Sahebjot Bal <sbal7@uwo.ca>
 * 
 * This class is for storing account information
 */

/**
 * imported libraries used 
 */
import java.io.*;
import java.util.*;

/**
 * Creates a hashmap to store usernames and passwords. It creates a new file for each user, name of file is the username and inside file is password
 */
public class IDandPass {
	//class body

    private HashMap<String, String> loginInfo = new HashMap<String, String>();

    IDandPass() {
        // Read all the text files in "users" and add them to "loginInfo"
        File folder = new File("users//");
        File[] listOfFiles = folder.listFiles();

        String[] usernames;
        String[] passwords;

        //Get Usernames and passwords
        usernames = getUsernames(listOfFiles);
        passwords = getPasswords(listOfFiles);

        //Write them into hashtable
        writeUserBase(usernames, passwords);

    }
    /**
     * Finds account information
     * @return user account information
     */
    public HashMap getLoginInfo() {
        return loginInfo;
    }

    /**
     * Finds usernames stored 
     * @param listOfFiles file where usernames are stored
     * @return usernames if found else null
     */
    private String[] getUsernames(File[] listOfFiles) {
        try {
            if (listOfFiles != null && listOfFiles.length > 0) {
                String[] usernames = new String[listOfFiles.length];
                for (int i = 0; i < listOfFiles.length; i++) {
                    usernames[i] = listOfFiles[i].getName().split("\\.")[0];
                }
                return usernames;
            }
        } catch (NullPointerException e) {
            System.out.println("Please Create a user");
        }
        return null;
    }

    /**
     * Finds passwards stored
     * @param listOfFiles files where usernames are stored
     * @return passwords if found else null
     */
    private String[] getPasswords(File[] listOfFiles) {
        try {
            if (listOfFiles != null && listOfFiles.length > 0) {
                String[] passwords = new String[listOfFiles.length];
                for (int i = 0; i < listOfFiles.length; i++) {

                    File file = new File(listOfFiles[i].getPath());
                    Scanner scanner = new Scanner(file);
                    String line = "";

                    line = scanner.nextLine();
                    passwords[i] = line;

                }
                return passwords;
            }
        } catch (Exception e) {
            System.out.println("Password read error");
        }
        return null;
    }
    
    /**
     * Creates new user
     * @param users new username 
     * @param passes new password
     */
    private void writeUserBase(String[] users, String[] passes) {
        for (int i = 0; i < users.length; i++) {
            loginInfo.put(users[i], passes[i]);
        }
    }
}