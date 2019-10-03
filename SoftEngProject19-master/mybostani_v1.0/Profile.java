import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class Profile extends JDialog{
    private JButton btn1;
    private JButton btn2;    
    private JButton btn3;
    private JButton btnCancel;
public Profile(Frame parent) {
        super(parent, "Profile", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        btn1 = new JButton("MyGarden");
        btn1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MyGarden OpenGarden = new MyGarden(parent);
               OpenGarden.setVisible(true);
           }
        });
        
        btn2 = new JButton("Fav_Plant");
        btn2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Fav_Plant select_fav_plants = new Fav_Plant(parent);
               select_fav_plants.setVisible(true);
            }
        });
       
          
        btn3 = new JButton("Notes");
       btn3.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
              Notes myNotes = new Notes(parent);
               myNotes.setVisible(true);
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
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_START);
        
        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
    }

}