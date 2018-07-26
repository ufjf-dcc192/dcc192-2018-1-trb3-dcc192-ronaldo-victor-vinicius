package br.com.trab3.DAOs;

import br.com.trab3.modelos.Comentario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComentarioDAO {

    private static Connection conexao;
    private static ComentarioDAO instancia = null;
    private PreparedStatement insertComentarioStatement;

    private PreparedStatement selectAllComentariosByIdItemStatement;
    private PreparedStatement selectComentarioByIdComentarioStatement;
    private PreparedStatement selectCountComentariosByIdUsuarioStatement;
    private PreparedStatement selectAllComentariosNaoAvaliadosByIdUsuarioAndByIdItemStatement;

    private PreparedStatement deleteComentarioByIdComentarioStatement;

    private PreparedStatement updateComentarioByIdComentarioStatement;

    public ComentarioDAO() {
        try {
            ComentarioDAO.conexao = Conexao.getInstance();

            insertComentarioStatement = ComentarioDAO.conexao.prepareStatement(
                    "INSERT INTO comentario(titulo, texto, data_hora_criacao, data_hora_ultima_atualizacao, id_usuario_proprietario, id_item_comentado) "
                    + "VALUES(?, ?,  LOCALTIMESTAMP,  LOCALTIMESTAMP, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            selectComentarioByIdComentarioStatement = ComentarioDAO.conexao.prepareStatement(
                    "SELECT * "
                    + "FROM comentario "
                    + "WHERE id_comentario = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);
            selectAllComentariosByIdItemStatement = ComentarioDAO.conexao.prepareStatement(
                    "SELECT *,\n"
                    + "(pn.qtd_avaliacoes_positivas - pn.qtd_avaliacoes_negativas) AS diferenca_qtd_avaliacoes\n"
                    + "FROM (SELECT *,\n"
                    + "(SELECT count(*) FROM avaliacao_comentario as ac1 WHERE ac1.id_comentario_avaliado = co.id_comentario AND ac1.avaliacao > 0) AS qtd_avaliacoes_positivas,\n"
                    + "(SELECT count(*) FROM avaliacao_comentario as ac2 WHERE ac2.id_comentario_avaliado = co.id_comentario AND ac2.avaliacao < 0) AS qtd_avaliacoes_negativas\n"
                    + "FROM comentario as co\n"
                    + "WHERE co.id_item_comentado = ?) AS pn\n"
                    + "ORDER BY diferenca_qtd_avaliacoes DESC", Statement.RETURN_GENERATED_KEYS);
            selectCountComentariosByIdUsuarioStatement = ComentarioDAO.conexao.prepareStatement(
                    "SELECT COUNT(*) AS qtd_comentarios "
                    + "FROM comentario "
                    + "WHERE id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);
            selectAllComentariosNaoAvaliadosByIdUsuarioAndByIdItemStatement = ComentarioDAO.conexao.prepareStatement(
                    "SELECT *,\n"
                    + "(pn.qtd_avaliacoes_positivas - pn.qtd_avaliacoes_negativas) AS diferenca_qtd_avaliacoes\n"
                    + "FROM (SELECT *,\n"
                    + "(SELECT count(*) FROM avaliacao_comentario as ac1 WHERE ac1.id_comentario_avaliado = co.id_comentario AND ac1.avaliacao > 0) AS qtd_avaliacoes_positivas,\n"
                    + "(SELECT count(*) FROM avaliacao_comentario as ac2 WHERE ac2.id_comentario_avaliado = co.id_comentario AND ac2.avaliacao < 0) AS qtd_avaliacoes_negativas\n"
                    + "FROM comentario AS co\n"
                    + "WHERE id_item_comentado = ? AND id_comentario NOT IN \n"
                    + "(SELECT id_comentario_avaliado FROM avaliacao_comentario WHERE id_usuario_proprietario = ?)) AS pn\n"
                    + "ORDER BY diferenca_qtd_avaliacoes DESC", Statement.RETURN_GENERATED_KEYS);

            deleteComentarioByIdComentarioStatement = ComentarioDAO.conexao.prepareStatement(
                    "DELETE FROM avaliacao_comentario WHERE id_comentario_avaliado = ?; "
                    + "DELETE FROM comentario WHERE id_comentario = ? AND id_usuario_proprietario = ?");

            updateComentarioByIdComentarioStatement = ComentarioDAO.conexao.prepareStatement(
                    "UPDATE comentario "
                    + "SET titulo = ?, texto = ?, data_hora_ultima_atualizacao = LOCALTIMESTAMP "
                    + "WHERE id_comentario = ? AND id_usuario_proprietario = ?");
        } catch (SQLException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ComentarioDAO getInstance() {
        try {
            if (instancia == null || conexao == null || conexao.isClosed()) {
                instancia = new ComentarioDAO();
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

    public boolean insertComentario(String titulo, String texto, Integer idUsuarioProprietario, Integer idItemComentado) {
        try {
            insertComentarioStatement.clearParameters();
            insertComentarioStatement.setString(1, titulo);
            insertComentarioStatement.setString(2, texto);
            insertComentarioStatement.setInt(3, idUsuarioProprietario);
            insertComentarioStatement.setInt(4, idItemComentado);
            insertComentarioStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<Comentario> selectAllComentariosByIdItem(Integer idItem) {
        ResultSet resultado = null;
        ArrayList<Comentario> comentarios;
        comentarios = new ArrayList<>();
        try {
            selectAllComentariosByIdItemStatement.clearParameters();
            selectAllComentariosByIdItemStatement.setInt(1, idItem);
            resultado = selectAllComentariosByIdItemStatement.executeQuery();

            while (resultado.next()) {
                Integer idComentario = (Integer) resultado.getInt("id_comentario");
                String titulo = resultado.getString("titulo");
                String texto = resultado.getString("texto");
                String dataHoraCriacao = resultado.getString("data_hora_criacao");
                String dataHoraUltimaAtualizacao = resultado.getString("data_hora_ultima_atualizacao");
                Integer idUsuarioProprietario = (Integer) resultado.getInt("id_usuario_proprietario");
                Integer quantidadeAvaliacoesPositivas = (Integer) resultado.getInt("qtd_avaliacoes_positivas");
                Integer quantidadeAvaliacoesNegativas = (Integer) resultado.getInt("qtd_avaliacoes_negativas");

                comentarios.add(new Comentario(idComentario, titulo, texto, dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuarioProprietario, idItem, quantidadeAvaliacoesPositivas, quantidadeAvaliacoesNegativas));
            }
            return comentarios;
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
        return new ArrayList<>();
    }

    public ArrayList<Comentario> selectAllComentariosNaoAvaliadosByIdUsuarioAndByIdItem(Integer idUsuario, Integer idItem) {
        ResultSet resultado = null;
        ArrayList<Comentario> comentarios;
        comentarios = new ArrayList<>();
        try {
            selectAllComentariosNaoAvaliadosByIdUsuarioAndByIdItemStatement.clearParameters();
            selectAllComentariosNaoAvaliadosByIdUsuarioAndByIdItemStatement.setInt(1, idItem);
            selectAllComentariosNaoAvaliadosByIdUsuarioAndByIdItemStatement.setInt(2, idUsuario);
            resultado = selectAllComentariosNaoAvaliadosByIdUsuarioAndByIdItemStatement.executeQuery();

            while (resultado.next()) {
                Integer idComentario = (Integer) resultado.getInt("id_comentario");
                String titulo = resultado.getString("titulo");
                String texto = resultado.getString("texto");
                String dataHoraCriacao = resultado.getString("data_hora_criacao");
                String dataHoraUltimaAtualizacao = resultado.getString("data_hora_ultima_atualizacao");
                Integer quantidadeAvaliacoesPositivas = (Integer) resultado.getInt("qtd_avaliacoes_positivas");
                Integer quantidadeAvaliacoesNegativas = (Integer) resultado.getInt("qtd_avaliacoes_negativas");

                comentarios.add(new Comentario(idComentario, titulo, texto, dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuario, idItem, quantidadeAvaliacoesPositivas, quantidadeAvaliacoesNegativas));
            }
            return comentarios;
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
        return new ArrayList<>();
    }

    public Comentario selectComentarioByIdComentario(Integer idComentario, Integer idUsuario) {
        ResultSet resultado = null;
        Comentario comentario = null;
        try {
            selectComentarioByIdComentarioStatement.clearParameters();
            selectComentarioByIdComentarioStatement.setInt(1, idComentario);
            selectComentarioByIdComentarioStatement.setInt(2, idUsuario);
            resultado = selectComentarioByIdComentarioStatement.executeQuery();

            while (resultado.next()) {
                String titulo = resultado.getString("titulo");
                String texto = resultado.getString("texto");
                String dataHoraCriacao = resultado.getString("data_hora_criacao");
                String dataHoraUltimaAtualizacao = resultado.getString("data_hora_ultima_atualizacao");
                Integer idItemComentado = (Integer) resultado.getInt("id_item_comentado");
                Integer idUsuarioProprietario = (Integer) resultado.getInt("id_usuario_proprietario");

                comentario = new Comentario(idComentario, titulo, texto, dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuarioProprietario, idItemComentado, 0, 0);
            }
            return comentario;
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

    public boolean deleteComentarioByIdComentario(Integer idComentario, Integer idUsuario) {
        try {
            deleteComentarioByIdComentarioStatement.clearParameters();
            deleteComentarioByIdComentarioStatement.setInt(1, idComentario);
            deleteComentarioByIdComentarioStatement.setInt(2, idComentario);
            deleteComentarioByIdComentarioStatement.setInt(3, idUsuario);
            deleteComentarioByIdComentarioStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateComentarioByIdComentario(Integer idComentario, String titulo, String texto, Integer idUsuarioProprietario) {
        try {
            updateComentarioByIdComentarioStatement.clearParameters();
            updateComentarioByIdComentarioStatement.setString(1, titulo);
            updateComentarioByIdComentarioStatement.setString(2, texto);
            updateComentarioByIdComentarioStatement.setInt(3, idComentario);
            updateComentarioByIdComentarioStatement.setInt(4, idUsuarioProprietario);
            updateComentarioByIdComentarioStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Integer selectCountComentariosByIdUsuario(Integer idUsuario) {
        ResultSet resultado = null;
        try {
            selectCountComentariosByIdUsuarioStatement.clearParameters();
            selectCountComentariosByIdUsuarioStatement.setInt(1, idUsuario);
            resultado = selectCountComentariosByIdUsuarioStatement.executeQuery();

            Integer qtdComentarios = 0;
            if (resultado.next()) {
                qtdComentarios = (Integer) resultado.getInt("qtd_comentarios");
            }
            return qtdComentarios;
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
        return 0;
    }
}
