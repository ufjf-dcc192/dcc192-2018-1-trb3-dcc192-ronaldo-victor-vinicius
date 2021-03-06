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

<h5>Grupo: </h5>
<h6>José Ronaldo Silveira Miguel - 201176016</h6>
<h6>Vinícius Filipe de Sousa Clemente - 201276042</h6>
<h6>Victor Crisóstomo Cruz Reis - 201176037</h6>

<br />

<div class="alert alert-warning" role="alert">
    <h4>Sobre a AVALIAÇÃO de itens e de comentários</h4>
    <ul>
        <li>
            A página "/avaliar.html" tornou-se desnecessária pois do lado de cada 
            ITEM e de cada COMENTÁRIO tem dois botões que os avaliam positiva ou 
            negativamente.
        </li>
        <li>
            Ao clicar pela primeira vez em um botão de avaliação, executa-se um 
            INSERT no banco.
        </li>
        <li>
            Ao clicar pela segunda vez num mesmo botão executa-se um DELETE do 
            registro no banco. 
        </li>
        <li>
            Ao clicar em um dos botões e depois clicar em outro será feito um 
            UPDATE no registro.
        </li>
    </ul>
</div>

<div class="alert alert-warning" role="alert">
    <h4>Sobre comentários</h4>
    <ul>
        <li>
            A página "/item-comentarios.html​." tornou-se desnecessária pois do 
            a página "/item?id_item=123" além de destacar os principais comentários 
            também exibe os demais comentários.
        </li>
    </ul>
</div>



<hr />
<h5>Estatísticas: </h5>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
