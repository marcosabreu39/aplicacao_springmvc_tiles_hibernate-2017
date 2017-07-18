<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<h3 id="titulo">${mensagem}</h3>

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
		<td>${empregado.departamento}</td>
	</tr>
	<tr>
		<td>Cadastrado em:</td>
		<td><fmt:formatDate value="${empregado.dataCadastro}" type="both" pattern="dd/MM/yyyy HH:mm:ss" dateStyle="full"/></td>
	</tr>

</table>