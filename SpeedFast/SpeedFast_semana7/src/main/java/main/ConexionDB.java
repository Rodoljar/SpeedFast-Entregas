package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // Cambiamos el nombre del esquema a speedfast_db
    private static final String URL = "jdbc:mysql://localhost:3306/speedfast_s6";
    private static final String USUARIO = "root";
    private static final String PASSWORD = System.getenv("DB_PASSWORD");

    /**
     * Método centralizado para obtener una conexión a MySQL.
     */
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conexion = obtenerConexion()) {
            if (conexion != null && !conexion.isClosed()) {
                System.out.println("¡Conexión centralizada exitosa a la base de datos speedfast_db!");
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }
}