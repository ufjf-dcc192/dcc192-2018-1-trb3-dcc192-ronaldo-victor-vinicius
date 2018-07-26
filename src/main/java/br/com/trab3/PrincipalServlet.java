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

@WebServlet(name = "PrincipalServlet", urlPatterns = {"/", "/index.html",
    "/usuario-novo.html", "/visao-geral-do-usuario.html", "/usuario-dados.html", "/login.html", "/logout.html", "/meus-comentarios.html", "/a-avaliar.html", "/trolls.html", "/curadores.html",
    "/item-novo.html", "/item-editar.html", "/item-listar.html", "/item-excluir.html", "/item.html", "/item-comentarios.html",
    "/comentar.html", "/comentario-excluir.html", "/comentario-editar.html",
    "/avaliar.html avaliacao-positiva-item.html", "/avaliacao-negativa-item.html", "/avaliacao-positiva-comentario.html", "/avaliacao-negativa-comentario.html",
    "/ranking.html"})
public class PrincipalServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> rotas = new HashMap<>();

        rotas.put("/", "br.com.trab3.IndexCommand");
        rotas.put("/index.html", "br.com.trab3.IndexCommand");

        rotas.put("/usuario-novo.html", "br.com.trab3.usuario.UsuarioNovoCommand");
        rotas.put("/usuario-dados.html", "br.com.trab3.usuario.UsuarioDadosCommand");
        rotas.put("/login.html", "br.com.trab3.usuario.UsuarioLoginCommand");
        rotas.put("/logout.html", "br.com.trab3.usuario.UsuarioLogoutCommand");
        rotas.put("/trolls.html", "br.com.trab3.usuario.UsuariosTrollsCommand");
        rotas.put("/curadores.html", "br.com.trab3.usuario.UsuariosCuradoresCommand");
        rotas.put("/visao-geral-do-usuario.html", "br.com.trab3.usuario.UsuarioVisaoGeralCommand");
        rotas.put("/a-avaliar.html", "br.com.trab3.usuario.UsuarioAvaliacoesPendentesCommand");

        rotas.put("/item-novo.html", "br.com.trab3.item.ItemNovoCommand");
        rotas.put("/item-editar.html", "br.com.trab3.item.ItemEditarCommand");
        rotas.put("/item-listar.html", "br.com.trab3.item.ItemListarCommand");
        rotas.put("/item-excluir.html", "br.com.trab3.item.ItemExcluirCommand");
        rotas.put("/item.html", "br.com.trab3.item.ItemDetalhesCommand");
        rotas.put("/item-comentarios.html", "br.com.trab3.item.ItemComentariosCommand");
        rotas.put("/ranking.html", "br.com.trab3.item.ItemRankingCommand");

        rotas.put("/comentar.html", "br.com.trab3.comentario.ComentarioNovoCommand");
        rotas.put("/comentario-editar.html", "br.com.trab3.comentario.ComentarioEditarCommand");
        rotas.put("/comentario-excluir.html", "br.com.trab3.comentario.ComentarioExcluirCommand");
        rotas.put("/meus-comentarios.html", "br.com.trab3.comentario.ComentarioListarMeusComentariosCommand");

        rotas.put("/avaliar.html", "br.com.trab3.avaliacao.AvaliarCommand");
        rotas.put("/avaliacao-positiva-item.html", "br.com.trab3.avaliacao.AvaliacaoPositivaItemCommand");
        rotas.put("/avaliacao-negativa-item.html", "br.com.trab3.avaliacao.AvaliacaoNegativaItemCommand");
        rotas.put("/avaliacao-positiva-comentario.html", "br.com.trab3.avaliacao.AvaliacaoPositivaComentarioCommand");
        rotas.put("/avaliacao-negativa-comentario.html", "br.com.trab3.avaliacao.AvaliacaoNegativaComentarioCommand");

        String path = request.getServletPath();
        String clazzName;
        if (request.getSession().isNew() 
                || request.getSession().getAttribute("id_usuario") == null
                || "".equals(request.getSession().getAttribute("id_usuario"))) {
            if ("/".equals(path) || "/index.html".equals(path) || "/usuario-novo.html".equals(path) || "/login.html".equals(path)) {
                clazzName = rotas.get(path);
            } else {
                clazzName = rotas.get("/login.html");
                request.setAttribute("erro", "É necessário fazer LOGIN para acessar " + path);
            }
        } else {
            if ("/usuario-novo.html".equals(path) || "/login.html".equals(path)) {
                clazzName = rotas.get("/visao-geral-do-usuario.html");
                request.setAttribute("erro", "É necessário fazer LOGOUT para acessar " + path);
            } else {
                clazzName = rotas.get(path);
            }
        }
        
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
        rotas.put("/login.html", "br.com.trab3.usuario.UsuarioLoginCommandPost");

        rotas.put("/item-novo.html", "br.com.trab3.item.ItemNovoCommandPost");
        rotas.put("/item-editar.html", "br.com.trab3.item.ItemEditarCommandPost");

        rotas.put("/comentar.html", "br.com.trab3.comentario.ComentarioNovoCommandPost");
        rotas.put("/comentario-editar.html", "br.com.trab3.comentario.ComentarioEditarCommandPost");

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
