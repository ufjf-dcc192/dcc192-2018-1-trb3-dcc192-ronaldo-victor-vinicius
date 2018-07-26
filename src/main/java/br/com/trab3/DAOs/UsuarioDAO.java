package br.com.trab3.DAOs;

import br.com.trab3.modelos.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuarioDAO {

    private static Connection conexao;
    private static UsuarioDAO instancia = null;
    private PreparedStatement insertStatement;
    private PreparedStatement loginStatement;
    private PreparedStatement selectAllUsuariosTrollsStatement;
    private PreparedStatement selectAllUsuariosOrderByCuradoresStatement;

    public UsuarioDAO() {
        try {
            conexao = Conexao.getInstance();

            insertStatement = UsuarioDAO.conexao.prepareStatement(
                    "INSERT INTO usuario(nome_completo, email, login, senha) "
                    + "VALUES(?, ?, ?, ?)");
            loginStatement = UsuarioDAO.conexao.prepareStatement(
                    "SELECT * "
                    + "FROM usuario "
                    + "WHERE login = ? AND senha = ?");
            selectAllUsuariosTrollsStatement = UsuarioDAO.conexao.prepareStatement(
                    "SELECT *\n"
                    + "FROM\n"
                    + "(SELECT u.*, \n"
                    + "avn.qtd_comentarios_avaliados_negativamente,\n"
                    + "qtd_co.qtd_comentarios\n"
                    + "FROM usuario AS u,\n"
                    + "(SELECT DISTINCT \n"
                    + "COUNT(ac2.id_comentario_avaliado) AS qtd_comentarios_avaliados_negativamente,\n"
                    + "us2.id_usuario\n"
                    + "FROM usuario AS us2, \n"
                    + "comentario AS co2, \n"
                    + "(SELECT DISTINCT ac3.id_comentario_avaliado, ac3.avaliacao FROM avaliacao_comentario AS ac3) AS ac2\n"
                    + "WHERE us2.id_usuario = co2.id_usuario_proprietario AND co2.id_comentario = ac2.id_comentario_avaliado AND ac2.avaliacao < 0 \n"
                    + "GROUP BY us2.id_usuario) AS avn,\n"
                    + "(SELECT COUNT(*) AS qtd_comentarios,\n"
                    + "us3.id_usuario\n"
                    + "FROM comentario AS co4, usuario AS us3 \n"
                    + "WHERE us3.id_usuario = co4.id_usuario_proprietario GROUP BY us3.id_usuario) AS qtd_co\n"
                    + "WHERE avn.id_usuario = u.id_usuario AND u.id_usuario = qtd_co.id_usuario\n"
                    + "GROUP BY u.id_usuario, avn.qtd_comentarios_avaliados_negativamente, qtd_co.qtd_comentarios) AS resultado\n"
                    + "WHERE resultado.qtd_comentarios_avaliados_negativamente >= DIV(resultado.qtd_comentarios + MOD(resultado.qtd_comentarios, 2), 2) ORDER BY resultado.qtd_comentarios_avaliados_negativamente DESC", Statement.RETURN_GENERATED_KEYS);
            selectAllUsuariosOrderByCuradoresStatement = UsuarioDAO.conexao.prepareStatement(
                    "SELECT *,\n"
                    + "(pn.qtd_avaliacoes_positivas - pn.qtd_avaliacoes_negativas) AS somatorio\n"
                    + "FROM (SELECT u.*, avp.qtd_avaliacoes_positivas, avn.qtd_avaliacoes_negativas\n"
                    + "FROM usuario AS u,\n"
                    + "(SELECT COUNT(ac1.id_comentario_avaliado) AS qtd_avaliacoes_positivas,\n"
                    + "us1.id_usuario\n"
                    + "FROM usuario AS us1, comentario AS co1, avaliacao_comentario AS ac1\n"
                    + "WHERE us1.id_usuario = co1.id_usuario_proprietario AND co1.id_comentario = ac1.id_comentario_avaliado AND ac1.avaliacao > 0 GROUP BY us1.id_usuario) as avp,\n"
                    + "(SELECT COUNT(ac2.id_comentario_avaliado) AS qtd_avaliacoes_negativas,\n"
                    + "us2.id_usuario\n"
                    + "FROM usuario AS us2, comentario AS co2, avaliacao_comentario AS ac2\n"
                    + "WHERE us2.id_usuario = co2.id_usuario_proprietario AND co2.id_comentario = ac2.id_comentario_avaliado AND ac2.avaliacao < 0 GROUP BY us2.id_usuario) as avn\n"
                    + "WHERE avp.id_usuario = avn.id_usuario AND avp.id_usuario = u.id_usuario\n"
                    + "GROUP BY u.id_usuario, avp.qtd_avaliacoes_positivas, avn.qtd_avaliacoes_negativas) AS pn\n"
                    + "ORDER BY somatorio DESC", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static UsuarioDAO getInstance() {
        try {
            if (instancia == null || conexao == null || conexao.isClosed()) {
                instancia = new UsuarioDAO();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return instancia;
    }

    public void closeConnection() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public ArrayList<Usuario> selectAllUsuariosOrderByCuradores() {
        ResultSet resultado = null;
        ArrayList<Usuario> usuarios;
        usuarios = new ArrayList<>();
        try {
            selectAllUsuariosOrderByCuradoresStatement.clearParameters();
            resultado = selectAllUsuariosOrderByCuradoresStatement.executeQuery();

            while (resultado.next()) {
                Integer idUsuario = (Integer) resultado.getInt("id_usuario");
                String email = resultado.getString("email");
                String login = resultado.getString("login");
                String nomeCompleto = resultado.getString("nome_completo");
                Integer quantidadeAvaliacoesPositivas = resultado.getInt("qtd_avaliacoes_positivas");
                Integer quantidadeAvaliacoesNegativas = resultado.getInt("qtd_avaliacoes_negativas");

                Usuario usuario = new Usuario(idUsuario, nomeCompleto, login, email, "");
                usuario.setQtdAvaliacoesPositivas(quantidadeAvaliacoesPositivas);
                usuario.setQtdAvaliacoesNegativas(quantidadeAvaliacoesNegativas);
                usuario.setQtdComentarios(ComentarioDAO.getInstance().selectCountComentariosByIdUsuario(idUsuario));
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
            } catch (SQLException ex) {
            }
        }
        return null;
    }

    public ArrayList<Usuario> selectAllUsuariosTrolls() {
        ResultSet resultado = null;
        ArrayList<Usuario> usuarios;
        usuarios = new ArrayList<>();
        try {
            selectAllUsuariosTrollsStatement.clearParameters();
            resultado = selectAllUsuariosTrollsStatement.executeQuery();

            while (resultado.next()) {
                Integer idUsuario = (Integer) resultado.getInt("id_usuario");
                String email = resultado.getString("email");
                String login = resultado.getString("login");
                String nomeCompleto = resultado.getString("nome_completo");
                Integer qtd_comentarios_avaliados_negativamente = resultado.getInt("qtd_comentarios_avaliados_negativamente");
                Integer qtd_comentarios = resultado.getInt("qtd_comentarios");

                Usuario usuario = new Usuario(idUsuario, nomeCompleto, login, email, "");
                usuario.setQtdComentariosAvaliadosNegativamente(qtd_comentarios_avaliados_negativamente);
                usuario.setQtdComentarios(qtd_comentarios);
                usuarios.add(usuario);
            }
            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
            } catch (SQLException ex) {
            }
        }
        return null;
    }
}
