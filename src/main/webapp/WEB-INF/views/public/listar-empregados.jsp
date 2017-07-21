<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored="false" %>

<h3 id="titulo">${mensagem}</h3>

<c:url value="/atualizar-dados/listar-empregados/listar-selecionado" var="url" />

<fieldset class="form-group">
	
<legend class="scheduler-border">Selecionar Departamento</legend>

<div style="text-align: center">

<form:form method="post" action="${url}" commandName="empregado">
		
<table class="table table-inverse table-hover">
	<th>Selecione o empregado que deseja editar</th>
    <tr>
    <td>
        <ul style="text-decoration: none;">        
          <form:label path=""></form:label> <form:radiobuttons path="cpf" element="li" items="${empregados}" itemLabel="nome" itemValue="cpf" />        
        </ul>
    </td>
    </tr>
    <tr>
        <td>
            <button type="submit" class="btn btn-default">Selecionar empregado</button>
        </td>
    </tr>
</table>

</form:form>
</div>
</fieldset>