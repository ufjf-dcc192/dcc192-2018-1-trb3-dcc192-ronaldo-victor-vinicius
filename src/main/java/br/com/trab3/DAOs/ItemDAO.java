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
            
//            insertStatement = ItemDAO.conexao.prepareStatement("INSERT INTO usuario(nome_completo, email, login, senha) VALUES(?, ?, ?, ?)");
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
    
//    public void criarUsuario(String nomeCompleto, String email, String login, String senha) {
//        try {
//            insertStatement.clearParameters();
//            insertStatement.setString(1, nomeCompleto);
//            insertStatement.setString(2, email);
//            insertStatement.setString(3, login);
//            insertStatement.setString(4, senha);
//            insertStatement.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(ItemDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
