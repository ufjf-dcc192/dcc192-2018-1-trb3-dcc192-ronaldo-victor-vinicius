package br.com.trab3;

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

public class DAO {
    private static Connection conexao;
    private static DAO instancia;
    
    public DAO() {
        try {
            if (conexao == null) {
                conexao = DriverManager.getConnection("jdbc:derby://localhost:1527/trabalho3", "usuario", "senha");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DAO getInstance() {
        if (instancia == null) {
            instancia = new DAO();
        }
        return instancia;
    }
    
    public void criarUsuario(String nomeCompleto, String email, String login, String senha) {
        try {
            Statement comando = conexao.createStatement();
            comando.executeUpdate(String.format("INSERT INTO usuario(nome_completo, email, login, senha) VALUES('%s', '%s', '%s', '%s')", nomeCompleto, email, login, senha));
            comando.close();
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
