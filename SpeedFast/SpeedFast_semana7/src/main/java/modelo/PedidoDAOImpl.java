package modelo;

import main.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAOImpl implements PedidoDAO {

    @Override
    public boolean guardar(Pedido pedido) {
        String sql = "INSERT INTO pedido (id, direccion, tipo, estado, repartidor) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, pedido.getId());
            ps.setString(2, pedido.getDireccionEntrega());
            ps.setString(3, pedido.getTipo());
            ps.setString(4, pedido.getEstado().name());
            ps.setString(5, pedido.getRepartidorAsignado() != null ? pedido.getRepartidorAsignado() : "Sin asignar");

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al guardar pedido: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Pedido> obtenerTodos() {
        List<Pedido> lista = new ArrayList<>();
        String sql = "SELECT * FROM pedido";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String direccion = rs.getString("direccion");
                String tipo = rs.getString("tipo");
                String estadoStr = rs.getString("estado");
                String repartidor = rs.getString("repartidor");

                EstadoPedido estado;
                try {
                    estado = EstadoPedido.valueOf(estadoStr.toUpperCase());
                } catch (Exception e) {
                    estado = EstadoPedido.PENDIENTE;
                }

                Pedido p = new Pedido(id, direccion, tipo, estado);
                if (repartidor != null && !repartidor.trim().isEmpty()) {
                    p.setRepartidorAsignado(repartidor);
                }
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener pedidos: " + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM pedido WHERE id = ?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar pedido: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarEstadoYRepartidor(int idPedido, EstadoPedido estado, String repartidor) {
        String sql = "UPDATE pedido SET estado = ?, repartidor = ? WHERE id = ?";

        try (Connection con = ConexionDB.obtenerConexion();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, estado.name());
            ps.setString(2, repartidor);
            ps.setInt(3, idPedido);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar pedido: " + e.getMessage());
            return false;
        }
    }
}