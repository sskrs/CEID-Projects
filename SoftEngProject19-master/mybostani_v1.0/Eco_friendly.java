import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Eco_friendly extends JDialog{
    private JButton btn1;
    private JButton btn2;    
    private JButton btn3;
    private JButton btn4;
    private JButton btnCancel;
public Eco_friendly(Frame parent) {
        super(parent, "Eco_friendly Menu", true);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cst = new GridBagConstraints();
        
        JButton btn1 = new JButton("Κομποστοποιητής");
        JButton btn2 = new JButton("CO2");
        JButton btn3 = new JButton("Aνθεκτικά Φυτά");
        btn3.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
               //dhmiourgia stigmiotypou gia na emfanistei selida Product ena paththei to btn1
               Plant_tole anfyta = new Plant_tole(parent);
               anfyta.setVisible(true);
          }
        });
        JButton btn4 = new JButton("Βοήθεια");
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
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
    

