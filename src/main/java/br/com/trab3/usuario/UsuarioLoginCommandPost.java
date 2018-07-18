package br.com.trab3.usuario;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.UsuarioDAO;
import br.com.trab3.modelos.Usuario;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UsuarioLoginCommandPost implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        Usuario usuario = UsuarioDAO.getInstance().login(login, senha);
        if (usuario == null) {
            RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/usuario/usuario-login.jsp");
            request.setAttribute("titulo", "Login");
            request.setAttribute("erro", "Login ou senha incorretos.");
            dispacher.forward(request, response);
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("id_usuario", usuario.getId());
            session.setAttribute("nome_completo", usuario.getNome());
            session.setAttribute("email", usuario.getEmail());
            session.setAttribute("login", usuario.getLogin());
            response.sendRedirect("visao-geral-do-usuario.html");
        }
    }
}
