package br.com.trab3.usuario;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.AvaliacaoComentarioDAO;
import br.com.trab3.DAOs.AvaliacaoItemDAO;
import br.com.trab3.DAOs.ComentarioDAO;
import br.com.trab3.DAOs.ItemDAO;
import br.com.trab3.DAOs.LinkDAO;
import br.com.trab3.DAOs.UsuarioDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UsuarioLogoutCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        if (session != null) {
            session.invalidate();
        }
        
        AvaliacaoComentarioDAO.getInstance().closeConnection();
        AvaliacaoItemDAO.getInstance().closeConnection();
        ComentarioDAO.getInstance().closeConnection();
        ItemDAO.getInstance().closeConnection();
        LinkDAO.getInstance().closeConnection();
        UsuarioDAO.getInstance().closeConnection();
        response.sendRedirect("index.html");
    }
}
