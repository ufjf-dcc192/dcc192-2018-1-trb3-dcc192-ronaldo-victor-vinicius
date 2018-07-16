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

<hr />
<h5>Estatísticas: </h5>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
