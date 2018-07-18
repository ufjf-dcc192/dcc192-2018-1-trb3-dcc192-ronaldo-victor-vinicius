package br.com.trab3;

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
    "/usuario-novo.html", "/visao-geral-do-usuario.html", "/usuario-dados.html", "/login.html", "/logout.html", "/meus-comentarios.html", "/a-avaliar.html", "/trolls.html", "/curadores.html",
    "/item-novo.html", "/item-editar.html", "/item-listar.html", "/item-excluir.html", "/item.html", "/item-comentarios.html", 
    "/comentar.html", "/comentario-excluir.html",
    "/avaliar.html",
    "/ranking.html"})
public class PrincipalServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> rotas = new HashMap<>();

        rotas.put("/index.html", "br.com.trab3.IndexCommand");
        
        rotas.put("/usuario-novo.html", "br.com.trab3.usuario.UsuarioNovoCommand");
        rotas.put("/usuario-dados.html", "br.com.trab3.usuario.UsuarioDadosCommand");
        rotas.put("/login.html", "br.com.trab3.usuario.UsuarioLoginCommand");
        rotas.put("/logout.html", "br.com.trab3.usuario.UsuarioLogoutCommand");
        rotas.put("/meus-comentarios.html", "br.com.trab3.usuario.UsuarioLMeusComentariosCommand");
        rotas.put("/a-avaliar.html", "br.com.trab3.usuario.UsuarioAvaliacoesPendentesCommand");
        rotas.put("/trolls.html", "br.com.trab3.usuario.UsuariosTrollsCommand");
        rotas.put("/curadores.html", "br.com.trab3.usuario.UsuariosCuradoresCommand");
        rotas.put("/visao-geral-do-usuario.html", "br.com.trab3.usuario.UsuarioVisaoGeralCommand");
        
        rotas.put("/item-novo.html", "br.com.trab3.item.ItemNovoCommand");
        rotas.put("/item-editar.html", "br.com.trab3.item.ItemEditarCommand");
        rotas.put("/item-listar.html", "br.com.trab3.item.ItemListarCommand");
        rotas.put("/item-excluir.html", "br.com.trab3.item.ItemExcluirCommand");
        rotas.put("/item.html", "br.com.trab3.item.ItemDetalhesCommand");
        rotas.put("/item-comentarios.html", "br.com.trab3.item.ItemComentariosCommand");
        rotas.put("/ranking.html", "br.com.trab3.item.ItemRankingCommand");

        rotas.put("/comentar.html", "br.com.trab3.comentario.ComentarioNovoCommand");
        rotas.put("/comentario-excluir.html", "br.com.trab3.comentario.ComentarioExcluirCommand");
        
        rotas.put("/avaliar.html", "br.com.trab3.outros.AvaliarCommand");
        
        
        
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
        rotas.put("/usuario-novo.html", "br.com.trab3.usuario.UsuarioNovoCommandPost");

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
