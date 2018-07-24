package br.com.trab3.DAOs;

import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    public static Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String username = "btuwadyqwxlcmd";
        String password = "d7df1bbcb5b92499ea43279862ae24ca840fe1c9d242a026311687a52f394bb9";
        String dbUrl = "jdbc:postgresql://ec2-54-235-193-34.compute-1.amazonaws.com:5432/da9p15t0v65m7c?sslmode=require";

        return DriverManager.getConnection(dbUrl, username, password);
//        return DriverManager.getConnection(System.getenv("DATABASE_URL"));
//        return DriverManager.getConnection(System.getenv("postgres://btuwadyqwxlcmd:d7df1bbcb5b92499ea43279862ae24ca840fe1c9d242a026311687a52f394bb9@ec2-54-235-193-34.compute-1.amazonaws.com:5432/da9p15t0v65m7c"));
    }
}
