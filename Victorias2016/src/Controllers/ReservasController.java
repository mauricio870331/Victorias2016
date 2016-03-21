/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import App.Principal;
import Model.HabitacionesDAO;

import Model.ReservasDAO;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mauricio Herrera
 */
public class ReservasController {

    HabitacionesDAO haDao = new HabitacionesDAO();
    ReservasDAO reseDAO = new ReservasDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    Principal pr = new Principal();

    public void cargarReservas(JTable tbReserevas) {
        Date now = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
        String year = new SimpleDateFormat("yyyy").format(now);
        String month = new SimpleDateFormat("MM").format(now);
        String day = new SimpleDateFormat("dd").format(now);
        Calendar cal = null;
        switch (month) {
            case "01":
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.JANUARY, 1);
                break;
            case "02":
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.FEBRUARY, 1);
                break;
            case "03":
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.MARCH, 1);
                break;
            case "04":
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.APRIL, 1);
                break;
            case "05":
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.MAY, 1);
                break;
            case "06":
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.JUNE, 1);
                break;
            case "07":
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.JULY, 1);
                break;
            case "08":
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.AUGUST, 1);
                break;
            case "09":
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.SEPTEMBER, 1);
                break;
            case "10":
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.OCTOBER, 1);
                break;
            case "11":
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.NOVEMBER, 1);
                break;
            case "12":
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.DECEMBER, 1);
                break;
        }
        // Get the number of days in that month 
        int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // int numero de dias        
        String titulos[] = new String[days + 1];//cabeceras de la tabla
        titulos[0] = "Habitaciones";
        for (int i = 1; i <= days; i++) {
            titulos[i] = String.valueOf(i);
        }
        modelo = new DefaultTableModel(null, titulos) {
            @Override
            public boolean isCellEditable(int row, int column) {//para evitar que las celdas sean editables
                return false;
            }
        };
        Object[] columna = new Object[days + 1];
        int numRows = haDao.getListHabitaciones().size();
        int numRows2 = reseDAO.getListReservas().size();
        for (int i = 0; i < numRows; i++) {
            String[] dfs = null;
            String[] dfll = null;
            columna[0] = haDao.getListHabitaciones().get(i).getHabitacion();

            for (int j = 0; j < numRows2; j++) {
                int idhabibitacionR = reseDAO.getListReservas().get(j).getIdHabitacion();
                int idHabitacionH = haDao.getListHabitaciones().get(i).getIdHabitacion();
                if (idhabibitacionR == idHabitacionH) {
                    String fechaSalida = reseDAO.getListReservas().get(j).getFechaSalida();
                    dfs = fechaSalida.split("-");
                    String fechaLlegada = reseDAO.getListReservas().get(j).getFechaLLegada();
                    dfll = fechaLlegada.split("-");
                    int fllegada = Integer.parseInt(dfll[2]);
                    int fsalida = Integer.parseInt(dfs[2]);

                    for (int k = 1; k <= days; k++) {
                        if (k >= fllegada && k <= fsalida) {
                            if (fllegada == k) {
                                columna[k] = reseDAO.getListReservas().get(j).getIdUsuario();
                            } else {
                                columna[k] = "X";
                            }
                        }
                    }
                }
            }

            modelo.addRow(columna);
        }
        tbReserevas.setModel(modelo);
        tbReserevas.getColumnModel().getColumn(0).setPreferredWidth(200);

    }

}
