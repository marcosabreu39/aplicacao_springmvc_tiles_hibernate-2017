<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:choose>
 <c:when test="${fn:contains(mensagem, 'Erro')}">
 <c:set var="classe" value="alert alert-danger"/>  
 </c:when>
 
 <c:otherwise>
 <c:set var="classe" value="text-info"/>
 </c:otherwise> 
  </c:choose>
  
 <div class="${classe}" role="alert">
 <h3 id="titulo">${mensagem}</h3>
 </div>

<fieldset class="form-group">
	
<legend class="scheduler-border">Dados cadastrais</legend>

<div style="text-align: center;">

<table class="table table-inverse table-hover">

	<tr scope="row">
		<td>Nome:</td>
		<td>${empregado.nome}</td>
	</tr>
	<tr scope="row">
		<td>Endereço:</td>
		<td>${empregado.endereco}</td>
	</tr>
	<tr scope="row">
		<td>CPF:</td>
		<td>${empregado.cpf}</td>
	</tr>
	
	<tr scope="row">
		<td>Departamento:</td>
	
		<td><c:if test="${not empty departamento.nomeDepartamento }">
	
	<c:out value="${departamento.nomeDepartamento}"></c:out>
	
	</c:if></td>
	</tr>	
	
	<tr scope="row">
		<td>Cadastrado em:</td>
		<td><fmt:formatDate value="${empregado.dataCadastro}" type="both" pattern="dd/MM/yyyy HH:mm:ss" dateStyle="full"/></td>
	</tr>
	
	</table>
	
	<table class="table table-bordered table-inverse">
	
	<tr scope="row">
	
	<td>
		
	<c:url value="/atualizar-dados/listar-empregados/listar-selecionado/atualizar-empregado" var="url2" />
		
	<form:form method="post" action="${url2}" commandName="empregado">
		
		<div class="form-group row">
		
		<form:hidden path="cpf"/>
				
		<div class="col-sm-12">		
		<button type="submit" class="btn btn-default">Atualizar dados pessoais do empregado <span class="glyphicon glyphicon-pencil"></span></button>
		</div>
		
		<div class="col-sm-12">		
		<form:errors path="cpf" class="form-control alert alert-danger" />		
		</div>
		
		</div>	
				
	</form:form>
	
	</td>
	
	</tr>
	
	<tr scope="row">
	
	<td>		
	
	<c:url value="/atualizar-dados/listar-empregados/listar-selecionado/atualizar-depto" var="url1" />	
		
	<form:form method="post" action="${url1}" commandName="empregado">
		
		<div class="form-group row">
		
		<form:hidden path="cpf"/>
		
		<div class="col-sm-12">		
		<button type="submit" class="btn btn-default">Atualizar o departamento do empregado <span class="glyphicon glyphicon-pencil"></span></button>
		</div>
		
		<div class="col-sm-12">		
		<form:errors path="cpf" id="form-error" class="form-control" />		
		</div>
		
		</div>
		
	</form:form>	
	
	</td>
	
	</tr>
	
	<tr scope="row">
	
	<td>
	
	<c:url value="/atualizar-dados/listar-empregados/listar-selecionado/remover-empregado" var="url1" />	
		
	<form:form method="post" action="${url1}" commandName="empregado">
		
		<div class="form-group row">
		
		<form:hidden path="cpf"/>
			
		<div class="col-sm-12">		
		<button type="submit" class="btn btn-default">Remover os dados do empregado <span class="glyphicon glyphicon-remove"></span></button>
		</div>
		
		<div class="col-sm-12">		
		<form:errors path="cpf" id="form-error" class="form-control" />		
		</div>
		
		</div>
		
	</form:form>	
	
	</td>	
	
	</tr>
	
</table>			

</div>		
		
</fieldset>