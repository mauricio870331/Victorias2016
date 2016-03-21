/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.HabitacionesDAO;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mauricio Herrera
 */
public class HabitacionesController {
    HabitacionesDAO haDao = new HabitacionesDAO();   
    DefaultTableModel modelo = new DefaultTableModel();
}
