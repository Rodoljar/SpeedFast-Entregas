package Semana2_SpeedFast_ClaseAbstracta;

public class PedidoEncomienda extends Pedido {

    public PedidoEncomienda(String idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        // 20 min base + 1.5 min por kilómetro, ajustado a entero
        double tiempoCalculado = 20 + (distanciaKm * 1.5);
        return (int) tiempoCalculado;
    }
}