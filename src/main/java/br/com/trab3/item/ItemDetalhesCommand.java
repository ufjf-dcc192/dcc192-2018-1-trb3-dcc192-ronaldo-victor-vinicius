package br.com.trab3.item;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.ItemDAO;
import br.com.trab3.modelos.Comentario;
import br.com.trab3.modelos.Item;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemDetalhesCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/item/item-detalhes.jsp");
        request.setAttribute("titulo", "Detalhes do Item");
        
        Integer idItem = Integer.parseInt(request.getParameter("id_item"));
        Integer idUsuario = (Integer) Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());
        Item item = ItemDAO.getInstance().selectItemById(idItem, idUsuario);
        
        Boolean isComentado = false;
        for (Comentario comentario : item.getComentarios()) {
            if (Objects.equals(comentario.getIdUsuarioProprietario(), idUsuario)) {
                isComentado = true;
            }
        }
        
        request.setAttribute("item", item);
        request.setAttribute("id_usuario", idUsuario);
        request.setAttribute("isComentado", isComentado);

        dispacher.forward(request, response);
    }
}
