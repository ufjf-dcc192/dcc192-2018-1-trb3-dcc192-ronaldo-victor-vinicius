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

<a class="btn btn-dark" href="usuario-dados.html">Meus Dados</a>
<a class="btn btn-dark" href="item-listar.html">Meus Itens</a>
<a class="btn btn-dark" href="meus-comentarios.html">Meus Comentários</a>
<a class="btn btn-dark" href="a-avaliar.html">Avaliações Pendentes</a>

<hr />

<h2>Estatísticas</h2>
<div class="row">
    <div class="col-6">
        <h4>Estatísticas sobre os MEUS ITENS</h4>
        <ul class="list-group">
            <li class="list-group-item">
                <div class="row">
                    <div class="col-6 text-right">Meus itens</div>
                    <div class="col-6">${countMeusItens}</div>
                </div>
            </li>
            <li class="list-group-item list-group-item-success">
                <div class="row">
                    <div class="col-6 text-right">Total de Avaliações Positivas</div>
                    <div class="col-6">${countAvaliacoesPositivasMeusItens}</div>
                </div>
            </li>
            <li class="list-group-item list-group-item-danger">
                <div class="row">
                    <div class="col-6 text-right">Total de Avaliações Negativas</div>
                    <div class="col-6">${countAvaliacoesNegativasMeusItens}</div>
                </div>
            </li>
        </ul>
    </div>
    <div class="col-6">
        <h4>Minhas AVALIAÇÕES feitas a ITENS</h4>
        <ul class="list-group">
            <li class="list-group-item list-group-item-success">
                <div class="row">
                    <div class="col-6 text-right">Total de Avaliações Positivas</div>
                    <div class="col-6">${countAvaliacoesPositivasFeitasAItens}</div>
                </div>
            </li>
            <li class="list-group-item list-group-item-danger">
                <div class="row">
                    <div class="col-6 text-right">+ Total de Avaliações Negativas</div>
                    <div class="col-6">${countAvaliacoesNegativasFeitasAItens}</div>
                </div>
            </li>
            <li class="list-group-item">
                <div class="row">
                    <div class="col-6 text-right">= Total avaliações feitas</div>
                    <div class="col-6">${countAvaliacoesFeitasAItens}</div>
                </div>
            </li>
        </ul>
    </div>
</div>

<hr />

<div class="row">
    <div class="col-6">
        <h4>Estatísticas sobre os MEUS COMENTÁRIOS</h4>
        <ul class="list-group">
            <li class="list-group-item">
                <div class="row">
                    <div class="col-6 text-right">Meus Comentários</div>
                    <div class="col-6">${countMeusComentarios}</div>
                </div>
            </li>
            <li class="list-group-item list-group-item-success">
                <div class="row">
                    <div class="col-6 text-right">Total de Avaliações Positivas</div>
                    <div class="col-6">${countAvaliacoesPositivasMeusComentarios}</div>
                </div>
            </li>
            <li class="list-group-item list-group-item-danger">
                <div class="row">
                    <div class="col-6 text-right">Total de Avaliações Negativas</div>
                    <div class="col-6">${countAvaliacoesNegativasMeusComentarios}</div>
                </div>
            </li>
        </ul>
    </div>
    <div class="col-6">
        <h4>Minhas AVALIAÇÕES feitas a COMENTÁRIOS</h4>
        <ul class="list-group">
            <li class="list-group-item list-group-item-success">
                <div class="row">
                    <div class="col-6 text-right">Total de Avaliações Positivas</div>
                    <div class="col-6">${countAvaliacoesPositivasFeitasAComentarios}</div>
                </div>
            </li>
            <li class="list-group-item list-group-item-danger">
                <div class="row">
                    <div class="col-6 text-right">+ Total de Avaliações Negativas</div>
                    <div class="col-6">${countAvaliacoesNegativasFeitasAComentarios}</div>
                </div>
            </li>
            <li class="list-group-item">
                <div class="row">
                    <div class="col-6 text-right">= Total de avaliações feitas</div>
                    <div class="col-6">${countAvaliacoesFeitasAComentarios}</div>
                </div>
            </li>
        </ul>
    </div>
</div>




<%@include file="/WEB-INF/jspf/rodape.jspf" %>
