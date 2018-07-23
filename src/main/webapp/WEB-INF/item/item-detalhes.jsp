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

<div class="row">
    <div class="col-11">
        <nav>
            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                <a class="nav-item nav-link active" id="nav-descricao-tab" data-toggle="tab" href="#nav-descricao" role="tab" aria-controls="nav-descricao" aria-selected="true">Detalhes</a>
                <a class="nav-item nav-link" id="nav-links-tab" data-toggle="tab" href="#nav-links" role="tab" aria-controls="nav-links" aria-selected="false">Links</a>
                <a class="nav-item nav-link" id="nav-comenarios-tab" data-toggle="tab" href="#nav-comenarios" role="tab" aria-controls="nav-comenarios" aria-selected="false">Principais Comentários</a>
            </div>
        </nav>
        <div class="tab-content" id="nav-tabContent">
            <div class="tab-pane fade show active" id="nav-descricao" role="tabpanel" aria-labelledby="nav-descricao-tab">
                ${item.getDescricao()}<br />
                AVALIAÇÃO FINAL: ${item.calcularSomatorioDeAvaliacoes()}
            </div>
            <div class="tab-pane fade" id="nav-links" role="tabpanel" aria-labelledby="nav-links-tab">
                <ul class="list-group">
                    <c:forEach var="link" items="${item.getLinks()}">
                        <li class="list-group-item">ID: ${link.getIdLink()} | LINK: ${link.getLink()}</li>
                    </c:forEach>
                </ul>
            </div>
            <div class="tab-pane fade" id="nav-comenarios" role="tabpanel" aria-labelledby="nav-comenarios-tab">
                <c:forEach var="comentario" items="${item.getPrincipaisComentarios()}">
                    <% // request.setAttribute("isVertical", false); request.setAttribute("isDisabled", true);%>
                    <%--<%@include file="/WEB-INF/jspf/comentario.jspf" %>--%>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="col-1">
        <% request.setAttribute("isVertical", true); request.setAttribute("isDisabled", false);%>
        <%@include file="/WEB-INF/jspf/avaliacao-item.jspf" %>
    </div>
</div>


<hr/>
<h3>Todos os Comentários</h3>

<c:forEach var="comentario" items="${item.getComentarios()}">
    <% // request.setAttribute("isVertical", false); request.setAttribute("isDisabled", true);%>
    <%--<%@include file="/WEB-INF/jspf/comentario.jspf" %>--%>
</c:forEach>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
