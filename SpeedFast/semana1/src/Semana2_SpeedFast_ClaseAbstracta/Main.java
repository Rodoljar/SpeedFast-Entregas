package Semana2_SpeedFast_ClaseAbstracta;

public class Main {
    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("    SPEEDFAST - CÁLCULO DE TIEMPOS DE ENTREGA");
        System.out.println("=================================================\n");

        // Creación de objetos usando Polimorfismo (referencia de la clase abstracta Pedido)
        Pedido pedido1 = new PedidoComida("COM-101", "Av. Providencia 1234", 4.5);
        Pedido pedido2 = new PedidoEncomienda("ENC-202", "Calle Los Alerces 567", 8.0);
        Pedido pedido3 = new PedidoExpress("EXPR-303", "Moneda 990, Santiago", 6.2);

        // Procesamiento y pruebas comparativas
        Pedido[] listaPedidos = {pedido1, pedido2, pedido3};

        for (Pedido p : listaPedidos) {
            p.mostrarResumen();
            System.out.println("Tiempo Estimado de Entrega: " + p.calcularTiempoEntrega() + " minutos");
            System.out.println("----------------------------------------\n");
        }
    }
}