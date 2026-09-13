package com.speedfast.concurrencia;

import com.speedfast.modelo.Pedido;
import java.util.List;
import java.util.Random;

public class Repartidor implements Runnable {
    private String nombre;
    private List<Pedido> pedidosAsignados;

    public Repartidor(String nombre, List<Pedido> pedidosAsignados) {
        this.nombre = nombre;
        this.pedidosAsignados = pedidosAsignados;
    }

    @Override
    public void run() {
        System.out.println("--> [INICIO] Repartidor " + nombre + " comienza su turno con " + pedidosAsignados.size() + " pedidos.");
        Random random = new Random();

        for (Pedido pedido : pedidosAsignados) {
            pedido.despachar();
            System.out.println("   [PROGRESO] " + nombre + " entregando pedido " + pedido.getIdPedido() + " hacia " + pedido.obtenerEstadoActual());

            try {
                // Simula el tiempo de entrega de forma aleatoria (entre 1000ms y 3000ms)
                int tiempoSimulacion = 1000 + random.nextInt(2000);
                Thread.sleep(tiempoSimulacion);

                pedido.setEstado("Entregado");
                System.out.println("   [ÉXITO] " + nombre + " ha ENTREGADO el pedido " + pedido.getIdPedido());

            } catch (InterruptedException e) {
                System.out.println("   [ERROR] El repartidor " + nombre + " fue interrumpido.");
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("<-- [FIN] Repartidor " + nombre + " ha completado todos sus pedidos.");
    }
}