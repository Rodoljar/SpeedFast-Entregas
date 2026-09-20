package servicios;

import modelo.Pedido;
import java.util.LinkedList;
import java.util.Queue;

public class ZonaDeCarga {
    private Queue<Pedido> pedidosPendientes = new LinkedList<>();

    // Método para agregar pedidos a la zona de carga
    public synchronized void agregarPedido(Pedido p) {
        pedidosPendientes.add(p);
        System.out.println("Pedido #" + p.getId() + " agregado. Destino: " + p.getDireccionEntrega());
    }

    // Método para que un repartidor retire un pedido
    public synchronized Pedido retirarPedido() {
        if (pedidosPendientes.isEmpty()) {
            return null;
        }
        return pedidosPendientes.poll();
    }

    // --- NUEVO MÉTODO PARA CANCELAR Y ELIMINAR UN PEDIDO ---
    public synchronized void removerPedido(Pedido p) {
        pedidosPendientes.remove(p);
        System.out.println("Pedido #" + p.getId() + " ha sido cancelado y eliminado de la zona de carga.");
    }

    public synchronized Queue<Pedido> getPedidos() {
        return pedidosPendientes;
    }

    public synchronized boolean estaVacia() {
        return pedidosPendientes.isEmpty();
    }
}