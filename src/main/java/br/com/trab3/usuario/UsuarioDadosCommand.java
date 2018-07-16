package br.com.trab3.usuario;

import br.com.trab3.Comando;
import br.com.trab3.Comando;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioDadosCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/usuario/usuario-dados.jsp");
        request.setAttribute("titulo", "Dados do Usuário");
        dispacher.forward(request, response);
    }
}
