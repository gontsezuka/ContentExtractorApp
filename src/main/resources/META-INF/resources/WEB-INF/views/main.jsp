
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>ContentExtractor Admin</title>
        <link href="../../static/css/style.css" rel="stylesheet" />
        <link href="../../static/css/styles.css" rel="stylesheet">
        <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/js/all.min.js" crossorigin="anonymous"></script>
    </head>
<body>
    <c:choose>
        <c:when test="${mode=='MODE_HOME'}">
			    <body class="sb-nav-fixed">
			    
			        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
			            <a class="navbar-brand" href="${pageContext.request.contextPath }/dashboard/${USERID }">OPTIFLEX SOFTWARE</a><button class="btn btn-link btn-sm order-1 order-lg-0" id="sidebarToggle" href="#"><i class="fas fa-bars"></i></button
			            ><!-- Navbar Search-->
			           
			        
			            <!-- Navbar-->
			            <ul class="navbar-nav ml-auto ml-md-0">
			                <li class="nav-item dropdown">
			                    <a class="nav-link dropdown-toggle" id="userDropdown" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
			                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
			                        <div class="dropdown-divider"></div>
			                        <a class="dropdown-item" href="${pageContext.request.contextPath }/logout/${USERID}">Logout</a>
			                    </div>
			                </li>
			            </ul>
			        </nav>
			        <div id="layoutSidenav">
			            <div id="layoutSidenav_nav">
			                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
			                    <div class="sb-sidenav-menu">
			                        <div class="nav">
			                            <div class="sb-sidenav-menu-heading">Core</div>
			                            <a class="nav-link" href="${pageContext.request.contextPath }/dashboard/${USERID}"><div class="sb-nav-link-icon"><i class="fas fa-tachometer-alt"></i></div>ContentExtractor Dashboard</a>
			                            
			                         
			                           
			                            <div class="sb-sidenav-menu-heading">Management</div>
			                            <a class="nav-link" href="${pageContext.request.contextPath }/allfiles/${USERID}"><div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>Files</a>
			                            
			                            <a class="nav-link" href="${pageContext.request.contextPath }/alldocuments/${USERID}"><div class="sb-nav-link-icon"><i class="fas fa-table"></i></div>Documents</a>
			                            <a class="nav-link" href="${pageContext.request.contextPath }/updateprofile/${USERID}"><div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>Profile</a>
			                        </div>
			                    </div>
			                    <div class="sb-sidenav-footer">
			                        <div class="small">Logged in as:</div>
			                        	<span><c:out value="${USERNAME }"></c:out></span>
			                    </div>
			                </nav>
			            </div>
			            
			            <div id="layoutSidenav_content">
			                <main>
			                    <div class="container-fluid">
									<c:choose>

									<c:when test="${middle=='DASHBOARD'}">

			                        <h1 class="mt-4">Content Extractor Dashboard</h1>
			                        <ol class="breadcrumb mb-4">
			                            <li class="breadcrumb-item active">Dashboard</li>
			                        </ol>
			
			                
			                    
			                        <div class="row">
			                            <div class="col-xl-3 col-md-6">
			                                <div class="card bg-primary text-white mb-4">
			                                    <div class="card-body">Primary Card</div>
			                                    <div class="card-footer d-flex align-items-center justify-content-between">
			                                        <a class="small text-white stretched-link" href="#">View Details</a>
			                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
			                                    </div>
			                                </div>
			                            </div>
			                            <div class="col-xl-3 col-md-6">
			                                <div class="card bg-warning text-white mb-4">
			                                    <div class="card-body">Warning Card</div>
			                                    <div class="card-footer d-flex align-items-center justify-content-between">
			                                        <a class="small text-white stretched-link" href="#">View Details</a>
			                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
			                                    </div>
			                                </div>
			                            </div>
			                            <div class="col-xl-3 col-md-6">
			                                <div class="card bg-success text-white mb-4">
			                                    <div class="card-body">Success Card</div>
			                                    <div class="card-footer d-flex align-items-center justify-content-between">
			                                        <a class="small text-white stretched-link" href="#">View Details</a>
			                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
			                                    </div>
			                                </div>
			                            </div>
			                            <div class="col-xl-3 col-md-6">
			                                <div class="card bg-danger text-white mb-4">
			                                    <div class="card-body">Danger Card</div>
			                                    <div class="card-footer d-flex align-items-center justify-content-between">
			                                        <a class="small text-white stretched-link" href="#">View Details</a>
			                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
			                                    </div>
			                                </div>
			                            </div>
			                        </div>
			                     
			
			
			                            <div>
							               <div class="container" id="homediv">
											<div class="jumbotron text-center">
												<h1>Welcome to Optiflex Software</h1>
												<h3>Offering quality Software since 0000.</h3>
											</div>
										</div>
										<c:if test="${not empty error }">
													<div class= "alert alert-danger">
														<c:out value="${error }"></c:out>
													</div>
												</c:if>
										<div id="uploadButton" class="container-fluid" style="background-color: gray;">
										
											<button type="button" class="btn btn-primary" onclick="showUpload()">Add a File</button>
										</div>
										
										<div id="uploadDiv" class="container-fluid" style="display: none;">
											<form method="POST" action="/createfile" enctype="multipart/form-data">
															
													
																<input type="hidden" name="USERID" value="${USERID}">
																<input type="hidden" name="id" value="${file.id}">
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
										
										
										
										
										
										<hr>
										
										
										
										
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
															    <label for="document">Enter the Document name</label>
															    <input type="text" class="form-control" value="${document.name }" id="documentname" name="documentname" placeholder="Enter document name">
															    <small id="emailHelp" class="form-text text-muted">We'll never share your documents with anyone</small>
															  </div>
														  </div>
														  
													<div class="form-group">
													    <label for="exampleFormControlFile1">CLICK TO SELECT A DOCUMENT TO UPLOAD </label>
													    <input type="file" class="form-control-file" id="document" name="document">
													 </div>
													 <br>
													 <button type="submit" class="btn btn-primary" id="btn">Upload</button>
											</form>
									
										</div>
									
									<hr>
										

									<hr>
										
							                            				
							                            
										</div>
										


									</c:when>





									<c:when test="${middle=='FILES'}">
													<div class="container text-center" id="tasksDiv">
																<h3>FILES</h3>
																<hr>
																<div class="table-responsive">
																	<table class="table table-striped table-bordered">
																		<thead>
																			<tr>
																				<th>Id</th>
																				<th>Filename</th>
																				<th>Created date</th>
																				<th>Delete</th>
																				<th>Edit</th>
																			</tr>
																		</thead>
																		<tbody>
																			<c:forEach var="file" items="${FILES }">
																				<tr>
																					<td>${file.id}</td>
																					<td>${file.filename}</td>
																					<td>${file.createddate}</td>
																					<td><a class="btn btn-dark" href="${pageContext.request.contextPath }/deletefile/${USERID}/${file.id}">DELETE</a></td>
																					<td><a class="btn btn-dark" href="${pageContext.request.contextPath }/editfile/${USERID}/${file.id}">EDIT</a></td>
																				</tr>
																			</c:forEach>
																		</tbody>
																	</table>
																</div>
															</div>



									</c:when>



									<c:when test="${middle=='DOCUMENTS'}">
												<div class="container text-center" id="docDiv">
												<h3>Documents</h3>
												<hr>
												<div class="table-responsive">
													<table class="table table-striped table-bordered">
														<thead>
															<tr>
																<th>Document id</th>
																<th>Document name</th>
																<th>created date</th>
																<th>Delete</th>
																<th>Preview</th>
															</tr>
														</thead>
														<tbody>
															<c:forEach var="document" items="${DOCUMENTS}">
																<tr>
																	<td>${document.id}</td>
																	<td>${document.documentname}</td>
																	<td>${document.createddate}</td>
																	<td><a class="btn btn-dark" href="${pageContext.request.contextPath }/deletedocument/${USERID}/${document.id}">DELETE</a></td>
																	<td><a class="btn btn-dark" href="${pageContext.request.contextPath }/previewdocument/${USERID}/${document.id}">PREVIEW</a></td>
																</tr>
															</c:forEach>
														</tbody>
													</table>
												</div>
											</div>



									</c:when>
									
									
									<c:when test="${middle=='UPDATE'}">
										<div class="container-fluid">
											<div class="container text-center" id="profileDiv">
												<h3>Update Profile</h3>
												<hr>
												<div id="divUpdate">
													<form class="form-horizontal" method="POST" action="/updateuser">
														<input type="hidden" name="USERID" value="${USERID }" />
														<div class="form-group">
															<label id="usernameUpdateLabel"class="control-label col-md-8">Username</label>
															<div class="col-md-7">
																<input type="text" class="form-control" name="usernameUpdate" id="usernameUpdate"
																	value="${USER.username }" />
															</div>
														</div>
														
														
														<div class="form-group">
															<label id="passwordUpdateLabel" class="control-label col-md-8">Password</label>
															<div class="col-md-7">
																<input type="text" class="form-control" name="passwordUpdate" id="passwordUpdate"
																	value="${USER.password }" />
															</div>
														</div>
														<div class="form-group ">
															<input type="submit" class="btn btn-primary" value="Update Profile" />
														</div>
													</form>
												</div>
											</div>
									
									</div>
									</c:when>
									
									
									
									
									
									<c:when test="${middle=='PREVIEW'}">
		
										<div class="container text-center" >
											<h2>DOCUMENT PREVIEW</h2>
											<div><a class="btn btn-primary" href="${pageContext.request.contextPath }/viewextractedcontent/${USERID}/${DOCUMENT.id}">VIEW EXTRACTED TEXT</a></div>
											<hr>
											<div id="previewDiv">
												<embed src="${DOCUMENTNAME}" type="application/pdf" width="100%" height="600px"/>
											</div>
										</div>
										
									</c:when>
									
									
									
									
									<c:when test="${middle=='EXTRACTED'}">
		
										<div class="container text-center" >
											<h2>EXTRACTED CONTENT</h2>
											<hr>
											<div id="extractedDiv">
												<embed src="${DOCUMENTNAME}" type="text/plain" width="100%" height="600px"/>
											</div>
										</div>
										
									</c:when>
									
									
									
									
			                    </c:choose>
			            
			                    </div>
			                </main>
			                <footer class="py-4 bg-light mt-auto">
			                    <div class="container-fluid">
			                        <div class="d-flex align-items-center justify-content-between small">
			                            <div class="text-muted">Copyright &copy; Optiflex Information Technology 2020</div>
			                            <div>
			                                <a href="#">Privacy Policy</a>
			                                &middot;
			                                <a href="#">Terms &amp; Conditions</a>
			                            </div>
			                        </div>
			                    </div>
			                </footer>
			            </div>
			        
			   		</div>
			    </body>
        </c:when>

        <c:when test="${mode=='MODE_LOGIN'}">

							<body class="bg-primary">
				        <div id="layoutAuthentication">
				            <div id="layoutAuthentication_content">
				                <main>
				                <c:if test="${not empty error }">
										<div class= "alert alert-danger">
											<c:out value="${error }"></c:out>
											</div>
									</c:if>
				                    <div class="container">
				                        <div class="row justify-content-center">
				                            <div class="col-lg-5">
				                                <div class="card shadow-lg border-0 rounded-lg mt-5">
				                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">ContentExtractor Login </h3></div>
				                                    <div class="card-body">
				                                        <form method="post" action="/loginuser">
				                                            <div class="form-group"><label class="small mb-1" for="inputusername">Username</label><input class="form-control py-4" id="username" type="text" placeholder="Enter email username" name="username" value="${user.username}"/></div>
				                                            <div class="form-group"><label class="small mb-1" for="inputpassword">Password</label><input class="form-control py-4" id="password" type="password" placeholder="Enter password" name="password"value="${user.password}"/></div>
				                                            <div class="form-group">
				                                            </div>
															<button type="submit" class="btn btn-primary">Log in</button>	
				                                        </form>			                                       
				                                    </div>
				                                    <div class="card-footer text-center">
				                                        <div class="small"><a href="/register">Need an account? Sign up!</a></div>
				                                    </div>
				                                </div>
				                            </div>
				                        </div>
				                    </div>
				                </main>
				            </div>
				            <div id="layoutAuthentication_footer">
				                <footer class="py-4 bg-light mt-auto">
				                    <div class="container-fluid">
				                        <div class="d-flex align-items-center justify-content-between small">
				                            <div class="text-muted">Copyright &copy; Optiflex Information Technology 2020</div>
				                            <div>
				                                <a href="#">Privacy Policy</a>
				                                &middot;
				                                <a href="#">Terms &amp; Conditions</a>
				                            </div>
				                        </div>
				                    </div>
				                </footer>
				            </div>
				        </div>
				        <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
				        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
				        <script src="../../static/js/scripts.js"></script>
				    </body>
		



        </c:when>





        <c:when test="${mode=='MODE_REGISTER'}">

				<body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                <c:if test="${not empty error }">
						<div class= "alert alert-danger">
							<c:out value="${error }"></c:out>
							</div>
					</c:if>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">ContentExtractor Registration</h3></div>
                                    <div class="card-body">
                                        <form method="post" action="/registeruser">
                                            <div class="form-group"><label class="small mb-1" for="inputusername">Username</label><input class="form-control py-4" id="username" type="text" placeholder="Username" name="username" value="${user.username}"/></div>
                                            <div class="form-group"><label class="small mb-1" for="inputpassword">Password</label><input class="form-control py-4" id="password" type="password" placeholder="Password" name="password" value="${user.password}"/></div>
                                            <div class="form-group">
											</div>
											<button type="submit" class="btn btn-primary btn-block">Register...</button>
 
											 </form>
                                    </div>
                                    <div class="card-footer text-center">
                                        <div class="small"><a href="/login">Have an account? Sign IN!</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
            <div id="layoutAuthentication_footer">
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Optiflex Information Technology 2020</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../../static/js/scripts.js"></script>
    </body>
				



        </c:when>
</c:choose>
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../../static/js/scripts.js"></script>
        <script src="../../static/js/main.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/datatables-demo.js"></script>
    </body>
</html>
