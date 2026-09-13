package Semana1_SpeedFast_SobreEscritura_SobreCarga;

public class Pedido {
    protected String idPedido;
    protected String direccionEntrega;
    protected String tipoPedido;

    public Pedido(String idPedido, String direccionEntrega, String tipoPedido) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.tipoPedido = tipoPedido;
    }

    public void asignarRepartidor() {
        System.out.println("Asignando repartidor genérico para el pedido ID: " + idPedido);
    }

    public void asignarRepartidor(String nombreRepartidor) {
        System.out.println("Asignando al repartidor " + nombreRepartidor + " para el pedido ID: " + idPedido);
    }

    public String getIdPedido() { return idPedido; }
    public String getDireccionEntrega() { return direccionEntrega; }
    public String getTipoPedido() { return tipoPedido; }
}
