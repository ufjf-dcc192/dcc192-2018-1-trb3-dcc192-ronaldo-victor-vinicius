package br.com.trab3.item;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.ItemDAO;
import br.com.trab3.modelos.Item;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemEditarCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/item/item-editar.jsp");
        request.setAttribute("titulo", "Editar Item");

        Integer idItem = Integer.parseInt(request.getParameter("id_item"));
        Integer idUsuario = (Integer) Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());
        Item item = ItemDAO.getInstance().selectItemById(idItem);
        request.setAttribute("item", item);

        dispacher.forward(request, response);
    }
}
