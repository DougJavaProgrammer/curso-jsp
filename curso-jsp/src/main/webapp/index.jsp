<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<title>Curso JSP</title>

<style type="text/css">
form {
	position: absolute;
	top: 30%;
	left: 40%;
	right: 30%;
}

h5 {
	position: absolute;
	top: 20%;
	right: 30%;
	left: 40%;
}

button {
	position: absolute;
	top: 100%;
	right: 33%;
	left: 0%;
}
</style>

</head>
<body>

	<h5>Bem vindo ao curso de JSP</h5>

	<form action="ServletLogin" method="post"
		class="row g-3 needs-validation" novalidate>
		<input type="hidden" value="<%=request.getParameter("url")%>"
			name="url">

		<div class="col-md-6">
			<label class="form-label">Login</label> <input type="text"
				class="form-control" placeholder="CPF ou E-mail" required="required">
			<div class="invalid-feedback">Obrigatório informar um login</div>
			<div class="valid-feedback">Tudo ok por aqui</div>
			</div>
									
			<div class="col-md-6">
				<label class="form-label">Senha</label> <input type="password"
					class="form-control" placeholder="admin" required="required">
					<div class="invalid-feedback">Senha em branco</div>
					<div class="valid-feedback">Tudo ok por aqui também</div>
			</div>
			<div class="col-12">
				<button type="submit" class="btn btn-primary">Acessar</button>
			</div>
			
	</form>

	<h4>${msg}</h4>

	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous">
		
	</script>



	<!--script gerado por bootstrap para validar campos de entrada-->
	<script>
		//Example starter JavaScript for disabling form submissions if there are invalid fields
		(function() {
			'use strict'

			// Fetch all the forms we want to apply custom Bootstrap validation styles to
			var forms = document.querySelectorAll('.needs-validation')

			// Loop over them and prevent submission
			Array.prototype.slice.call(forms).forEach(function(form) {
				form.addEventListener('submit', function(event) {
					if (!form.checkValidity()) {
						event.preventDefault()
						event.stopPropagation()
					}

					form.classList.add('was-validated')
				}, false)
			})
		})()
	</script>

</body>
</html>