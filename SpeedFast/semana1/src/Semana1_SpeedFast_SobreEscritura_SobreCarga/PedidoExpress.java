package Semana1_SpeedFast_SobreEscritura_SobreCarga;

public class PedidoExpress extends Pedido {
    private int distanciaKm;

    public PedidoExpress(String idPedido, String direccionEntrega, int distanciaKm) {
        super(idPedido, direccionEntrega, "Compra Express");
        this.distanciaKm = distanciaKm;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("[Compra Express] Buscando al repartidor más cercano con disponibilidad inmediata para el pedido " + idPedido);
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("\n[Semana1_SpeedFast_SobreCarga_SobreEscritura.Pedido Express]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Repartidor más cercano con disponibilidad inmediata encontrado.");
        System.out.println("→ Semana1_SpeedFast_SobreCarga_SobreEscritura.Pedido asignado a " + nombreRepartidor);
    }
}