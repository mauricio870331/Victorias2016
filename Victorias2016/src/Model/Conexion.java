package Model;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {   

    public Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost/hotel", "root", "");
        } catch (ClassNotFoundException | SQLException | InstantiationException | IllegalAccessException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return con;
    }


}
