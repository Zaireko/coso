/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uv.org.dswebpractica03.empleado;

/**
 *
 * @author Danielaa
 */
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import uv.org.dsweb.practica03.db.DBConnection;


@Named(value = "empleado")
@SessionScoped
public class EmpleadoBean implements Serializable {

    private final EmpleadoDAO empleadoDAO;
    private List<Empleado> empleados;
    private Empleado empleado;
    private DBConnection connectionManager = new DBConnection();
//        
    // Constructor y inicialización
    public EmpleadoBean() {
        empleadoDAO = new EmpleadoDAO(new DBConnection());
    }

    @PostConstruct
    public void init() {
        empleados = empleadoDAO.getAllEmpleados();
        empleado = new Empleado();
    }

    // Getters y Setters
    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    // Métodos CRUD
    public void addEmpleado() {
        empleadoDAO.addEmpleado(empleado);
        empleados = empleadoDAO.getAllEmpleados(); // Actualizar la lista
        empleado = new Empleado(); // Limpiar el formulario
    }

    public void updateEmpleado() {
        empleadoDAO.updateEmpleado(empleado);
        empleados = empleadoDAO.getAllEmpleados(); // Actualizar la lista
        empleado = new Empleado(); // Limpiar el formulario
    }

    public void deleteEmpleado(int id) {
        empleadoDAO.deleteEmpleado(id);
        empleados = empleadoDAO.getAllEmpleados(); // Actualizar la lista
    }
    public void prepareUpdate(Empleado empleado) {
    this.empleado = empleado;
}

}
