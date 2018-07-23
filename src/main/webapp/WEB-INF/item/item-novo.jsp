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
        <label for="titulo">Título</label>
        <input type="text" class="form-control" id="titulo" name="titulo" placeholder="Crie um título para o item" />
    </div>
    <div class="form-group">
        <label for="descricao">Descricao</label>
        <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Descreva o item" />
    </div>

    <div class="field_wrapper">
        <div class="form-group">
            <label>Links</label>
            <button class="btn btn-outline-secondary add_button" type="button" onclick="javascript:void(0);">Adicionar</button>
        </div>
        <div class="form-group">
            <div class="input-group">
                <input type="text" id="link1" name="link1" class="form-control" placeholder="Digite um link" aria-label="Digite um link" aria-describedby="button-addon1" />
                <div class="input-group-append" id="button-addon1" name="button-addon1">
                    <button class="btn btn-outline-secondary remove_button" type="button" onclick="javascript:void(0);">Remover</button>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        $(document).ready(function () {
            var addButton = $('.add_button'); //Add button selector
            var wrapper = $('.field_wrapper'); //Input field wrapper
            var x = 2;

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

<button type="submit" class="btn btn-primary">Cadastrar</button>
</form>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
