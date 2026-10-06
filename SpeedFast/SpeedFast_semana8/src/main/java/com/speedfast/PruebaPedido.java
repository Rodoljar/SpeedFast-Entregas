package com.speedfast;

import com.speedfast.dao.PedidoDAO;
import com.speedfast.modelo.*;

public class PruebaPedido {
    public static void main(String[] args) {
        PedidoDAO dao = new PedidoDAO();
        try {
            dao.create(new Pedido("Av. Providencia 123", TipoPedido.COMIDA, EstadoPedido.PENDIENTE));
            dao.create(new Pedido("Calle Larga 45", TipoPedido.EXPRESS, EstadoPedido.EN_REPARTO));
            System.out.println("Todos: " + dao.readAll());
            System.out.println("Solo EXPRESS: " + dao.readFiltrado(null, TipoPedido.EXPRESS));
            System.out.println("Solo PENDIENTE: " + dao.readFiltrado(EstadoPedido.PENDIENTE, null));

            Pedido primero = dao.readAll().get(0);
            primero.setEstado(EstadoPedido.ENTREGADO);
            dao.update(primero);
            System.out.println("Editado: " + dao.readFiltrado(EstadoPedido.ENTREGADO, null));

            for (Pedido p : dao.readAll()) dao.delete(p.getId());
            System.out.println("Después de eliminar: " + dao.readAll());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}