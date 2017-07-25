<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-inverse navbar-static-top">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="<c:url value="/"/>">Projeto</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">      
      <ul class="nav navbar-nav">
      	<c:set var="ativo" value="${pagina == 'index' ? 'active' : ''}"></c:set>            
        <li class="${ativo}"><a href="<c:url value="/"/>"><i class="glyphicons glyphicons-home"></i>Home</a></li>
        <c:set var="ativo" value="${pagina == 'cadastro' ? 'active' : ''}"></c:set>
        <li class="${ativo}"><a href="<c:url value="/cadastro"/>">Cadastrar Empregado</a></li>
        <c:set var="ativo" value="${pagina == 'dados' ? 'active' : ''}"></c:set>
        <li class="${ativo}"><a href="<c:url value="/atualizar-dados"/>">Atualizar Dados</a></li>        
      </ul>      
      
      <ul class="nav navbar-nav navbar-right">        
        <li class="dropdown">
        <c:set var="ativo" value="${pagina == 'departamento' ? 'active' : ''}"></c:set>
        <li class="${ativo}"><a href="<c:url value="/criar-departamento"/>">Criar Departamento</a></li>        
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>