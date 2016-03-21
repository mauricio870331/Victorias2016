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
public class ReservasDAO {

    Conexion conexion;
    Connection cn;
    PreparedStatement pstm;
    String sql;
    ResultSet rs;

    public ReservasDAO() {
        conexion = new Conexion();
        cn = conexion.getConexion();
    }

    public ArrayList<Reservas> getListReservas() {

        ArrayList listaReservas = new ArrayList();
        Reservas reservas;
        try {

            sql = "SELECT * FROM reservas_habitaciones";

            pstm = cn.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                reservas = new Reservas();
                reservas.setIdReserva(rs.getInt("id_reserva"));
                reservas.setIdHabitacion(rs.getInt("id_habitacion"));
                reservas.setFechaLLegada(rs.getString("fecha_llegada"));
                reservas.setFechaSalida(rs.getString("fecha_salida"));
                reservas.setIdUsuario(rs.getInt("id_usuario"));
                reservas.setEstadoReserva(rs.getString("estado_reserva"));
                reservas.setPersonas(rs.getInt("personas"));
                listaReservas.add(reservas);
            }
        } catch (Exception e) {
            System.out.println("error" + e +" "+getClass());
        }
        return listaReservas;

    }

}
