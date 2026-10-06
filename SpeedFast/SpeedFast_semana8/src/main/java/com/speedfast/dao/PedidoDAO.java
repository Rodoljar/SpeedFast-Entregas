package com.speedfast.dao;

import com.speedfast.conexion.ConexionDB;
import com.speedfast.modelo.EstadoPedido;
import com.speedfast.modelo.Pedido;
import com.speedfast.modelo.TipoPedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PedidoDAO {


    public boolean create(Pedido p) throws SQLException {
        String sql = "INSERT INTO pedidos (direccion, tipo, estado) VALUES (?, ?, ?)";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getDireccion());
            ps.setString(2, p.getTipo().name());
            ps.setString(3, p.getEstado().name());
            return ps.executeUpdate() > 0;
        }
    }


    public List<Pedido> readAll() throws SQLException {
        return readFiltrado(null, null);
    }


    public List<Pedido> readFiltrado(EstadoPedido estado, TipoPedido tipo) throws SQLException {
        List<Pedido> lista = new ArrayList<>();
        StringBuilder sql = new StringBuilder(
                "SELECT id, direccion, tipo, estado FROM pedidos WHERE 1=1");
        if (estado != null) sql.append(" AND estado = ?");
        if (tipo != null) sql.append(" AND tipo = ?");
        sql.append(" ORDER BY id");

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql.toString())) {
            int i = 1;
            if (estado != null) ps.setString(i++, estado.name());
            if (tipo != null) ps.setString(i++, tipo.name());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String t = rs.getString("tipo");
                    String e = rs.getString("estado");
                    lista.add(new Pedido(
                            rs.getInt("id"),
                            rs.getString("direccion"),
                            t == null ? null : TipoPedido.valueOf(t),
                            e == null ? null : EstadoPedido.valueOf(e)));
                }
            }
        }
        return lista;
    }


    public boolean update(Pedido p) throws SQLException {
        String sql = "UPDATE pedidos SET direccion = ?, tipo = ?, estado = ? WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, p.getDireccion());
            ps.setString(2, p.getTipo().name());
            ps.setString(3, p.getEstado().name());
            ps.setInt(4, p.getId());
            return ps.executeUpdate() > 0;
        }
    }


    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
