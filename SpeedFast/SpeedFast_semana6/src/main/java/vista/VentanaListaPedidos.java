package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import modelo.Pedido;
import servicios.ZonaDeCarga;

public class VentanaListaPedidos extends JFrame {

    private JTable tablaPedidos;
    private DefaultTableModel modeloTabla;
    private ZonaDeCarga zonaDeCarga;

    public VentanaListaPedidos(ZonaDeCarga zonaDeCarga) {
        this.zonaDeCarga = zonaDeCarga;

        setTitle("Listado de Pedidos - SpeedFast");
        setSize(700, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Columnas de la tabla
        String[] columnas = {"ID", "Dirección", "Tipo de Reparto", "Estado", "Repartidor"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaPedidos = new JTable(modeloTabla);

        JScrollPane scrollPane = new JScrollPane(tablaPedidos);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para los botones
        JPanel panelInferior = new JPanel();
        JButton btnRefrescar = new JButton("Refrescar Tabla");
        JButton btnCancelarPedido = new JButton("Cancelar Pedido");

        btnCancelarPedido.setForeground(Color.RED);

        panelInferior.add(btnRefrescar);
        panelInferior.add(btnCancelarPedido);
        add(panelInferior, BorderLayout.SOUTH);

        refrescarDatos();

        // Acción de refrescar
        btnRefrescar.addActionListener(e -> refrescarDatos());

        // Acción para cancelar y eliminar el pedido seleccionado
        btnCancelarPedido.addActionListener(e -> {
            int filaSeleccionada = tablaPedidos.getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, selecciona un pedido de la tabla para cancelar.",
                        "Advertencia",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de que deseas cancelar y eliminar este pedido?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                // Obtenemos el ID del pedido desde la primera columna
                int idPedido = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

                // Pedido correspondiente
                Pedido pedidoAEliminar = null;
                for (Pedido p : zonaDeCarga.getPedidos()) {
                    if (p.getId() == idPedido) {
                        pedidoAEliminar = p;
                        break;
                    }
                }

                if (pedidoAEliminar != null) {
                    //  método que creamos en ZonaDeCarga
                    zonaDeCarga.removerPedido(pedidoAEliminar);
                    refrescarDatos(); // Actualizamos la tabla visualmente
                    JOptionPane.showMessageDialog(this,
                            "El pedido ha sido cancelado y eliminado correctamente.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private void refrescarDatos() {
        modeloTabla.setRowCount(0);
        for (Pedido p : zonaDeCarga.getPedidos()) {
            Object[] fila = {
                    p.getId(),
                    p.getDireccionEntrega(),
                    p.getTipo(),
                    p.getEstado(),
                    p.getRepartidorAsignado()
            };
            modeloTabla.addRow(fila);
        }
    }
}