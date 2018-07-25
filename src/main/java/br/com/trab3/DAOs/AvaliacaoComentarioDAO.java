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
    private PreparedStatement selectCountAvaliacoesPositivasComentariosByIdUsuarioStatement;
    private PreparedStatement selectCountAvaliacoesNegativasComentariosByIdUsuarioStatement;
    private PreparedStatement selectCountAvaliacoesPositivasMeusComentariosByIdUsuarioStatement;
    private PreparedStatement selectCountAvaliacoesNegativasMeusComentariosByIdUsuarioStatement;
    
    private PreparedStatement updateAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement;
    
    private PreparedStatement deleteAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement;
    
    public AvaliacaoComentarioDAO() {
        try {
            AvaliacaoComentarioDAO.conexao = Conexao.getInstance();
            
            insertAvaliacaoComentarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("INSERT INTO avaliacao_comentario(avaliacao, id_comentario_avaliado, id_usuario_proprietario) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            selectAvaliacaoByIdComentarioAndByIdUsuarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("SELECT * FROM avaliacao_comentario WHERE id_comentario_avaliado = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);
            selectAllAvaliacoesByIdComentarioAndByIdUsuarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("SELECT * FROM avaliacao_comentario WHERE id_comentario_avaliado = ?", Statement.RETURN_GENERATED_KEYS);
            selectCountAvaliacoesPositivasComentariosByIdUsuarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("SELECT COUNT(*) AS qtd_avaliacoes_positivas FROM avaliacao_comentario WHERE id_usuario_proprietario = ? AND avaliacao > 0", Statement.RETURN_GENERATED_KEYS);
            selectCountAvaliacoesNegativasComentariosByIdUsuarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("SELECT COUNT(*) AS qtd_avaliacoes_negativas FROM avaliacao_comentario WHERE id_usuario_proprietario = ? AND avaliacao < 0", Statement.RETURN_GENERATED_KEYS);
            selectCountAvaliacoesPositivasMeusComentariosByIdUsuarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("SELECT COUNT(*) AS qtd_avaliacoes_positivas FROM avaliacao_comentario WHERE id_usuario_proprietario = ? AND avaliacao > 0", Statement.RETURN_GENERATED_KEYS);
            selectCountAvaliacoesNegativasMeusComentariosByIdUsuarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("SELECT COUNT(*) AS qtd_avaliacoes_negativas FROM avaliacao_comentario WHERE id_usuario_proprietario = ? AND avaliacao < 0", Statement.RETURN_GENERATED_KEYS);
            
            updateAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("UPDATE avaliacao_comentario SET avaliacao = ? WHERE id_comentario_avaliado = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);
            
            deleteAvaliacaoComentarioByIdComentarioAndIdUsuarioStatement = AvaliacaoComentarioDAO.conexao.prepareStatement("DELETE FROM avaliacao_comentario WHERE id_comentario_avaliado = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static AvaliacaoComentarioDAO getInstance() {
        try {
            if (instancia == null || conexao == null || conexao.isClosed()) {
                instancia = new AvaliacaoComentarioDAO();
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
            
            if (resultado.next()) {
                Integer idAvaliacaoItem = (Integer) resultado.getInt("id_avaliacao_comentario");
                Integer avaliacao = (Integer) resultado.getInt("avaliacao");

                return new AvaliacaoComentario(idAvaliacaoItem, avaliacao, idComentario, idUsuario);
            }
            return null;
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
    
    public Integer selectCountAvaliacoesPositivasComentariosByIdUsuario(Integer idUsuarioProprietario) {
        ResultSet resultado = null;
        try {
            selectCountAvaliacoesPositivasComentariosByIdUsuarioStatement.clearParameters();
            selectCountAvaliacoesPositivasComentariosByIdUsuarioStatement.setInt(1, idUsuarioProprietario);
            resultado = selectCountAvaliacoesPositivasComentariosByIdUsuarioStatement.executeQuery();
            
            Integer qtdAvaliacoes = 0;
            if (resultado.next()) {
                qtdAvaliacoes = (Integer) resultado.getInt("qtd_avaliacoes_positivas");
            }
            return qtdAvaliacoes;
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
    
    public Integer selectCountAvaliacoesNegativasComentariosByIdUsuario(Integer idUsuarioProprietario) {
        ResultSet resultado = null;
        try {
            selectCountAvaliacoesNegativasComentariosByIdUsuarioStatement.clearParameters();
            selectCountAvaliacoesNegativasComentariosByIdUsuarioStatement.setInt(1, idUsuarioProprietario);
            resultado = selectCountAvaliacoesNegativasComentariosByIdUsuarioStatement.executeQuery();
            
            Integer qtdAvaliacoes = 0;
            if (resultado.next()) {
                qtdAvaliacoes = (Integer) resultado.getInt("qtd_avaliacoes_negativas");
            }
            return qtdAvaliacoes;
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
    
    public Integer selectCountAvaliacoesPositivasMeusComentariosByIdUsuario(Integer idUsuarioProprietario) {
        ResultSet resultado = null;
        try {
            selectCountAvaliacoesPositivasMeusComentariosByIdUsuarioStatement.clearParameters();
            selectCountAvaliacoesPositivasMeusComentariosByIdUsuarioStatement.setInt(1, idUsuarioProprietario);
            resultado = selectCountAvaliacoesPositivasMeusComentariosByIdUsuarioStatement.executeQuery();
            
            Integer qtdAvaliacoes = 0;
            if (resultado.next()) {
                qtdAvaliacoes = (Integer) resultado.getInt("qtd_avaliacoes_positivas");
            }
            return qtdAvaliacoes;
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
    
    public Integer selectCountAvaliacoesNegativasMeusComentariosByIdUsuario(Integer idUsuarioProprietario) {
        ResultSet resultado = null;
        try {
            selectCountAvaliacoesNegativasMeusComentariosByIdUsuarioStatement.clearParameters();
            selectCountAvaliacoesNegativasMeusComentariosByIdUsuarioStatement.setInt(1, idUsuarioProprietario);
            resultado = selectCountAvaliacoesNegativasMeusComentariosByIdUsuarioStatement.executeQuery();
            
            Integer qtdAvaliacoes = 0;
            if (resultado.next()) {
                qtdAvaliacoes = (Integer) resultado.getInt("qtd_avaliacoes_negativas");
            }
            return qtdAvaliacoes;
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
