package br.com.trab3.usuario;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.UsuarioDAO;
import br.com.trab3.modelos.Usuario;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuariosTrollsCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/usuario/usuarios-listar.jsp");
        request.setAttribute("titulo", "Usuários Trolls");
        
        ArrayList<Usuario> usuarios = UsuarioDAO.getInstance().selectAllUsuariosTrolls();
        request.setAttribute("usuarios", usuarios);
        request.setAttribute("isTrolls", true);
        
        dispacher.forward(request, response);
    }
}
