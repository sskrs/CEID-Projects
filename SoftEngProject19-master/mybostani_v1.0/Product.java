import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class Product extends JDialog{
    private JButton btnGet;
    private JButton btnCancel;
public Product(Frame parent) {
        super(parent, "List of Products", true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        String[] listElems = new String[8];
        //proionta selidas thelo na agoraso
        listElems[0] = "Σπόροι ";
        listElems[1] = "Λιπάσματα";
        listElems[2] = "Έτοιμα Φυτά";
        listElems[3] = "Εντομοαπωθητικά";
        listElems[4] = "Χώμα";
        listElems[5] = "Εργαλεία" ;
        listElems[6] = "Γλάστρες";
        listElems[7] = "Εδαφοκάλυψη" ;
        
        JList list = new JList(listElems);
        JScrollPane pane = new JScrollPane(list);
        JButton btnGet = new JButton("Επιλέξτε");
        btnGet.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedElem = "";
                int selectedIndices[] = list.getSelectedIndices();
                for (int j = 0; j < selectedIndices.length; j++) {
                    String elem =
                            (String) list.getModel().getElementAt(selectedIndices[j]);
                    selectedElem += "\n" + elem;
                    Bucket.editBucket(selectedElem);
                   
                }
                //emfanish mhnymatos gia ta proionta poy exoyn epilegei
                JOptionPane.showMessageDialog(parent,
                    "Έχετε επιλέξει:" + Bucket.editBucket(selectedElem));
                  
            }
        });
        
       btnCancel = new JButton("Cancel");
       btnCancel.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               dispose();
            }
        });
        
        JPanel bp = new JPanel();
        bp.add(btnCancel);
        bp.add(btnGet);
        getContentPane().add(pane, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_START);
        
        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
    }

}

