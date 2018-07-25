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

<form method="POST">
    <div class="form-group">
        <label for="nomeCompleto">Nome Completo</label>
        <input type="text" class="form-control" maxlength="100" id="nomeCompleto" name="nomeCompleto" placeholder="Digite seu nome completo">
    </div>
    <div class="form-group">
        <label for="login">E-mail</label>
        <input type="email" class="form-control" maxlength="100" id="email" name="email" placeholder="Digite seu email">
    </div>
    <div class="form-group">
        <label for="login">Login</label>
        <input type="text" class="form-control" maxlength="20" id="login" name="login" placeholder="Digite seu login">
    </div>
    <div class="form-group">
        <label for="senha">Senha</label>
        <input type="password" class="form-control" maxlength="20" id="senha" name="senha" placeholder="Digite sua senha">
    </div>

    <button type="submit" class="btn btn-primary">Cadastrar</button>
</form>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
