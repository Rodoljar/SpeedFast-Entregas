package com.speedfast.modelo;

public class PedidoComida extends Pedido {
    private boolean esTermico;

    public PedidoComida(String idPedido, String direccionEntrega, double distanciaKm, boolean esTermico) {
        super(idPedido, direccionEntrega, distanciaKm);
        this.esTermico = esTermico;
    }

    @Override
    public double calcularTiempoEntrega() {
        // Estimación base: 5 min por km + factor térmico
        return (distanciaKm * 5) + (esTermico ? 2 : 5);
    }

    @Override
    public void despachar() {
        this.estado = "En camino (Comida)";
        System.out.println("Despachando pedido de comida caliente ID: " + idPedido);
    }
}