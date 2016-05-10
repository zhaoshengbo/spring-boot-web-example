<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Bootstrap 101 Template</title>
<!-- Bootstrap -->
<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/my.css" rel="stylesheet">
</head>
<body class="container">

	<div class="row mt">
		<div class="col-md-4">
			<div class="panel panel-primary">
				<div class="panel-heading">Having fun!</div>
				<form id="calForm" action="calculate" class="panel-body"
					method="post">
					<div class="row top5">
						<div class="col-md-12">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">num1</span> <input
									type="text" class="form-control" placeholder="num1"
									aria-describedby="basic-addon1" name="num1">
							</div>
						</div>
					</div>
					<div class="row top5">
						<div class="col-md-12">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">num2</span> <input
									type="text" class="form-control" placeholder="num2"
									aria-describedby="basic-addon1" name="num2">
							</div>
						</div>
					</div>

					<div class="row top5">
						<div class="col-md-12">
							<div class="input-group">
								<span class="input-group-addon" id="basic-addon1">result</span>
								<input type="text" class="form-control" placeholder="result"
									aria-describedby="basic-addon1" name="result" value="${result}">
							</div>
						</div>
					</div>

					<input class="span2" id="opt" name="opt" type="hidden">
					<div class="row top5">
						<div class="col-md-12">
							<div class="btn-group btn-block">
								<button type="button"
									class="btn btn-primary dropdown-toggle col-md-12"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="true">
									calculate <span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li onclick="$('#opt').val('1'); $('#calForm').submit()"><a
										href="#">plus</a></li>
									<li onclick="$('#opt').val('2'); $('#calForm').submit()"><a
										href="#">subtract</a></li>
									<li onclick="$('#opt').val('3'); $('#calForm').submit()"><a
										href="#">multiple</a></li>
									<li onclick="$('#opt').val('4'); $('#calForm').submit()"><a
										href="#">divide</a></li>
								</ul>
							</div>
						</div>
					</div>

					<c:if test="${ok == false}">
						<div class="row top5">
							<div class="col-md-12">
								<div class="alert alert-warning alert-dismissible" role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>Warning!</strong> "${errMsg}"
								</div>
							</div>
						</div>
					</c:if>
				</form>
			</div>
		</div>

		<div class="col-md-8">
			<div class=" panel panel-primary">
				<div class="panel-heading">Calculate details<span class="badge pull-right">${detailCount}</span></div>
				<div class="panel-body">
					<table class="table">
						<thead>
							<tr>
								<td>#</td>
								<td>num1</td>
								<td>num2</td>
								<td>opt</td>
								<td>result</td>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${detailList}" var="detail">
								<tr>
									<td>#</td>
									<td>${detail.num1}</td>
									<td>${detail.num2}</td>
									<c:choose>
										<c:when test="${detail.opt == 1}">
											<td>+</td>
										</c:when>
										<c:when test="${detail.opt == 2}">
											<td>-</td>
										</c:when>
										<c:when test="${detail.opt == 3}">
											<td>*</td>
										</c:when>
										<c:when test="${detail.opt == 4}">
											<td>/</td>
										</c:when>
										<c:otherwise>
											<td>unknown</td>
										</c:otherwise>
									</c:choose>
									<td>${detail.result}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>

					<form id="topage" action="topage" method="post">

						<input id="page" name="page" type="hidden" value="${currentPage}"/>
						<div class="col-md-6 col-md-offset-3">
							<nav>
								<ul class="pagination">

									<c:choose>
										<c:when test="${isFirstPage == true}">
											<li class="page-item disabled"><a class="page-link"
												href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
													<span class="sr-only">Previous</span>
											</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item" onclick="$('#page').val(parseInt($('#page').val()) - 1);$('#topage').submit()"><a class="page-link" href="#"
												aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
													<span class="sr-only">Previous</span>
											</a></li>
										</c:otherwise>
									</c:choose>


									<c:forEach items="${pageList}" var="page">
										<c:choose>
											<c:when test="${currentPage == page}">
												<li class="page-item active"><a class="page-link"
													href="#">${page}</a></li>
											</c:when>
											<c:otherwise>
												<li class="page-item" onclick="$('#page').val(${page});$('#topage').submit()"><a class="page-link" href="#">${page}</a></li>
											</c:otherwise>
										</c:choose>
									</c:forEach>

									<c:choose>
										<c:when test="${isLastPage == true}">
											<li class="page-item disabled"><a class="page-link"
												href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
													<span class="sr-only">Next</span>
											</a></li>
										</c:when>
										<c:otherwise>
											<li class="page-item" onclick="$('#page').val(parseInt($('#page').val()) + 1);$('#topage').submit()"><a class="page-link" href="#"
												aria-label="Next"> <span aria-hidden="true">&raquo;</span>
													<span class="sr-only">Next</span>
											</a></li>
										</c:otherwise>
									</c:choose>

								</ul>
							</nav>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="js/jquery-1.12.3.js"></script>
	<script src="js/bootstrap.js"></script>
</body>

</html>