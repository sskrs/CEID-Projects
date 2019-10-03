import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Planting extends JDialog{
    private JButton btn1;
    private JButton btn2;    
    private JButton btn3;
    private JButton btn4;
    private JButton btn5;
    private JButton btn6;
    private JButton btnCancel;
    public Planting(Frame parent) {
        super(parent, "Planting Menu", true);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cst = new GridBagConstraints();
        
        //ektos apo to btnCancel gia logous suntomias den balame kapoio allo event sta upoloipa button
        JButton btn1 = new JButton("Φρούτα");
        JButton btn2 = new JButton("Λαχανικά");
        JButton btn3 = new JButton("Αρωματικά");
        JButton btn4 = new JButton("Βρώσιμα Άνθη");
        JButton btn5 = new JButton("Βότανα");
        JButton btn6 = new JButton("Βοήθεια");
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        //thesi pou tha emfanistoun ta button sto frame
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 0;
        panel.add(btn1,cst);
 
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 0;
        panel.add(btn2);
 
        cst.gridx = 2;
        cst.gridy = 0;
        panel.add(btn3, cst);

 
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridwidth = 3;
        cst.gridx = 0;
        cst.gridy = 1;
        panel.add(btn4,cst);
 
        
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 2;
        cst.gridwidth = 1;   
        cst.gridy = 2;       
 
        panel.add(btnCancel,cst);
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.PAGE_START);
        
        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
       }
}