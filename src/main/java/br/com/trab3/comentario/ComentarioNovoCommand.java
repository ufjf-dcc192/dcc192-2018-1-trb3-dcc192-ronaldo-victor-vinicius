package br.com.trab3.comentario;

import br.com.trab3.Comando;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComentarioNovoCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/comentario/comentar.jsp");
        request.setAttribute("titulo", "Adicionar Comentário");
        dispacher.forward(request, response);
    }
}
