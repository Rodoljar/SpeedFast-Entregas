public class PedidoComida extends Pedido {
    private String tipoComida;

    public PedidoComida(String idPedido, String direccionDestino, double costoBase, String tipoComida) {
        super(idPedido, direccionDestino, costoBase);
        this.tipoComida = tipoComida;
    }

    @Override
    public int calcularTiempoEntrega() {

        return 25; // Minutos
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("Tipo de Comida: " + tipoComida);
        System.out.println("Tiempo Estimado: " + calcularTiempoEntrega() + " minutos.\n");
    }
}
