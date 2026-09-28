package modelo;

import java.util.List;

public interface PedidoDAO {
    boolean guardar(Pedido pedido);
    List<Pedido> obtenerTodos();
    boolean eliminar(int id);
    boolean actualizarEstadoYRepartidor(int idPedido, EstadoPedido estado, String repartidor);
}