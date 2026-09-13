package Semana2_SpeedFast_ClaseAbstracta;

public class PedidoComida extends Pedido {

    public PedidoComida(String idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        // 15 min base + 2 min por cada kilómetro
        return 15 + (int) (distanciaKm * 2);
    }
}

