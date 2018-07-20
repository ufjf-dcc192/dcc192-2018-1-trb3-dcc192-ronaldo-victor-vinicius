package br.com.trab3.DAOs;

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
    
    public ItemDAO() {
        try {
            ItemDAO.conexao = Conexao.getConnection();
            
            insertStatement = ItemDAO.conexao.prepareStatement("INSERT INTO item(titulo, descricao, data_hora_criacao, data_hora_ultima_atualizacao, id_usuario_proprietario) VALUES(?, ?,  LOCALTIMESTAMP,  LOCALTIMESTAMP, ?)");
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
    
    public void criarItem(String titulo, String descricao, String idUsuarioProprietario) {
        try {
            insertStatement.clearParameters();
            insertStatement.setString(1, titulo);
            insertStatement.setString(2, descricao);
            insertStatement.setString(3, idUsuarioProprietario);
            insertStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
