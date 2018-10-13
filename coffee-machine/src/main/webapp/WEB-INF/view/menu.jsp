<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Coffee</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<!-- <link href="https://afeld.github.io/emoji-css/emoji.css" rel="stylesheet"> -->

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>




<style>
/* Make the image fully responsive */
.carousel-inner img {
	width: 100%;
	height: 50%;
}
</style>


</head>
<body>

	<div class="container">

		<div class="card">
			<%-- <jsp:include page="../view/fragments/nav.jsp" /> --%>
			<%@ include file="../view/fragments/nav.jsp"%>



			<div class="card-header bg-danger text-white">
				<h1>
					<i>Welcome To Coffee Love!</i>
				</h1>

			</div>
			<!-- CARD HEAD END -->





			<!-- <div id="demo" class="carousel slide" data-ride="carousel"> 

  <ul class="carousel-indicators">
    <li data-target="#demo" data-slide-to="0" class="active"></li>
    <li data-target="#demo" data-slide-to="1"></li>
    <li data-target="#demo" data-slide-to="2"></li>
  </ul>
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img src="images/m1.jpg" alt="Los Angeles" width="1100" height="500">
      <div class="carousel-caption">
        <h3>Los Angeles</h3>
        <p>We had such a great time in LA!</p>
      </div>   
    </div>
    <div class="carousel-item">
      <img src="images/m2.jpg" alt="Chicago" width="1100" height="500">
      <div class="carousel-caption">
        <h3>Chicago</h3>
        <p>Thank you, Chicago!</p>
      </div>   
    </div>
    <div class="carousel-item">
      <img src="images/m3.jpg" alt="New York" width="1100" height="500">
      <div class="carousel-caption">
        <h3>New York</h3>
        <p>We love the Big Apple!</p>
      </div>   
    </div>
  </div>
  <a class="carousel-control-prev" href="#demo" data-slide="prev">
    <span class="carousel-control-prev-icon"></span>
  </a>
  <a class="carousel-control-next" href="#demo" data-slide="next">
    <span class="carousel-control-next-icon"></span>
  </a>
</div>

-->











			<!-- The Modal -->
			<div class="modal fade" id="myModal">
				<div class="modal-dialog  modal-lg">
					<div class="modal-content">

						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">Add new drink in the house.</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>

						<!-- Modal body -->
						<div class="modal-body">
							<form:form action="/coffee/drink/add" method="post" modelAttribute="drink">
								<div class="form-group">
									<label for="name">Drink Name:</label>
									<form:input path="name" cssClass="form-control" id="name"></form:input>
								</div>
								<div class="form-group">
									<label for="cost">Cost:</label>
									<form:input path="cost" cssClass="form-control" id="cost"></form:input>
								</div>


								<div class="form-group">
									<label for="ingredients">Ingredients:</label>
								</div>
								<div class="form-group">

									<%--   	<form:select id="ingredients" path="ingredients[${ingr.key}]"  cssClass="custom-select">
  		<form:option value="">--select--</form:option>
  		 <form:options items="${drink.ingredients}" />  
  	</form:select>  
 --%>

									<c:forEach items="${drink.ingredients}" var="ingr">

										<label class="form-check-label" for="i">${ingr.key }</label>

										<form:input id="i" path="ingredients[${ingr.key}]"
											cssClass="form-control" />

									</c:forEach>

								</div>


								<div class="form-group form-check">
									<label class="form-check-label"> <input
										class="form-check-input" type="checkbox"> Remember me
									</label>
								</div>
								<button type="submit" class="btn btn-primary">Submit</button>
							</form:form>

						</div>

						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>














			<div class="card-body">












				<table class="table table-hover">
					<tr class="thead-dark">

						<th>Drink Name</th>
						<th>Cost</th>
						<th>You dig it?</th>
					</tr>

					<c:forEach items="${menu}" var="menu">
						<tr>
							<td><i class="em em-white_check_mark"></i> <i>${menu.name}</i></td>
							<td><i>${menu.cost}</i> <i class="fa fa-inr"></i></td>
							<th><a class=" btn btn-outline-danger "
								href="/coffee/drink/${menu.id}"> <i>Order</i> <i
									class="em em-coffee"></i>
							</a></th>

						</tr>
					</c:forEach>
				</table>

			</div>
		</div>
	</div>


</body>
</html>