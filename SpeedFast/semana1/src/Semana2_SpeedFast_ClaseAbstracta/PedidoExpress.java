package Semana2_SpeedFast_ClaseAbstracta;

public class PedidoExpress extends Pedido {

    public PedidoExpress(String idPedido, String direccionEntrega, double distanciaKm) {
        super(idPedido, direccionEntrega, distanciaKm);
    }

    @Override
    public int calcularTiempoEntrega() {
        int tiempoBase = 10;
        if (distanciaKm > 5) {
            tiempoBase += 5; // se agregan 5 min extra si supera los 5 km
        }
        return tiempoBase;
    }
}
