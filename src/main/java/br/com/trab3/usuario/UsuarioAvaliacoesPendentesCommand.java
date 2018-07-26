package br.com.trab3.usuario;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.ItemDAO;
import br.com.trab3.modelos.Item;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioAvaliacoesPendentesCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/comentario/comentario-listar-meus-comentarios.jsp");
        request.setAttribute("titulo", "Avaliações pendentes");
        
        Integer idUsuario = Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());
        ArrayList<Item> itens = ItemDAO.getInstance().selectAllItensAndComentariosNaoAvaliadosByIdUsuario(idUsuario);
        
        request.setAttribute("itens", itens);
        request.setAttribute("id_usuario", idUsuario);
        dispacher.forward(request, response);
    }
}
