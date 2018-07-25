package br.com.trab3.usuario;

import br.com.trab3.Comando;
import br.com.trab3.DAOs.AvaliacaoComentarioDAO;
import br.com.trab3.DAOs.AvaliacaoItemDAO;
import br.com.trab3.DAOs.ComentarioDAO;
import br.com.trab3.DAOs.ItemDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UsuarioVisaoGeralCommand implements Comando {

    @Override
    public void exec(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispacher = request.getRequestDispatcher("/WEB-INF/usuario/usuario-visao-geral.jsp");
        request.setAttribute("titulo", "Visão Geral do Usuário");
        
        Integer idUsuario = Integer.parseInt(request.getSession().getAttribute("id_usuario").toString());
        
        // Meus Itens
        Integer countMeusItens = ItemDAO.getInstance().selectCountItensByIdUsuario(idUsuario);
        Integer countAvaliacoesPositivasMeusItens = AvaliacaoItemDAO.getInstance().selectCountAvaliacoesPositivasMeusItensByIdUsuario(idUsuario);
        Integer countAvaliacoesNegativasMeusItens = AvaliacaoItemDAO.getInstance().selectCountAvaliacoesNegativasMeusItensByIdUsuario(idUsuario);
        request.setAttribute("countMeusItens", countMeusItens);
        request.setAttribute("countAvaliacoesPositivasMeusItens", countAvaliacoesPositivasMeusItens);
        request.setAttribute("countAvaliacoesNegativasMeusItens", countAvaliacoesNegativasMeusItens);
        
        // Avaliações FEITAS a itens
        Integer countAvaliacoesPositivasFeitasAItens = AvaliacaoItemDAO.getInstance().selectCountAvaliacoesPositivasItensByIdUsuario(idUsuario);
        Integer countAvaliacoesNegativasFeitasAItens = AvaliacaoItemDAO.getInstance().selectCountAvaliacoesNegativasItensByIdUsuario(idUsuario);
        request.setAttribute("countAvaliacoesFeitasAItens", countAvaliacoesPositivasFeitasAItens + countAvaliacoesNegativasFeitasAItens);
        request.setAttribute("countAvaliacoesPositivasFeitasAItens", countAvaliacoesPositivasFeitasAItens);
        request.setAttribute("countAvaliacoesNegativasFeitasAItens", countAvaliacoesNegativasFeitasAItens);
        
        // Meus Comentários
        Integer countMeusComentarios = ComentarioDAO.getInstance().selectCountComentariosByIdUsuario(idUsuario);
        Integer countAvaliacoesPositivasMeusComentarios = AvaliacaoComentarioDAO.getInstance().selectCountAvaliacoesPositivasMeusComentariosByIdUsuario(idUsuario);
        Integer countAvaliacoesNegativasMeusComentarios = AvaliacaoComentarioDAO.getInstance().selectCountAvaliacoesNegativasMeusComentariosByIdUsuario(idUsuario);
        request.setAttribute("countMeusComentarios", countMeusComentarios);
        request.setAttribute("countAvaliacoesPositivasMeusComentarios", countAvaliacoesPositivasMeusComentarios);
        request.setAttribute("countAvaliacoesNegativasMeusComentarios", countAvaliacoesNegativasMeusComentarios);
        
        // Avaliaçõs FEITAS a comentários
        Integer countAvaliacoesPositivasFeitasAComentarios = AvaliacaoComentarioDAO.getInstance().selectCountAvaliacoesPositivasComentariosByIdUsuario(idUsuario);
        Integer countAvaliacoesNegativasFeitasAComentarios = AvaliacaoComentarioDAO.getInstance().selectCountAvaliacoesNegativasComentariosByIdUsuario(idUsuario);
        request.setAttribute("countAvaliacoesFeitasAComentarios", countAvaliacoesPositivasFeitasAComentarios + countAvaliacoesNegativasFeitasAComentarios);
        request.setAttribute("countAvaliacoesPositivasFeitasAComentarios", countAvaliacoesPositivasFeitasAComentarios);
        request.setAttribute("countAvaliacoesNegativasFeitasAComentarios", countAvaliacoesNegativasFeitasAComentarios);
        
        
        
        dispacher.forward(request, response);
    }
}
