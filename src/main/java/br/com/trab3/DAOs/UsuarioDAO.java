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

public class UsuarioDAO {
    private static Connection conexao;
    private static UsuarioDAO instancia = null;
    private PreparedStatement insertStatement;
    
    public UsuarioDAO() {
        try {
            UsuarioDAO.conexao = Conexao.getConnection();
            
            insertStatement = UsuarioDAO.conexao.prepareStatement("INSERT INTO usuario(nome_completo, email, login, senha) VALUES(?, ?, ?, ?)");
        } catch (URISyntaxException | SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static UsuarioDAO getInstance() {
        if (UsuarioDAO.instancia == null) {
            UsuarioDAO.instancia = new UsuarioDAO();
        }
        return UsuarioDAO.instancia;
    }
    
    public void criarUsuario(String nomeCompleto, String email, String login, String senha) {
        try {
            insertStatement.clearParameters();
            insertStatement.setString(1, nomeCompleto);
            insertStatement.setString(2, email);
            insertStatement.setString(3, login);
            insertStatement.setString(4, senha);
            insertStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
