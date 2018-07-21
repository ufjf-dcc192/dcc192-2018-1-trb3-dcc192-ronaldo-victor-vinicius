<%@page 
    import="br.com.trab3.modelos.Item" 
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
        <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Crie um título para o item" value="${item.getTitulo()}" />
    </div>
    <div class="form-group">
        <label for="descricao">Descricao</label>
        <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Descreva o item" value="${item.getDescricao()}" />
    </div>

    <div class="field_wrapper">
        <div class="form-group">
            <label>Links</label>
            <button class="btn btn-outline-secondary add_button" type="button" onclick="javascript:void(0);">Adicionar</button>
        </div>
        <c:forEach var="link" items="${item.getLinks()}">
            <div class="form-group">
                <div class="input-group">
                    <input value="${link.getLink()}" type="text" id="link${item.getLinks().indexOf(link)+1}" name="link${item.getLinks().indexOf(link)+1}" class="form-control" placeholder="Digite um link" aria-label="Digite um link" aria-describedby="button-addon${item.getLinks().indexOf(link)+1}" />
                    <div class="input-group-append" id="button-addon${item.getLinks().indexOf(link)+1}" name="button-addon${item.getLinks().indexOf(link)+1}">
                        <button class="btn btn-outline-secondary remove_button" type="button" onclick="javascript:void(0);">Remover</button>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <script type="text/javascript">
        $(document).ready(function () {
            var addButton = $('.add_button'); //Add button selector
            var wrapper = $('.field_wrapper'); //Input field wrapper
            var x = ${item.getLinks().size()+1};

            //Once add button is clicked
            $(addButton).click(function () {
                $(wrapper).append(`<div class="form-group">
        <div class="input-group">
            <input type="text" id="link` + x + `" name="link` + x + `" class="form-control" placeholder="Digite um link" aria-label="Digite um link" aria-describedby="button-addon` + x + `" />
            <div class="input-group-append" id="button-addon` + x + `" name="button-addon` + x + `">
                <button class="btn btn-outline-secondary remove_button" type="button" onclick="javascript:void(0);">Remover</button>
            </div>
        </div>
    </div>`); //Add field html
                x++; //Increment field counter
            });

            //Once remove button is clicked
            $(wrapper).on('click', '.remove_button', function (e) {
                e.preventDefault();
                $(this).parent('div').parent('div').remove(); //Remove field html
                x--; //Decrement field counter
            });
        });
    </script>
</div>

<input type="hidden" id="id_item" name="id_item" value="${item.getIdItem()}">
<button type="submit" class="btn btn-primary">Salvar Edições</button>
</form>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
