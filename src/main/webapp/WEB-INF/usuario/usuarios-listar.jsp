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

<table class="table table-striped table-hover">
    <thead class="thead-dark">
        <tr>
            <th scope="col" class="text-center">id_usuario</th>
            <th scope="col" class="text-center">Nome Completo</th>
            <th scope="col" class="text-center">E-mail</th>
            <th scope="col" class="text-center">Login</th>
            <th scope="col" class="text-center">Qtd Comentários</th>
            <c:if test="${isCuradores}">
                <th scope="col" class="text-center">Avaliações<br />Positivas</th>
                <th scope="col" class="text-center">Avaliações<br />Negativas</th>
                <th scope="col" class="text-center">Diferença Qtd<br />Avaliações</th>
            </c:if>
            <c:if test="${isTrolls}">
                <th scope="col" class="text-center">Comentários<br />Avaliados<br />Negativamente</th>
            </c:if>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="usuario" items="${usuarios}">
            <tr>
                <td class="text-center">${usuario.getId()}</td>
                <td class="text-center">${usuario.getNome()}</td>
                <td class="text-center">${usuario.getEmail()}</td>
                <td class="text-center">${usuario.getLogin()}</td>
                <td class="text-center">${usuario.getQtdComentarios()}</td>
                <c:if test="${isCuradores}">
                    <td class="text-center">${usuario.getQtdAvaliacoesPositivas()}</td>
                    <td class="text-center">${usuario.getQtdAvaliacoesNegativas()}</td>
                    <td class="text-center bg-info">${usuario.getQtdAvaliacoesPositivas() - usuario.getQtdAvaliacoesNegativas()}</td>
                </c:if>
                <c:if test="${isTrolls}">
                    <td class="text-center bg-info">
                        ${usuario.getQtdComentariosAvaliadosNegativamente()} 
                        (<fmt:formatNumber type="number" maxFractionDigits="1" value="${100*usuario.getQtdComentariosAvaliadosNegativamente()/usuario.getQtdComentarios()}" />%)
                    </td>
                </c:if>

            </tr>
        </c:forEach>
    </tbody>
</table>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
