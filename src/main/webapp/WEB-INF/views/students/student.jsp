<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="mainTemplate">
	<tiles:putAttribute name="title" value="Students List" />
	<tiles:putAttribute name="body">
		<h1>This is Student Page</h1>
		<div class="panel panel-default">
			<div class="panel-heading">List of Students</div>
			<div class="panel-body">
				<table class="table table-striped">
					<tr>
						<th>Id</th>
						<th>First Name</th>
						<th>Last Name</th>
						<th>Age</th>
						<th>Gender</th>
						<th>Status</th>
						<th></th>
						<th></th>
						<th></th>
					</tr>
					<c:forEach items="${students}" var="student">
						<tr>
							<td>${student.id}</td>
							<td>${student.firstName}</td>
							<td>${student.lastName}</td>
							<td>${student.age}</td>
							<td><c:out
									value="${student.gender == 'M'.charAt(0) ? 'Male' : 'Female'}" /></td>
							<td>Active</td>
							<td><a href='<c:url value="/student/edit/${student.id}"/>'
								class="link link-default">Edit</a></td>
							<td><a
								href='<c:url value="/student/delete/${student.id}" />'
								class="link link-default">Delete</a></td>
							<td><input type="checkbox"></td>
						</tr>

					</c:forEach>
				</table>
				<a href='<c:url value="/student/add"/>' class="btn btn-default">Add
					Student</a> &nbsp;<a href="#" class="btn btn-default">Delete
					Selected</a>
			</div>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>