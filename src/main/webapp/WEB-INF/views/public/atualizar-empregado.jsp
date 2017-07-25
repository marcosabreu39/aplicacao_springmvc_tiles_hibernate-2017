<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<c:choose>
 <c:when test="${fn:contains(mensagem, 'erro')}">
 <c:set var="classe" value="alert alert-danger"/>  
 </c:when>
 
 <c:otherwise>
 <c:set var="classe" value="text-info"/>
 </c:otherwise> 
  </c:choose>
  
 <div class="${classe}" role="alert">
 <h3 id="titulo">${mensagem}</h3>
 </div>

<div>

<c:url value="/atualizar-dados/listar-empregados/listar-selecionado/atualizar-empregado/concluir" var="url" />
	
<fieldset class="form-group">
	
<legend class="scheduler-border">Atualização do cadastro</legend>
	
<form:form method="post" action="${url}" commandName="empregado">	
				
	<div class="form-group row">
		<label class="col-sm-2 control-label">Nome:</label>	
		<div class="col-sm-5">	
		<form:input path="nome" type="text" class="form-control" placeholder="Insira o nome do empregado." />		
		</div>
		<div class="col-sm-4">
		<form:errors path="nome" class="form-control alert alert-danger" />		
		</div>
	</div>
		
		<div class="form-group row">
		<label class="col-sm-2 control-label">Endereço:</label>	
		<div class="col-sm-5">	
		<form:input path="endereco" type="text" class="form-control" placeholder="Insira o endereço do empregado." />		
		</div>
		<div class="col-sm-4">
		<form:errors path="endereco" class="form-control alert alert-danger" />		
		</div>
		</div>
				
		<div class="form-group row">
		<label class="col-sm-2 control-label">CPF:</label>	
		<div class="col-sm-5">	
		<form:input path="cpf" type="text" class="form-control" placeholder="Insira o CPF do empregado." />			
		</div>
		<div class="col-sm-4">
		<form:errors path="cpf" class="form-control alert alert-danger" />		
		</div>
		</div>		
		
		<div class="form-group row">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-5">
		<button type="submit" class="btn btn-default">Atualizar os dados do empregado <span class="glyphicon glyphicon-save"></span></button>	
		</div>
		</div>		
			
	</form:form>	

</fieldset>
</div>
		