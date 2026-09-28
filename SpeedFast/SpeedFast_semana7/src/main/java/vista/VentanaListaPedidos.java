package vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import modelo.Pedido;
import modelo.PedidoDAO;
import modelo.PedidoDAOImpl;
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

        // Acción de refrescar datos desde la base de datos
        btnRefrescar.addActionListener(e -> refrescarDatos());

        // Acción para cancelar y eliminar el pedido seleccionado de la Base de Datos
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
                    "¿Estás seguro de que deseas cancelar y eliminar este pedido de la Base de Datos?",
                    "Confirmar Eliminación",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                int idPedido = (int) modeloTabla.getValueAt(filaSeleccionada, 0);

                // Eliminación persistente en MySQL mediante el DAO
                PedidoDAO pedidoDAO = new PedidoDAOImpl();
                boolean eliminado = pedidoDAO.eliminar(idPedido);

                if (eliminado) {
                    refrescarDatos(); // Se vuelve a consultar la BD, actualizando la tabla
                    JOptionPane.showMessageDialog(this,
                            "El pedido ha sido eliminado de la base de datos correctamente.",
                            "Éxito",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Error al intentar eliminar el pedido de la base de datos.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void refrescarDatos() {
        modeloTabla.setRowCount(0);

        // Consulta a la Base de Datos mediante el DAO
        PedidoDAO pedidoDAO = new PedidoDAOImpl();
        List<Pedido> pedidosBD = pedidoDAO.obtenerTodos();

        for (Pedido p : pedidosBD) {
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