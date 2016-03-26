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
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mauricio Herrera
 */
public class ReservasController implements ActionListener, MouseListener {

    int m = 0;
    HabitacionesDAO haDao = new HabitacionesDAO();
    ReservasDAO reseDAO = new ReservasDAO();
    DefaultTableModel modelo = new DefaultTableModel();
    UsuariosDAO userDao = new UsuariosDAO();
    Principal pr = new Principal();

    public ReservasController(Principal pr) {
        this.pr = pr;
        this.pr.tbViewReservas.addMouseListener(this);
        this.pr.btnNextMonth.addActionListener(this);
    }

    public void cargarReservas(JTable tbReserevas) {
        Date now = new Date(); // java.util.Date, NOT java.sql.Date or java.sql.Timestamp!
        String year = new SimpleDateFormat("yyyy").format(now);
        String month = new SimpleDateFormat("MM").format(now);
        int mn = Integer.parseInt(month);
        int mes = mn + m;
        String day = new SimpleDateFormat("dd").format(now);
        String mesActual = "";
        Calendar cal = null;
        switch (mes) {
            case 1:
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.JANUARY, 1);
                mesActual = "Enero";
                break;
            case 2:
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.FEBRUARY, 1);
                mesActual = "Febrero";
                break;
            case 3:
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.MARCH, 1);
                mesActual = "Marzo";
                break;
            case 4:
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.APRIL, 1);
                mesActual = "Abril";
                break;
            case 5:
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.MAY, 1);
                mesActual = "Mayo";
                break;
            case 6:
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.JUNE, 1);
                mesActual = "Junio";
                break;
            case 7:
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.JULY, 1);
                mesActual = "Julio";
                break;
            case 8:
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.AUGUST, 1);
                mesActual = "Agosto";
                break;
            case 9:
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.SEPTEMBER, 1);
                mesActual = "Septiembre";
                break;
            case 10:
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.OCTOBER, 1);
                mesActual = "Octubre";
                break;
            case 11:
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.NOVEMBER, 1);
                mesActual = "Noviembre";
                break;
            case 12:
                cal = new GregorianCalendar(Integer.parseInt(year), Calendar.DECEMBER, 1);
                mesActual = "Diciembre";
                break;
        }
        pr.lblMes.setText(mesActual);
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
                    int fllegadaDia = Integer.parseInt(dfll[2]);
                    int fllegadaMes = Integer.parseInt(dfll[1]);
                    int fsalidaDIa = Integer.parseInt(dfs[2]);
                    int fsalidaMes = Integer.parseInt(dfs[1]);
                    for (int k = 1; k <= days; k++) {
                        if (fllegadaMes == mes) {    //validacion para saber cuantos dias y mes que esta reservada una habitacion                      
                            if (k >= fllegadaDia && k <= fsalidaDIa) {
                                columna[k] = reseDAO.getListReservas().get(j).getIdUsuario();
                            } else {
                                columna[k] = null;
                            }
                        }
                        if (fllegadaMes < fsalidaMes) {                            
                            if (k >= fllegadaDia && k <= days) {
                                columna[k] = reseDAO.getListReservas().get(j).getIdUsuario();
                            } else {
                                columna[k] = null;
                            }
                        }
                        if (fsalidaMes == mes) {                            
                            if (k <= fsalidaDIa && k <= days) {
                                columna[k] = reseDAO.getListReservas().get(j).getIdUsuario();
                            } else {
                                columna[k] = null;
                            }
                        }
                    }
                }
            }

            modelo.addRow(columna);
        }
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setBackground(new Color(102, 205, 170));
        tbReserevas.setModel(modelo);
        tbReserevas.getColumnModel().getColumn(0).setPreferredWidth(200);
        tbReserevas.setDefaultRenderer(Object.class, new TooltipJTable());
        if (mes == mn) {
            tbReserevas.getColumnModel().getColumn(Integer.parseInt(day)).setHeaderRenderer(headerRenderer);
        }

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == pr.btnNextMonth) {
            m = m + 1;
            cargarReservas(pr.tbViewReservas);
        }
    }
}
