import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import static javax.swing.JOptionPane.*;

public class NThread extends JDialog{


    public NThread(Frame parent){
        super(parent, "Εισαγωγή Νέου Thread", true);
        
        JTable table = new JTable(); 
        Object[] columns = {"Title","Body","Date","Category"};
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);


        table.setModel(model);
        table.setBackground(Color.LIGHT_GRAY);
        table.setForeground(Color.black);
        Font font = new Font("",1,22);
        table.setFont(font);
        table.setRowHeight(30);
        

        JTextField text1 = new JTextField();
        JTextField text2 = new JTextField();
        JTextField text3 = new JTextField();
        JTextField text4 = new JTextField();
        

        JButton btnAdd = new JButton("Νέο thread");
        JButton btnDelete = new JButton("Διαγραφή thread");
        JButton btnUpdate = new JButton("Τροποποίηση thread");     
        
        text1.setBounds(20, 220, 100, 25);
        text2.setBounds(20, 250, 100, 25);
        text3.setBounds(20, 280, 100, 25);
        text4.setBounds(20, 310, 100, 25);
        
        btnAdd.setBounds(150, 220, 100, 25);
        btnUpdate.setBounds(150, 265, 100, 25);
        btnDelete.setBounds(150, 310, 100, 25);
        
        JScrollPane pane = new JScrollPane(table);
        pane.setBounds(0, 0, 880, 200);
        
        setLayout(null);
        
        add(pane);
        
        //bazoume ta keimena pou plhktrologhsame sto jframe
        add(text1);
        add(text2);
        add(text3);
        add(text4);
    
        // bazoume ta buttons sto jframe
        add(btnAdd);
        add(btnDelete);
        add(btnUpdate);
        
        //dhmiourgia pinaka antikeimenou gia ta dedomena grammhs
        Object[] row = new Object[4];
        
        
        btnAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                row[0] = text1.getText();
                row[1] = text2.getText();
                row[2] = text3.getText();
                row[3] = text4.getText();
                model.addRow(row);
            }
        });
        
        
        btnDelete.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();
                if(i >= 0){
                    //diagrash seiras pou exoume markarei
                    model.removeRow(i);
                }
                else{
                    //exoume prosthesei ena warning message se periptwsh 
                    //pou o xristis paei na diagrapsei kati xwris na yparxei eggrafh 
                    // ston pinaka me ta thread
                    JOptionPane.showMessageDialog(parent,
                            "Διαγραφή thread Error","ΠΡΟΣΟΧΗ!",
                            JOptionPane.WARNING_MESSAGE);
                    //System.out.println("Διαγραφή thread Error");
                }
            }
        });
        
        table.addMouseListener(new MouseAdapter(){
        
        @Override
        public void mouseClicked(MouseEvent e){
            int i = table.getSelectedRow();
            text1.setText(model.getValueAt(i, 0).toString());
            text2.setText(model.getValueAt(i, 1).toString());
            text3.setText(model.getValueAt(i, 2).toString());
            text4.setText(model.getValueAt(i, 3).toString());
        }
        });
        
        btnUpdate.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int i = table.getSelectedRow();
                if(i >= 0) 
                {
                   model.setValueAt(text1.getText(), i, 0);
                   model.setValueAt(text2.getText(), i, 1);
                   model.setValueAt(text3.getText(), i, 2);
                   model.setValueAt(text4.getText(), i, 3);
                }
                else{
                    //opws kai sto diagrafh warning etsi kai edw an den exei 
                    //epilegei thread gia tropopoihsh kai paththei to 
                    //button Update tha emfanistei warning message
                    JOptionPane.showMessageDialog(parent,
                            "Δεν επιλέχθηκε thread για τροποποιήση!" ,"ΠΡΟΣΟΧΗ!",
                            JOptionPane.WARNING_MESSAGE);
                   
                }
            }
        });
        
        JPanel bp = new JPanel();
        getContentPane().add(pane,BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_START);
        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
        setSize(900,400);
        setLocationRelativeTo(parent);
        pane.setVisible(true);
        
    }
}