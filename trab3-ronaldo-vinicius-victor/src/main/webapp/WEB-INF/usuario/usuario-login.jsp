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

<form>
    <div class="form-group">
        <label for="login">Login</label>
        <input type="text" class="form-control" id="login" placeholder="Digite seu login">
    </div>
    <div class="form-group">
        <label for="senha">Senha</label>
        <input type="password" class="form-control" id="senha" placeholder="Digite sua senha">
    </div>

    <button type="submit" class="btn btn-primary">Entrar</button>
</form>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
