package br.com.trab3.comentario;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.ComentarioDAO;
import br.com.trab3.DAOs.ItemDAO;
import br.com.trab3.modelos.Comentario;
import br.com.trab3.modelos.Item;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComentarioEditarCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/comentario/comentario-editar.jsp");
        request.setAttribute("titulo", "Editar Comentário");

        Integer idComentario = Integer.parseInt(request.getParameter("id_comentario"));
        Integer idUsuario = (Integer) Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());
        Comentario comentario = ComentarioDAO.getInstance().selectComentarioByIdComentario(idComentario, idUsuario);
        Item item = ItemDAO.getInstance().selectItemById(comentario.getIdItemComentado());

        if (!Objects.equals(comentario.getIdUsuarioProprietario(), idUsuario)) {
            response.sendRedirect("item.html?id_item=" + item.getIdItem());
        }

        request.setAttribute("item", item);
        request.setAttribute("comentario", comentario);
        dispacher.forward(request, response);
    }
}
