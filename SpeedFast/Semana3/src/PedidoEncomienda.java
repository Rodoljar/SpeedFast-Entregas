public class PedidoEncomienda extends Pedido {
    private double pesoKg;

    public PedidoEncomienda(String idPedido, String direccionDestino, double costoBase, double pesoKg) {
        super(idPedido, direccionDestino, costoBase);
        this.pesoKg = pesoKg;
    }

    @Override
    public int calcularTiempoEntrega() {
        // El tiempo aumenta según el peso
        return (int) (40 + (pesoKg * 5));
    }

    @Override
    public void mostrarResumen() {
        super.mostrarResumen();
        System.out.println("Peso Encomienda: " + pesoKg + " kg");
        System.out.println("Tiempo Estimado: " + calcularTiempoEntrega() + " minutos.\n");
    }
}