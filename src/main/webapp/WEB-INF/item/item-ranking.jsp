<%@page 
    import="java.util.Date" 
    errorPage="../pagina-de-erro.jsp" 
    isErrorPage="false" 
    contentType="text/html" 
    pageEncoding="UTF-8" %>

<!--The core group of tags are the most commonly used JSTL tags.-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--The JSTL formatting tags are used to format and display text, the date, the time, and numbers for internationalized Websites.-->
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="/WEB-INF/jspf/cabecalho.jspf" %>

<c:if test="${hasErroCriterio}">
    <div class="alert alert-danger" role="alert">
        Critério de ordenação inválido!
    </div>
</c:if>

<h4>Ordenar por:</h4>
<div class="form-group">
    <a href="ranking.html?criterio=dataHoraCriacao" class="btn btn-dark">Data/Hora Criação</a>
    <a href="ranking.html?criterio=dataHoraUltimaAtualiazacao" class="btn btn-dark">Data/Hora Última Atualizacao</a>
    <a href="ranking.html?criterio=qtdTotalDeAvaliacoes" class="btn btn-dark">Total de Avaliações</a>
    <a href="ranking.html?criterio=melhorAvaliacao" class="btn btn-dark">Melhor avaliação (diferença)</a>
</div>

<hr />

<table class="table table-striped table-hover">
    <thead class="thead-dark">
        <tr>
            <th scope="col" class="text-center">id_item</th>
            <th scope="col" class="text-center">id_usuario</th>
            <th scope="col" class="text-center">Título</th>
            <th scope="col" class="text-center">Data/hora<br />criação</th>
            <th scope="col" class="text-center">Data/hora<br />atualização</th>
            <th scope="col" class="text-center">Avaliações</th>
            <th scope="col" class="text-center">Total de<br />avaliações</th>
            <th scope="col" class="text-center">Diferença<br />avaliações</th>
            <th scope="col" class="text-center">Ações</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${itens}">
            <tr>
                <td class="text-center">${item.getIdItem()}</td>
                <td class="text-center">${item.getIdUsuarioProprietario()}</td>
                <td class="text-center">${item.getTitulo()}</td>
                <td class="text-center">${item.getDataHoraCriacao()}</td>
                <td class="text-center">${item.getDataHoraUltimaAtualizacao()}</td>
                <td class="text-center">
                    <% request.setAttribute("isVertical", false); request.setAttribute("isDisabled", true);%>
                    <%@include file="/WEB-INF/jspf/avaliacao-item.jspf" %>
                </td>
                <td class="text-center">${item.getQuantidadeAvaliacoesPositivas() + item.getQuantidadeAvaliacoesNegativas()}</td>
                <td class="text-center">${item.calcularSomatorioDeAvaliacoes()}</td>
                <td class="text-center">
                    <div class="btn-group">
                        <a href="item.html?id_item=${item.getIdItem()}" class="btn btn-info" title="Ver Item"><i class="fas fa-clipboard-list"></i></a>
                    </div>
                </td>
            </tr>
        </c:forEach>

    </tbody>
</table>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
