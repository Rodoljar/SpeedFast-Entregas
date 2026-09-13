package Semana2_SpeedFast_ClaseAbstracta;

public abstract class Pedido {
    protected String idPedido;
    protected String direccionEntrega;
    protected double distanciaKm;

    // Constructor
    public Pedido(String idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
    }

    // Método implementado común para mostrar el resumen del pedido
    public void mostrarResumen() {
        System.out.println("----------------------------------------");
        System.out.println("ID Pedido: " + idPedido);
        System.out.println("Dirección de Entrega: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
    }

    // Método abstracto: obliga a las subclases a implementarlo con su propia lógica
    public abstract int calcularTiempoEntrega();

    // Getters y Setters por si se necesitan
    public String getIdPedido() { return idPedido; }
    public String getDireccionEntrega() { return direccionEntrega; }
    public double getDistanciaKm() { return distanciaKm; }
}

