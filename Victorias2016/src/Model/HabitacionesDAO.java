/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Mauricio Herrera
 */
public class HabitacionesDAO {

    Conexion conexion;
    Connection cn;
    PreparedStatement pstm;
    String sql;
    ResultSet rs;

    public HabitacionesDAO() {
        conexion = new Conexion();
        cn = conexion.getConexion();
    }

    public ArrayList<Habitaciones> getListHabitaciones() {

        ArrayList listaHabitaciones = new ArrayList();
        Habitaciones Habitacion;
        try {

            sql = "SELECT * FROM habitaciones";

            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Habitacion = new Habitaciones();
                Habitacion.setIdHabitacion(rs.getInt("id_habitacion"));
                Habitacion.setHabitacion(rs.getString("habitacion"));
                Habitacion.setIdTipoHabitacion(rs.getInt("id_tipo_habitacion"));
                Habitacion.setDisponibilidad(rs.getInt("disponibilidad"));
                listaHabitaciones.add(Habitacion);
            }
        } catch (Exception e) {
            System.out.println("error" + e);
        }
        return listaHabitaciones;

    }

}
