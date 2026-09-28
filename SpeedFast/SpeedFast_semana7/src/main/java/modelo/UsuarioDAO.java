package modelo;

import java.util.List;

public interface UsuarioDAO {
    // Método DML (INSERT) para registrar usuarios
    boolean registrar(Usuario usuario);

    // Método DQL (SELECT) para autenticar en el Login
    boolean validarLogin(String username, String password);

    // Método DQL (SELECT) para consultar todos los usuarios
    List<Usuario> obtenerTodos();
}
