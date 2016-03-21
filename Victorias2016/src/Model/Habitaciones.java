
package Model;

/**
 *
 * @author Mauricio Herrera
 */
public class Habitaciones {
  private int idHabitacion;
  private String habitacion;
  private int idTipoHabitacion;
  private int disponibilidad;

    public Habitaciones() {
        this.idHabitacion = 0;
        this.habitacion = "";
        this.idTipoHabitacion = 0;
        this.disponibilidad = 0;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    public int getIdTipoHabitacion() {
        return idTipoHabitacion;
    }

    public void setIdTipoHabitacion(int idTipoHabitacion) {
        this.idTipoHabitacion = idTipoHabitacion;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    
  
  
}
