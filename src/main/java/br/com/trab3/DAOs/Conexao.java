package br.com.trab3.DAOs;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    private static Connection conexao;

    private static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String username = "btuwadyqwxlcmd";
            String password = "d7df1bbcb5b92499ea43279862ae24ca840fe1c9d242a026311687a52f394bb9";
            String dbUrl = "jdbc:postgresql://ec2-54-235-193-34.compute-1.amazonaws.com:5432/da9p15t0v65m7c?sslmode=require";

            return DriverManager.getConnection(dbUrl, username, password);
//        return DriverManager.getConnection(System.getenv("DATABASE_URL"));
//        return DriverManager.getConnection(System.getenv("postgres://btuwadyqwxlcmd:d7df1bbcb5b92499ea43279862ae24ca840fe1c9d242a026311687a52f394bb9@ec2-54-235-193-34.compute-1.amazonaws.com:5432/da9p15t0v65m7c"));
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Connection getInstance() {
        try {
            if (conexao == null || conexao.isClosed()) {
                conexao = Conexao.getConnection();
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexao;
    }
}
