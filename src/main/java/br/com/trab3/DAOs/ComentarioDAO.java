package br.com.trab3.DAOs;

import br.com.trab3.modelos.Comentario;
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

public class ComentarioDAO {
    private static Connection conexao;
    private static ComentarioDAO instancia = null;
    private PreparedStatement insertStatement;
    
    public ComentarioDAO() {
        try {
            ComentarioDAO.conexao = Conexao.getConnection();
            
            
        } catch (URISyntaxException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ComentarioDAO getInstance() {
        if (ComentarioDAO.instancia == null) {
            ComentarioDAO.instancia = new ComentarioDAO();
        }
        return ComentarioDAO.instancia;
    }
    
    public ArrayList<Comentario> selectAllComentariosByIdItem(Integer idItem) {
//        ResultSet resultado = null;
//        ArrayList<Comentario> links;
//        links = new ArrayList<>();
//        try {
//            selectAllLinksByIdItemStatement.clearParameters();
//            selectAllLinksByIdItemStatement.setInt(1, idItem);
//            resultado = selectAllLinksByIdItemStatement.executeQuery();
//
//            while (resultado.next()) {
//                Integer idLink = resultado.getInt("id_item_relacionado");
//                String link = resultado.getString("link");
//
//                links.add(new Comentario(idLink, link, idItem));
//            }
//            return links;
//        } catch (SQLException ex) {
//            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                if (resultado != null) {
//                    resultado.close();
//                }
////                if(insertItemStatement != null) insertItemStatement.close();
////                if(conexao != null) conexao.close();
//            } catch (SQLException ex) {
//            }
//        }
        return null;
    }
}
