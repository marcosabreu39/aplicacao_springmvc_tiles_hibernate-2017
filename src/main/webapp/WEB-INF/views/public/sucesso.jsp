<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h3 id="titulo">${mensagem}</h3>

<fieldset class="form-group">
	
<legend class="scheduler-border">Sucesso na operação</legend>

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
	<c:if test="${not empty departamento.nomeDepartamento }">
	<tr>
		<td>Departamento:</td>
	
		<td>
	
	<c:out value="${departamento.nomeDepartamento}"></c:out>
	
		</td>
	</tr>	
	</c:if>
	
	<c:if test="${not empty empregado.dataCadastro}">
	
	<tr>
		<td>Cadastrado em:</td>
		<td><fmt:formatDate value="${empregado.dataCadastro}" type="both" pattern="dd/MM/yyyy HH:mm:ss" dateStyle="full"/></td>
	</tr>
	
	</c:if>
	
	

</table>
</div>
</fieldset>