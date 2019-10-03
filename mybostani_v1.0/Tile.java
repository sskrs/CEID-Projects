import java.awt.GridLayout;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class Tile extends JDialog{
    private JButton btn1;
    private JTable table;
    private DefaultTableModel tableModel;
    private JPopupMenu popupMenu;
    private JMenuItem menuItemAdd;
    private JMenuItem menuItemRemove;
    private JMenuItem menuItemEdit;
    private JMenuItem menuItemInfo;


    public Tile(Frame parent, String side1, String side2){
        super(parent, "MyGarden", true);
        
         Color BROWN = new Color(102,51,0);
        JPanel panel = new JPanel(new GridLayout(4, 4, 3, 3));
        int xside1 = Integer.parseInt(side1);
        int yside2 = Integer.parseInt(side2);
        for (int j = 0; j < xside1*yside2; j++) {

           if (j==3){
           //exoume balei oti to trito tile tha exei diaforetiko periexomeno
           //h logikh einai oti tha gemizei me to proion pou theloume
           //sto sygkekrimeno paradeigma emfanizei k3 
           //k= karota kai 3 o arithmos tou tile
           btn1 = new JButton("k"+j);
           btn1.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
              PopupW newpop = new PopupW(parent);
              newpop.setVisible(true);
            }
       });
        }else {
           btn1 = new JButton(""+j);
           
            //dhmiourghsame gia to background tn button ena custom xrwma
            //gia n moiazei me oti eixame stis mockup screens
            btn1.setBackground(BROWN);
            btn1.setForeground(Color.black);
           
            btn1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
              PopupW newtile = new PopupW(parent);
              newtile.setVisible(true);
            }
        });
            }
                
            panel.add(btn1);

        }
        JPanel f = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
        f.setSize(500, 500);
        f.setVisible(true);
    }
}
