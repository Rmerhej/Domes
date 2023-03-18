    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <jsp:include page="header.jsp"></jsp:include>

    <form action="/payer">
      <div class="mb-3">
        <label for="numcarte" class="form-label">Numéro Carte</label>
        <input type="text" class="form-control" id="numcarte" aria-describedby="emailHelp">
      </div>
      <div class="mb-3">
        <label for="typecarte" class="form-label">Type de Carte</label>
        <input type="text" class="form-control" id="typecarte">
      </div>
      <div class="mb-3 form-check">
       <label class="form-check-label" for="dateexp">Date D'expiration</label>
        <input type="date" class="form-check-input" id="dateexp">

      </div>
      <button type="submit" class="btn btn-primary">Submit</button>
    </form>

   <jsp:include page="footer.jsp"></jsp:include>