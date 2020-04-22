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

public class HapusData extends JFrame{
    
    String[][] data = new String [300][3];
    String[] kolom = {"NIM","NAMA","ALAMAT"};
    JLabel ljudul;
    JTable tabel;
    JButton del, btnBack;
    JScrollPane scrollPane; 
    Statement statement;
    ResultSet resultSet;
    
    public static String dataterpilih;
    
    public void HapusData(){
        setTitle("Hapus Data Mahasiswa");
        
        ljudul = new JLabel("SELURUH DATA MAHASISWA");
        btnBack = new JButton("Kembali");
        del = new JButton("Hapus");
        tabel = new JTable(data, kolom);
        scrollPane = new JScrollPane(tabel);
        
        setLayout(null);
        add(ljudul);
        add(del);
        add(btnBack);
        add(scrollPane);    
        
        ljudul.setBounds(160, 10, 200, 20);   
        btnBack.setBounds(120, 50, 100, 20);  
        del.setBounds(250, 50, 100, 20);  
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
        
        tabel.addMouseListener(new MouseAdapter(){
            @Override
           public void mouseClicked(MouseEvent e){ 
               int baris = tabel.getSelectedRow();
               int kolom = tabel.getSelectedColumn();
               dataterpilih = tabel.getValueAt(baris, 0).toString();
                
                
               System.out.println(dataterpilih);
                del.addActionListener((java.awt.event.ActionEvent f) -> {
                  btnBuatactionListener();
                }); 
           }

            private void btnBuatactionListener() {
               KoneksiDB koneksi = new KoneksiDB();
                 try{
            
                statement = koneksi.getKoneksi().createStatement();
                statement.executeUpdate("DELETE FROM `data_mhs` WHERE nim='" + dataterpilih + "'");
                JOptionPane.showMessageDialog(null, "Berhasil Dihapus");
                
            } catch (SQLException sqle){
            JOptionPane.showMessageDialog(rootPane, "data gagal dihapus" + sqle);
        } catch (ClassNotFoundException classe){
            JOptionPane.showMessageDialog(rootPane, "data tidak ditemukan" + classe);
        }
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
