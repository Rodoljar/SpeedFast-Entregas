package com.speedfast.modelo;

public class PedidoExpress extends Pedido {
    private int prioridad; // 1 (Alta) a 3 (Baja)

    public PedidoExpress(String idPedido, String direccionEntrega, double distanciaKm, int prioridad) {
        super(idPedido, direccionEntrega, distanciaKm);
        this.prioridad = prioridad;
    }

    @Override
    public double calcularTiempoEntrega() {
        return (distanciaKm * 3) / prioridad;
    }

    @Override
    public void despachar() {
        this.estado = "Prioritario en ruta";
        System.out.println("Despachando pedido EXPRESS ID: " + idPedido);
    }
}