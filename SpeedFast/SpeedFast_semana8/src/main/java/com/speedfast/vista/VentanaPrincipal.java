package com.speedfast.vista;

import com.speedfast.dao.*;
import com.speedfast.modelo.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class VentanaPrincipal extends JFrame {

    private RepartidorDAO repartidorDAO = new RepartidorDAO();
    private PedidoDAO pedidoDAO = new PedidoDAO();
    private EntregaDAO entregaDAO = new EntregaDAO();

    // Componentes Repartidores
    private JTextField txtNombreRepartidor;
    private JTable tablaRepartidores;
    private DefaultTableModel modeloTablaRepartidores;
    private int idRepartidorSeleccionado = -1;

    // Componentes Pedidos
    private JTextField txtDireccionPedido;
    private JComboBox<TipoPedido> cbTipoPedido;
    private JComboBox<EstadoPedido> cbEstadoPedido;
    private JComboBox<Object> cbFiltroTipoPedido;
    private JComboBox<Object> cbFiltroEstadoPedido;
    private JTable tablaPedidos;
    private DefaultTableModel modeloTablaPedidos;
    private int idPedidoSeleccionado = -1;

    // Componentes Entregas
    private JComboBox<Pedido> cbEntregaPedido;
    private JComboBox<Repartidor> cbEntregaRepartidor;
    private JTextField txtFechaEntrega; // YYYY-MM-DD
    private JTextField txtHoraEntrega;  // HH:MM:SS
    private JTable tablaEntregas;
    private DefaultTableModel modeloTablaEntregas;
    private int idEntregaSeleccionada = -1;

    public VentanaPrincipal() {
        setTitle("SpeedFast - Gestión de Pedidos y Entregas");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Repartidores", crearPanelRepartidores());
        tabbedPane.addTab("Pedidos", crearPanelPedidos());
        tabbedPane.addTab("Entregas", crearPanelEntregas());

        add(tabbedPane);

        // Carga inicial de datos
        cargarRepartidores();
        cargarPedidos();
        cargarEntregas();
        actualizarCombosEntregas();
    }


    private JPanel crearPanelRepartidores() {
        JPanel panel = new JPanel(new BorderLayout());

        // Formulario Superior
        JPanel panelForm = new JPanel(new FlowLayout());
        panelForm.add(new JLabel("Nombre:"));
        txtNombreRepartidor = new JTextField(20);
        panelForm.add(txtNombreRepartidor);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnEditar = new JButton("Actualizar Seleccionado");
        JButton btnEliminar = new JButton("Eliminar Seleccionado");

        panelForm.add(btnGuardar);
        panelForm.add(btnEditar);
        panelForm.add(btnEliminar);

        panel.add(panelForm, BorderLayout.NORTH);

        // Tabla
        modeloTablaRepartidores = new DefaultTableModel(new String[]{"ID", "Nombre"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaRepartidores = new JTable(modeloTablaRepartidores);
        panel.add(new JScrollPane(tablaRepartidores), BorderLayout.CENTER);

        // Eventos
        tablaRepartidores.getSelectionModel().addListSelectionListener(e -> {
            int row = tablaRepartidores.getSelectedRow();
            if (row != -1) {
                idRepartidorSeleccionado = (int) modeloTablaRepartidores.getValueAt(row, 0);
                txtNombreRepartidor.setText((String) modeloTablaRepartidores.getValueAt(row, 1));
            }
        });

        btnGuardar.addActionListener(e -> {
            if (txtNombreRepartidor.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                repartidorDAO.create(new Repartidor(txtNombreRepartidor.getText().trim()));
                txtNombreRepartidor.setText("");
                cargarRepartidores();
                actualizarCombosEntregas();
                JOptionPane.showMessageDialog(this, "Repartidor guardado con éxito.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar: " + ex.getMessage(), "Error SQL", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEditar.addActionListener(e -> {
            if (idRepartidorSeleccionado == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un repartidor de la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                Repartidor r = new Repartidor(idRepartidorSeleccionado, txtNombreRepartidor.getText().trim());
                repartidorDAO.update(r);
                txtNombreRepartidor.setText("");
                idRepartidorSeleccionado = -1;
                cargarRepartidores();
                actualizarCombosEntregas();
                JOptionPane.showMessageDialog(this, "Repartidor actualizado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnEliminar.addActionListener(e -> {
            if (idRepartidorSeleccionado == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un repartidor a eliminar.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                repartidorDAO.delete(idRepartidorSeleccionado);
                txtNombreRepartidor.setText("");
                idRepartidorSeleccionado = -1;
                cargarRepartidores();
                actualizarCombosEntregas();
                JOptionPane.showMessageDialog(this, "Repartidor eliminado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se puede eliminar (posiblemente tiene entregas asociadas).", "Error SQL", JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private void cargarRepartidores() {
        try {
            modeloTablaRepartidores.setRowCount(0);
            List<Repartidor> lista = repartidorDAO.readAll();
            for (Repartidor r : lista) {
                modeloTablaRepartidores.addRow(new Object[]{r.getId(), r.getNombre()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar repartidores: " + e.getMessage());
        }
    }


    private JPanel crearPanelPedidos() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(2, 1));
        JPanel panelInputs = new JPanel(new FlowLayout());

        panelInputs.add(new JLabel("Dirección:"));
        txtDireccionPedido = new JTextField(15);
        panelInputs.add(txtDireccionPedido);

        panelInputs.add(new JLabel("Tipo:"));
        cbTipoPedido = new JComboBox<>(TipoPedido.values());
        panelInputs.add(cbTipoPedido);

        panelInputs.add(new JLabel("Estado:"));
        cbEstadoPedido = new JComboBox<>(EstadoPedido.values());
        panelInputs.add(cbEstadoPedido);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnEditar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        panelInputs.add(btnGuardar);
        panelInputs.add(btnEditar);
        panelInputs.add(btnEliminar);

        // Panel de Filtros
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelFiltros.setBorder(BorderFactory.createTitledBorder("Filtros de búsqueda"));

        cbFiltroTipoPedido = new JComboBox<>();
        cbFiltroTipoPedido.addItem("TODOS");
        for (TipoPedido t : TipoPedido.values()) cbFiltroTipoPedido.addItem(t);

        cbFiltroEstadoPedido = new JComboBox<>();
        cbFiltroEstadoPedido.addItem("TODOS");
        for (EstadoPedido ep : EstadoPedido.values()) cbFiltroEstadoPedido.addItem(ep);

        JButton btnFiltrar = new JButton("Aplicar Filtro");

        panelFiltros.add(new JLabel("Filtrar Tipo:"));
        panelFiltros.add(cbFiltroTipoPedido);
        panelFiltros.add(new JLabel("Filtrar Estado:"));
        panelFiltros.add(cbFiltroEstadoPedido);
        panelFiltros.add(btnFiltrar);

        panelForm.add(panelInputs);
        panelForm.add(panelFiltros);

        panel.add(panelForm, BorderLayout.NORTH);

        modeloTablaPedidos = new DefaultTableModel(new String[]{"ID", "Dirección", "Tipo", "Estado"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaPedidos = new JTable(modeloTablaPedidos);
        panel.add(new JScrollPane(tablaPedidos), BorderLayout.CENTER);

        // Eventos
        tablaPedidos.getSelectionModel().addListSelectionListener(e -> {
            int row = tablaPedidos.getSelectedRow();
            if (row != -1) {
                idPedidoSeleccionado = (int) modeloTablaPedidos.getValueAt(row, 0);
                txtDireccionPedido.setText((String) modeloTablaPedidos.getValueAt(row, 1));
                cbTipoPedido.setSelectedItem(modeloTablaPedidos.getValueAt(row, 2));
                cbEstadoPedido.setSelectedItem(modeloTablaPedidos.getValueAt(row, 3));
            }
        });

        btnGuardar.addActionListener(e -> {
            if (txtDireccionPedido.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una dirección.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                Pedido p = new Pedido(
                        txtDireccionPedido.getText().trim(),
                        (TipoPedido) cbTipoPedido.getSelectedItem(),
                        (EstadoPedido) cbEstadoPedido.getSelectedItem()
                );
                pedidoDAO.create(p);
                txtDireccionPedido.setText("");
                cargarPedidos();
                actualizarCombosEntregas();
                JOptionPane.showMessageDialog(this, "Pedido registrado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnEditar.addActionListener(e -> {
            if (idPedidoSeleccionado == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un pedido.");
                return;
            }
            try {
                Pedido p = new Pedido(
                        idPedidoSeleccionado,
                        txtDireccionPedido.getText().trim(),
                        (TipoPedido) cbTipoPedido.getSelectedItem(),
                        (EstadoPedido) cbEstadoPedido.getSelectedItem()
                );
                pedidoDAO.update(p);
                txtDireccionPedido.setText("");
                idPedidoSeleccionado = -1;
                cargarPedidos();
                actualizarCombosEntregas();
                JOptionPane.showMessageDialog(this, "Pedido actualizado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnEliminar.addActionListener(e -> {
            if (idPedidoSeleccionado == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un pedido.");
                return;
            }
            try {
                pedidoDAO.delete(idPedidoSeleccionado);
                txtDireccionPedido.setText("");
                idPedidoSeleccionado = -1;
                cargarPedidos();
                actualizarCombosEntregas();
                JOptionPane.showMessageDialog(this, "Pedido eliminado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se puede eliminar (posiblemente asociado a una entrega).", "Error SQL", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnFiltrar.addActionListener(e -> {
            Object selEstado = cbFiltroEstadoPedido.getSelectedItem();
            Object selTipo = cbFiltroTipoPedido.getSelectedItem();

            EstadoPedido eFiltro = (selEstado instanceof EstadoPedido) ? (EstadoPedido) selEstado : null;
            TipoPedido tFiltro = (selTipo instanceof TipoPedido) ? (TipoPedido) selTipo : null;

            try {
                modeloTablaPedidos.setRowCount(0);
                List<Pedido> lista = pedidoDAO.readFiltrado(eFiltro, tFiltro);
                for (Pedido p : lista) {
                    modeloTablaPedidos.addRow(new Object[]{p.getId(), p.getDireccion(), p.getTipo(), p.getEstado()});
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al filtrar: " + ex.getMessage());
            }
        });

        return panel;
    }

    private void cargarPedidos() {
        try {
            modeloTablaPedidos.setRowCount(0);
            List<Pedido> lista = pedidoDAO.readAll();
            for (Pedido p : lista) {
                modeloTablaPedidos.addRow(new Object[]{p.getId(), p.getDireccion(), p.getTipo(), p.getEstado()});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar pedidos: " + e.getMessage());
        }
    }


    private JPanel crearPanelEntregas() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel panelForm = new JPanel(new FlowLayout());

        cbEntregaPedido = new JComboBox<>();
        cbEntregaRepartidor = new JComboBox<>();
        txtFechaEntrega = new JTextField(8); // AAAA-MM-DD
        txtFechaEntrega.setText(LocalDate.now().toString());
        txtHoraEntrega = new JTextField(6);   // HH:MM:SS
        txtHoraEntrega.setText(LocalTime.now().toString().substring(0, 8));

        panelForm.add(new JLabel("Pedido:"));
        panelForm.add(cbEntregaPedido);
        panelForm.add(new JLabel("Repartidor:"));
        panelForm.add(cbEntregaRepartidor);
        panelForm.add(new JLabel("Fecha (YYYY-MM-DD):"));
        panelForm.add(txtFechaEntrega);
        panelForm.add(new JLabel("Hora (HH:MM:SS):"));
        panelForm.add(txtHoraEntrega);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnEditar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        panelForm.add(btnGuardar);
        panelForm.add(btnEditar);
        panelForm.add(btnEliminar);

        panel.add(panelForm, BorderLayout.NORTH);

        modeloTablaEntregas = new DefaultTableModel(new String[]{"ID", "ID Pedido", "Repartidor", "Fecha", "Hora"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tablaEntregas = new JTable(modeloTablaEntregas);
        panel.add(new JScrollPane(tablaEntregas), BorderLayout.CENTER);


        tablaEntregas.getSelectionModel().addListSelectionListener(e -> {
            int row = tablaEntregas.getSelectedRow();
            if (row != -1) {
                idEntregaSeleccionada = (int) modeloTablaEntregas.getValueAt(row, 0);
                txtFechaEntrega.setText(modeloTablaEntregas.getValueAt(row, 3).toString());
                txtHoraEntrega.setText(modeloTablaEntregas.getValueAt(row, 4).toString());
            }
        });

        btnGuardar.addActionListener(e -> {
            Pedido p = (Pedido) cbEntregaPedido.getSelectedItem();
            Repartidor r = (Repartidor) cbEntregaRepartidor.getSelectedItem();

            if (p == null || r == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un pedido y un repartidor.", "Validación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                LocalDate fecha = LocalDate.parse(txtFechaEntrega.getText().trim());
                LocalTime hora = LocalTime.parse(txtHoraEntrega.getText().trim());

                Entrega entrega = new Entrega(p, r, fecha, hora);
                entregaDAO.create(entrega);
                cargarEntregas();
                JOptionPane.showMessageDialog(this, "Entrega registrada exitosamente.");
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(this, "Formato de fecha u hora inválido. Use YYYY-MM-DD y HH:MM:SS.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al registrar entrega: " + ex.getMessage());
            }
        });

        btnEditar.addActionListener(e -> {
            if (idEntregaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una entrega de la tabla.");
                return;
            }
            Pedido p = (Pedido) cbEntregaPedido.getSelectedItem();
            Repartidor r = (Repartidor) cbEntregaRepartidor.getSelectedItem();

            try {
                LocalDate fecha = LocalDate.parse(txtFechaEntrega.getText().trim());
                LocalTime hora = LocalTime.parse(txtHoraEntrega.getText().trim());

                Entrega entrega = new Entrega(idEntregaSeleccionada, p, r, fecha, hora);
                entregaDAO.update(entrega);
                idEntregaSeleccionada = -1;
                cargarEntregas();
                JOptionPane.showMessageDialog(this, "Entrega actualizada.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        btnEliminar.addActionListener(e -> {
            if (idEntregaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione una entrega.");
                return;
            }
            try {
                entregaDAO.delete(idEntregaSeleccionada);
                idEntregaSeleccionada = -1;
                cargarEntregas();
                JOptionPane.showMessageDialog(this, "Entrega eliminada.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        return panel;
    }

    private void cargarEntregas() {
        try {
            modeloTablaEntregas.setRowCount(0);
            List<Entrega> lista = entregaDAO.readAll();
            for (Entrega e : lista) {
                modeloTablaEntregas.addRow(new Object[]{
                        e.getId(),
                        e.getPedido().getId() + " - " + e.getPedido().getDireccion(),
                        e.getRepartidor().getNombre(),
                        e.getFecha(),
                        e.getHora()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar entregas: " + e.getMessage());
        }
    }

    private void actualizarCombosEntregas() {
        try {
            cbEntregaPedido.removeAllItems();
            for (Pedido p : pedidoDAO.readAll()) {
                cbEntregaPedido.addItem(p);
            }

            cbEntregaRepartidor.removeAllItems();
            for (Repartidor r : repartidorDAO.readAll()) {
                cbEntregaRepartidor.addItem(r);
            }
        } catch (Exception e) {
            System.out.println("Error al actualizar combos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}