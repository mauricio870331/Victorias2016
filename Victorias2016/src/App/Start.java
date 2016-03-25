/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package App;
//import Model.*;
//import Controllers.*;

import Controllers.ReservasController;
import com.jtattoo.plaf.acryl.AcrylLookAndFeel;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Mauricio
 */
public class Start {

    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            props.put("logoString", "M-Systems");
            AcrylLookAndFeel.setCurrentTheme(props);
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            Principal pr = new Principal();
            ReservasController rc = new ReservasController(pr);            
            rc.cargarReservas(pr.tbViewReservas);
            pr.lbltooltip.setVisible(false);           
            pr.setLocationRelativeTo(null);
            pr.setVisible(true);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("error " + e);
        }

    }
}
