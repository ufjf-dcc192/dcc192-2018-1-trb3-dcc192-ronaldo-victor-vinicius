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

<%@include file="/WEB-INF/jspf/item.jspf" %>

<hr />

<form method="POST">
    <div class="form-group">
        <label for="titulo">Título do Comentário</label>
        <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Crie um título para o comentário" />
    </div>
    <div class="form-group">
        <label for="texto">Texto do Comentário</label>
        <textarea rows="4" class="form-control" id="texto" name="texto" placeholder="Digite aqui o seu comentário"></textarea>
    </div>
    
    <input type="hidden" id="id_item_comentado" name="id_item_comentado" value="${item.getIdItem()}">
    <button type="submit" class="btn btn-primary">Comentar</button>
</form>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
