package vista;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import servicios.ZonaDeCarga;
import modelo.Pedido;
import modelo.EstadoPedido;
import modelo.PedidoDAO;
import modelo.PedidoDAOImpl;

public class VentanaAsignarRepartidor extends JFrame {

    private JComboBox<String> cmbRepartidores;
    private JComboBox<Pedido> cmbPedidos;
    private ZonaDeCarga zonaDeCarga;

    public VentanaAsignarRepartidor(JFrame parent, ZonaDeCarga zonaDeCarga) {
        this.zonaDeCarga = zonaDeCarga;

        setTitle("Asignar Repartidor a Pedido");
        setSize(450, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel(" Seleccionar Repartidor:"));
        String[] repartidores = {"Fredy Turbina", "Ana Gómez", "Manuel Morales"};
        cmbRepartidores = new JComboBox<>(repartidores);
        add(cmbRepartidores);

        add(new JLabel(" Seleccionar Pedido:"));
        cmbPedidos = new JComboBox<>();
        cargarPedidosPendientes();
        add(cmbPedidos);

        JButton btnAsignar = new JButton("Asignar e Iniciar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnAsignar);
        add(btnCancelar);

        btnAsignar.addActionListener(e -> {
            Pedido pedidoSeleccionado = (Pedido) cmbPedidos.getSelectedItem();
            if (pedidoSeleccionado == null) {
                JOptionPane.showMessageDialog(this, "No hay pedidos pendientes para asignar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String repartidor = (String) cmbRepartidores.getSelectedItem();

            // 1. Actualizamos el objeto en memoria local
            pedidoSeleccionado.setRepartidorAsignado(repartidor);
            pedidoSeleccionado.setEstado(EstadoPedido.EN_REPARTO);

            // 2. Persistimos los cambios directamente en MySQL usando el DAO
            PedidoDAO pedidoDAO = new PedidoDAOImpl();
            boolean actualizado = pedidoDAO.actualizarEstadoYRepartidor(
                    pedidoSeleccionado.getId(),
                    EstadoPedido.EN_REPARTO,
                    repartidor
            );

            if (actualizado) {
                String mensaje = "=== ASIGNACIÓN EXITOSA ===\n\n" +
                        "🆔 ID Pedido: " + pedidoSeleccionado.getId() + "\n" +
                        "📍 Dirección: " + pedidoSeleccionado.getDireccionEntrega() + "\n" +
                        "📦 Tipo de Reparto: " + pedidoSeleccionado.getTipo() + "\n" +
                        "👤 Repartidor Asignado: " + repartidor + "\n" +
                        "🚀 Estado: EN CURSO (EN_REPARTO)";

                JOptionPane.showMessageDialog(this, mensaje, "Entrega en Curso", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar la asignación en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }

    private void cargarPedidosPendientes() {
        cmbPedidos.removeAllItems();

        // Consultamos la Base de Datos para mostrar siempre pedidos reales y actualizados
        PedidoDAO pedidoDAO = new PedidoDAOImpl();
        List<Pedido> pedidosBD = pedidoDAO.obtenerTodos();

        for (Pedido p : pedidosBD) {
            if (p.getEstado() == EstadoPedido.PENDIENTE) {
                cmbPedidos.addItem(p);
            }
        }
    }
}