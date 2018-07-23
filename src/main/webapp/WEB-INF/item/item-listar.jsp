<%@page 
    import="java.util.Date, br.com.trab3.modelos.Item" 
    errorPage="../pagina-de-erro.jsp" 
    isErrorPage="false" 
    contentType="text/html" 
    pageEncoding="UTF-8" %>

<!--The core group of tags are the most commonly used JSTL tags.-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--The JSTL formatting tags are used to format and display text, the date, the time, and numbers for internationalized Websites.-->
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="/WEB-INF/jspf/cabecalho.jspf" %>

<div class="form-group">
    <a href="item-novo.html" class="btn btn-dark">Novo Item</a>
</div>

<table class="table table-striped table-hover">
    <thead class="thead-dark">
        <tr>
            <th scope="col" class="text-center">id_item</th>
            <th scope="col" class="text-center">Título</th>
            <th scope="col" class="text-center">Descrição</th>
            <th scope="col" class="text-center">Links</th>
            <th scope="col" class="text-center">Comentários</th>
            <th scope="col" class="text-center">Avaliações</th>
            <th scope="col" class="text-center">Ações</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="item" items="${itens}">
            <tr>
                <td class="text-center">${item.getIdItem()}</td>
                <td>${item.getTitulo()}</td>
                <td>${item.getDescricao()}</td>
                <td class="text-center">${item.getQuantidadeLinks()}</td>
                <td class="text-center">${item.getQuantidadeComentarios()}</td>
                <td class="text-center">
                    <% request.setAttribute("isVertical", false); request.setAttribute("isDisabled", true);%>
                    <%@include file="/WEB-INF/jspf/avaliacao-item.jspf" %>
                </td>
                <td class="text-center">
                    <div class="btn-group">
                        <a href="item.html?id_item=${item.getIdItem()}" class="btn btn-info" title="Ver Item"><i class="fas fa-clipboard-list"></i></a>
                        <a href="item-editar.html?id_item=${item.getIdItem()}" class="btn btn-dark" title="Editar Item"><i class="fas fa-edit"></i></a>
                        <button onclick="confirm('Clique em OK para EXCLUIR o item ${item.getTitulo()}.') ? (location.href = 'item-excluir.html?id_item=${item.getIdItem()}') : false" class="btn btn-danger" title="Excluir Item"><i class="fas fa-trash-alt"></i></button>
                    </div>
                </td>
            </tr>
        </c:forEach>

    </tbody>
</table>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
