<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<h3 id="titulo">${mensagem}</h3>

<fieldset class="form-group">
	
<legend class="scheduler-border">Dados do empregado selecionado</legend>

<div style="text-align: center;" align="center">
<table class="table table-inverse table-hover">

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
	
	<tr>
		<td>Departamento:</td>
	
		<td><c:if test="${not empty departamento.nomeDepartamento }">
	
	<c:out value="${departamento.nomeDepartamento}"></c:out>
	
	</c:if></td>
	</tr>	
	
	<tr>
		<td>Cadastrado em:</td>
		<td><fmt:formatDate value="${empregado.dataCadastro}" type="both" pattern="dd/MM/yyyy HH:mm:ss" dateStyle="full"/></td>
	</tr>
	
	<tr>
		<td>
	
	<c:url value="/atualizar-dados/listar-empregados/listar-selecionado/atualizar-empregado" var="url2" />
		
	<form:form method="post" action="${url2}" commandName="empregado">
		<form:hidden path="cpf"/>
				
		<button type="submit" class="btn btn-default">Atualizar dados do empregado</button>
				
		<form:errors path="cpf" id="form-error" class="form-control" />		
		
	</form:form>		
		</td>
		
		<td>
	
	<c:url value="/atualizar-dados/listar-empregados/listar-selecionado/remover-empregado" var="url1" />	
		
	<form:form method="post" action="${url1}" commandName="empregado">
		
		<form:hidden path="cpf"/>
				
		<button type="submit" class="btn btn-default">Remover os dados do empregado</button>
				
		<form:errors path="cpf" id="form-error" class="form-control" />		
		
	</form:form>
		</td>
	</tr>
</table>

</div>
</fieldset>