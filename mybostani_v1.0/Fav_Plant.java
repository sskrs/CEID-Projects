import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class Fav_Plant extends JDialog{
    private JButton btnCancel;
    private JButton btnnew;
public Fav_Plant(Frame parent) {
        super(parent, "Αγαπημένα Φυτά", true);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        String[] columns = {"Όνομα", "Είδος"};
        //gemisma me paradeigma apo emas
        Object[][] data = {
            {"Πιπεριά","Λαχανικά"},
            {"Μέντα","Αρωματικά"},
            {"Λεμόνι","Φρούτα"}};
        
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnnew = new JButton("Προσθήκη νέου");
   
        JPanel bp = new JPanel();
        bp.add(btnnew);
        bp.add(btnCancel);
       
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_START);

        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
        scrollPane.setVisible(true);
    }

}