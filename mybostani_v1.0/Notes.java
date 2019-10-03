import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class Notes extends JDialog{
    private JButton btnCancel;
    private JButton btnnew;
    private boolean succeeded;
public Notes(Frame parent) {
        super(parent, "Σημειώσεις", true);
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        String[] columns = {"", ""};
        
        //gemisma me diafores shmeiwseis apo emas
        Object[][] data = {
            {"Τίτλος 1","20 Μαρτίου 2019"},
            {"Τίτλος 2","15 Ιουνίου 2019"},
            {"Τίτλος 3","7 Σεπτεμβρίου 2019"}};
        
        JTable table = new JTable(data, columns);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        btnnew = new JButton("Προσθήκη νέας Σημείωσης");
        
        JPanel bp = new JPanel();
        bp.add(btnCancel);
        bp.add(btnnew);
        getContentPane().add(scrollPane,BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_START);

        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
        scrollPane.setVisible(true);
    }

}