package com.speedfast.dao;

import com.speedfast.conexion.ConexionDB;
import com.speedfast.modelo.*;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;



public class EntregaDAO {


    public boolean create(Entrega e) throws SQLException {
        String sql = "INSERT INTO entregas (id_pedido, id_repartidor, fecha, hora) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, e.getPedido().getId());
            ps.setInt(2, e.getRepartidor().getId());
            ps.setDate(3, Date.valueOf(e.getFecha()));
            ps.setTime(4, Time.valueOf(e.getHora()));
            return ps.executeUpdate() > 0;
        }
    }


    public List<Entrega> readAll() throws SQLException {
        List<Entrega> lista = new ArrayList<>();
        String sql = "SELECT e.id AS entrega_id, e.fecha, e.hora, " +
                "p.id AS pedido_id, p.direccion, p.tipo, p.estado, " +
                "r.id AS repartidor_id, r.nombre AS repartidor_nombre " +
                "FROM entregas e " +
                "INNER JOIN pedidos p ON e.id_pedido = p.id " +
                "INNER JOIN repartidores r ON e.id_repartidor = r.id " +
                "ORDER BY e.id";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pedido p = new Pedido(
                        rs.getInt("pedido_id"),
                        rs.getString("direccion"),
                        rs.getString("tipo") != null ? TipoPedido.valueOf(rs.getString("tipo")) : null,
                        rs.getString("estado") != null ? EstadoPedido.valueOf(rs.getString("estado")) : null
                );

                Repartidor r = new Repartidor(
                        rs.getInt("repartidor_id"),
                        rs.getString("repartidor_nombre")
                );

                LocalDate fecha = rs.getDate("fecha") != null ? rs.getDate("fecha").toLocalDate() : null;
                LocalTime hora = rs.getTime("hora") != null ? rs.getTime("hora").toLocalTime() : null;

                lista.add(new Entrega(rs.getInt("entrega_id"), p, r, fecha, hora));
            }
        }
        return lista;
    }


    public boolean update(Entrega e) throws SQLException {
        String sql = "UPDATE entregas SET id_pedido = ?, id_repartidor = ?, fecha = ?, hora = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, e.getPedido().getId());
            ps.setInt(2, e.getRepartidor().getId());
            ps.setDate(3, Date.valueOf(e.getFecha()));
            ps.setTime(4, Time.valueOf(e.getHora()));
            ps.setInt(5, e.getId());
            return ps.executeUpdate() > 0;
        }
    }


    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM entregas WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}