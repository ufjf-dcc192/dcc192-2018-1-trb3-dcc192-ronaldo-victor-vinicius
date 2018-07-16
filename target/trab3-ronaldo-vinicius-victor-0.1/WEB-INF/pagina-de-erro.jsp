<%@page isErrorPage="true"%>
<%request.setAttribute("titulo", "Página de Erro");%>
<%@include file="jspf/cabecalho.jspf" %>
<p style="color:red">
    Erro ocorrido em: <br>
    <%=request.getHeader("referer")%>
</p>
<p style="color:red">
    ERRO: <br>
    <%=exception.toString()%>
</p>
<%@include file="jspf/rodape.jspf" %>