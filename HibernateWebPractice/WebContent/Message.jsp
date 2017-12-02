<%@page import="org.apache.tomcat.util.codec.binary.Base64"%>
<%@page import="javax.swing.ImageIcon"%>
<%@page import="java.awt.Toolkit"%>
<%@page import="java.awt.Image"%>
<%@page import="fileUpload.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Message</title>
</head>
<body>
    <center><%
    
    User user=(User)request.getSession().getAttribute("user");
    
    %>
        <h3><%= 
        user.getUserName()
        /* request.getAttribute("Message") */%></h3>
        <%
        String getByteCode = new Base64().encodeToString(user.getUpfile());
        %>
<img  src="data:image/jpeg;base64, <%=getByteCode %>" onerror="this.src='apsys.jpg'" width="160" height="160" >    </center>
</body>
</html>