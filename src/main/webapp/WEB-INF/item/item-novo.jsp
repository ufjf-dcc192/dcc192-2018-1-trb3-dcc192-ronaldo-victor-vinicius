<%@page 
    import="java.util.Date" 
    errorPage="pagina-de-erro.jsp" 
    isErrorPage="false" 
    contentType="text/html" 
    pageEncoding="UTF-8" %>

<!--The core group of tags are the most commonly used JSTL tags.-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!--The JSTL formatting tags are used to format and display text, the date, the time, and numbers for internationalized Websites.-->
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="/WEB-INF/jspf/cabecalho.jspf" %>

<form method="POST">
    <div class="form-group">
        <label for="titulo">Título</label>
        <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Crie um título para o item">
    </div>
    <div class="form-group">
        <label for="descricao">Descricao</label>
        <input type="email" class="form-control" id="descricao" name="descricao" placeholder="Descreva o item">
    </div>
    <div class="form-group">
        <label for="link1">Links</label>
        <input type="text" class="form-control" id="link1" name="link1" placeholder="Adicione um link a este item">

    </div>
    <div class="input-group">
        <input type="text" class="form-control" placeholder="Recipient's username" aria-label="Recipient's username with two button addons" aria-describedby="button-addon4">
        <div class="input-group-append" id="button-addon4">
            <button class="btn btn-outline-secondary" type="button">Button</button>
            <button class="btn btn-outline-secondary" type="button">Button</button>
        </div>
    </div>
</div>

<button type="submit" class="btn btn-primary">Cadastrar</button>
</form>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
