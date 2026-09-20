package vista;

import javax.swing.*;
import java.awt.*;
import modelo.Pedido;
import modelo.EstadoPedido;
import servicios.ZonaDeCarga;

public class VentanaRegistroPedido extends JFrame {

    private JTextField txtId;
    private JTextField txtDireccion;
    private JComboBox<String> cmbTipo;

    public VentanaRegistroPedido(JFrame parent, ZonaDeCarga zonaDeCarga) {
        setTitle("Registrar Nuevo Pedido");
        setSize(350, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(4, 2, 10, 10));

        add(new JLabel(" ID del Pedido:"));
        txtId = new JTextField();
        add(txtId);

        add(new JLabel(" Dirección:"));
        txtDireccion = new JTextField();
        add(txtDireccion);

        add(new JLabel(" Tipo de Pedido:"));
        String[] tipos = {"comida", "encomienda", "express"};
        cmbTipo = new JComboBox<>(tipos);
        add(cmbTipo);

        JButton btnGuardar = new JButton("Guardar");
        JButton btnCancelar = new JButton("Cancelar");

        add(btnGuardar);
        add(btnCancelar);

        btnGuardar.addActionListener(e -> {
            try {
                if (txtId.getText().trim().isEmpty() || txtDireccion.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                int id = Integer.parseInt(txtId.getText().trim());
                String direccion = txtDireccion.getText().trim();
                String tipo = (String) cmbTipo.getSelectedItem();

                Pedido nuevoPedido = new Pedido(id, direccion, tipo, EstadoPedido.PENDIENTE);
                zonaDeCarga.agregarPedido(nuevoPedido);

                JOptionPane.showMessageDialog(this, "¡Pedido registrado con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El ID debe ser un número entero.", "Error de formato", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());
    }
}