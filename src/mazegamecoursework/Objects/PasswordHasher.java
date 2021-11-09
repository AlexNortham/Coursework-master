package mazegamecoursework.Objects;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PasswordHasher {

    public String HashString(String password) {
        try {
            
            MessageDigest md = MessageDigest.getInstance("MD5"); //This creates a MessageDigest object with the MD5 hashing algorithm
            md.update(password.getBytes()); //This updates the MessageDigest with the bytes of the password to be hashed
            byte[] hashedBytes = md.digest(); //This digests the password into a hashed set of bytes

            StringBuilder sb = new StringBuilder();
            for(byte i: hashedBytes){
                sb.append(String.format("%02x", i));
            }//This constructs the hashed set of bytes into a hashed string

            return sb.toString();
            //This returns the hashed string
            
        } catch (NoSuchAlgorithmException ex) {
            
            Logger.getLogger(PasswordHasher.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }

    }
}
