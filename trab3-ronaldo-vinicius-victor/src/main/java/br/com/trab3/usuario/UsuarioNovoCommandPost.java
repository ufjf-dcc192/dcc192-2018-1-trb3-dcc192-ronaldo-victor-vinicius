package br.com.trab3.usuario;

import br.com.trab3.Comando;
import br.com.trab3.Comando;
import br.com.trab3.DAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioNovoCommandPost implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeCompleto = request.getParameter("nomeCompleto");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        DAO.getInstance().criarUsuario(nomeCompleto, email, login, senha);
        response.sendRedirect("visao-geral-do-usuario.html");
    }
}
