package vista;

import javax.swing.*;
import java.awt.*;
import servicios.ZonaDeCarga;
import modelo.Pedido;
import modelo.EstadoPedido;

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

            // Guardamos el repartidor y actualizamos estado
            pedidoSeleccionado.setRepartidorAsignado(repartidor);
            pedidoSeleccionado.setEstado(EstadoPedido.EN_REPARTO);

            // Mensaje emergente completo con tipo de reparto y repartidor asignado
            String mensaje = "=== ASIGNACIÓN EXITOSA ===\n\n" +
                    "🆔 ID Pedido: " + pedidoSeleccionado.getId() + "\n" +
                    "📍 Dirección: " + pedidoSeleccionado.getDireccionEntrega() + "\n" +
                    "📦 Tipo de Reparto: " + pedidoSeleccionado.getTipo() + "\n" +
                    "👤 Repartidor Asignado: " + repartidor + "\n" +
                    "🚀 Estado: EN CURSO (EN_REPARTO)";

            JOptionPane.showMessageDialog(this, mensaje, "Entrega en Curso", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        btnCancelar.addActionListener(e -> dispose());
    }

    private void cargarPedidosPendientes() {
        cmbPedidos.removeAllItems();
        for (Pedido p : zonaDeCarga.getPedidos()) {
            if (p.getEstado() == EstadoPedido.PENDIENTE) {
                cmbPedidos.addItem(p);
            }
        }
    }
}