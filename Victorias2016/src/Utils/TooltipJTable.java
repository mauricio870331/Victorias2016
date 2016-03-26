package Utils;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Mauricio
 */
public class TooltipJTable extends DefaultTableCellRenderer {

//    Conexion conexion;
//    Connection cn;
//    PreparedStatement pstm;
//    String sql;
//    ResultSet rs;
//    UsuariosDAO userDao = new UsuariosDAO();
    public TooltipJTable() {
//        conexion = new Conexion();
//        cn = conexion.getConexion();
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int colum) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, colum);
//        int filas = table.getRowCount();
//        int columnas = table.getColumnCount();
//            table.getTableHeader().setBackground(Color.cyan);
        if (colum == 0) {
            cell.setForeground(Color.BLACK);
            cell.setBackground(null);
        } else if (value != null) {
            Color c = new Color(50, 205, 50);
            cell.setBackground(c);
            cell.setForeground(c);
        } else if (isSelected) {
            Color c = new Color(211,211,211);
            cell.setBackground(c);
        } else {
            cell.setBackground(null);
        }

//        if (colum > 0) {
//            if (value instanceof Integer) {
//                Color c = new Color(152, 251, 152);
//                cell.setBackground(c);
//                cell.setForeground(c);
//            }
//            if (value == null) {
//                cell.setBackground(null);
//                cell.setForeground(Color.BLACK);
//            }
//        }
        return cell;
    }

}
