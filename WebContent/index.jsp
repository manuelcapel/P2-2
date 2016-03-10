
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
</head>
<body>	

<div class="container">
  <div class="jumbotron">
    <h1 class="text-center">Ejemplo con Servlet Http, JPA y JSP</h1> 
  </div>
</div>

<div class="container">
    	<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Buscar</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">Registrar</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" action="ListaCorreosServlet" method="post" role="form" style="display: block;">
									<div class="form-group">
										<input type="text" name="email" id="email" tabindex="1" class="form-control" placeholder="Email" value="">
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="hidden" name="action" value="seleccionarUsuario"/>
												<input type="submit" name="login-submit" id="login-submit" tabindex="4" class="form-control btn btn-search" value="Buscar">
											</div>
										</div>
									</div>
									<div class="form-group">
										<c:if test="${not empty buscado}">
										Nombre : <c:out value="${buscado.getNombre()}" />
										<br />
										Apellido : <c:out value="${buscado.getApellido()}" />
										</c:if>
									</div>
								</form>
								<form id="register-form" action="ListaCorreosServlet" method="post" role="form" style="display: none;">
									<div class="form-group">
										<input type="text" name="nombre" id="nombre" tabindex="1" class="form-control" placeholder="Nombre" value="" required>
									</div>
									<div class="form-group">
										<input type="text" name="apellido" id="apellido" tabindex="1" class="form-control" placeholder="Apellido" value="" required>
									</div>
									<div class="form-group">
										<input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email" value="" required>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="hidden" name="action" value="insertar"/>
												<input type="submit" name="register-submit" id="register-submit" tabindex="4" class="form-control btn btn-register" value="Registrar usuario">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


<div class="container">
<div id="lista" class="container">
	<h3 class="text-center">Lista de usuarios</h3>
</div>
<c:if test="${empty usuarios}">
	<div id="labelBaseDatos" class="text-center">
		<span class="label label-warning">No hay usuarios en la base de datos</span>
	</div>
</c:if>
<c:if test="${not empty usuarios}">
	<table class="table table-striped">
		<thead>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Email</th>
		</thead>
		<tbody>
			<c:forEach var="u" items="${usuarios}">
				<tr>
					<form action="ListaCorreosServlet" method="post" role="form">
						<td>
							<input type="text" name="nombre" value="${u.getNombre()}"/>
						</td>
						<td>
							<input type="text" name="apellido" value="${u.getApellido()}"/>
						</td>
						<td>
							<input type="text" name="email" value="${u.getEmail()}" readonly/>								
						</td>
						<td>
							<input type="hidden" name="action" value="actualizar"/>
							<input type="submit" id="actualizar-usuario" value="Actualizar" class="btn btn-info" onclick="actualizar()"/>
						</td>
					</form>
					<td>							
						<form action="ListaCorreosServlet" method="post" role="form">
							<input type="hidden" name="action" value="eliminar"/>
							<input type="hidden" name="email" value="${u.getEmail()}"/>
							<input type="submit" value="Eliminar" class="btn btn-danger" onclick="borrar()" />
						</form>
					</td>
				</tr>	
			</c:forEach>
		</tbody>
	</table>
</c:if>
</div>

<div id="footer">
      <div class="container">
        <p class="text-muted credit"> </p>
      </div>
</div>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
  	<!-- <script src"js/jquery.min.js"></script> -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootbox.min.js"></script>
    <script>
    $(function() {

        $('#login-form-link').click(function(e) {
    		$("#login-form").delay(100).fadeIn(100);
     		$("#register-form").fadeOut(100);
    		$('#register-form-link').removeClass('active');
    		$(this).addClass('active');
    		e.preventDefault();
    	});
    	$('#register-form-link').click(function(e) {
    		$("#register-form").delay(100).fadeIn(100);
     		$("#login-form").fadeOut(100);
    		$('#login-form-link').removeClass('active');
    		$(this).addClass('active');
    		e.preventDefault();
    	});
    	
   	});
    
    function actualizar(){
    	alert("Usuario actualizado");
    }
    
    function borrar(){
    	alert("Usuario borrado");
    }
       
  
    </script>
</body>
</html>
