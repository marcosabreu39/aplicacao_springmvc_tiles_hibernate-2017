<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ page isELIgnored="false" %>

<div>

<h3 id="titulo">${mensagem}</h3>	
	
<c:url value="/cadastro" var="url" />
	
<fieldset class="form-group">
	
<legend class="scheduler-border">Cadastro de Empregados</legend>
	
<form:form method="post" action="${url}" commandName="empregado"  >
	
	<div class="form-group row">
		<label class="col-sm-2 control-label">Departamento:</label>
		<div class="col-sm-5">
		<div class="btn-group">
		<%-- <form:select class="Dropdown-menu" path="departamento">
		<form:option class="dropdown-item" value="NONE" label="Selecione o departamento aonde o empregado ficará alocado" cssClass="default" />
		<form:options path="codDepartamento" items="${empregado.departamentos.nomeDepartamento}"/>		
		</form:select> --%>
		
		<select class="Dropdown-menu">
		  <option class="dropdown-item" style="background-color: black; color: white;">Selecione o departamento aonde o empregado ficará alocado</option>
     	  <c:forEach var="departamento" items="${empregado.departamentos}">
          <option class="dropdown-item"><c:out value="${departamento.nomeDepartamento}"/></option>
                    
     	  </c:forEach>
     	  
     	  <c:set  var="codDep" value="${departamento.id}"/>
     	  
		</select>
		<form:hidden path="codDepartamento" value="${codDep}" />	 		
		</div>
		</div>
		<div class="col-sm-4">
		<form:errors path="codDepartamento" id="form-error" class="form-control" />		
		</div>
	</div>
			
	<div class="form-group row">
		<label class="col-sm-2 control-label">Nome:</label>	
		<div class="col-sm-5">	
		<form:input path="nome" type="text" class="form-control" placeholder="Insira o nome do empregado." />		
		</div>
		<div class="col-sm-4">
		<form:errors path="nome" id="form-error" class="form-control" />		
		</div>
		</div>
		
		<div class="form-group row">
		<label class="col-sm-2 control-label">Endereço:</label>	
		<div class="col-sm-5">	
		<form:input path="endereco" type="text" class="form-control" placeholder="Insira o endereço do empregado." />		
		</div>
		<div class="col-sm-4">
		<form:errors path="endereco" id="form-error" class="form-control" />		
		</div>
		</div>
				
		<div class="form-group row">
		<label class="col-sm-2 control-label">CPF:</label>	
		<div class="col-sm-5">	
		<form:input path="cpf" type="text" class="form-control" placeholder="Insira o CPF do empregado." />			
		</div>
		<div class="col-sm-4">
		<form:errors path="cpf" class="form-control" id="form-error" />		
		</div>
		</div>		
		
		<div class="form-group row">
		<label class="col-sm-2 control-label"></label>
		<div class="col-sm-5">
		<button type="submit" class="btn btn-default">Cadastrar o novo empregado</button>	
		</div>
		</div>		
			
	</form:form>	

</fieldset>
</div>
