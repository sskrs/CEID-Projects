import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 


public class PopupW extends JDialog{
    private JButton btn2;    
    private JButton btn3;
    private JButton btn4;
    private JButton btnCancel;
    private boolean succeeded;
public PopupW(Frame parent) {
        super(parent, "Eίδος", true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cst = new GridBagConstraints();

        JButton btn2 = new JButton("Διαγραφή");
        JButton btn3 = new JButton("Επεξεργασία");
        JButton btn4 = new JButton("Πληροφοριες");
        JButton btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                succeeded = true;
                dispose();


            }
        });
       

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridwidth = 2;
        cst.gridy = 0;
        panel.add(btn4,cst);
 

        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridwidth = 2;
        cst.gridx = 0;
        cst.gridy = 1;
        panel.add(btn2);
   
         cst.fill = GridBagConstraints.HORIZONTAL;
         cst.gridx = 0;
         cst.gridy = 2;
         cst.gridwidth = 2;
         panel.add(btn3, cst);
        
         cst.fill = GridBagConstraints.HORIZONTAL;
         cst.gridx = 0;
         cst.gridy = 3;
         cst.gridwidth = 2;
        panel.add(btnCancel, cst);
        
        getContentPane().add(panel, BorderLayout.CENTER);
        
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
    public boolean isSucceeded() {
        return succeeded;
    }
}
