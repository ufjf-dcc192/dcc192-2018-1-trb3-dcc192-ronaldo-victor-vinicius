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
        <input type="text" class="form-control" id="descricao" name="descricao" placeholder="Descreva o item">
    </div>
    <!--    <div class="form-group">
            <label for="links">Links</label>
            <button class="btn btn-outline-secondary" type="button">Adicionar</button>
            <div class="input-group" id="links">
                <input type="text" id="link1" name="link1" class="form-control" placeholder="Digite um link" aria-label="Digite um link" aria-describedby="button-addon4">
                <div class="input-group-append" id="button-addon4" name="button-addon4">
                    <button class="btn btn-outline-secondary" type="button">Remover</button>
                </div>
            </div>
        </div>-->


    <div class="field_wrapper">
        <div class="form-group">
            <label>Links</label>
            <button class="btn btn-outline-secondary add_button" type="button" onclick="javascript:void(0);">Adicionar</button>
        </div>
        <div class="form-group">
            <div class="input-group">
                <input type="text" id="link1" name="link1" class="form-control" placeholder="Digite um link" aria-label="Digite um link" aria-describedby="button-addon1">
                <div class="input-group-append" id="button-addon1" name="button-addon1">
                    <button class="btn btn-outline-secondary remove_button" type="button" onclick="javascript:void(0);">Remover</button>
                </div>
            </div>
        </div>
    </div>
    <!--    <div class="field_wrapper">
            <div>
                <input type="text" name="links[]" value=""/>
                <a href="javascript:void(0);" class="add_button" title="Add field"><img src="add-icon.png"/></a>
            </div>
        </div>-->
    <script type="text/javascript">
        $(document).ready(function () {
            var addButton = $('.add_button'); //Add button selector
            var wrapper = $('.field_wrapper'); //Input field wrapper
            var x = 2;

            //Once add button is clicked
            $(addButton).click(function () {
                $(wrapper).append(`<div class="form-group">
        <div class="input-group">
            <input type="text" id="link` + x + `" name="link` + x + `" class="form-control" placeholder="Digite um link" aria-label="Digite um link" aria-describedby="button-addon` + x + `">
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
    <!--    <div class="form-group">
            <label for="link1">Links</label>
            <input type="text" class="form-control" id="link1" name="link1" placeholder="Adicione um link a este item">
        </div>-->
    <!--    <label>Informe o link e clique em adicionar:</label>
        <div class="input-group">
            <input class="form-control" type="text" placeholder="Insira aqui o link a ser vinculado ao item" id="link"/><br/>
            <span class="input-group-btn">
                <button class="btn btn-primary" type="button" id="add">Adicionar</button>
                <button class="btn btn-warning" type="button" id="remove">Remover</button>
            </span>
        </div>
        <label>Links</label>
        <select class="form-control" multiple="multiple" name="links" id="links">
        </select>-->
</div>

<button type="submit" class="btn btn-primary">Cadastrar</button>
</form>

<%@include file="/WEB-INF/jspf/rodape.jspf" %>
