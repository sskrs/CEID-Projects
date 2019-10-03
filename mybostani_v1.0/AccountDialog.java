import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
 
public class AccountDialog extends JDialog {
 
    private JTextField tfUsername;
    private JTextField tfEmail;
    private JPasswordField pfPassword;
    private JLabel lbUsername;
    private JLabel lbEmail;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    private boolean succeeded;
 
    public AccountDialog(Frame parent) {
        super(parent, "Είσοδος στοιχείων", true);
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();
 
        cs.fill = GridBagConstraints.HORIZONTAL;
 
        lbUsername = new JLabel("Username: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbUsername, cs);
 
        tfUsername = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfUsername, cs);
 
        lbPassword = new JLabel("Password: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbPassword, cs);
 
        pfPassword = new JPasswordField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(pfPassword, cs);
            
        lbEmail = new JLabel("Email: ");
        cs.gridx = 0;
        cs.gridy = 2;
        cs.gridwidth = 1;
        panel.add(lbEmail, cs);
 
        tfEmail = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 2;
        cs.gridwidth = 2;
        panel.add(tfEmail, cs);
        
        panel.setBorder(new LineBorder(Color.GRAY));
        
        //me to patima tou button oloklhrwsh elegxetai an ikanopoihthikan oles oi synthikes 
        //dhmiourgias logariasmou pou perigrafontai sth method authenticate ths klashs 
        //signup dld to format twn stoixeiwn username, password, email
        btnLogin = new JButton("Ολοκλήρωση");
        btnLogin.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                if (Signup.authenticate(getUsername(), getPassword(),getEmail())) {
                    JOptionPane.showMessageDialog(AccountDialog.this,
                            "Καλωσόρισες " + getUsername() + "!",
                            "Επιτυχία δημιουργίας λογαριασμού",
                            JOptionPane.INFORMATION_MESSAGE);
                    succeeded = true;
                    dispose();
                } else {
                  JOptionPane.showMessageDialog(AccountDialog.this,
                           "Παρακαλώ εισάγετε έγκυρα στοιχεία",
                           "Αποτυχία δημιουργίας λογαριασμού",
                          JOptionPane.ERROR_MESSAGE);
                            tfUsername.setText("");
                    pfPassword.setText("");
                    tfEmail.setText("");
                    succeeded = false;
 
                }
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnLogin);
        bp.add(btnCancel);
 
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);
 
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }
 
    public String getUsername() {
        return tfUsername.getText().trim();
    }
 
    public String getPassword() {
        return new String(pfPassword.getPassword());
    }
 
    public String getEmail() {
        return tfEmail.getText().trim();
    }
    public boolean isSucceeded() {
        return succeeded;
    }
}
