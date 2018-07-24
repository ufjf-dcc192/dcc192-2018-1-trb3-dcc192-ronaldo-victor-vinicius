package br.com.trab3.item;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.ItemDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemExcluirCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idItem = Integer.parseInt(request.getParameter("id_item"));

        Integer idUsuario = Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());
        ItemDAO.getInstance().deleteItemById(idItem, idUsuario);
        response.sendRedirect("item-listar.html");
    }
}
