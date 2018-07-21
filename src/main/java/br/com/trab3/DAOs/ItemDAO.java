package br.com.trab3.DAOs;

import br.com.trab3.modelos.Item;
import br.com.trab3.modelos.Link;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItemDAO {

    private static Connection conexao;
    private static ItemDAO instancia = null;
    
    private PreparedStatement selectAllItensStatement;
    private PreparedStatement selectItemByIdStatement;
    private PreparedStatement selectAllLinksByIdItemStatement;
    
    private PreparedStatement insertItemStatement;
    private PreparedStatement insertLinkStatement;
    
    private PreparedStatement deleteItemByIdStatement;
    private PreparedStatement deleteAllLinksByIdItemStatement;
    
    private PreparedStatement updateItemByIdStatement;

    public ItemDAO() {
        try {
            ItemDAO.conexao = Conexao.getConnection();

            insertItemStatement = ItemDAO.conexao.prepareStatement("INSERT INTO item(titulo, descricao, data_hora_criacao, data_hora_ultima_atualizacao, id_usuario_proprietario) VALUES(?, ?,  LOCALTIMESTAMP,  LOCALTIMESTAMP, ?)", Statement.RETURN_GENERATED_KEYS);
            insertLinkStatement = ItemDAO.conexao.prepareStatement("INSERT INTO link(link, id_item_relacionado) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);

            selectItemByIdStatement = ItemDAO.conexao.prepareStatement("SELECT * FROM item WHERE id_item = ? AND id_usuario_proprietario = ?", Statement.RETURN_GENERATED_KEYS);
            selectAllItensStatement = ItemDAO.conexao.prepareStatement("SELECT i.id_item, i.titulo, i.descricao, i.data_hora_criacao, i.data_hora_ultima_atualizacao, i.id_usuario_proprietario, count(l.id_link) AS qtd_links FROM item as i, link as l WHERE i.id_usuario_proprietario = ? AND l.id_item_relacionado = i.id_item GROUP BY i.id_item", Statement.RETURN_GENERATED_KEYS);
            selectAllLinksByIdItemStatement = ItemDAO.conexao.prepareStatement("SELECT * FROM link WHERE id_item_relacionado = ?", Statement.RETURN_GENERATED_KEYS);

            deleteItemByIdStatement = ItemDAO.conexao.prepareStatement("DELETE FROM link WHERE id_item_relacionado = ?; DELETE FROM item WHERE id_item = ? AND id_usuario_proprietario = ?");
            deleteAllLinksByIdItemStatement = ItemDAO.conexao.prepareStatement("DELETE FROM link WHERE id_item_relacionado = ?");

            updateItemByIdStatement = ItemDAO.conexao.prepareStatement("UPDATE item SET titulo = ?, descricao = ?, data_hora_ultima_atualizacao = LOCALTIMESTAMP WHERE id_item = ? AND id_usuario_proprietario = ?");
        } catch (URISyntaxException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ItemDAO getInstance() {
        if (ItemDAO.instancia == null) {
            ItemDAO.instancia = new ItemDAO();
        }
        return ItemDAO.instancia;
    }

    public ArrayList<Link> selectAllLinksByIdItem(Integer idItem) {
        ResultSet resultado = null;
        ArrayList<Link> links;
        links = new ArrayList<>();
        try {
            selectAllLinksByIdItemStatement.clearParameters();
            selectAllLinksByIdItemStatement.setInt(1, idItem);
            resultado = selectAllLinksByIdItemStatement.executeQuery();

            while (resultado.next()) {
                Integer idLink = resultado.getInt("id_item_relacionado");
                String link = resultado.getString("link");

                links.add(new Link(idLink, link, idItem));
            }
            return links;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
//                if(insertItemStatement != null) insertItemStatement.close();
//                if(conexao != null) conexao.close();
            } catch (SQLException ex) {
            }
        }
        return null;
    }

    public boolean insertAllLinksByIdItem(Integer idItem, ArrayList<String> links) {
        try {
            insertLinkStatement.clearParameters();
            for (String link : links) {
                insertLinkStatement.setString(1, link);
                insertLinkStatement.setInt(2, idItem);
                insertLinkStatement.addBatch();
            }
            insertLinkStatement.executeBatch();
            insertLinkStatement.clearBatch();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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

                insertAllLinksByIdItem(idItem, links);

                item = new Item(idItem, titulo, descricao, selectAllLinksByIdItem(idItem), dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuarioProprietario);
            }
            return item;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
//                if(insertItemStatement != null) insertItemStatement.close();
//                if(conexao != null) conexao.close();
            } catch (SQLException ex) {
            }
        }
        return null;
    }

    public ArrayList<Item> selectAllItens(Integer idUsuarioProprietario) {
        ResultSet resultado = null;
        ArrayList<Item> itens;
        itens = new ArrayList<>();
        try {
            selectAllItensStatement.clearParameters();
            selectAllItensStatement.setInt(1, idUsuarioProprietario);
            resultado = selectAllItensStatement.executeQuery();

            while (resultado.next()) {
                Integer idItem = (Integer) resultado.getInt("id_item");
                String titulo = resultado.getString("titulo");
                String descricao = resultado.getString("descricao");
                String dataHoraCriacao = resultado.getString("data_hora_criacao");
                String dataHoraUltimaAtualizacao = resultado.getString("data_hora_ultima_atualizacao");
                Integer quantidadeLinks = resultado.getInt("qtd_links");

                Item item = new Item(idItem, titulo, descricao, dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuarioProprietario);
                item.setQuantidadeLinks(quantidadeLinks);
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
//                if(insertItemStatement != null) insertItemStatement.close();
//                if(conexao != null) conexao.close();
            } catch (SQLException ex) {
            }
        }
        return null;
    }

    public boolean deleteItemById(Integer idItem, Integer idUsuarioProprietario) {
        try {
            deleteItemByIdStatement.clearParameters();
            deleteItemByIdStatement.setInt(1, idItem);
            deleteItemByIdStatement.setInt(2, idItem);
            deleteItemByIdStatement.setInt(3, idUsuarioProprietario);
            deleteItemByIdStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean deleteAllLinksByIdItem(Integer idItem) {
        try {
            deleteAllLinksByIdItemStatement.clearParameters();
            deleteAllLinksByIdItemStatement.setInt(1, idItem);
            deleteAllLinksByIdItemStatement.executeUpdate();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Item selectItemById(Integer idItem, Integer idUsuarioProprietario) {
        ResultSet resultado = null;
        Item item = null;
        try {

            selectItemByIdStatement.clearParameters();
            selectItemByIdStatement.setInt(1, (int) idItem);
            selectItemByIdStatement.setInt(2, (int) idUsuarioProprietario);
            resultado = selectItemByIdStatement.executeQuery();

            while (resultado.next()) {
                String titulo = resultado.getString("titulo");
                String descricao = resultado.getString("descricao");
                String dataHoraCriacao = resultado.getString("data_hora_criacao");
                String dataHoraUltimaAtualizacao = resultado.getString("data_hora_ultima_atualizacao");

                item = new Item(idItem, titulo, descricao, selectAllLinksByIdItem(idItem), dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuarioProprietario);
            }
            return item;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
//                if(insertItemStatement != null) insertItemStatement.close();
//                if(conexao != null) conexao.close();
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

            deleteAllLinksByIdItem(idItem);
            insertAllLinksByIdItem(idItem, links);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
