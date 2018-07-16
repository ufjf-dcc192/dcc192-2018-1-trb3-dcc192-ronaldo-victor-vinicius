package br.com.trab3.trab3.ronaldo.vinicius.victor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PrincipalServlet", urlPatterns = {"/index.html", 
    "/usuario-novo.html", "/login.html", "/logout.html",
    "/item-novo.html", "/item-editar.html", "/item-listar.html", "/item-excluir.html"})
public class PrincipalServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> rotas = new HashMap<>();

        rotas.put("/index.html", "br.com.trab3.trab3.ronaldo.vinicius.victor.IndexCommand");
        
        rotas.put("/usuario-novo.html", "br.com.trab3.trab3.ronaldo.vinicius.victor.UsuarioNovoCommand");
        rotas.put("/login.html", "br.com.trab3.trab3.ronaldo.vinicius.victor.LoginCommand");
        rotas.put("/logout.html", "br.com.trab3.trab3.ronaldo.vinicius.victor.LogoutCommand");
        
        rotas.put("/item-novo.html", "br.com.trab3.trab3.ronaldo.vinicius.victor.ItemNovoCommand");
        rotas.put("/item-editar.html", "br.com.trab3.trab3.ronaldo.vinicius.victor.ItemEditarCommand");
        rotas.put("/item-listar.html", "br.com.trab3.trab3.ronaldo.vinicius.victor.ItemListarCommand");
        rotas.put("/item-excluir.html", "br.com.trab3.trab3.ronaldo.vinicius.victor.ItemExcluirCommand");

        String clazzName = rotas.get(request.getServletPath());
        try {
            Comando comando = (Comando) Class.forName(clazzName).newInstance();
            comando.exec(request, response);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            response.sendError(500, "Erro: " + ex);
            Logger.getLogger(PrincipalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> rotas = new HashMap<>();
//        rotas.put("/index.html", "codigos.DeletaEventoCommandPost");

        String clazzName = rotas.get(request.getServletPath());
        try {
            Comando comando = (Comando) Class.forName(clazzName).newInstance();
            comando.exec(request, response);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
            response.sendError(500, "Erro: " + ex);
            Logger.getLogger(PrincipalServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
