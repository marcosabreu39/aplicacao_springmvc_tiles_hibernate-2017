<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored="false" %>

<h3 id="titulo">${mensagem}</h3>

<c:url value="/cadastro/selecionar-depto/concluir" var="url" />

<fieldset class="form-group">
	
<legend class="scheduler-border">Selecionar Departamento</legend>

<div style="text-align: center">

<table class="table table-inverse table-hover">

<%-- <c:forEach items="${empregados}" var="empregado"></c:forEach> --%>

	<tr>
		<td>Nome:</td>
		<td>${empregado.nome}</td>
	</tr>
	<tr>
		<td>Endereço:</td>
		<td>${empregado.endereco}</td>
	</tr>
	<tr>
		<td>CPF:</td>
		<td>${empregado.cpf}</td>
	</tr>
		
</table>	

</div>

<form:form method="post" action="${url}" commandName="departamento">

	<div class="form-group row">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-5">
		<div class="btn-group">
		<form:select class="Dropdown-menu" path="id" id="id">
			<form:option class="dropdown-item" value="NONE" label="Selecione o departamento aonde o empregado ficará alocado" cssClass="default" />
			<form:options items="${departamentos}"  itemLabel="nomeDepartamento" itemValue="id"  />		
		</form:select>				
		</div>
		</div>
		<div class="col-sm-1">
		
		<button type="submit" class="btn btn-default">Finalizar o Cadastro</button>
		</div>
		<div class="col-sm-4">
		<form:errors path="id" id="form-error" class="form-control" />		
		</div>
	</div>	
</form:form>

</fieldset>
