package br.com.trab3.DAOs;

import br.com.trab3.modelos.AvaliacaoItem;
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

public class AvaliacaoItemDAO {
    private static Connection conexao;
    private static AvaliacaoItemDAO instancia = null;
    private PreparedStatement selectAllAvaliacoesByIdItemStatement;
    
    public AvaliacaoItemDAO() {
        try {
            AvaliacaoItemDAO.conexao = Conexao.getConnection();
            
            selectAllAvaliacoesByIdItemStatement = AvaliacaoItemDAO.conexao.prepareStatement("SELECT * FROM avaliacao_item WHERE id_item_avaliado = ?", Statement.RETURN_GENERATED_KEYS);
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
    
    public ArrayList<AvaliacaoItem> selectAllAvaliacoesByIdItem(Integer idItem, Integer idUsuario) {
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

                avaliacoes.add(new AvaliacaoItem(idAvaliacaoItem, avaliacao, idItem, idUsuario));
            }
            return avaliacoes;
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
        return new ArrayList<>();
    }
}
