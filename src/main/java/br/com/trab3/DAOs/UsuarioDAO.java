package br.com.trab3.DAOs;

import br.com.trab3.modelos.Usuario;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

    private static Connection conexao;
    private static UsuarioDAO instancia = null;
    private PreparedStatement insertStatement;
    private PreparedStatement loginStatement;

    public UsuarioDAO() {
        try {
            UsuarioDAO.conexao = Conexao.getConnection();

            insertStatement = UsuarioDAO.conexao.prepareStatement("INSERT INTO usuario(nome_completo, email, login, senha) VALUES(?, ?, ?, ?)");
            loginStatement = UsuarioDAO.conexao.prepareStatement("SELECT * FROM usuario WHERE login = ? AND senha = ?");
        } catch (URISyntaxException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static UsuarioDAO getInstance() {
        if (UsuarioDAO.instancia == null) {
            UsuarioDAO.instancia = new UsuarioDAO();
        }
        return UsuarioDAO.instancia;
    }

    public void insertUsuario(String nomeCompleto, String email, String login, String senha) {
        try {
            insertStatement.clearParameters();
            insertStatement.setString(1, nomeCompleto);
            insertStatement.setString(2, email);
            insertStatement.setString(3, login);
            insertStatement.setString(4, senha);
            insertStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario login(String login, String senha) {
        try {
            loginStatement.clearParameters();
            loginStatement.setString(1, login);
            loginStatement.setString(2, senha);
            ResultSet resultado = loginStatement.executeQuery();

            if (!resultado.next()) {
                return null;
            }
            Integer id_usuario = resultado.getInt("id_usuario");
            String nomeCompleto = resultado.getString("nome_completo");
            String email = resultado.getString("email");

            return new Usuario(id_usuario, nomeCompleto, login, email, senha);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
