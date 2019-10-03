import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class Member extends JDialog{
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btn1;
    private JButton btn2;    
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btnProfile;
    private JButton btnCancel;
    private boolean succeeded;
public Member(Frame parent) {
        super(parent, "Main Menu", true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
      
       btn1 = new JButton("Θέλω να φυτέψω");
        btn1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Planting nplanting = new Planting(parent);
                nplanting.setVisible(true);
            }
        });
        btn2 = new JButton("Θέλω να αγοράσω");
        btn2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Purchase agora = new Purchase(parent);
                agora.setVisible(true);
            }
        });
        btn3 = new JButton("Συζήτηση");
        btn3.addActionListener(new ActionListener() {
            //stigmiotypo gia na emfanistei to forum
            public void actionPerformed(ActionEvent e) {
                Forum nforum = new Forum(parent);
                nforum.setVisible(true);
            }
        });
        btn4 = new JButton("Eco friendly");
        btn4.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                Eco_friendly eco = new Eco_friendly(parent);
                eco.setVisible(true);
            }
        });
        
        btn5 = new JButton("Profile");
        btn5.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                //stigmiotypo gia na emfanistei to profile tou xristi
                Profile profil = new Profile(parent);
                profil.setVisible(true);
            }
        });
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btn1);
        bp.add(btn2);
        bp.add(btn3);
        bp.add(btn4);
        bp.add(btn5);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_START);
        
        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
    
    }

}
