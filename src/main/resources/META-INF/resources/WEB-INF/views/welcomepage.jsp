<!DOCTYPE html >
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="sat, 01 Dec 2001 00:00:00 GMT">
<title>TechHub | home</title>
<link href="static/css/bootstrap.min.css" rel="stylesheet">
<link href="static/css/style.css" rel="stylesheet">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

	<script type="text/javascript" src="../../static/js/main.js"></script>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
	
</head>
<body>

<c:choose>
	<c:when test="${nav == 'TRUE' }">
	<div role="navigation">
		<div class="navbar navbar-inverse">
			<a href="/welcome" class="navbar-brand">TechHub Softwares</a>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li><a href="/login">Login</a></li>
					<li><a href="/register">New Registration</a></li>
					<li><a href="/allfiles?USERID=${USERID}">Files</a></li>
					<li><a href="/alldocuments?USERID=${USERID}">Documents</a></li>
				</ul>
			</div>
		</div>
	</div>
	</c:when>
</c:choose>

	<c:choose>
		<c:when test="${mode=='MODE_HOME' }">
			<div class="container" id="homediv">
				<div class="jumbotron text-center">
					<h1>Welcome to Optiflex Softwares,<span><c:out value="${USERNAME }"></c:out></span></h1>
					<h3>Offering quality Softwares since 0000</h3>
				</div>
			</div>
			
				
				<c:if test="${not empty error }">
						<div class= "alert alert-danger">
							<c:out value="${error }"></c:out>
						</div>
					</c:if>
			<div id="uploadButton" class="container-fluid" style="background-color: lime;">
			
				<button type="button" class="btn btn-primary" onclick="showUpload()">Create a Folder</button>
			</div>
			
			<div id="uploadDiv" class="container-fluid" style="display: none;">
				<form method="POST" action="/createfile">
				
					<input type="hidden" name="USERID" value="${USERID}">
					<input type="hidden" name="id" value="${file.id }">
						<div class="form-row">
							  <div class="form-group col-md-6">
							    <label for="filename">Enter the file name</label>
							    <input type="text" class="form-control" value="${file.filename }" id="filename" name="filename" placeholder="Enter ile/Folder">
							    <small id="emailHelp" class="form-text text-muted">We'll never share your files with anyone</small>
							  </div>
						  </div>
					  <input type="hidden" name="createddate" value="${file.createddate }">
					 <br>
					  <button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
			
			
			
			
			<div>
				<c:if test="${EMPTY=='TRUE' }">
					<div class= "alert alert-danger">
							<c:out value="">Create a file first</c:out>
						</div>
				</c:if>
				<form action="/createdocument"  method="post" enctype="multipart/form-data">
					<div class="form-group">
						<input type="hidden" name="USERID" value="${USERID}">
							<select class="form-control" name="FILEID" id="FILEID">
								<c:forEach items="${ FILES}" var="FILEID">
									<option values="${FILEID.id}"> <c:out value="${FILEID.filename}"></c:out> </option>
								</c:forEach>
							
							</select><br>
					</div>
						
						<div class="form-row">
								  <div class="form-group col-md-6">
								    <label for="document">Enter the file name</label>
								    <input type="text" class="form-control" value="${document.name }" id="documentname" name="documentname" placeholder="Enter document name">
								    <small id="emailHelp" class="form-text text-muted">We'll never share your files with anyone</small>
								  </div>
							  </div>
							  
						<div class="form-group">
						    <label for="exampleFormControlFile1">CLICK TO SELECT A FILE</label>
						    <input type="file" class="form-control-file" id="document" name="document">
						 </div>
						 <br>
						 <button type="submit" class="btn btn-primary">Submit</button>
				</form>
		
			</div>
				
				<c:import url=""/>
			<div>

				


			</div>
		</c:when>



		<c:when test="${mode=='MODE_REGISTER' }">
			<div class="container text-center">
				<h3>New Registration</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="save-user">
						<c:if test="${not empty error }">
						<div class= "alert alert-danger">
							<c:out value="${error }"></c:out>
							</div>
					</c:if>
					<input type="hidden" name="id" value="${user.id }" />
					<div class="form-group">
						<label class="control-label col-md-3">Username</label>
						<div class="col-md-4">
							<input type="text" class="form-control" name="username"
								value="${user.username }" />
						</div>
					</div>
					
					<input type="hidden" name="id" value="${user.createddate }" />
					<div class="form-group">
						<label class="control-label col-md-3">Password</label>
						<div class="col-md-4">
							<input type="password" class="form-control" name="password"
								value="${user.password }" />
						</div>
					</div>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Register" />
					</div>
				</form>
			</div>
		</c:when>
		
		
		
		
		
		
		
		
		
		
		<c:when test="${mode=='ALL_FILES' }">
			<div class="container text-center" id="tasksDiv">
				<h3>All FOLDERS</h3>
				<hr>
				<div class="table-responsive">
					<table id="filesTable" class="display">
						<thead>
							<tr>
								<th>Id</th>
								<th>Filename</th>
								<th>Created Date</th>
							</tr>
						</thead>
						
					</table>
				</div>
			</div>
		</c:when>


		<c:when test="${mode=='ALL_DOCUMENTS' }">
			<div class="container text-center" id="tasksDiv">
				<h3>All FOLDERS</h3>
				<hr>
				<div class="table-responsive">
					<table id="documentsTable" class="display">
						<thead>
							<tr>
								<th>Id</th>
								<th>Document</th>
								<th>Created Date</th>
							</tr>
						</thead>
						
					</table>
				</div>
			</div>
		</c:when>


		<c:when test="${mode=='MODE_UPDATE' }">
			<div class="container text-center">
				<h3>Update User</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="/save-user">
					<input type="hidden" name="id" value="${user.id }" />
					<div class="form-group">
						<label class="control-label col-md-3">Username</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="username"
								value="${user.username }" />
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-3">Password</label>
						<div class="col-md-7">
							<input type="password" class="form-control" name="password"
								value="${user.password }" />
						</div>
					</div>
					<div class="form-group ">
						<input type="submit" class="btn btn-primary" value="Update" />
					</div>
				</form>
			</div>
		</c:when>

		<c:when test="${mode=='MODE_LOGIN' }">
			<div class="container text-center">
				<h3>User Login</h3>
				<hr>
				<form class="form-horizontal" method="POST" action="/login-user">
					<c:if test="${not empty error }">
						<div class= "alert alert-danger">
							<c:out value="${error }"></c:out>
							</div>
					</c:if>
					
							<div class="form-group">
								<label class="control-label col-md-3">Username</label>
								<div class="col-md-4">
									<input type="text" class="form-control" name="username"
										value="${user.username }" />
								</div>
							</div>
							
							<div class="form-group">
								<label class="control-label col-md-3">Password</label>
								<div class="col-md-4">
									<input type="password" class="form-control" name="password"
										value="${user.password }" />
								</div>
							</div>
							
							<div class="form-group ">
								<input type="submit" class="btn btn-primary" value="Login" />
							</div>
		  		</form>
			</div>
		</c:when>
	</c:choose>


	<!-- Optional JavaScript -->
	<!-- jQuery first, then Popper.js, then Bootstrap JS -->
	<script src="static/js/jquery-1.11.1.min.js"></script>
	<script src="static/js/bootstrap.min.js"></script>
</body>
</html>