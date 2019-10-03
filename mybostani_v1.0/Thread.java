import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class Thread extends JDialog{
    private JButton btnCancel;
public Thread(Frame parent) {
        super(parent, "Stored Threads", true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        //gia dieukolhnsh gemisame to menu me kapoia paradeigmata
        String[] columns = {"Τίτλος", "Κείμενο", "Ημερομηνία", "Κατηγορία"};
 
        Object[][] data = {
            {"Thread 1", "Mpla mpla mpla", "1/1/19", 1},
            {"Thread 2", "Mpla mpla mpla", "1/2/19", 1},
            {"Thread 3", "Mpla mpla mpla", "1/3/19", 2},
            {"Thread 4", "Mpla mpla mpla", "23/3/19", 3}};
        
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
     
        
        JPanel bp = new JPanel();
        bp.add(btnCancel);
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_START);

        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
        scrollPane.setVisible(true);
    }

}