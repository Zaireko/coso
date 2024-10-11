package uv.org.dsweb.practica03.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/ejemplo";
    private static final String USER = "postgres";
    private static final String PASSWORD = "boli";
    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());

    public Connection getConnection() {
        Connection conn = null;
        try {
            // Cargar el driver de PostgreSQL
            Class.forName("org.postgresql.Driver");
            // Obtener la conexión
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            logger.log(Level.SEVERE, "Driver de PostgreSQL no encontrado", e);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error al obtener la conexión", e);
        }
        return conn;
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                    logger.log(Level.INFO, "Conexión cerrada exitosamente");
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error al cerrar la conexión", e);
            }
        }
    }
}
