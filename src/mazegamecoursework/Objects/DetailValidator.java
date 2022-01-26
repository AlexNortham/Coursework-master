package mazegamecoursework.Objects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetailValidator {
    private final Pattern emailPattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    private final Pattern usernamePattern = Pattern.compile("(([a-zA-Z0-9]{0,32})$)");

    public boolean Match(String email, String username, String password){

        Matcher emailMatcher = emailPattern.matcher(email);
        System.out.println(emailMatcher.matches());
        Matcher usernameMatcher = usernamePattern.matcher(username);
        System.out.println(usernameMatcher.matches());
        boolean passwordOk = password.length() <= 64;
        return emailMatcher.matches() && usernameMatcher.matches() && passwordOk;
    }
}
