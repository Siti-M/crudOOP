import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.*;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class LihatData extends JFrame{
    String[][] data = new String [300][3];
    String[] kolom = {"NIM","NAMA","ALAMAT"};
    JLabel ljudul;
    JTable tabel;
    JButton btnBack;
    JScrollPane scrollPane; 
    Statement statement;
    ResultSet resultSet;
    
    public void LihatData(){
        setTitle("Tampilkan Data Mahasiswa");
        
        ljudul = new JLabel("SELURUH DATA MAHASISWA");
        btnBack = new JButton("Kembali");
        tabel = new JTable(data, kolom);
        scrollPane = new JScrollPane(tabel);
        
        setLayout(null);
        add(ljudul);
        add(btnBack);
        add(scrollPane);    
        
        ljudul.setBounds(160, 10, 200, 20);   
        btnBack.setBounds(190, 50, 100, 20);  
        scrollPane.setBounds(50, 100, 390, 350);  
        
        
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
}