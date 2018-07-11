<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>     
    </head>
    <body>
        <div class="container jumbotron">
        <!-- c:if test="${pageContext.request.method=='POST'}"-->
            <form action="estudiante" method="post"  role="form" data-toggle="validator" >
        <!-- c:if-->
        <c:set var="income" scope="session" value="${4000*4}"/>  
        <c:if test="${income > 8000}">  
            <p>My income is: <c:out value="${income}"/><p>  
        </c:if>  
         
                <c:if test ="${empty action}">                        	
                    <c:set var="action" value="guardar"/>
                </c:if>
                <input type="hidden" id="action" name="action" value="${action}">
                <input type="hidden" id="idEstudiante" name="idEstudiante" value="${estudiante.id}">
                <br><br>
                <h2>Detalle del Estudiante</h2>
                <hr><br>
                <div class="form-group">
                    <label for="nombre" class="control-label col-xs-4">Nombre:</label>
                    <input type="text" name="nombre" id="nombre" class="form-control" value="${estudiante.nombre}" required />                                   
                    
                    <label for="alias" class="control-label col-xs-4">Alias:</label>                    
                    <input type="text" name="alias" id="alias" class="form-control" value="${estudiante.alias}" required /> 

                    <label for="apellido" class="control-label col-xs-4">Apellidos:</label>                   
                    <input type="text" name="apellido" id="apellido" class="form-control" value="${estudiante.apellidos}" required /> 

                    <label for="telefono" class="control-label col-xs-4">Teléfono:</label>
                    <input type="text" name="telefono" id="telefono" class="form-control" value="${estudiante.telefono}" required/>

                    <label for="email" class="control-label col-xs-4">E-mail:</label>                   
                    <input type="text" name="email" id="email" class="form-control" value="${estudiante.email}" placeholder="Escriba su email" required/>

                    <label for="fnacimiento" class="control-label col-xs-4">Fecha de Nacimiento</label>                 
                    <input type="text" pattern="^\d{2}[-/.]\d{2}[-/.]\d{4}$" name="fnacimiento" id="fnacimiento" class="form-control" value="${estudiante.fechaNacimiento}" maxlength="10" placeholder="dd-MM-yyyy" required/>

                    <br></br>
                    <button type="submit" class="btn btn-primary">Guardar</button> 
                </div>                                                      
            </form>
        </div>
    </body>
</html>