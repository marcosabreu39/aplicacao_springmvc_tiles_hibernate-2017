<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<!DOCTYPE html>
<html lang="pt-BR">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
   
    <title><tiles:getAsString name="title"/></title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha/js/bootstrap.min.js"></script>
    <link href="resources/css/custom.css" rel="stylesheet">    
        
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