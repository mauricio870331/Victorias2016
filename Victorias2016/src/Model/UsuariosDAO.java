/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mauricio Herrera
 */
public class UsuariosDAO {

    Conexion conexion;
    Connection cn;
    PreparedStatement pstm;
    String sql;
    ResultSet rs;

    public UsuariosDAO() {
        conexion = new Conexion();
        cn = conexion.getConexion();
    }

    public ArrayList<Usuarios> getInfoUsuario(String id) {
        ArrayList listInfoUsuario = new ArrayList();
        Usuarios usuario;
        try {
            sql = "SELECT * FROM usuarios where id_usuario = ?";
            pstm = cn.prepareStatement(sql);
            pstm.setInt(1, Integer.parseInt(id));
            rs = pstm.executeQuery();
            while (rs.next()) {
                usuario = new Usuarios();
                usuario.setDocumento(rs.getString("documento"));
                usuario.setNombres(rs.getString("nombres"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                listInfoUsuario.add(usuario);
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("error" + e + " " + getClass());
        }
        return listInfoUsuario;

    }

}
