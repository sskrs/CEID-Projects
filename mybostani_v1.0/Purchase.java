import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class Purchase extends JDialog{
    private JTextField tfUsername;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btn1;
    private JButton btn2;    
    private JButton btn3;
    private JButton btnCancel;

public Purchase(Frame parent) {
        super(parent, "Purchase Menu", true);
       
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        btn1 = new JButton("Προϊόν");
        btn1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               //dhmiourgia stigmiotypou gia na emfanistei selida Product ena paththei to btn1
               Product proion = new Product(parent);
               proion.setVisible(true);
          }
        });
        
        btn2 = new JButton("Καλάθι");
              
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btn1);
        bp.add(btn2);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_START);
        
        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
    }

}