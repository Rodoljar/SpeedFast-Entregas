package com.speedfast.main;

import com.speedfast.concurrencia.Repartidor;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import com.speedfast.modelo.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE SIMULACIÓN CONCURRENTE SPEEDFAST ===");

        // Creamos pedidos
        Pedido p1 = new PedidoComida("C-001", "Av. Providencia 1234", 4.5, true);
        Pedido p2 = new PedidoComida("C-002", "Ñuñoa 567", 2.0, false);

        Pedido p3 = new PedidoEncomienda("E-101", "Santiago Centro 89", 6.0, 3.5);
        Pedido p4 = new PedidoEncomienda("E-102", "Las Condes 4321", 10.2, 7.0);

        Pedido p5 = new PedidoExpress("X-901", "Viollier99", 5.0, 1);
        Pedido p6 = new PedidoExpress("X-902", "zenteno 45", 3.1, 2);

        // Instanciamos  tres repartidores con 2 o mas pedidos asignados
        Repartidor rep1 = new Repartidor("Fredy Turbina", Arrays.asList(p1, p2));
        Repartidor rep2 = new Repartidor("Ana Gómez", Arrays.asList(p3, p4));
        Repartidor rep3 = new Repartidor("Manuel Morales", Arrays.asList(p5, p6));

        // Usamos ExecutorService para ejecutar los hilos en paralelo
        ExecutorService executor = Executors.newFixedThreadPool(3);

        executor.execute(rep1);
        executor.execute(rep2);
        executor.execute(rep3);

        // Apagamos el executor y esperamos a que todos los hilos terminen
        executor.shutdown();

        try {
            // Espera hasta que todas las tareas terminen (máximo 1 minuto )
            if (!executor.awaitTermination(1, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        System.out.println("\n=== SIMULACIÓN FINALIZADA: Todos los repartidores han completado sus entregas. ===");
    }
}
