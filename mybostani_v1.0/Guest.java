import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class Guest extends JDialog{
    private JButton btn1;
    private JButton btn2;    
    private JButton btn4;
    private JButton btnCancel;
public Guest(Frame parent) {
        super(parent, "Main Menu", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        

        btn1 = new JButton("Θέλω να φυτέψω");
        btn1.addActionListener(new ActionListener() {
            //energeia afotou paththei to eco sto menu
            public void actionPerformed(ActionEvent e) {
                //dhmiourgia stigmiotypoy gia na emfanistei to parathyro t fytema
                Planting nplanting = new Planting(parent);
                nplanting.setVisible(true);
            }
        });
        btn2 = new JButton("Θέλω να αγοράσω");
        btn2.addActionListener(new ActionListener() {
            //energeia afotou paththei to eco sto menu
            public void actionPerformed(ActionEvent e) {
                //dhmiourgia stigmiotypoy gia na emfanistei to parathyro ths agoras
                Purchase agora = new Purchase(parent);
                agora.setVisible(true);
            }
        });
        
        btn4 = new JButton("Eco friendly");
        btn4.addActionListener(new ActionListener() {
            //energeia afotou paththei to eco sto menu
            public void actionPerformed(ActionEvent e) {
                //dhmiourgia stigmiotypoy gia na emfanistei to parathyro ths eco friendly
                Eco_friendly eco = new Eco_friendly(parent);
                eco.setVisible(true);
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                //kleisimo kai epistrofh stn prohgoymenh selida
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btn1);
        bp.add(btn2);
        bp.add(btn4);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_START);
        
        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
    
    }

}
