import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class Grid extends JDialog {

    private JTextField tfside1;
    private JTextField tfside2;
    private JLabel lbside1;
    private JLabel lbside2;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
 
    public Grid(Frame parent) {
        super(parent, "Εισαγωγή Διαστάσεων: Εμβαδόν Κήπου", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbside1 = new JLabel("Πλευρά 01: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbside1, cs);
 
        tfside1 = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfside1, cs);
        
     
        lbside2 = new JLabel("Πλευρά 02: ");
        cs.gridx = 0;
       cs.gridy = 1;
       cs.gridwidth = 1;
       panel.add(lbside2, cs);
 
        tfside2 = new JTextField(20);
       cs.gridx = 1;
       cs.gridy = 1;
       cs.gridwidth = 2;
        panel.add(tfside2, cs);
        panel.setBorder(new LineBorder(Color.GRAY));
 
        btnLogin = new JButton("Insert");
 
        btnLogin.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
               Tile newtile = new Tile(parent, getUsername(),getPassword());
              newtile.setVisible(true);
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
        //AfterLogin afterlogin = new AfterLogin(parent);
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
    
    
    public String getUsername() {
        return tfside1.getText().trim();
    }

    
    public String getPassword() {
        return tfside2.getText().trim();
    }
 
    public boolean isSucceeded() {
        return succeeded;
    }
}