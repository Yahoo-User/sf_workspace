<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib	uri="http://java.sun.com/jsp/jstl/core"	prefix="c" %>
<%@taglib	uri="http://java.sun.com/jsp/jstl/fmt"	prefix="fmt" %>


<!DOCTYPE html>

<html>
<head>
	<meta charset="UTF-8">
	<title>list.jsp</title>

	<style>

		* {	
			margin: 0 auto;
			padding: 0;
		}

		#wrapper {
			width: 1024px;

			font-family: 'Lucida Sans', 'Lucida Sans Regular', 'Lucida Grande', 'Lucida Sans Unicode', Geneva, Verdana, sans-serif;
			font-size: 14px;
		}
		
		table {
			width: 95%;

			border: 1px ridge green;
			border-collapse: collapse;

			text-align: center;
		}

		th {
			padding: 10px;

			color: white;
			background-color: purple;

			font-size: 16px;
		}

		td {
			padding: 3px;
		}

		caption {
			font-size: 16px;
			font-weight: bold;

			padding: 0;
		}

		#topmenu > li {
			float: left;

			text-align: center;
			line-height: 50px;
			list-style: none;

			width: 33%;
			height: 50px;
		}

		#searchForm .search1 {
			width: 60px;
			height: 30px;
		}

		#searchForm .search2 {
			height: 25px;
		}

		#regBtn {
			width: 150px;
			height: 35px;

			border: 0;

			font-size: 15px;
			font-weight: bold;

			color: white;
			background-color: red;
		}

		/* tr:nth-child(2n) {
			background-color: rgb(255, 254, 188);
		} */

		tr:hover {
			background-color: rgb(239, 253, 226);
			cursor: pointer;
		}

		a, a:link, a:visited {
			text-decoration: none;

			color: black;

			cursor: pointer;
		}

		td:nth-child(2) {
			text-align: left;

			width: 300px;
			padding-left: 10px;
		}

		#pagination {
			width: 95%;
			margin: 0 auto;

			font-size: 16px;
		}

		#pagination ul {
			float: right;
		}

		#pagination li {
			float: left;

			width: 30px;
			height: 30px;
			/* border: 1px dotted black; */

			list-style: none;
			text-align: center;
			line-height: 30px;
		}

		.prev, .next {
			width: 70px!important;

			color: white!important;
			background-color: rgb(155, 155, 155);
		}

		li.currPage, a.currPage {
			font-weight: bold;

			color: white!important;
			background-color: green;
		}

	</style>


	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-migrate/3.3.2/jquery-migrate.min.js"></script>

	<script>

		$(function () {
			console.clear();
			console.log("jquery started...");

			var result = '<c:out value="${result}" />';
			// var result = '${result}';

			if(result.length > 0) {
				alert(result);
			} // if


			$('#regBtn').on('click', function () {
				console.log('onclicked on #regBtn...');

				self.location = '/board/register${cri.pagingUri}';
			}); // onclick


			$('a.prev, a.next').on('click', function (e) {
				console.log('onclicked on a.prev or a.next ...');
				console.log('\t+ this:', this);

				e.preventDefault();


				var paginationForm = $('#paginationForm');

				paginationForm.attr('action', '/board/listPerPage');
				paginationForm.attr('method', 'GET');

				paginationForm.find('input[name=currPage]').val($(this).attr('href'));
				paginationForm.find('input[name=amount]').val('${pageMaker.cri.amount}');
				paginationForm.find('input[name=pagesPerPage]').val('${pageMaker.cri.pagesPerPage}');
				paginationForm.find('input[name=type]').val('${pageMaker.cri.type}');
				paginationForm.find('input[name=keyword]').val('${pageMaker.cri.keyword}');

				paginationForm.submit();
			}); // onclick
			
		});	// jq

	</script>

</head>

<body>

	<p>&nbsp;</p>

	<div id="wrapper">

		<table border="1">
			<caption>
				<ul id="topmenu">
					<li>
						<form id="searchForm" action="/board/listPerPage" method="GET">
							<!-- <input type="hidden" name="currPage" 		value="${pageMaker.cri.currPage}"> -->
							<input type="hidden" name="currPage" 		value="1">
							<input type="hidden" name="amount" 			value="${pageMaker.cri.amount}">
							<input type="hidden" name="pagesPerPage" 	value="${pageMaker.cri.pagesPerPage}">

							<select name="type" class="search1">
								<option value="T" ${ ( "T" eq pageMaker.cri.type ) ? "selected" : "" }>제목</option>
								<option value="C" ${ ( "C" eq pageMaker.cri.type ) ? "selected" : "" }>내용</option>
								<option value="W" ${ ( "W" eq pageMaker.cri.type ) ? "selected" : "" }>작성자</option>
							</select>

							<input type="text" name="keyword" class="search2" value="${pageMaker.cri.keyword}">
							
							<button class='search1'>Search</button>
						</form>
					</li>
					<li>tbl_board (${pageMaker.totalAmount})</li>
					<li><button id="regBtn" type="button">REGISTER</button></li>
				</ul>
			</caption>

			<thead>
				<tr>
					<th>bno</th>
					<th>title</th>
					<!-- <th>content</th> -->
					<th>writer</th>
					<th>insert_ts</th>
					<th>update_ts</th>
				</tr>
			</thead>

			<tbody>

				<c:forEach items="${list}" var="board">
					<tr>
						<td><c:out value="${board.bno}" /></td>
						<td>
							<a href="/board/get${cri.pagingUri}&bno=${board.bno}"><c:out value="${board.title}" /></a>
						</td>

						<!-- <td><c:out value="${board.content}" /></td> -->

						<td><c:out value="${board.writer}" /></td>
						<td><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${board.insert_ts}" /></td>
						<td><fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss" value="${board.update_ts}" /></td>
					</tr>
				</c:forEach>

			</tbody>

			<tfoot></tfoot>
		</table>


		<p>&nbsp;</p>


		<div id="pagination">
			<form id="paginationForm">
				<input type="hidden" name="currPage">
				<input type="hidden" name="amount">
				<input type="hidden" name="pagesPerPage">
				<input type="hidden" name="type">
				<input type="hidden" name="keyword">

				<ul>
					<c:if test="${pageMaker.prev}">
						<li class="prev"><a class='prev' href="${pageMaker.startPage - 1}">Prev</a></li>
					</c:if>

					<c:forEach var="pageNum" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">

						<li class="${ param.currPage eq pageNum? 'currPage' : '' }">

							<!-- <a 	class="${pageMaker.cri.currPage == pageNum? 'currPage' : ''}"
								href="/board/listPerPage?currPage=${pageNum}&amount=${pageMaker.cri.amount}&pagesPerPage=${pageMaker.cri.pagesPerPage}&type=${pageMaker.cri.type}&keyword=${pageMaker.cri.keyword}">
								<c:out value="${pageNum}" /> -->

							<a 	class="${ param.currPage eq pageNum? 'currPage' : '' }"
								data-temp="${ pageMaker.cri.setCurrPage(pageNum) }"
								href="/board/listPerPage${pageMaker.cri.pagingUri}">

								<c:out value="${pageNum}" />

							</a>
						
						</li>

					</c:forEach>

					<c:if test="${pageMaker.next}">
						<li class="next"><a class='next' href="${pageMaker.endPage + 1}">Next</a></li>
					</c:if>
				</ul>
			</form>
		</div>

	</div>

</body>
</html>