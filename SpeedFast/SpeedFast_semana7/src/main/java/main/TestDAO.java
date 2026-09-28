package main;

import modelo.Usuario;
import modelo.UsuarioDAO;
import modelo.UsuarioDAOImpl;

public class TestDAO {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAOImpl();

        // 1. Probar inserción (DML)
        Usuario nuevo = new Usuario("maria", "secret123");
        boolean registrado = usuarioDAO.registrar(nuevo);
        System.out.println("¿Usuario registrado con éxito?: " + registrado);

        // 2. Probar validación de Login (DQL)
        boolean loginCorrecto = usuarioDAO.validarLogin("maria", "secret123");
        System.out.println("¿Login válido para maria?: " + loginCorrecto);

        // 3. Probar listado general
        System.out.println("\n--- Lista de Usuarios en BD ---");
        for (Usuario u : usuarioDAO.obtenerTodos()) {
            System.out.println("ID: " + u.getId() + " | Usuario: " + u.getUsername());
        }
    }
}
