<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<tiles:insertDefinition name="mainTemplate">
	<tiles:putAttribute name="title" value="Student Information System" />
	<tiles:putAttribute name="body">
		<h1>Welcome to Student Information System</h1>
		<ul>
			<li><a href='<c:url value="/student/" />'>Mange Students</a></li>
			<li><a href='<c:url value="/teacher/" />'>Mange Teachers</a></li>
		</ul>
	</tiles:putAttribute>
</tiles:insertDefinition>


