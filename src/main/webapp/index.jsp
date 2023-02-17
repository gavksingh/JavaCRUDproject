
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
<title> Project</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
.error {
	color: red;
	font-style: italic;
}
</style>

<link rel="stylesheet" href="css/style.css">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<body style="font-size: 14px;">

	<%-- <input type="hidden" id="status" value="<%= request.getAttribute("status") %>"> --%>

	<nav class="navbar navbar-dark bg-dark" style="margin-bottom: 15px;"
		style="font-size: 14px;">
		<h3>Employee Management CRUD Application</h3>
	</nav>
	<br>
	<!-- <form id = "frmEmp"> -->
	<div class="row" style="padding-left:12px; width: 117%;">
	<form id = "frmEmp">
		<div class="col-sm-12">
			<div class="container" style="font-size:27px; width: 105%;">

				<!-- <form id="frmEmp"> -->
					<div class="one-line">
						<div class="form-group" align="left">
							<label>First Name </label> <input type="text" name="fname"
								id="fname" class="form-control" style="height: 40px;"
								placeholder="First + Middle Name" required="">
						</div>

						<div class="form-group">
							<label>Last Name</label> <input type="text" name="lname"
								id="lname" class="form-control" style="height: 40px"
								placeholder="last name" size="50px" required>
						</div>
					</div>

					<div class="one-line">
						<div class="form-group">

							<label>Email ID : </label> <input type="email" name="email"
								id="email" class="form-control" style="height: 40px"
								placeholder="enter email id" size="30px" required>
						</div>

						<div class="form-group">
							<label>Contact No : </label> <input type="number" name="number"
								id="number" class="form-control" style="height: 40px"
								placeholder="enter mobile no " size="30px" required>
						</div>
					</div>

					<div class="form-group">
						<label for="address">Address :</label>
						<textarea name="address" id="address" class="form-control" rows="5"
							required></textarea>
					</div>

					<!-- <textarea class = "form-control" name = "address" placeholder="Address"></textarea>
                        <br/> -->

					<div class="form-group">
						<div class="gender">
							<label for="gender" class="radio-label">Gender :</label>
							<div class="form-radio-item">
								<input type="radio" name="gender" id="male" value="Male">
								<label for="male">Male</label> <span class="check"></span>
							</div>
							<div class="form-radio-item">
								<input type="radio" name="gender" id="female" value="Female">
								<label for="female">Female</label> <span class="check"></span>
							</div>
						</div>
					</div>

					<!-- <div class="form-check form-check-inline">
					
						<input class="form-check-input" type="radio"
							name="inlineRadioOptions" id="inlineRadio1" value="option1">
						<label class="form-check-label" for="inlineRadio1">1</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input" type="radio"
							name="inlineRadioOptions" id="inlineRadio2" value="option2">
						<label class="form-check-label" for="inlineRadio2">2</label>
					</div> -->


					<div class="form-group" align="right" style="zoom: 1.3;">
						
						
					<input type="submit" class="btn btn-info" id="add" name="add"
							onclick="add()" value="Submit" />
						<!-- <button type="button" class="btn btn-info" id="add" name="add"
							onclick="add()">Add</button> -->
						<input type="reset" class="btn btn-warning" id="reset"
							value="Reset" /> 
						<input type="button" class="btn btn-warning" id="update" value="Update"
							onclick="update()" />
						<!-- <button type="button" class="btn btn-danger" id="get_delete"
							onclick="get_delete()">Delete</button> -->
					</div>
			<!-- 	</form> -->
			</div>
		</div>
		</form>

		<div class="col-sm-7" style="padding-left:15px; zoom: 0.97;">

			<div class="panel-body">
				<table id="tbl-student" class="table table-bordered" cellspacing="0"
					width="100%">
					<thead>
						<tr>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>
							<th></th>


						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>


<!-- 	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script> -->

	<script src="https://code.jquery.com/jquery-3.6.3.min.js"
		integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU="
		crossorigin="anonymous"></script>

<!-- 	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script> -->

<!-- 	<script src="component/jquery/jquery.js" type="text/javascript"></script>
	<script src="component/jquery/jquery.min.js" type="text/javascript"></script>
	<script src="component/jquery.validate.min.js" type="text/javascript"></script> -->

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/jquery-validation@1.17.0/dist/jquery.validate.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"
		type="text/javascript"></script>

	<script src="js/register.js"></script>

</body>
</html>