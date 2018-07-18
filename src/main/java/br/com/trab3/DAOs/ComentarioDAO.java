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

public class ComentarioDAO {
    private static Connection conexao;
    private static ComentarioDAO instancia = null;
    private PreparedStatement insertStatement;
    
    public ComentarioDAO() {
        try {
            ComentarioDAO.conexao = Conexao.getConnection();
            
//            insertStatement = ComentarioDAO.conexao.prepareStatement("INSERT INTO usuario(nome_completo, email, login, senha) VALUES(?, ?, ?, ?)");
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
    
//    public void criarUsuario(String nomeCompleto, String email, String login, String senha) {
//        try {
//            insertStatement.clearParameters();
//            insertStatement.setString(1, nomeCompleto);
//            insertStatement.setString(2, email);
//            insertStatement.setString(3, login);
//            insertStatement.setString(4, senha);
//            insertStatement.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(ComentarioDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
