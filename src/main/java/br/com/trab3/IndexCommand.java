package br.com.trab3;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/index.jsp");
        request.setAttribute("titulo", "Página inicial");
        request.setAttribute("login", "AAAAAAA");
        dispacher.forward(request, response);
    }
}
