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

public class ItemRankingCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/item/item-ranking.jsp");
        request.setAttribute("titulo", "Ranking de itens");
        
        Integer idUsuario = Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());
        String criterio = request.getParameter("criterio");
        ArrayList<Item> itens;
        
        request.setAttribute("hasErroCriterio", false);
        if ("dataHoraCriacao".equals(criterio)) {
            itens = ItemDAO.getInstance().selectAllItensOrderByDataHoraCriacao();
        } else if ("dataHoraUltimaAtualiazacao".equals(criterio)) {
            itens = ItemDAO.getInstance().selectAllItensOrderByDataHoraUltimaAtualizacao();
        } else if ("qtdTotalDeAvaliacoes".equals(criterio)) {
            itens = ItemDAO.getInstance().selectAllItensOrderByQtdTotalDeAvaliacoes();
        } else if ("melhorAvaliacao".equals(criterio)) {
            itens = ItemDAO.getInstance().selectAllItensOrderByMelhorAvaliacao();
        } else {
            itens = ItemDAO.getInstance().selectAllItensOrderByDataHoraCriacao();
            request.setAttribute("hasErroCriterio", true);
        }
        
        request.setAttribute("itens", itens);
        request.setAttribute("id_usuario", idUsuario);
        dispacher.forward(request, response);
    }
}
