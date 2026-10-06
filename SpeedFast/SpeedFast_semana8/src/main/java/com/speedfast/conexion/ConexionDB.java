package com.speedfast.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {

    private static final String URL =
            "jdbc:mysql://localhost:3306/speedfast_db?serverTimezone=America/Santiago";
    private static final String USUARIO = "root";
    private static final String CLAVE = "Mutante999oracle";


    public static Connection getConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }
}

