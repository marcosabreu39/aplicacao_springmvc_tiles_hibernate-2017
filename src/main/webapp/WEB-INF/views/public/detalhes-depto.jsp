<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:choose>
	<c:when test="${fn:contains(mensagem, 'erro')}">
		<c:set var="classe" value="alert alert-danger" />
	</c:when>

	<c:when test="${fn:contains(mensagem, 'sucesso')}">
		<c:set var="classe" value="text-success" />
	</c:when>

	<c:otherwise>
		<c:set var="classe" value="text-info" />
	</c:otherwise>
</c:choose>

<div class="${classe}" role="alert">
	<h3 id="titulo">${mensagem}</h3>
</div>

<fieldset class="form-group">

	<c:url value="/excluir-departamento" var="url" />

	<legend class="scheduler-border">Controle de Departamentos</legend>

	<div style="text-align: center;">
	<span>Detalhes do departamento ${departamento.nomeDepartamento}</span>
	</div>

	<table class="table table-inverse table-hover">

		<thead class="thead-inverse">

			<tr>

				<th>Nome</th>
				<th>Endereço</th>
				<th>CPF</th>

			</tr>

		</thead>

		<c:forEach var="empregado" items="${departamento.empregados}">

			<tr>

				<td>${empregado.nome}</td>
				<td>${empregado.endereco}</td>
				<td>${empregado.cpf}</td>

			</tr>

		</c:forEach>

		<tr>

			<td colspan="3">Total de empregados:${fn:length(departamento.empregados)}</td>

		</tr>

	</table>

	<form:form method="post" action="${url}" commandName="departamento">

		<form:hidden path="id" />

		<div class="form-group row">
			<label class="col-sm-2 control-label">Excluir Departamento</label>
			<div class="col-sm-5">
				<button type="submit" class="btn btn-default">
					Excluir Departamento <span class="glyphicon glyphicon-remove"></span>
				</button>
			</div>
			<div class="col-sm-4">
				<form:errors path="id" class="form-control alert alert-danger" />
			</div>
		</div>

	</form:form>

</fieldset>
