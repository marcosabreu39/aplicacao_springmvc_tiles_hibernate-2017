<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:choose>
 <c:when test="${fn:contains(mensagem, 'erro')}">
 <c:set var="classe" value="alert alert-danger"/>  
 </c:when>
 
 <c:when test="${fn:contains(mensagem, 'sucesso')}">
 <c:set var="classe" value="text-success"/>  
 </c:when>
 
 <c:otherwise>
 <c:set var="classe" value="text-info"/>
 </c:otherwise> 
 </c:choose>
  
 <div class="${classe}" role="alert">
 <h3 id="titulo">${mensagem}</h3>
 </div>

<fieldset class="form-group">

<%-- <c:url value="/excluir-departamento" var="url1" /> --%>

<c:url value="/detalhes-departamento" var="url1" />

<c:url value="/criar-departamento" var="url2" />
	
	<legend class="scheduler-border">Controle de Departamentos</legend>

<form:form method="post" action="${url1}" commandName="departamento">

<div class="form-group row">
	<label class="col-sm-2 control-label"></label>
	<div class="col-sm-5">
		<div class="btn-group">
		<form:select class="form-control" id="exampleSelect1" path="id">
		<form:option class="dropdown-item" selected="true" disabled="true" readonly="true" value="" label="Todos os departamentos" cssStyle="display: none;" />
		<form:options items="${departamentos}" itemLabel="nomeDepartamento" itemValue="id" />
		</form:select> 	
		</div>
		</div>
	</div>

		<div class="form-group row">
		<label class="col-sm-2 control-label">Excluir Departamento</label>
		<div class="col-sm-5">
		<button type="submit" class="btn btn-default">Detalhes do Departamento <span class="glyphicon glyphicon-th-list"></span></button>	
		</div>
		<div class="col-sm-4">
		<form:errors path="id" class="form-control alert alert-danger" />
		</div>
		</div>
		
</form:form>

<form:form method="post" action="${url2}" commandName="departamento">
	<div class="form-group row">
		<label class="col-sm-2 control-label">Novo Departamento:</label>	
		<div class="col-sm-5">	
		<form:input path="nomeDepartamento" type="text" class="form-control" placeholder="Novo departamento entre 3 e 25 caracteres." />		
		</div>
		<div class="col-sm-4">
		<form:errors path="nomeDepartamento" class="form-control alert alert-danger" />
		</div>
		</div>
		
		<div class="form-group row">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-5">
		<button type="submit" class="btn btn-default">Criar Departamento <span class="glyphicon glyphicon-save"></span></button>	
		</div>
		</div>	
	
</form:form>
	
	<table class="table table-inverse table-hover">
	
	<c:forEach var="status"  items="${results}">
	
	<tr scope="row"><td>${status.key}</td><td>${status.value}</td></tr>
	
	</c:forEach>
	
	</table>
	
</fieldset>	