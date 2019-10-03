import java.util.regex.Pattern; 
import java.util.regex.Matcher;

public class Signup {

    public static boolean authenticate(String username, String password, String email) {
            //elegxei to format twn stoixeiwn an einai swsto
            //regex gia format email
            //regexName gia format username
            //regexPWD gia format password
            String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
            String regexNAME = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*$";
            String regexPWD ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*$";
            Pattern pattern = Pattern.compile(regex);
            Pattern patternNAME = Pattern.compile(regexNAME);
            Pattern patternPWD = Pattern.compile(regexPWD);
            Matcher matcher = pattern.matcher(email);
            Matcher matcherNAME = patternNAME.matcher(username);
            Matcher matcherPWD = patternPWD.matcher(password);

            boolean matches = matcher.matches();
            boolean matches1 = matcherNAME.matches();
            boolean matches2 = matcherPWD.matches();
            if(matcher.matches() && matcherNAME.matches() && matcherPWD.matches()) {
            return true;
        } else {
            return false; }
    }
}