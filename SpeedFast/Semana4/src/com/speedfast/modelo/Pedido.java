package com.speedfast.modelo;

import com.speedfast.interfaces.Cancelable;
import com.speedfast.interfaces.Despachable;
import com.speedfast.interfaces.Rastreable;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {
    protected String idPedido;
    protected String direccionEntrega;
    protected double distanciaKm;
    protected String estado;

    public Pedido(String idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.estado = "Pendiente";
    }

    public abstract double calcularTiempoEntrega();

    public void mostrarResumen() {
        System.out.println("Pedido ID: " + idPedido + " | Dirección: " + direccionEntrega + " | Distancia: " + distanciaKm + " km");
    }

    @Override
    public boolean cancelarPedido() {
        if ("Pendiente".equals(estado)) {
            this.estado = "Cancelado";
            return true;
        }
        return false;
    }

    @Override
    public String obtenerEstadoActual() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdPedido() {
        return idPedido;
    }
}
