package service;

import model.Usuario;
import java.util.ArrayList;
import java.util.List;

public class ServicoUsuario {
    private final List<Usuario> usuarios = new ArrayList<>();

    public Usuario cadastrarUsuario(String nome, String email, String telefone) {
        Usuario usuario = new Usuario(nome, email, telefone);
        usuarios.add(usuario);
        return usuario;
    }

    public Usuario buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElse(null);
    }

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }
}
