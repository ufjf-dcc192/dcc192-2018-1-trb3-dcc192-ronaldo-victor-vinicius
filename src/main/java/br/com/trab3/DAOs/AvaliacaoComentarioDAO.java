package br.com.trab3.DAOs;

import br.com.trab3.modelos.AvaliacaoComentario;
import java.net.URISyntaxException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AvaliacaoComentarioDAO {
    private static Connection conexao;
    private static AvaliacaoComentarioDAO instancia = null;
    private PreparedStatement insertAvaliacaoComentarioStatement;
    
    private PreparedStatement selectAvaliacaoByIdComentarioAndByIdUsuarioStatement;
    private PreparedStatement selectAllAvaliacoesByIdComentarioAndByIdUsuarioStatement;
    
    private PreparedStatement updateAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement;
    
    private PreparedStatement deleteAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement;
    
    public AvaliacaoComentarioDAO() {
        try {
            AvaliacaoComentarioDAO.conexao = Conexao.getConnection();
            
            insertAvaliacaoComentarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("INSERT INTO avaliacao_comentario(avaliacao, id_comentario_avaliado, id_usuario_proprietario) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            selectAvaliacaoByIdComentarioAndByIdUsuarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("SELECT * FROM avaliacao_comentario WHERE id_comentario_avaliado = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);
            selectAllAvaliacoesByIdComentarioAndByIdUsuarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("SELECT * FROM avaliacao_comentario WHERE id_comentario_avaliado = ?", Statement.RETURN_GENERATED_KEYS);
            
            updateAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("UPDATE avaliacao_comentario SET avaliacao = ? WHERE id_comentario_avaliado = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);
            
            deleteAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("DELETE FROM avaliacao_comentario WHERE id_comentario_avaliado = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);
        } catch (URISyntaxException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AvaliacaoComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static AvaliacaoComentarioDAO getInstance() {
        if (AvaliacaoComentarioDAO.instancia == null) {
            AvaliacaoComentarioDAO.instancia = new AvaliacaoComentarioDAO();
        }
        return AvaliacaoComentarioDAO.instancia;
    }
    
    public boolean insertAvaliacaoComentario(Integer avaliacao, Integer idComentario, Integer idUsuario) {
        try {
            insertAvaliacaoComentarioStatement.clearParameters();
            insertAvaliacaoComentarioStatement.setInt(1, avaliacao);
            insertAvaliacaoComentarioStatement.setInt(2, idComentario);
            insertAvaliacaoComentarioStatement.setInt(3, idUsuario);
            insertAvaliacaoComentarioStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean updateAvaliacaoComentarioByIdComentarioAndIdUsuario(Integer avaliacao, Integer idComentario, Integer idUsuario) {
        try {
            updateAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement.clearParameters();
            updateAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement.setInt(1, avaliacao);
            updateAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement.setInt(2, idComentario);
            updateAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement.setInt(3, idUsuario);
            updateAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean deleteAvaliacaoComentarioByIdComentarioAndIdUsuario(Integer idComentario, Integer idUsuario) {
        try {
            deleteAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement.clearParameters();
            deleteAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement.setInt(1, idComentario);
            deleteAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement.setInt(2, idUsuario);
            deleteAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public AvaliacaoComentario selectAvaliacaoByIdComentarioAndByIdUsuario(Integer idComentario, Integer idUsuario) {
        ResultSet resultado = null;
        try {
            selectAvaliacaoByIdComentarioAndByIdUsuarioStatement.clearParameters();
            selectAvaliacaoByIdComentarioAndByIdUsuarioStatement.setInt(1, idComentario);
            selectAvaliacaoByIdComentarioAndByIdUsuarioStatement.setInt(2, idUsuario);
            resultado = selectAvaliacaoByIdComentarioAndByIdUsuarioStatement.executeQuery();
            resultado.next();
            
            Integer idAvaliacaoItem = (Integer) resultado.getInt("id_avaliacao_comentario");
            Integer avaliacao = (Integer) resultado.getInt("avaliacao");
            
            return new AvaliacaoComentario(idAvaliacaoItem, avaliacao, idComentario, idUsuario);
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
    
    public ArrayList<AvaliacaoComentario> selectAllAvaliacoesByIdComentario(Integer idComentario) {
        ResultSet resultado = null;
        ArrayList<AvaliacaoComentario> avaliacoes;
        avaliacoes = new ArrayList<>();
        try {
            selectAllAvaliacoesByIdComentarioAndByIdUsuarioStatement.clearParameters();
            selectAllAvaliacoesByIdComentarioAndByIdUsuarioStatement.setInt(1, idComentario);
            resultado = selectAllAvaliacoesByIdComentarioAndByIdUsuarioStatement.executeQuery();

            while (resultado.next()) {
                Integer idAvaliacaoComentario = (Integer) resultado.getInt("id_avaliacao_comentario");
                Integer avaliacao = (Integer) resultado.getInt("avaliacao");
                Integer idUsuarioProprietario = (Integer) resultado.getInt("id_usuario_proprietario");

                avaliacoes.add(new AvaliacaoComentario(idAvaliacaoComentario, avaliacao, idComentario, idUsuarioProprietario));
            }
            return avaliacoes;
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
}
