<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<c:if test="${fn:contains(mensagem, 'erro')}">			
			<c:set var="cor" value="color: red;"/>
			</c:if>
			
<c:if test="${fn:contains(mensagem, 'sucesso')}">			
			<c:set var="cor" value="color: green;"/>
			</c:if>			

<h3 id="titulo" style="${cor}">${mensagem}</h3>

<fieldset class="form-group">
	
	<legend class="scheduler-border">Controle de Departamentos</legend>

<div class="form-group row">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-5">
		<div class="btn-group">
		<form:select class="form-control" id="exampleSelect1" path="departamento">
		<form:option class="dropdown-item" value="NONE" label="Departamentos que já existem" cssClass="default" />
		<form:options items="${departamentos}" itemLabel="nomeDepartamento" itemValue="id" />
		</form:select> 	
		</div>
		</div>
</div>

<form:form method="post" action="criar-departamento" commandName="departamento">
	<div class="form-group row">
		<label class="col-sm-2 control-label">Novo Departamento:</label>	
		<div class="col-sm-5">	
		<form:input path="nomeDepartamento" type="text" class="form-control" placeholder="Novo departamento entre 3 e 25 caracteres." />		
		</div>
		<div class="col-sm-4">
		<form:errors path="nomeDepartamento" class="form-control" id="form-error" />
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