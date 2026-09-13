package main;

import modelo.EstadoPedido;
import modelo.Pedido;
import servicios.Repartidor;
import servicios.ZonaDeCarga;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE SIMULACIÓN CONCURRENTE SPEEDFAST (ZONA DE CARGA) ===");

        // 1. Instancia una ZonaDeCarga
        ZonaDeCarga zonaDeCarga = new ZonaDeCarga();
        System.out.println("[Zona de carga inicializada]");

        // 2. Agrega al menos 5 pedidos al sistema con destinos variados
        zonaDeCarga.agregarPedido(new Pedido(1, "Santiago Centro", EstadoPedido.PENDIENTE));
        zonaDeCarga.agregarPedido(new Pedido(2, "Providencia", EstadoPedido.PENDIENTE));
        zonaDeCarga.agregarPedido(new Pedido(3, "Ñuñoa", EstadoPedido.PENDIENTE));
        zonaDeCarga.agregarPedido(new Pedido(4, "Recoleta", EstadoPedido.PENDIENTE));
        zonaDeCarga.agregarPedido(new Pedido(5, "Las Condes", EstadoPedido.PENDIENTE));

        // 3. Crea 3 hilos de tipo Repartidor utilizando los nombres clásicos de SpeedFast
        Thread rep1 = new Thread(new Repartidor("Fredy Turbina", zonaDeCarga));
        Thread rep2 = new Thread(new Repartidor("Ana Gómez", zonaDeCarga));
        Thread rep3 = new Thread(new Repartidor("Manuel Morales", zonaDeCarga));

        // Inicia los hilos
        rep1.start();
        rep2.start();
        rep3.start();

        try {
            // Espera la finalización de la ejecución concurrente
            rep1.join();
            rep2.join();
            rep3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("El hilo principal fue interrumpido.");
        }

        // Mensajes de cierre requeridos por la pauta
        System.out.println("[Zona de carga vacía]");
        System.out.println("Todas los pedidos han sido entregados correctamente.");
        System.out.println("=== SIMULACIÓN FINALIZADA ===");
    }
}