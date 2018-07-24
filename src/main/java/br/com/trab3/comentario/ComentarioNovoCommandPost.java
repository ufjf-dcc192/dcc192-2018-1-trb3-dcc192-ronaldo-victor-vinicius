package br.com.trab3.comentario;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.ComentarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComentarioNovoCommandPost implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idItemComentado = Integer.parseInt(request.getParameter("id_item_comentado"));
        String titulo = request.getParameter("titulo");
        String texto = request.getParameter("texto");

        Integer idUsuario = Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());
        ComentarioDAO.getInstance().insertComentario(titulo, texto, idUsuario, idItemComentado);
        response.sendRedirect("item.html?id_item=" + idItemComentado);
    }
}
