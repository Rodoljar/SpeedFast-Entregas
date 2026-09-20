package modelo;

import servicios.ZonaDeCarga;

public class Repartidor implements Runnable {
    private String nombre;
    private ZonaDeCarga zonaDeCarga;
    private javax.swing.JTextArea areaLog; // Opcional para mostrar en la interfaz

    public Repartidor(String nombre, ZonaDeCarga zonaDeCarga) {
        this.nombre = nombre;
        this.zonaDeCarga = zonaDeCarga;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        // Lógica de simulación de entrega
        System.out.println("[Repartidor - " + nombre + "] Iniciando turno...");
    }
}