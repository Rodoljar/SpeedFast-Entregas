package vista;

import javax.swing.*;
import java.awt.*;
import servicios.ZonaDeCarga;

public class VentanaPrincipal extends JFrame {

    private ZonaDeCarga zonaDeCarga;

    public VentanaPrincipal() {
        zonaDeCarga = new ZonaDeCarga();

        setTitle("SpeedFast - Gestión de Entregas");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Título superior
        JLabel lblTitulo = new JLabel("=== SPEEDFAST: PANEL PRINCIPAL ===", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel central con botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JButton btnRegistrar = new JButton("Registrar Pedido");
        JButton btnListar = new JButton("Listar Pedidos");
        JButton btnAsignar = new JButton("Asignar Repartidor / Iniciar");

        panelBotones.add(btnRegistrar);
        panelBotones.add(btnListar);
        panelBotones.add(btnAsignar);

        add(panelBotones, BorderLayout.CENTER);

        // Acciones de los botones
        btnRegistrar.addActionListener(e -> {
            VentanaRegistroPedido ventanaRegistro = new VentanaRegistroPedido(this, zonaDeCarga);
            ventanaRegistro.setVisible(true);
        });

        btnListar.addActionListener(e -> {
            VentanaListaPedidos ventanaLista = new VentanaListaPedidos(zonaDeCarga);
            ventanaLista.setVisible(true);
        });

        btnAsignar.addActionListener(e -> {
            VentanaAsignarRepartidor ventanaAsignar = new VentanaAsignarRepartidor(this, zonaDeCarga);
            ventanaAsignar.setVisible(true);
        });
    }
}