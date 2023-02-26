    <%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <jsp:include page="header.jsp"></jsp:include>
    <title>connectionErrorPage</title>
    <h1 class="text-center" style="color:red">Veuillez créer un compte Client pour pouvoir se connecter</h1>
            <div>
                            <a href="/newCompte"
                                                class="btn btn-success">CREER VOTRE COMPTE CLIENT</a>
                        </div>
    <jsp:include page="footer.jsp"></jsp:include>