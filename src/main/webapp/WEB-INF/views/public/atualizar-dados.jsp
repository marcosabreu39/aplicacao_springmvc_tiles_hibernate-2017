<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored="false" %>

<h3 id="titulo">${mensagem}</h3>

<c:url value="/atualizar-dados/listar-empregados" var="url" />

<fieldset class="form-group">
	
<legend class="scheduler-border">Atualizar dados cadastrais de um empregado</legend>

<div style="text-align: center">

</div>

<form:form method="post" action="${url}" commandName="departamento">

	<div class="form-group row">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-5">
		<div class="btn-group">
		<form:select class="Dropdown-menu" path="id" id="id">
			<form:option class="dropdown-item" value="NONE" label="Selecione o departamento aonde o empregado está alocado" cssClass="default" />
			<form:options items="${departamentos}"  itemLabel="nomeDepartamento" itemValue="id"  />		
		</form:select>				
		</div>
		</div>
		<div class="col-sm-1">
		
		<button type="submit" class="btn btn-default">Listar Empregados do Departamento</button>
		</div>
		<div class="col-sm-4">
		<form:errors path="id" id="form-error" class="form-control" />		
		</div>
	</div>	
</form:form>

</fieldset>
