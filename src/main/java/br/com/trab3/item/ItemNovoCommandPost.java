package br.com.trab3.item;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.ItemDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ItemNovoCommandPost implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        HttpSession session = request.getSession();
        
        ItemDAO.getInstance().criarItem(titulo, descricao, session.getAttribute("id_usuario").toString());
        response.sendRedirect("item_listar.html");
    }
}
