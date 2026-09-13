public class PedidoExpress extends Pedido {
    private boolean esZonaPrioritaria;

    public PedidoExpress(String idPedido, String direccionDestino, double costoBase, boolean esZonaPrioritaria) {
        super(idPedido, direccionDestino, costoBase);
        this.esZonaPrioritaria = esZonaPrioritaria;
    }

    @Override
    public int calcularTiempoEntrega() {
        // Prioridad máxima
        return esZonaPrioritaria ? 15 : 20;
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("Zona Prioritaria: " + (esZonaPrioritaria ? "Sí" : "No"));
        System.out.println("Tiempo Estimado: " + calcularTiempoEntrega() + " minutos.\n");
    }
}