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

<div class="row">
    <div class="col-11">
        <nav>
            <div class="nav nav-tabs" id="nav-tab" role="tablist">
                <a class="nav-item nav-link active" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">Detalhes</a>
                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Links</a>
                <a class="nav-item nav-link" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">Principais Comentários</a>
            </div>
        </nav>
        <div class="tab-content" id="nav-tabContent">
            <div class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
                detalhes detalhes detalhes detalhes
                AVALIAÇÃO FINAL
            </div>
            <div class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
                linkslinkslinkslinkslinks
            </div>
        </div>
    </div>
    <div class="col-1">
        <div class="btn-group-vertical btn-block">
            <button type="button" class="btn btn-success"><i class="fas fa-angle-up"></i> &nbsp; 99</button>
            <button type="button" class="btn btn-danger"><i class="fas fa-angle-down"></i> &nbsp; 54</button>
        </div>
    </div>
</div>


<hr/>
<h3>Todos os Comentários</h3>

<div class="row">
    <div class="card col-11">
        <div class="card-body">
            <h5 class="card-title">Título do comentário</h5>
            <h6 class="card-subtitle mb-2 text-muted">Autor</h6>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
            <a href="#" class="card-link">Card link</a>
        </div>
    </div>
    <div class="col-1">
        <div class="btn-group-vertical btn-block">
            <button type="button" class="btn btn-success"><i class="fas fa-angle-up"></i> &nbsp; 5</button>
            <button type="button" class="btn btn-danger"><i class="fas fa-angle-down"></i> &nbsp; 2</button>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
