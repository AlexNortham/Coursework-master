
package mazegamecoursework.Objects;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.regex.*;
public class EmailValidation {
    private String emailTo;
    private final Random random = new Random();
    private final String from = "mazegamecoursework@gmail.com";
    private final String password = "PlayMaze123";
    private String code;

    //These are the global variables I will be using in the class

    public EmailValidation(String emailTo) {
        this.emailTo = emailTo;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }
    
    public String getCode(){
        return code;
    }
    
    public void setCode(String code){
        this.code = code;
    }

    //This is my constructor, getter and setter functions

    
    public void getNewCode(){
        int codeNumber = random.nextInt(9998)+1;
        code = Integer.toString(codeNumber);
        if(codeNumber < 1000){
            code = "0" + code;
        }
        if(codeNumber < 100){
            code = "0" + code;
        }
        if (codeNumber < 10){
            code = "0" + code;
        }
    } //This generates a code and formats it in the correct way
    
    public boolean sendEmail(){
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
        
        Session session = Session.getDefaultInstance(properties,
                new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, "PlayMaze123");
            }
        }); //This sets up the session by logging into the email account that will send the confirmation email
        
        try{
            MimeMessage mime = new MimeMessage(session); //This creates a new email based on the session

            mime.setFrom(new InternetAddress(from)); //This sets the sender email address to the global variable "from"

            mime.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo)); //This sets the recipient email address to the parametric variable "emailTo" entered by the user

            mime.setSubject("Verify Your Email Address");
            mime.setText("Your verification code is " + code + ", please enter the code into the box on the application to verify your email address"); //These lines set up the email header and content, including the code randomly generated earlier

            Transport.send(mime); //This sends the email

            return true; //This returns true to validate that an email has been sent

        }catch(Exception e){
            e.printStackTrace();
            return false; //This returns false to signify that an email has not been sent

        }
    }
    
    public boolean validateAddress(){
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailTo);
        return matcher.matches();
    } //This creates a pattern matcher to verify that the parametric variable "emailTo" is in the correct format
}
