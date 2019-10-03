import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class MyGarden extends JDialog{

    private JButton btn1;
    private JButton btnCancel;
public MyGarden(Frame parent) {
        super(parent, "MyGarden", true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        //dhmiourgia garden
        btn1 = new JButton("Create Grid");
        btn1.addActionListener(new ActionListener() {
                
            public void actionPerformed(ActionEvent e) {
                Grid CreateGrid = new Grid(parent);
                CreateGrid.setVisible(true);
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
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_START);

        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
    }

}