package br.com.trab3.avaliacao;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.AvaliacaoComentarioDAO;
import br.com.trab3.modelos.AvaliacaoComentario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AvaliacaoPositivaComentarioCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idComentario = Integer.parseInt(request.getParameter("id_comentario"));
        Integer idItem = Integer.parseInt(request.getParameter("id_item"));
        Integer idUsuario = Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());

        AvaliacaoComentario avaliacao = AvaliacaoComentarioDAO.getInstance().selectAvaliacaoByIdComentarioAndByIdUsuario(idComentario, idUsuario);
        if (avaliacao == null) {
            AvaliacaoComentarioDAO.getInstance().insertAvaliacaoComentario(1, idComentario, idUsuario);
        } else if (avaliacao.getAvaliacao() < 0) {
            AvaliacaoComentarioDAO.getInstance().updateAvaliacaoComentarioByIdComentarioAndIdUsuario(1, idComentario, idUsuario);
        } else {
            AvaliacaoComentarioDAO.getInstance().deleteAvaliacaoComentarioByIdComentarioAndIdUsuario(idComentario, idUsuario);
        }
        
        String[] array;
        array = request.getHeader("referer").split("/");
        response.sendRedirect(array[array.length-1]);
    }
}
