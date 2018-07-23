package br.com.trab3.DAOs;

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

public class LinkDAO {
    private static Connection conexao;
    private static LinkDAO instancia = null;
    private PreparedStatement insertLinkStatement;
    private PreparedStatement selectAllLinksByIdItemStatement;
    private PreparedStatement deleteAllLinksByIdItemStatement;
    
    public LinkDAO() {
        try {
            LinkDAO.conexao = Conexao.getConnection();
            
            insertLinkStatement = LinkDAO.conexao.prepareStatement("INSERT INTO link(link, id_item_relacionado) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            selectAllLinksByIdItemStatement = LinkDAO.conexao.prepareStatement("SELECT * FROM link WHERE id_item_relacionado = ?", Statement.RETURN_GENERATED_KEYS);
            
            deleteAllLinksByIdItemStatement = LinkDAO.conexao.prepareStatement("DELETE FROM link WHERE id_item_relacionado = ?");
            
            
        } catch (URISyntaxException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(LinkDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static LinkDAO getInstance() {
        if (LinkDAO.instancia == null) {
            LinkDAO.instancia = new LinkDAO();
        }
        return LinkDAO.instancia;
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
                Integer idLink = resultado.getInt("id_link");
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
}
