package Semana1_SpeedFast_SobreEscritura_SobreCarga;

public class PedidoComida extends Pedido {
    private boolean usaMochilaTermica;

    public PedidoComida(String idPedido, String direccionEntrega, boolean usaMochilaTermica) {
        super(idPedido, direccionEntrega, "Comida");
        this.usaMochilaTermica = usaMochilaTermica;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("[Comida] Buscando repartidor con mochila térmica obligatoria para el pedido " + idPedido + " hacia " + direccionEntrega);
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("\n[Semana1_SpeedFast_SobreCarga_SobreEscritura.Pedido Comida]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Verificando mochila térmica... " + (usaMochilaTermica ? "OK" : "Revisar"));
        System.out.println("→ Semana1_SpeedFast_SobreCarga_SobreEscritura.Pedido asignado a " + nombreRepartidor);
    }
}