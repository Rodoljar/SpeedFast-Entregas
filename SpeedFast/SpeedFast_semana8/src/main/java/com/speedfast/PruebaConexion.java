package com.speedfast;

import com.speedfast.conexion.ConexionDB;
import java.sql.Connection;

public class PruebaConexion {
    public static void main(String[] args) {
        try (Connection con = ConexionDB.getConexion()) {
            System.out.println("Conexión exitosa a " + con.getCatalog());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
