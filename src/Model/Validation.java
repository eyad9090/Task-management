package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this is validation class methods
 */

public class Validation {
    /**
     * validate email
     * @param emailAdressen
     * @return
     */
    public static boolean isValidEmail(String emailAdressen) {
        Pattern pattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(emailAdressen);
        return matcher.find();
    }

    /**
     * validate password
     * @param password
     * @return
     */
    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        return matcher.find();
    }

    /**
     * validate role
     * @param role
     * @return
     */
    public static boolean isValidRole(String role)
    {
        String[]roles={"employee","team-leader","project-manager","admin"};
        for(String r:roles)
        {
            if(r.equals(role))
                return true;
        }
        return false;
    }

    /**
     * validate id
     * @param id
     * @return
     */
    public static boolean isValidId(int id)
    {
        if(id>=1)
            return true;
        return false;
    }

    /**
     * validate percentage
     * @param percentage
     * @return
     */
    public static boolean isValidPercentage ( int percentage ){
        return percentage >=0 && percentage <= 100 ;
    }
}
