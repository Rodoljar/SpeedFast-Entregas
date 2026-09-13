package com.speedfast.modelo;

public class PedidoEncomienda extends Pedido {
    private double pesoKg;

    public PedidoEncomienda(String idPedido, String direccionEntrega, double distanciaKm, double pesoKg) {
        super(idPedido, direccionEntrega, distanciaKm);
        this.pesoKg = pesoKg;
    }

    @Override
    public double calcularTiempoEntrega() {
        return (distanciaKm * 8) + (pesoKg * 1.5);
    }

    @Override
    public void despachar() {
        this.estado = "En tránsito (Encomienda)";
        System.out.println("Despachando encomienda de " + pesoKg + " kg, ID: " + idPedido);
    }
}
