package br.com.trab3.DAOs;

import br.com.trab3.modelos.Item;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemDAO {

    private static Connection conexao;
    private static ItemDAO instancia = null;

    private PreparedStatement insertItemStatement;

    private PreparedStatement selectAllItensByIdUsuarioStatement;
    private PreparedStatement selectItemByIdStatement;
    private PreparedStatement selectAllItensOrderByDataHoraCriacaoStatement;
    private PreparedStatement selectAllItensOrderByDataHoraUltimaAtualizacaoStatement;
    private PreparedStatement selectAllItensOrderByQtdTotalDeAvaliacoesStatement;
    private PreparedStatement selectAllItensOrderByMelhorAvaliacaoStatement;

    private PreparedStatement deleteItemByIdStatement;

    private PreparedStatement updateItemByIdStatement;

    public ItemDAO() {
        try {
            ItemDAO.conexao = Conexao.getInstance();

            insertItemStatement = ItemDAO.conexao.prepareStatement("INSERT INTO item(titulo, descricao, data_hora_criacao, data_hora_ultima_atualizacao, id_usuario_proprietario) VALUES(?, ?,  LOCALTIMESTAMP,  LOCALTIMESTAMP, ?)", Statement.RETURN_GENERATED_KEYS);

            selectItemByIdStatement = ItemDAO.conexao.prepareStatement("SELECT * FROM item WHERE id_item = ?", Statement.RETURN_GENERATED_KEYS);
            selectAllItensByIdUsuarioStatement = ItemDAO.conexao.prepareStatement(
                    "SELECT *, \n"
                    + "(SELECT count(*) FROM link as l WHERE l.id_item_relacionado = i.id_item) AS qtd_links,\n"
                    + "(SELECT count(*) FROM comentario as co WHERE co.id_item_comentado = i.id_item) AS qtd_comentarios,\n"
                    + "(SELECT count(*) FROM avaliacao_item as ai1 WHERE ai1.id_item_avaliado = i.id_item AND ai1.avaliacao > 0) AS qtd_avaliacoes_positivas,\n"
                    + "(SELECT count(*) FROM avaliacao_item as ai2 WHERE ai2.id_item_avaliado = i.id_item AND ai2.avaliacao < 0) AS qtd_avaliacoes_negativas\n"
                    + "FROM item as i\n"
                    + "WHERE i.id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);
            selectAllItensOrderByDataHoraCriacaoStatement = ItemDAO.conexao.prepareStatement("SELECT *, \n"
                    + "(pn.qtd_avaliacoes_positivas + pn.qtd_avaliacoes_negativas) AS qtd_total_avaliacoes,\n"
                    + "(pn.qtd_avaliacoes_positivas - pn.qtd_avaliacoes_negativas) AS diferenca_qtd_avaliacoes\n"
                    + "FROM (SELECT *, \n"
                    + "(SELECT count(*) FROM avaliacao_item as ai1 WHERE ai1.id_item_avaliado = i.id_item AND ai1.avaliacao > 0) AS qtd_avaliacoes_positivas,\n"
                    + "(SELECT count(*) FROM avaliacao_item as ai2 WHERE ai2.id_item_avaliado = i.id_item AND ai2.avaliacao < 0) AS qtd_avaliacoes_negativas\n"
                    + "FROM item as i) AS pn\n"
                    + "ORDER BY pn.data_hora_criacao ASC", Statement.RETURN_GENERATED_KEYS);
            selectAllItensOrderByDataHoraUltimaAtualizacaoStatement = ItemDAO.conexao.prepareStatement("SELECT *, \n"
                    + "(pn.qtd_avaliacoes_positivas + pn.qtd_avaliacoes_negativas) AS qtd_total_avaliacoes,\n"
                    + "(pn.qtd_avaliacoes_positivas - pn.qtd_avaliacoes_negativas) AS diferenca_qtd_avaliacoes\n"
                    + "FROM (SELECT *, \n"
                    + "(SELECT count(*) FROM avaliacao_item as ai1 WHERE ai1.id_item_avaliado = i.id_item AND ai1.avaliacao > 0) AS qtd_avaliacoes_positivas,\n"
                    + "(SELECT count(*) FROM avaliacao_item as ai2 WHERE ai2.id_item_avaliado = i.id_item AND ai2.avaliacao < 0) AS qtd_avaliacoes_negativas\n"
                    + "FROM item as i) AS pn\n"
                    + "ORDER BY pn.data_hora_ultima_atualizacao DESC", Statement.RETURN_GENERATED_KEYS);
            selectAllItensOrderByQtdTotalDeAvaliacoesStatement = ItemDAO.conexao.prepareStatement("SELECT *, \n"
                    + "(pn.qtd_avaliacoes_positivas + pn.qtd_avaliacoes_negativas) AS qtd_total_avaliacoes,\n"
                    + "(pn.qtd_avaliacoes_positivas - pn.qtd_avaliacoes_negativas) AS diferenca_qtd_avaliacoes\n"
                    + "FROM (SELECT *, \n"
                    + "(SELECT count(*) FROM avaliacao_item as ai1 WHERE ai1.id_item_avaliado = i.id_item AND ai1.avaliacao > 0) AS qtd_avaliacoes_positivas,\n"
                    + "(SELECT count(*) FROM avaliacao_item as ai2 WHERE ai2.id_item_avaliado = i.id_item AND ai2.avaliacao < 0) AS qtd_avaliacoes_negativas\n"
                    + "FROM item as i) AS pn\n"
                    + "ORDER BY qtd_total_avaliacoes DESC, diferenca_qtd_avaliacoes DESC", Statement.RETURN_GENERATED_KEYS);
            selectAllItensOrderByMelhorAvaliacaoStatement = ItemDAO.conexao.prepareStatement("SELECT *, \n"
                    + "(pn.qtd_avaliacoes_positivas + pn.qtd_avaliacoes_negativas) AS qtd_total_avaliacoes,\n"
                    + "(pn.qtd_avaliacoes_positivas - pn.qtd_avaliacoes_negativas) AS diferenca_qtd_avaliacoes\n"
                    + "FROM (SELECT *, \n"
                    + "(SELECT count(*) FROM avaliacao_item as ai1 WHERE ai1.id_item_avaliado = i.id_item AND ai1.avaliacao > 0) AS qtd_avaliacoes_positivas,\n"
                    + "(SELECT count(*) FROM avaliacao_item as ai2 WHERE ai2.id_item_avaliado = i.id_item AND ai2.avaliacao < 0) AS qtd_avaliacoes_negativas\n"
                    + "FROM item as i) AS pn\n"
                    + "ORDER BY diferenca_qtd_avaliacoes DESC, qtd_total_avaliacoes DESC", Statement.RETURN_GENERATED_KEYS);

            deleteItemByIdStatement = ItemDAO.conexao.prepareStatement(
                    "DELETE FROM avaliacao_item WHERE id_item_avaliado = ?; "
                    + "DELETE FROM avaliacao_comentario WHERE id_comentario_avaliado IN (SELECT id_comentario FROM comentario WHERE id_item_comentado = ?); "
                    + "DELETE FROM comentario WHERE id_item_comentado = ?; "
                    + "DELETE FROM link WHERE id_item_relacionado = ?; "
                    + "DELETE FROM item WHERE id_item = ? AND id_usuario_proprietario = ?");

            updateItemByIdStatement = ItemDAO.conexao.prepareStatement("UPDATE item SET titulo = ?, descricao = ?, data_hora_ultima_atualizacao = LOCALTIMESTAMP WHERE id_item = ? AND id_usuario_proprietario = ?");
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ItemDAO getInstance() {
        try {
            if (instancia == null || conexao == null || conexao.isClosed()) {
                instancia = new ItemDAO();
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

    public Item insertItem(String titulo, String descricao, ArrayList<String> links, Integer idUsuarioProprietario) {
        ResultSet resultado = null;
        Item item = null;
        try {
            insertItemStatement.clearParameters();
            insertItemStatement.setString(1, titulo);
            insertItemStatement.setString(2, descricao);
            insertItemStatement.setInt(3, idUsuarioProprietario);
            insertItemStatement.executeUpdate();

            resultado = insertItemStatement.getGeneratedKeys();
            if (resultado != null && resultado.next()) {
                Integer idItem = (Integer) resultado.getInt("id_item");
                titulo = resultado.getString("titulo");
                descricao = resultado.getString("descricao");
                String dataHoraCriacao = resultado.getString("data_hora_criacao");
                String dataHoraUltimaAtualizacao = resultado.getString("data_hora_ultima_atualizacao");

                LinkDAO.getInstance().insertAllLinksByIdItem(idItem, links);

                item = new Item(idItem, titulo, descricao, dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuarioProprietario, LinkDAO.getInstance().selectAllLinksByIdItem(idItem), new ArrayList<>(), new ArrayList<>());
            }
            return item;
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

    public ArrayList<Item> selectAllItensByIdUsuario(Integer idUsuarioProprietario) {
        ResultSet resultado = null;
        ArrayList<Item> itens;
        itens = new ArrayList<>();
        try {
            selectAllItensByIdUsuarioStatement.clearParameters();
            selectAllItensByIdUsuarioStatement.setInt(1, idUsuarioProprietario);
            resultado = selectAllItensByIdUsuarioStatement.executeQuery();

            while (resultado.next()) {
                Integer idItem = (Integer) resultado.getInt("id_item");
                String titulo = resultado.getString("titulo");
                String descricao = resultado.getString("descricao");
                String dataHoraCriacao = resultado.getString("data_hora_criacao");
                String dataHoraUltimaAtualizacao = resultado.getString("data_hora_ultima_atualizacao");
                Integer quantidadeLinks = resultado.getInt("qtd_links");
                Integer quantidadeComentarios = resultado.getInt("qtd_comentarios");
                Integer quantidadeAvaliacoesPositivas = resultado.getInt("qtd_avaliacoes_positivas");
                Integer quantidadeAvaliacoesNegativas = resultado.getInt("qtd_avaliacoes_negativas");
//qtd_comentarios, qtd_avaliacoes_positivas, qtd_avaliacoes_negativas
                Item item = new Item(idItem, titulo, descricao, dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuarioProprietario, quantidadeLinks, quantidadeComentarios, quantidadeAvaliacoesPositivas, quantidadeAvaliacoesNegativas);
                itens.add(item);
            }
            return itens;
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

    public ArrayList<Item> selectAllItensOrderByDataHoraCriacao() {
        ResultSet resultado = null;
        ArrayList<Item> itens;
        itens = new ArrayList<>();
        try {
            selectAllItensOrderByDataHoraCriacaoStatement.clearParameters();
            resultado = selectAllItensOrderByDataHoraCriacaoStatement.executeQuery();

            while (resultado.next()) {
                Integer idItem = (Integer) resultado.getInt("id_item");
                String titulo = resultado.getString("titulo");
                String descricao = resultado.getString("descricao");
                String dataHoraCriacao = resultado.getString("data_hora_criacao");
                String dataHoraUltimaAtualizacao = resultado.getString("data_hora_ultima_atualizacao");
                Integer idUsuarioProprietario = (Integer) resultado.getInt("id_usuario_proprietario");
                Integer quantidadeAvaliacoesPositivas = resultado.getInt("qtd_avaliacoes_positivas");
                Integer quantidadeAvaliacoesNegativas = resultado.getInt("qtd_avaliacoes_negativas");

                Item item = new Item(idItem, titulo, descricao, dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuarioProprietario, 0, 0, quantidadeAvaliacoesPositivas, quantidadeAvaliacoesNegativas);
                itens.add(item);
            }
            return itens;
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

    public ArrayList<Item> selectAllItensOrderByDataHoraUltimaAtualizacao() {
        ResultSet resultado = null;
        ArrayList<Item> itens;
        itens = new ArrayList<>();
        try {
            selectAllItensOrderByDataHoraUltimaAtualizacaoStatement.clearParameters();
            resultado = selectAllItensOrderByDataHoraUltimaAtualizacaoStatement.executeQuery();

            while (resultado.next()) {
                Integer idItem = (Integer) resultado.getInt("id_item");
                String titulo = resultado.getString("titulo");
                String descricao = resultado.getString("descricao");
                String dataHoraCriacao = resultado.getString("data_hora_criacao");
                String dataHoraUltimaAtualizacao = resultado.getString("data_hora_ultima_atualizacao");
                Integer idUsuarioProprietario = (Integer) resultado.getInt("id_usuario_proprietario");
                Integer quantidadeAvaliacoesPositivas = resultado.getInt("qtd_avaliacoes_positivas");
                Integer quantidadeAvaliacoesNegativas = resultado.getInt("qtd_avaliacoes_negativas");

                Item item = new Item(idItem, titulo, descricao, dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuarioProprietario, 0, 0, quantidadeAvaliacoesPositivas, quantidadeAvaliacoesNegativas);
                itens.add(item);
            }
            return itens;
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

    public ArrayList<Item> selectAllItensOrderByQtdTotalDeAvaliacoes() {
        ResultSet resultado = null;
        ArrayList<Item> itens;
        itens = new ArrayList<>();
        try {
            selectAllItensOrderByQtdTotalDeAvaliacoesStatement.clearParameters();
            resultado = selectAllItensOrderByQtdTotalDeAvaliacoesStatement.executeQuery();

            while (resultado.next()) {
                Integer idItem = (Integer) resultado.getInt("id_item");
                String titulo = resultado.getString("titulo");
                String descricao = resultado.getString("descricao");
                String dataHoraCriacao = resultado.getString("data_hora_criacao");
                String dataHoraUltimaAtualizacao = resultado.getString("data_hora_ultima_atualizacao");
                Integer idUsuarioProprietario = (Integer) resultado.getInt("id_usuario_proprietario");
                Integer quantidadeAvaliacoesPositivas = resultado.getInt("qtd_avaliacoes_positivas");
                Integer quantidadeAvaliacoesNegativas = resultado.getInt("qtd_avaliacoes_negativas");

                Item item = new Item(idItem, titulo, descricao, dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuarioProprietario, 0, 0, quantidadeAvaliacoesPositivas, quantidadeAvaliacoesNegativas);
                itens.add(item);
            }
            return itens;
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

    public ArrayList<Item> selectAllItensOrderByMelhorAvaliacao() {
        ResultSet resultado = null;
        ArrayList<Item> itens;
        itens = new ArrayList<>();
        try {
            selectAllItensOrderByMelhorAvaliacaoStatement.clearParameters();
            resultado = selectAllItensOrderByMelhorAvaliacaoStatement.executeQuery();

            while (resultado.next()) {
                Integer idItem = (Integer) resultado.getInt("id_item");
                String titulo = resultado.getString("titulo");
                String descricao = resultado.getString("descricao");
                String dataHoraCriacao = resultado.getString("data_hora_criacao");
                String dataHoraUltimaAtualizacao = resultado.getString("data_hora_ultima_atualizacao");
                Integer idUsuarioProprietario = (Integer) resultado.getInt("id_usuario_proprietario");
                Integer quantidadeAvaliacoesPositivas = resultado.getInt("qtd_avaliacoes_positivas");
                Integer quantidadeAvaliacoesNegativas = resultado.getInt("qtd_avaliacoes_negativas");

                Item item = new Item(idItem, titulo, descricao, dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuarioProprietario, 0, 0, quantidadeAvaliacoesPositivas, quantidadeAvaliacoesNegativas);
                itens.add(item);
            }
            return itens;
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

    public boolean deleteItemById(Integer idItem, Integer idUsuarioProprietario) {
        try {
            deleteItemByIdStatement.clearParameters();
            deleteItemByIdStatement.setInt(1, idItem);
            deleteItemByIdStatement.setInt(2, idItem);
            deleteItemByIdStatement.setInt(3, idItem);
            deleteItemByIdStatement.setInt(4, idItem);
            deleteItemByIdStatement.setInt(5, idItem);
            deleteItemByIdStatement.setInt(6, idUsuarioProprietario);
            deleteItemByIdStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Item selectItemById(Integer idItem) {
        ResultSet resultado = null;
        Item item = null;
        try {

            selectItemByIdStatement.clearParameters();
            selectItemByIdStatement.setInt(1, (int) idItem);
            resultado = selectItemByIdStatement.executeQuery();

            while (resultado.next()) {
                String titulo = resultado.getString("titulo");
                String descricao = resultado.getString("descricao");
                String dataHoraCriacao = resultado.getString("data_hora_criacao");
                String dataHoraUltimaAtualizacao = resultado.getString("data_hora_ultima_atualizacao");
                Integer idUsuarioProprietario = (Integer) Integer.parseInt(resultado.getString("id_usuario_proprietario"));

                item = new Item(idItem, titulo, descricao, dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuarioProprietario, LinkDAO.getInstance().selectAllLinksByIdItem(idItem), ComentarioDAO.getInstance().selectAllComentariosByIdItem(idItem), AvaliacaoItemDAO.getInstance().selectAllAvaliacoesByIdItem(idItem));
            }
            return item;
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

    public boolean updateItemById(Integer idItem, String titulo, String descricao, Integer idUsuario, ArrayList<String> links) {
        try {
            updateItemByIdStatement.clearParameters();
            updateItemByIdStatement.setString(1, titulo);
            updateItemByIdStatement.setString(2, descricao);
            updateItemByIdStatement.setInt(3, idItem);
            updateItemByIdStatement.setInt(4, idUsuario);
            updateItemByIdStatement.executeUpdate();

            LinkDAO.getInstance().deleteAllLinksByIdItem(idItem);
            LinkDAO.getInstance().insertAllLinksByIdItem(idItem, links);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
