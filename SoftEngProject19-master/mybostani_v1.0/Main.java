import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.*;

public class Main {
   
    public static void main(String[] args) {
        //onoma arxikou parathurou molis o xristis anoigei thn efarmogh
        final JFrame frame = new JFrame("MyBostani Application");
        
        //dhmiourgia triwn arxikwn button pou isodynamoun me tis treis epiloges ths main page
        final JButton btnguest = new JButton("Επισκέπτης");
        final JButton btnuser = new JButton("Μέλος");
        final JButton btnnewacc = new JButton("Δημιουργία Λογαριασμού");
        btnguest.setBackground(Color.GREEN.darker());
        btnguest.setForeground(Color.WHITE);
        btnuser.setBackground(Color.GREEN.darker());
        btnuser.setForeground(Color.WHITE);
        btnnewacc.setBackground(Color.GREEN.darker());
        btnnewacc.setForeground(Color.WHITE);
        
        //events molis patithoun ta button
        //event gia button member            
        btnuser.addActionListener(
        new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        LoginDialog loginDlg = new LoginDialog(frame);
                        loginDlg.setVisible(true);
                        
                        //elegxos ena egine eisodos swstwn stoixeiwn
                        if(loginDlg.isSucceeded()){ 
                          Member afterlogin = new Member(frame);
                          afterlogin.setVisible(true);
                          
                        }
                        }
                    
                });


        //event gia button guest        
        btnguest.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        Guest afterguest = new Guest(frame);
                        afterguest.setVisible(true);
                     
                    }
                });
 
        //event gia button neou logariasmou        
        btnnewacc.addActionListener(
                new ActionListener(){
                     public void actionPerformed(ActionEvent e) {
                        AccountDialog newaccdialog = new AccountDialog(frame);
                        newaccdialog.setVisible(true);
                        
                        //kanei elegxo me xrisi ths isSucceeded gia na dei an to username
                        //to password kai to email exoun to swsto format
                        if(newaccdialog.isSucceeded()){
                          Member afteraccount = new Member(frame);
                          afteraccount.setVisible(true);
                          
                          
                        }
                    }
                });;
            


        //dhmiourgia neou stigmiotypoy gia to JPanel wste na emfanistoyn ta button
        //ta orismata sto BorderLayout aforoun to padding metaksi twn button
        JPanel panel = new JPanel(new BorderLayout(5,5));
    
       
        //topothetisi button sthn start, center kai end ths selidas
        panel.add(btnguest, BorderLayout.PAGE_START);
        panel.add(btnuser, BorderLayout.CENTER);
        panel.add(btnnewacc, BorderLayout.PAGE_END);
        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,200);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}