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

@WebServlet(name = "PrincipalServlet", urlPatterns = {"/index.html"})
public class PrincipalServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> rotas = new HashMap<>();

        rotas.put("/index.html", "br.com.trab3.trab3.ronaldo.vinicius.victor.IndexCommand");
//        rotas.put("/novoevento.html", "codigos.NovoEventoCommand");
//        rotas.put("/inscricao.html", "codigos.InscricaoEventoCommand");
//        rotas.put("/inscritos.html", "codigos.ListarInscritosEventoCommand");
//
//        rotas.put("/participantes.html", "codigos.ListarParticipantesCommand");
//        rotas.put("/novoParticipante.html", "codigos.NovoParticipanteCommand");
//        rotas.put("/editaParticipante.html", "codigos.EditaParticipanteCommand");

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
        rotas.put("/index.html", "codigos.DeletaEventoCommandPost");
//        rotas.put("/novoParticipante.html", "codigos.NovoParticipanteCommandPost");
//        rotas.put("/editaParticipante.html", "codigos.EditaParticipanteCommandPost");
//        rotas.put("/excluiParticipante.html", "codigos.DeletaParticipanteCommandPost");
//
//        rotas.put("/novoevento.html", "codigos.NovoEventoCommandPost");
//
//        rotas.put("/inscricao.html", "codigos.InscricaoEventoCommand");
//        rotas.put("/amigo.html", "codigos.VerAmigoOcultoCommand");

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
