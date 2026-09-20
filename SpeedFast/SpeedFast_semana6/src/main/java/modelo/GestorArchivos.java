package modelo;

import servicios.ZonaDeCarga;
import java.io.*;

public class GestorArchivos {

    private static final String RUTA_ARCHIVO = "pedidos.txt";

    // 1. Método para guardar un pedido individual
    public static void guardarPedido(Pedido pedido) {
        try (PrintWriter escritor = new PrintWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            // Usamos los métodos reales de Pedido: getId(), getDireccionEntrega(), getTipo(), getEstado()
            String linea = pedido.getId() + ";" + pedido.getDireccionEntrega() + ";" + pedido.getTipo() + ";" + pedido.getEstado();
            escritor.println(linea);
        } catch (IOException e) {
            System.err.println("Error al guardar el pedido en el archivo: " + e.getMessage());
        }
    }

    // 2. Método para leer y cargar los pedidos al iniciar la aplicación
    public static void cargarPedidos(ZonaDeCarga zonaDeCarga) {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length >= 4) {
                    int id = Integer.parseInt(partes[0]);
                    String direccionEntrega = partes[1];
                    String tipo = partes[2];
                    EstadoPedido estado = EstadoPedido.valueOf(partes[3]);

                    // Usamos el constructor real de Pedido(int id, String direccion, String tipo, EstadoPedido estado)
                    Pedido p = new Pedido(id, direccionEntrega, tipo, estado);
                    zonaDeCarga.agregarPedido(p);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error al leer el archivo de pedidos: " + e.getMessage());
        }
    }
}