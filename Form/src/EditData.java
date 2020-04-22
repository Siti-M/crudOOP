import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class EditData extends JFrame{
    
    String[][] data = new String [300][3];
    String[] kolom = {"NIM","NAMA","ALAMAT"};
    JLabel ljudul,lnim,lnama,lalamat;
    JTextField txnim,txnama,txalamat;
    JTable tabel;
    JButton edit, btnBack;
    JScrollPane scrollPane; 
    Statement statement;
    ResultSet resultSet;

    public static String pilih;
    public void EditData(){
        setTitle("Hapus Data Mahasiswa");
        
        ljudul = new JLabel("SELURUH DATA MAHASISWA");
        btnBack = new JButton("Kembali");
        edit = new JButton("Edit");
        tabel = new JTable(data, kolom);
        scrollPane = new JScrollPane(tabel);
        lnim = new JLabel("NIM");
        lnama = new JLabel("Nama");  
        lalamat = new JLabel("Alamat");
        
        txnim = new JTextField("");
        txnama = new JTextField("");
        txalamat = new JTextField("");
        
        setLayout(null);
        add(ljudul);
        add(lnim);
        add(lnama);
        add(lalamat);
        add(txnim);
        add(txnama);
        add(txalamat);
        add(edit);
        add(btnBack);
        add(scrollPane);    
        
        ljudul.setBounds(160, 10, 200, 20);   
        btnBack.setBounds(120, 50, 100, 20);  
        lnim.setBounds(150, 100, 100, 20);  
        txnim.setBounds(200, 100, 100, 20);  
        lnama.setBounds(150, 130, 100, 20);  
        txnama.setBounds(200, 130, 100, 20);  
        lalamat.setBounds(150, 160, 100, 20);  
        txalamat.setBounds(200, 160, 100, 20);  
        edit.setBounds(250, 50, 100, 20);  
        scrollPane.setBounds(50, 200, 390, 250);  
        
        
        setSize(500,500); //untuk luas jendela
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
//        pack();
        setLocationRelativeTo(null);
        
        btnBack.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
               new formmhs();
            }
        });
        
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                UpdateactionListener();
            }
        });
         try {
            KoneksiDB koneksi = new KoneksiDB();
            statement = koneksi.getKoneksi().createStatement();
            
            String sql = "SELECT * FROM data_mhs";
            resultSet = statement.executeQuery(sql);
            
            int p = 0;
            while (resultSet.next()){
                data[p][0]=resultSet.getString("nim");
                data[p][1]=resultSet.getString("nama");
                data[p][2]=resultSet.getString("alamat");
                p++;
            }
            statement.close();
            koneksi.getKoneksi().close();
            
        } catch (SQLException sqle){
            JOptionPane.showMessageDialog(rootPane, "Data Gagal Ditampilkan" + sqle);
        } catch (ClassNotFoundException classe){
            JOptionPane.showMessageDialog(rootPane, "Data Tidak Ditemukan" + classe);
        } 
    }
    
    
    private void UpdateactionListener() {
        KoneksiDB koneksi = new KoneksiDB();
        try {
            statement = koneksi.getKoneksi().createStatement();
            statement.executeUpdate("UPDATE data_mhs SET nama='" + txnama.getText() + "'," + "alamat='" +
            txalamat.getText() + "' WHERE nim='" + txnim.getText() + "'" );
            JOptionPane.showMessageDialog(null, "Data Berhasil di Update!", "Hasil", JOptionPane.INFORMATION_MESSAGE);
            statement.close();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Data Gagal di Update!", "Hasil", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Driver tidak ditemukan!", "Hasil", JOptionPane.ERROR_MESSAGE);
        }
    }
}
