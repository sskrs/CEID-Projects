import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;


public class Login {
 
    public static boolean authenticate(String username, String password) {
        //balame enan kyrio logariasmo gia tis anagkes toy project
        if (username.equals("mybostani") && password.equals("mybostani")) {
           return true;
        }
        return false;
    }
}