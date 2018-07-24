package br.com.trab3.DAOs;

import br.com.trab3.modelos.AvaliacaoComentario;
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

public class AvaliacaoComentarioDAO {
    private static Connection conexao;
    private static AvaliacaoComentarioDAO instancia = null;
    private PreparedStatement selectAllAvaliacoesByIdItemStatement;
    
    public AvaliacaoComentarioDAO() {
        try {
            AvaliacaoComentarioDAO.conexao = Conexao.getConnection();
            
            
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
    
    public ArrayList<AvaliacaoComentario> selectAllAvaliacoesByIdComentario(Integer idComentario) {
//        ResultSet resultado = null;
//        ArrayList<AvaliacaoItem> avaliacoes;
//        avaliacoes = new ArrayList<>();
//        try {
//            selectAllAvaliacoesByIdItemStatement.clearParameters();
//            selectAllAvaliacoesByIdItemStatement.setInt(1, idItem);
//            resultado = selectAllAvaliacoesByIdItemStatement.executeQuery();
//
//            while (resultado.next()) {
//                Integer idLink = resultado.getInt("id_item_relacionado");
//                String link = resultado.getString("link");
//
//                avaliacoes.add(new AvaliacaoItem(idLink, link, idItem));
//            }
//            return avaliacoes;
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
//        return null;
        return new ArrayList<>();
    }
}
