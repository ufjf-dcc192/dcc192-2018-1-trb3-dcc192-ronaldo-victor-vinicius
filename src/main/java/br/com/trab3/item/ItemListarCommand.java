package br.com.trab3.item;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.ItemDAO;
import br.com.trab3.modelos.Item;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemListarCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/item/item-listar.jsp");
        request.setAttribute("titulo", "Listar Itens");

        Integer idUsuario = Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());
        ArrayList<Item> itens = ItemDAO.getInstance().selectAllItensByIdUsuario(idUsuario);
        request.setAttribute("itens", itens);
        dispacher.forward(request, response);
    }
}
