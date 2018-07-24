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

    private PreparedStatement updateAvaliacaoItemByIdItemAndIdUsuarioStatement;

    private PreparedStatement deleteAvaliacaoItemByIdItemAndIdUsuarioStatement;

    public AvaliacaoItemDAO() {
        try {
            AvaliacaoItemDAO.conexao = Conexao.getConnection();
            insertAvaliacaoItemStatement = AvaliacaoItemDAO.conexao.prepareStatement("INSERT INTO avaliacao_item(avaliacao, id_item_avaliado, id_usuario_proprietario) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            selectAllAvaliacoesByIdItemStatement = AvaliacaoItemDAO.conexao.prepareStatement("SELECT * FROM avaliacao_item WHERE id_item_avaliado = ?", Statement.RETURN_GENERATED_KEYS);
            selectAvaliacaoByIdItemAndByIdUsuarioStatement = AvaliacaoItemDAO.conexao.prepareStatement("SELECT * FROM avaliacao_item WHERE id_item_avaliado = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);

            updateAvaliacaoItemByIdItemAndIdUsuarioStatement = AvaliacaoItemDAO.conexao.prepareStatement("UPDATE avaliacao_item SET avaliacao = ? WHERE id_item_avaliado = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);

            deleteAvaliacaoItemByIdItemAndIdUsuarioStatement = AvaliacaoItemDAO.conexao.prepareStatement("DELETE FROM avaliacao_item WHERE id_item_avaliado = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);
        } catch (URISyntaxException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(AvaliacaoItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static AvaliacaoItemDAO getInstance() {
        if (AvaliacaoItemDAO.instancia == null) {
            AvaliacaoItemDAO.instancia = new AvaliacaoItemDAO();
        }
        return AvaliacaoItemDAO.instancia;
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

            resultado.next();
            Integer idAvaliacaoItem = (Integer) resultado.getInt("id_avaliacao_item");
            Integer avaliacao = (Integer) resultado.getInt("avaliacao");

            return new AvaliacaoItem(idAvaliacaoItem, avaliacao, idItem, idUsuario);
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
