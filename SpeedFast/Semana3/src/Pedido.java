import java.util.ArrayList;
import java.util.List;

public abstract class Pedido implements Despachable, Cancelable, Rastreable {
    protected String idPedido;
    protected String direccionDestino;
    protected double costoBase;
    protected String repartidorAsignado;
    protected boolean estadoDespachado;
    protected boolean estadoCancelado;
    protected static List<String> historialEntregas = new ArrayList<>();

    public Pedido(String idPedido, String direccionDestino, double costoBase) {
        this.idPedido = idPedido;
        this.direccionDestino = direccionDestino;
        this.costoBase = costoBase;
        this.repartidorAsignado = "Sin asignar";
        this.estadoDespachado = false;
        this.estadoCancelado = false;
    }

    // Método implementado común
    public void mostrarResumen() {
        System.out.println("--- Resumen del Pedido [" + idPedido + "] ---");
        System.out.println("Destino: " + direccionDestino);
        System.out.println("Costo Base: $" + costoBase);
        System.out.println("Repartidor: " + repartidorAsignado);
        System.out.println("Despachado: " + (estadoDespachado ? "Sí" : "No"));
        System.out.println("Cancelado: " + (estadoCancelado ? "Sí" : "No"));
    }

    // Método abstracto (Polimorfismo / Abstracción)
    public abstract int calcularTiempoEntrega();

    // Métodos para sobrecarga de asignación de repartidor
    public void asignarRepartidor() {
        this.repartidorAsignado = "Repartidor Automático (SpeedFast)";
        System.out.println("-> Repartidor asignado automáticamente para el pedido " + idPedido);
    }

    public void asignarRepartidor(String nombre) {
        this.repartidorAsignado = nombre;
        System.out.println("-> Repartidor " + nombre + " asignado manualmente al pedido " + idPedido);
    }

    // Implementación de interfaces
    @Override
    public void despachar() {
        if (!estadoCancelado) {
            this.estadoDespachado = true;
            historialEntregas.add("Pedido " + idPedido + " despachado con éxito.");
            System.out.println("-> El pedido " + idPedido + " ha sido despachado.");
        } else {
            System.out.println("-> No se puede despachar el pedido " + idPedido + " porque está cancelado.");
        }
    }

    @Override
    public void cancelar() {
        this.estadoCancelado = true;
        historialEntregas.add("Pedido " + idPedido + " fue cancelado.");
        System.out.println("-> El pedido " + idPedido + " ha sido cancelado.");
    }

    @Override
    public void verHistorial() {
        System.out.println("--- Historial Global de Entregas ---");
        for (String registro : historialEntregas) {
            System.out.println("- " + registro);
        }
    }
}