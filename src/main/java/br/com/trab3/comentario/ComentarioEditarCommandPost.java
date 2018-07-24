package br.com.trab3.comentario;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.ComentarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComentarioEditarCommandPost implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idComentario = Integer.parseInt(request.getParameter("id_comentario"));
        String titulo = request.getParameter("titulo");
        String texto = request.getParameter("texto");
        Integer idItemComentado = Integer.parseInt(request.getParameter("id_item_comentado"));
        Integer idUsuario = Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());

        ComentarioDAO.getInstance().updateComentarioByIdComentario(idComentario, titulo, texto, idUsuario);
        response.sendRedirect("item.html?id_item=" + idItemComentado);
    }
}
