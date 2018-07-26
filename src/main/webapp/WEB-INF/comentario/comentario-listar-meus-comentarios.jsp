<%@page 
    import="java.util.ArrayList, br.com.trab3.modelos.Item, br.com.trab3.modelos.Comentario" 
    errorPage="../pagina-de-erro.jsp" 
    isErrorPage="false" 
    contentType="text/html" 
    pageEncoding="UTF-8" %>

<!--The core group of tags are the most commonly used JSTL tags.-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--The JSTL formatting tags are used to format and display text, the date, the time, and numbers for internationalized Websites.-->
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="/WEB-INF/jspf/cabecalho.jspf" %>

<c:forEach var="item" items="${itens}">
    <div class="row">
        <div class="col-11">
            <nav>
                <div class="nav nav-tabs" id="nav-tab" role="tablist">
                    <a class="nav-item nav-link active" id="nav-descricao-tab" data-toggle="tab" href="#nav-descricao" role="tab" aria-controls="nav-descricao" aria-selected="true">Detalhes do Item</a>
                    <a class="nav-item nav-link" id="nav-links-tab" data-toggle="tab" href="#nav-links" role="tab" aria-controls="nav-links" aria-selected="false">Links Relacionados</a>
                </div>
            </nav>
            <div class="tab-content p-1" id="nav-tabContent">
                <div class="tab-pane fade show active p-1" id="nav-descricao" role="tabpanel" aria-labelledby="nav-descricao-tab">
                    <%@include file="/WEB-INF/jspf/item.jspf" %>
                </div>
                <div class="tab-pane fade p-1" id="nav-links" role="tabpanel" aria-labelledby="nav-links-tab">
                    <ul class="list-group">
                        <c:forEach var="link" items="${item.getLinks()}">
                            <li class="list-group-item">ID: ${link.getIdLink()} | LINK: ${link.getLink()}</li>
                            </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-1">
            <br />
            <br />
            <% request.setAttribute("isVertical", true);
                request.setAttribute("isDisabled", false); %>
            <%@include file="/WEB-INF/jspf/avaliacao-item.jspf" %>
            <c:if test="${item.getIdUsuarioProprietario() == id_usuario}">
                <div class="btn-group-vertical btn-block text-center">
                    <a href="item-editar.html?id_item=${item.getIdItem()}" class="btn btn-dark" title="Editar Item"><i class="fas fa-edit"></i></a>
                    <button onclick="confirm('Clique em OK para EXCLUIR o item ${item.getTitulo()}.') ? (location.href = 'item-excluir.html?id_item=${item.getIdItem()}') : false" class="btn btn-danger" title="Excluir Item"><i class="fas fa-trash-alt"></i></button>
                </div>
            </c:if>

        </div>
    </div>
    
    ${request.setAttribute("comentarios", item.getComentarios())}
    <%@include file="/WEB-INF/jspf/comentario.jspf" %>
    
    <hr />
</c:forEach>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
