package Semana1_SpeedFast_SobreEscritura_SobreCarga;

public class PedidoEncomienda extends Pedido {
    private double pesoKg;
    private String tipoEmbalaje;

    public PedidoEncomienda(String idPedido, String direccionEntrega, double pesoKg, String tipoEmbalaje) {
        super(idPedido, direccionEntrega, "Encomienda");
        this.pesoKg = pesoKg;
        this.tipoEmbalaje = tipoEmbalaje;
    }

    @Override
    public void asignarRepartidor() {
        System.out.println("[Encomienda] Verificando peso (" + pesoKg + " kg) y tipo de embalaje (" + tipoEmbalaje + ") para el pedido " + idPedido);
    }

    @Override
    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("\n[Semana1_SpeedFast_SobreCarga_SobreEscritura.Pedido Encomienda]");
        System.out.println("Asignando repartidor...");
        System.out.println("→ Validando peso y embalaje... OK");
        System.out.println("→ Semana1_SpeedFast_SobreCarga_SobreEscritura.Pedido asignado a " + nombreRepartidor);
    }
}