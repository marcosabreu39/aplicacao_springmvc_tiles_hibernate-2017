<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
    <title><tiles:getAsString name="title"/></title>
    
    <!-- jquery -->
    <spring:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js" var="urlJquery"/>
	<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <!-- Bootstrap -->      
    <spring:url value="/resources/bootstrap-3.3.7/dist/css/bootstrap.css" var="urlBoostrapCss" />               
    <link href="${urlBoostrapCss}" rel="stylesheet">
    
    <!-- css -->
    <spring:url value="/resources/css/custom.css" var="urlCss" />        
    <link href="${urlCss}" rel="stylesheet">
            
  </head>
  <body>
    <div class="container-fluid">
    
        <div class="row">
            <div id="header">
                <tiles:insertAttribute name="nav" />
            </div>
        </div>
        
        <div class="row" style="min-height: 500px;">
            <div id="body">
                <tiles:insertAttribute name="body" />
            </div>
        </div>
            
            <div class="row">
            <div id="footer">
                <tiles:insertAttribute name="footer" />
            </div>
        </div>

    </div>
    
 </html>