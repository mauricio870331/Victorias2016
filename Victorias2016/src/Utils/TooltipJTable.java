package Utils;

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

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int colum) {
        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, colum);
        if (hasFocus && colum > 0) {
            System.out.println(cell.getText());
        }
        return cell;
    }
    
    
    
}
