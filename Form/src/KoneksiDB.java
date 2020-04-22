
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lab Informatika
 */
public class KoneksiDB {
    public Connection getKoneksi() throws ClassNotFoundException, SQLException{
       Class.forName("com.mysql.jdbc.Driver");
       String url="jdbc:mysql://localhost/mahasiswa";
       Connection con = DriverManager.getConnection(url,"root","");
        return con;
    }
}
