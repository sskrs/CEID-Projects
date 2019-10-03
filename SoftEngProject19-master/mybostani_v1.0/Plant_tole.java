import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;


public class Plant_tole extends JDialog{
    private JButton btnGet;
    private JButton btnCancel;
public Plant_tole(Frame parent) {
        super(parent, "Ανθεκτικά Φυτά στην Ξηρασία", true);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
        
        String[] listElems = new String[14];
        //proionta selidas thelo na agoraso
        listElems[0] = "Ακακία κυανόφυλλη";
        listElems[1] = "Αμυγδαλία";
        listElems[2] = "Αριά";
        listElems[3] = "Αρμυρίκη";
        listElems[4] = "Βραχυχίτων";
        listElems[5] = "Γαζία" ;
        listElems[6] = "Γιογιόμπα";
        listElems[7] = "Γλεδίτσια" ;
        listElems[8] = "Δάφνη του Απόλλωνα";
        listElems[9] = "Έλατο πίνσαπο-ισπανικό έλατο aka Γκλάουκα" ;
        listElems[10] = "Έλατο της Κεφαλονιάς";
        listElems[11] = "Ελιά" ;
        listElems[12] = "Κατάλπη";
        listElems[13] = "Κέδρος Άτλαντα" ;
        JList list = new JList(listElems);
        JScrollPane pane = new JScrollPane(list);
        
        //xrisimopoihsame t method editBucket ths klashs Bucket gia na mas emfanisei
        //ta epilegemena proionta ths lista sas mas
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
        getContentPane().add(bp, BorderLayout.PAGE_END);
        
        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
    }

}

