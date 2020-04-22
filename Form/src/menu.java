import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

/**
 *
 * @author Lab Informatika
 */
public class menu extends JFrame{
    JLabel ljudul;
    JButton input, update, del, lihat, tutup;
    Statement statement;
    
    public void menu(){
        
        setTitle("MENU");
        
        ljudul = new JLabel("MENU ");
        input = new JButton("1. Input Data Mahasiswa ");
        lihat = new JButton("2. Tampilkan Seluruh Data");
        del = new JButton("3. Hapus Data Mahasiswa");
        update = new JButton("4. Edit Data Mahasiswa");
        tutup = new JButton("0. Exit");
        
        input.setHorizontalAlignment(SwingConstants.LEFT);
        update.setHorizontalAlignment(SwingConstants.LEFT);
        del.setHorizontalAlignment(SwingConstants.LEFT);
        lihat.setHorizontalAlignment(SwingConstants.LEFT);
        tutup.setHorizontalAlignment(SwingConstants.LEFT);
        
        setLayout(null);
        add(ljudul);
        add(input);
        add(update);
        add(del);
        add(lihat);
        add(tutup);
        
       
        
        ljudul.setBounds(120, 30, 50, 20);
        input.setBounds(40, 70, 190, 20);
        lihat.setBounds(40, 92, 190, 20);
        del.setBounds(40, 114, 190, 20);
        update.setBounds(40, 136, 190, 20);
        tutup.setBounds(40, 158, 190, 20);
        
        setSize(300,250); //untuk luas jendela
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                formmhs lihat = new formmhs();
                lihat.formmhs();
            }
        });
        
        lihat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                LihatData cek = new LihatData();
                cek.LihatData();
            }
        });
        
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                EditData edit = new EditData();
                edit.EditData();
            }
        });
        
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                HapusData hapus = new HapusData();
                hapus.HapusData();
            }
        });
        
        tutup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });
    }
}
