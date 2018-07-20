package br.com.trab3.DAOs;

import br.com.trab3.modelos.Item;
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
    private PreparedStatement insertStatement;
    private PreparedStatement insertLinkStatement;

    public ItemDAO() {
        try {
            ItemDAO.conexao = Conexao.getConnection();

            insertStatement = ItemDAO.conexao.prepareStatement("INSERT INTO item(titulo, descricao, data_hora_criacao, data_hora_ultima_atualizacao, id_usuario_proprietario) VALUES(?, ?,  LOCALTIMESTAMP,  LOCALTIMESTAMP, ?)", Statement.RETURN_GENERATED_KEYS);
            insertLinkStatement = ItemDAO.conexao.prepareStatement("INSERT INTO link(link, id_item_relacionado) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
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

    public Item criarItem(String titulo, String descricao, ArrayList<String> links, Integer idUsuarioProprietario) {
        ResultSet resultado = null;
        Item item = null;
        try {
            insertStatement.clearParameters();
            insertStatement.setString(1, titulo);
            insertStatement.setString(2, descricao);
            insertStatement.setInt(3, idUsuarioProprietario);
            insertStatement.executeUpdate();
            
            resultado = insertStatement.getGeneratedKeys();
            if (resultado != null && resultado.next()) {
                Integer idItem = (Integer) resultado.getInt("id_item");
                titulo = resultado.getString("titulo");
                descricao = resultado.getString("descricao");
                String dataHoraCriacao = resultado.getString("data_hora_criacao");
                String dataHoraUltimaAtualizacao = resultado.getString("data_hora_ultima_atualizacao");

                item = new Item(idItem, titulo, descricao, dataHoraCriacao, dataHoraUltimaAtualizacao, idUsuarioProprietario);

                insertLinkStatement.clearParameters();
                for (String link : links) {
                    insertLinkStatement.setString(1, link);
                    insertLinkStatement.setInt(2, idItem);
                    insertLinkStatement.addBatch();
                }
                insertLinkStatement.executeBatch();
            }
            return item;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (resultado != null) {
                    resultado.close();
                }
//                if(insertStatement != null) insertStatement.close();
//                if(conexao != null) conexao.close();
            } catch (SQLException ex) {
            }
        }
        return null;
    }
}
