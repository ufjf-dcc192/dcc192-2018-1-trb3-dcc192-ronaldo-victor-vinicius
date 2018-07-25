package br.com.trab3.DAOs;

import br.com.trab3.modelos.AvaliacaoItem;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AvaliacaoItemDAO {

    private static Connection conexao;
    private static AvaliacaoItemDAO instancia = null;

    private PreparedStatement insertAvaliacaoItemStatement;

    private PreparedStatement selectAllAvaliacoesByIdItemStatement;
    private PreparedStatement selectAvaliacaoByIdItemAndByIdUsuarioStatement;
    private PreparedStatement selectCountAvaliacoesPositivasItensByIdUsuarioStatement;
    private PreparedStatement selectCountAvaliacoesNegativasItensByIdUsuarioStatement;
    private PreparedStatement selectCountAvaliacoesPositivasMeusItensByIdUsuarioStatement;
    private PreparedStatement selectCountAvaliacoesNegativasMeusItensByIdUsuarioStatement;

    private PreparedStatement updateAvaliacaoItemByIdItemAndIdUsuarioStatement;

    private PreparedStatement deleteAvaliacaoItemByIdItemAndIdUsuarioStatement;

    public AvaliacaoItemDAO() {
        try {
            AvaliacaoItemDAO.conexao = Conexao.getInstance();
            insertAvaliacaoItemStatement = AvaliacaoItemDAO.conexao.prepareStatement("INSERT INTO avaliacao_item(avaliacao, id_item_avaliado, id_usuario_proprietario) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            selectAllAvaliacoesByIdItemStatement = AvaliacaoItemDAO.conexao.prepareStatement("SELECT * FROM avaliacao_item WHERE id_item_avaliado = ?", Statement.RETURN_GENERATED_KEYS);
            selectAvaliacaoByIdItemAndByIdUsuarioStatement = AvaliacaoItemDAO.conexao.prepareStatement("SELECT * FROM avaliacao_item WHERE id_item_avaliado = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);
            selectCountAvaliacoesPositivasItensByIdUsuarioStatement = AvaliacaoItemDAO.conexao.prepareStatement("SELECT COUNT(*) AS qtd_avaliacoes_positivas FROM avaliacao_item WHERE id_usuario_proprietario = ? AND avaliacao > 0", Statement.RETURN_GENERATED_KEYS);
            selectCountAvaliacoesNegativasItensByIdUsuarioStatement = AvaliacaoItemDAO.conexao.prepareStatement("SELECT COUNT(*) AS qtd_avaliacoes_negativas FROM avaliacao_item WHERE id_usuario_proprietario = ? AND avaliacao < 0", Statement.RETURN_GENERATED_KEYS);
            selectCountAvaliacoesPositivasMeusItensByIdUsuarioStatement = AvaliacaoItemDAO.conexao.prepareStatement(
                    "SELECT count(*) AS qtd_avaliacoes_positivas \n"
                    + "FROM avaliacao_comentario as ac1, comentario as co\n"
                    + "WHERE co.id_usuario_proprietario = ? AND ac1.id_comentario_avaliado = co.id_comentario AND ac1.avaliacao > 0", Statement.RETURN_GENERATED_KEYS);
            selectCountAvaliacoesNegativasMeusItensByIdUsuarioStatement = AvaliacaoItemDAO.conexao.prepareStatement(
                    "SELECT count(*) AS qtd_avaliacoes_negativas \n"
                    + "FROM avaliacao_comentario as ac1, comentario as co\n"
                    + "WHERE co.id_usuario_proprietario = 8 AND ac1.id_comentario_avaliado = co.id_comentario AND ac1.avaliacao < 0", Statement.RETURN_GENERATED_KEYS);

            updateAvaliacaoItemByIdItemAndIdUsuarioStatement = AvaliacaoItemDAO.conexao.prepareStatement("UPDATE avaliacao_item SET avaliacao = ? WHERE id_item_avaliado = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);

            deleteAvaliacaoItemByIdItemAndIdUsuarioStatement = AvaliacaoItemDAO.conexao.prepareStatement("DELETE FROM avaliacao_item WHERE id_item_avaliado = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException ex) {
            Logger.getLogger(AvaliacaoItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static AvaliacaoItemDAO getInstance() {
        try {
            if (instancia == null || conexao == null || conexao.isClosed()) {
                instancia = new AvaliacaoItemDAO();
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

    public boolean insertAvaliacaoItem(Integer avaliacao, Integer idItem, Integer idUsuario) {
        try {
            insertAvaliacaoItemStatement.clearParameters();
            insertAvaliacaoItemStatement.setInt(1, avaliacao);
            insertAvaliacaoItemStatement.setInt(2, idItem);
            insertAvaliacaoItemStatement.setInt(3, idUsuario);
            insertAvaliacaoItemStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateAvaliacaoItemByIdItemAndIdUsuario(Integer avaliacao, Integer idItem, Integer idUsuario) {
        try {
            updateAvaliacaoItemByIdItemAndIdUsuarioStatement.clearParameters();
            updateAvaliacaoItemByIdItemAndIdUsuarioStatement.setInt(1, avaliacao);
            updateAvaliacaoItemByIdItemAndIdUsuarioStatement.setInt(2, idItem);
            updateAvaliacaoItemByIdItemAndIdUsuarioStatement.setInt(3, idUsuario);
            updateAvaliacaoItemByIdItemAndIdUsuarioStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteAvaliacaoItemByIdItemAndIdUsuario(Integer idItem, Integer idUsuario) {
        try {
            deleteAvaliacaoItemByIdItemAndIdUsuarioStatement.clearParameters();
            deleteAvaliacaoItemByIdItemAndIdUsuarioStatement.setInt(1, idItem);
            deleteAvaliacaoItemByIdItemAndIdUsuarioStatement.setInt(2, idUsuario);
            deleteAvaliacaoItemByIdItemAndIdUsuarioStatement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public ArrayList<AvaliacaoItem> selectAllAvaliacoesByIdItem(Integer idItem) {
        ResultSet resultado = null;
        ArrayList<AvaliacaoItem> avaliacoes;
        avaliacoes = new ArrayList<>();
        try {
            selectAllAvaliacoesByIdItemStatement.clearParameters();
            selectAllAvaliacoesByIdItemStatement.setInt(1, idItem);
            resultado = selectAllAvaliacoesByIdItemStatement.executeQuery();

            while (resultado.next()) {
                Integer idAvaliacaoItem = (Integer) resultado.getInt("id_avaliacao_item");
                Integer avaliacao = (Integer) resultado.getInt("avaliacao");
                Integer idUsuarioProprietario = (Integer) resultado.getInt("id_usuario_proprietario");

                avaliacoes.add(new AvaliacaoItem(idAvaliacaoItem, avaliacao, idItem, idUsuarioProprietario));
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

    public AvaliacaoItem selectAvaliacaoByIdItemAndByIdUsuario(Integer idItem, Integer idUsuario) {
        ResultSet resultado = null;
        try {
            selectAvaliacaoByIdItemAndByIdUsuarioStatement.clearParameters();
            selectAvaliacaoByIdItemAndByIdUsuarioStatement.setInt(1, idItem);
            selectAvaliacaoByIdItemAndByIdUsuarioStatement.setInt(2, idUsuario);
            resultado = selectAvaliacaoByIdItemAndByIdUsuarioStatement.executeQuery();

            if (resultado.next()) {
                Integer idAvaliacaoItem = (Integer) resultado.getInt("id_avaliacao_item");
                Integer avaliacao = (Integer) resultado.getInt("avaliacao");

                return new AvaliacaoItem(idAvaliacaoItem, avaliacao, idItem, idUsuario);
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

    public Integer selectCountAvaliacoesPositivasItensByIdUsuario(Integer idUsuarioProprietario) {
        ResultSet resultado = null;
        try {
            selectCountAvaliacoesPositivasItensByIdUsuarioStatement.clearParameters();
            selectCountAvaliacoesPositivasItensByIdUsuarioStatement.setInt(1, idUsuarioProprietario);
            resultado = selectCountAvaliacoesPositivasItensByIdUsuarioStatement.executeQuery();

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

    public Integer selectCountAvaliacoesNegativasItensByIdUsuario(Integer idUsuarioProprietario) {
        ResultSet resultado = null;
        try {
            selectCountAvaliacoesNegativasItensByIdUsuarioStatement.clearParameters();
            selectCountAvaliacoesNegativasItensByIdUsuarioStatement.setInt(1, idUsuarioProprietario);
            resultado = selectCountAvaliacoesNegativasItensByIdUsuarioStatement.executeQuery();

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

    public Integer selectCountAvaliacoesPositivasMeusItensByIdUsuario(Integer idUsuarioProprietario) {
        ResultSet resultado = null;
        try {
            selectCountAvaliacoesPositivasMeusItensByIdUsuarioStatement.clearParameters();
            selectCountAvaliacoesPositivasMeusItensByIdUsuarioStatement.setInt(1, idUsuarioProprietario);
            resultado = selectCountAvaliacoesPositivasMeusItensByIdUsuarioStatement.executeQuery();

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

    public Integer selectCountAvaliacoesNegativasMeusItensByIdUsuario(Integer idUsuarioProprietario) {
        ResultSet resultado = null;
        try {
            selectCountAvaliacoesNegativasMeusItensByIdUsuarioStatement.clearParameters();
            selectCountAvaliacoesNegativasMeusItensByIdUsuarioStatement.setInt(1, idUsuarioProprietario);
            resultado = selectCountAvaliacoesNegativasMeusItensByIdUsuarioStatement.executeQuery();

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
