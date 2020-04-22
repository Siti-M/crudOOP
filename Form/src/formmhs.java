import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Lab Informatika
 */
public class formmhs extends JFrame{
    JLabel lnim,lnama,lalamat,ljudul;
    JTextField txnim,txnama,txalamat;
    JButton simpan, kembali, del, lihat;
    Statement statement;
    
    public void formmhs(){
        
        setTitle("Form Data Mahasiswa");
        
        ljudul = new JLabel("INPUT DATA MAHASISWA ");
        lnim = new JLabel("NIM ");
        lnama = new JLabel("Nama ");  
        lalamat = new JLabel("Alamat ");
        
        txnim = new JTextField("");
        txnama = new JTextField("");
        txalamat = new JTextField("");
        
        simpan = new JButton("Simpan");
        kembali = new JButton("Kembali");
        
        setLayout(null);
        add(ljudul);
        add(lnim);
        add(lnama);
        add(lalamat);
        add(txnim);
        add(txnama);
        add(txalamat);
        add(simpan);
        add(kembali);
        
        
        ljudul.setBounds(120, 10, 200, 20);
        lnim.setBounds(75, 50, 30, 20);
        lnama.setBounds(75, 75, 50, 20);
        lalamat.setBounds(75, 100, 50, 20);
        txnim.setBounds(150, 50, 150, 20);
        txnama.setBounds(150, 75, 150, 20);
        txalamat.setBounds(150, 100, 150, 20);
        kembali.setBounds(85, 140, 90, 20);
        simpan.setBounds(200, 140, 90, 20);

        
        setSize(400,250); //untuk luas jendela
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        simpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                int a1 =  Integer.parseInt(txnim.getText());
                String a2 = txnama.getText();
                String a3 = txalamat.getText();
                        
                KoneksiDB koneksi = new KoneksiDB();
                    try {
                        statement = koneksi.getKoneksi().createStatement();
                        statement.executeUpdate("INSERT INTO data_mhs VALUES ('" + a2 + "','" + a1 +
                            "','" + a3 + "')" );
                        JOptionPane.showMessageDialog(rootPane, "Data Tersimpan");
                        
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(formmhs.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
                System.out.println("NIM : "+a1);
                System.out.println("Nama : "+a2);
                System.out.println("Alamat : "+a3);
                    
                } catch (NumberFormatException ex) {
                 JOptionPane.showMessageDialog(rootPane,"TIPE DATA SALAH");
                } catch (Error ext){
                 JOptionPane.showMessageDialog(rootPane,"SALAH");
                 
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(formmhs.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        kembali.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
               new menu();
            }
        });
        
    }
}
