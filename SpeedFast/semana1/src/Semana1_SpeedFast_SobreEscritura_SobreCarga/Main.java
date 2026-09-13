package Semana1_SpeedFast_SobreEscritura_SobreCarga;

public class Main {
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      SISTEMA DE ASIGNACIÓN DE REPARTOS SPEEDFAST ");
        System.out.println("==================================================\n");

        // Polimorfismo
        Pedido pedido1 = new PedidoComida("COM-001", "Av. Providencia 1234", true);
        Pedido pedido2 = new PedidoEncomienda("ENC-002", "Calle Los Alerces 567", 3.2, "Caja reforzada");
        Pedido pedido3 = new PedidoExpress("EXPR-003", "Moneda 990, Santiago", 1);

        System.out.println("--- 1. MÉTODOS SOBRESCRITOS ---");
        pedido1.asignarRepartidor();
        pedido2.asignarRepartidor();
        pedido3.asignarRepartidor();

        System.out.println("\n--- 2. MÉTODOS SOBRECARGADOS  ---");
        pedido1.asignarRepartidor("Eliseo Salazar");
        pedido2.asignarRepartidor("Ana Marquez");
        pedido3.asignarRepartidor("Matías Chaleco López");

        System.out.println("\n==================================================");
    }
}