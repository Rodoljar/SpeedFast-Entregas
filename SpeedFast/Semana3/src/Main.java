public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE ENTREGAS SPEEDFAST ===\n");

        // 1. Crear instancias de los pedidos
        Pedido pedido1 = new PedidoComida("COM-001", "Av. Providencia 1234", 8990, "Sushi");
        Pedido pedido2 = new PedidoEncomienda("ENC-002", "Calle Los Aromos 45", 15000, 3.5);
        Pedido pedido3 = new PedidoExpress("EXP-003", "Las Condes 890", 12000, true);

        // 2. Demostración de Polimorfismo en Asignación (Sobrecarga)
        System.out.println("--- ASIGNACIÓN DE REPARTIDORES ---");
        pedido1.asignarRepartidor();             // Automática
        pedido2.asignarRepartidor("Freddy Turbina"); // Manual con parámetros
        pedido3.asignarRepartidor("Belen Gómez");    // Manual con parámetros
        System.out.println();

        // 3. Mostrar resúmenes y tiempos estimados
        System.out.println("--- RESUMEN Y TIEMPOS ---");
        pedido1.mostrarResumen();
        pedido2.mostrarResumen();
        pedido3.mostrarResumen();

        // 4. Operaciones de Despacho y Cancelación (Interfaces)
        System.out.println("--- OPERACIONES DE ENVÍO ---");
        pedido1.despachar();
        pedido3.despachar();

        // Cancelamos un pedido para probar la interfaz Cancelable
        pedido2.cancelar();
        pedido2.despachar(); // Esto mostrará la validación de que no se puede despachar
        System.out.println();

        // 5. Visualizar Historial de Entregas (Rastreable)
        System.out.println("--- HISTORIAL DE MOVIMIENTOS ---");
        pedido1.verHistorial();
    }
}