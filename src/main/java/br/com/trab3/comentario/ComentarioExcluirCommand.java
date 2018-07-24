package br.com.trab3.comentario;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.ComentarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComentarioExcluirCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idItem = Integer.parseInt(request.getParameter("id_item"));
        Integer idComentario = Integer.parseInt(request.getParameter("id_comentario"));
        Integer idUsuario = Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());

        ComentarioDAO.getInstance().deleteComentarioByIdComentario(idComentario, idUsuario);
        response.sendRedirect("item.html?id_item=" + idItem);
    }
}
