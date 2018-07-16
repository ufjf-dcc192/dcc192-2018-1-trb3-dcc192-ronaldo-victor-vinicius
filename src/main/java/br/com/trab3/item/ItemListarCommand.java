package br.com.trab3.item;

import br.com.trab3.Comando;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemListarCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/item/item-listar.jsp");
        request.setAttribute("titulo", "Listar Itens");
        dispacher.forward(request, response);
    }
}
