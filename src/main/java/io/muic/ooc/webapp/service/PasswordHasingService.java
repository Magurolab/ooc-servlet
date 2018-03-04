package io.muic.ooc.webapp.service;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordHasingService {
    public static String gethashPassword(String textPassword){
        return BCrypt.hashpw(textPassword, BCrypt.gensalt(9));
    }
    public static boolean verifyPassword(String hashedPassword, String textPassword){
        return BCrypt.checkpw(textPassword, hashedPassword);
    }
}
