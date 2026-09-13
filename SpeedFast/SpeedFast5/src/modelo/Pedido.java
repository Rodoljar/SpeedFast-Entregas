package modelo;

public class Pedido {
    private int id;
    private String direccionEntrega;
    private EstadoPedido estado; // Usando el enum del paso 2

    // Constructor
    public Pedido(int id, String direccionEntrega, EstadoPedido estado) {
        this.id = id;
        this.direccionEntrega = direccionEntrega;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    // Método solicitado para aceptar String o actualizar
    public void setEstado(String nuevoEstado) {
        this.estado = EstadoPedido.valueOf(nuevoEstado.toUpperCase());
    }

    // toString()
    @Override
    public String toString() {
        return "Pedido #" + id + " [Destino: " + direccionEntrega + ", Estado: " + estado + "]";
    }
}
