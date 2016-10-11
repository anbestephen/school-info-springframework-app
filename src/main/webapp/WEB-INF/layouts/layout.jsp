<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><tiles:getAsString name="title" /></title>
<link rel="stylesheet"
	href='<c:url value="/resources/style/bootstrap.min.css" />'>
<link rel="stylesheet"
	href='<c:url value="/resources/style/bootstrap-theme.min.css" />' />
<link rel="stylesheet"
	href='<c:url value="/resources/style/main.css" />' />
<link rel="stylesheet"
	href='<c:url value="/resources/style/font-awesome.min.css"/>' />
</head>
<body>
	<div class="wrapper container-fluid">
		<div class="header">
			<tiles:insertAttribute name="header" />
		</div>
		<div class="message">
			<tiles:insertAttribute name="messages" />
		</div>
		<div class="content">
			<tiles:insertAttribute name="body" />
		</div>
		<div class="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>