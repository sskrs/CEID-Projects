import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class Forum extends JDialog{
    private JButton btn1;
    private JButton btn2;    
    private JButton btn3;
    private JButton btn4;
    private JButton btnCancel;
public Forum(Frame parent) {
        super(parent, "Forum Menu", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        btn1 = new JButton("Νέο thread");
        btn1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               NThread create_thread = new NThread(parent);
               create_thread.setVisible(true);
           }
        });
        
        btn2 = new JButton("Open thread");
        btn2.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
                Thread open_thread = new Thread(parent);
                open_thread.setVisible(true);
            }
        });
       
        //den exei mpei kapoio event molis patithei to btn3  
        btn3 = new JButton("Open discussion");
        
        btn4 = new JButton("Κατηγορίες");
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
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_START);
        
        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
    }

}