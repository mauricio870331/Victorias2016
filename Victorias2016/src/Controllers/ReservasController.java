/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import App.NewJDialog;
import App.Principal;
import Model.HabitacionesDAO;

import Model.ReservasDAO;
import Model.UsuariosDAO;
import Utils.TooltipJTable;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class ReservasController implements MouseListener {

    HabitacionesDAO haDao = new HabitacionesDAO();
    ReservasDAO reseDAO = new ReservasDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    UsuariosDAO userDao = new UsuariosDAO();
    Principal pr = new Principal();

    public ReservasController(Principal pr) {
        this.pr = pr;
        this.pr.tbViewReservas.addMouseListener(this);
    }

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
                            columna[k] = reseDAO.getListReservas().get(j).getIdUsuario();
                        }
                    }
                }
            }

            modelo.addRow(columna);
        }
        tbReserevas.setModel(modelo);
        tbReserevas.getColumnModel().getColumn(0).setPreferredWidth(200);
        tbReserevas.setDefaultRenderer(Object.class, new TooltipJTable());

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int fila = pr.tbViewReservas.rowAtPoint(e.getPoint());
        int columna = pr.tbViewReservas.columnAtPoint(e.getPoint());
        if ((fila > -1) && (columna > 0)) {
            String id = "";
            Point punto = MouseInfo.getPointerInfo().getLocation();
            int x = punto.x;
            int y = punto.y;
            if (x > 1190) {
                x = 1180;
            }
            if (pr.tbViewReservas.getValueAt(fila, columna) != null) {
                id = String.valueOf(pr.tbViewReservas.getValueAt(fila, columna));
            }           
            if (!id.equals("")) {
                String documento = userDao.getInfoUsuario(id).get(0).getDocumento();
                String Nombres = userDao.getInfoUsuario(id).get(0).getNombres();
                NewJDialog tt = new NewJDialog(null, true);
                System.out.println("x =" + x);
                tt.jLabel1.setText(documento);
                tt.jLabel2.setText(Nombres);
                tt.setLocation(x, y);
                tt.setVisible(true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
