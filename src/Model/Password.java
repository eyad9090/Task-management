package Model;

import java.util.Random;

/**
 * create automation password
 */

public class Password {
    /**
     * @param validChar valid character in password
     * @param pass automated password
     * @param length length of password
     */
    private String validChar = "QWERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm,.+*-@#!$&?123456789";
    private String pass = "";
    private int length = 8;

    public Password(){
        validChar = "QWERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm,.+*-@#!$&?123456789";
        String pass = "";
        length = 8;
    }
    Password ( int length ) {
        validChar = "QWERTYUIOPLKJHGFDSAZXCVBNMqwertyuioplkjhgfdsazxcvbnm,.+*-@#!$&?123456789";
        String pass = "";
        this.length = length;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }



    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getValidCahr() {
        return validChar;
    }

    public void setValidCahr(String validCahr) {
        this.validChar = validCahr;
    }

    /**
     * this method get valid password
     * @return string of valid password
     */

    public String createPassword (){
        Random rand = new Random();
        while ( !Validation.isValidPassword(pass) ){
            pass = "";
            for ( int i = 0 ; i <= this.length ; i++ ){
                pass += validChar.charAt(rand.nextInt(validChar.length()));
            }
        }
        return  pass;
    }




}
