<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Coffee dispensing machine</title>
</head>
<link href="https://afeld.github.io/emoji-css/emoji.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<body>

	<div class="container">
		<jsp:include page="../view/fragments/nav.jsp" />


		<div class="card">

			<div class="card-header  bg-danger">
				<h1>
					<i>Your Order Details</i>
				</h1>
			</div>

			<div class="card-body ">

				<div class="row">

					<h2>
						<i>Please collect your bill</i>
					</h2>
				</div>
				<div class="row">
					<div class="form-group">
						<label><i>Drink Name:</i></label> <label><i>${drink.name}</i></label>
					</div>

				</div>

				<div class="row">
					<div class="form-group col-md-6">
						<label for="order-code"><i>Amount:</i></label> <span> <i>${drink.cost}</i>
							<i class="fa fa-inr"></i></span>
					</div>
					
					<div class=" col-md-6">
						<img alt="coffee" class="col-md-6" src="/coffee/drink/multipart/${drink.filePath}" >
					</div>
				</div>

				<div class="row">
					<div class="form-group">
						<label for="order-code"><i>This ${drink.name}
								contains:</i></label>
						<c:forEach items="${drink.ingredients}" var="ingredient">

							<ul>
								<li><i>${ingredient.key} </i></li>
								<i>${ingredient.value} Units</i>
							</ul>
						</c:forEach>
					</div>
				</div>

			</div>
			<a class="btn btn-outline-danger" href="/coffee/">Check-out our
				other awesome drinks!</a>
		</div>


	</div>


</body>
</html>