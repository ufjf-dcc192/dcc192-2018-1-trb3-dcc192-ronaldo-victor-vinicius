package br.com.trab3.item;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.ItemDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ItemNovoCommandPost implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String titulo = request.getParameter("titulo");
        String descricao = request.getParameter("descricao");
        
        request.getParameterMap().keySet().remove("titulo");
        request.getParameterMap().keySet().remove("descricao");
        
        ArrayList links;
        links = new ArrayList<>();
        
        request.getParameterMap().keySet().forEach((key) -> {
            links.add(request.getParameter(key));
        });
        
        Integer idUsuario = Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());
        ItemDAO.getInstance().insertItem(titulo, descricao, links, idUsuario);
        response.sendRedirect("item-listar.html");
    }
}
