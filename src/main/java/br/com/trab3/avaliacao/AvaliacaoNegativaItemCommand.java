package br.com.trab3.avaliacao;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.AvaliacaoItemDAO;
import br.com.trab3.modelos.AvaliacaoItem;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AvaliacaoNegativaItemCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer idItem = Integer.parseInt(request.getParameter("id_item"));
        Integer idUsuario = Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());

        AvaliacaoItem avaliacao = AvaliacaoItemDAO.getInstance().selectAvaliacaoByIdItemAndByIdUsuario(idItem, idUsuario);
        if (avaliacao == null) {
            AvaliacaoItemDAO.getInstance().insertAvaliacaoItem(-1, idItem, idUsuario);
        } else if (avaliacao.getAvaliacao() > 0) {
            AvaliacaoItemDAO.getInstance().updateAvaliacaoItemByIdItemAndIdUsuario(-1, idItem, idUsuario);
        } else {
            AvaliacaoItemDAO.getInstance().deleteAvaliacaoItemByIdItemAndIdUsuario(idItem, idUsuario);
        }

        String[] array;
        array = request.getHeader("referer").split("/");
        response.sendRedirect(array[array.length-1]);
    }
}
