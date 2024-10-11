package uv.org.dswebpractica03.empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import uv.org.dsweb.practica03.db.DBConnection;

public class EmpleadoDAO implements EmpleadoRepository {

    private final DBConnection connectionManager;

    public EmpleadoDAO(DBConnection connectionManager) {
        this.connectionManager = connectionManager;
    }

    @Override
    public List<Empleado> getAllEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        System.out.println("get all");
        String sql = "SELECT * FROM empleadosuv";
        try (Connection conn = connectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getString("nombre"));
                Empleado empleado = new Empleado();
                empleado.setId(rs.getInt("id"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setDireccion(rs.getString("direccion"));
                empleado.setTelefono(rs.getString("telefono"));
                empleados.add(empleado);
                System.out.println("Empleado");
                System.out.println(empleado.getNombre());
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return empleados;
    }

    @Override
    public void addEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleadosuv (nombre, direccion, telefono) VALUES (?, ?, ?)";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getDireccion());
            ps.setString(3, empleado.getTelefono());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateEmpleado(Empleado empleado) {
        String sql = "UPDATE empleadosuv SET nombre=?, direccion=?, telefono=? WHERE id=?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getDireccion());
            ps.setString(3, empleado.getTelefono());
            ps.setInt(4, empleado.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteEmpleado(int id) {
        String sql = "DELETE FROM empleadosuv WHERE id=?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Empleado getEmpleadoById(int id) {
        String sql = "SELECT * FROM empleadosuv WHERE id=?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Empleado empleado = new Empleado();
                    empleado.setId(rs.getInt("id"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setDireccion(rs.getString("direccion"));
                    empleado.setTelefono(rs.getString("telefono"));
                    return empleado;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EmpleadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
