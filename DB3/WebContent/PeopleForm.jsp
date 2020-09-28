
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Merkle Application</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <center>
        <h1>Registeration</h1>
    </center>
    <div align="center">
        <c:if test="${people != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${people == null}">
            <form action="insert" method="post" >
        </c:if>
            <caption>
                <h2>
                    <c:if test="${people != null}">
                        Edit User
                    </c:if>
                    <c:if test="${people == null}">
                        Add User
                    </c:if>
                </h2>
            </caption>
     <input type="hidden" class="form-control" value = "${people.getId()}" name = "id" placeholder="id">
    <div class="form-row">
    <div class="form-group col-md-6">
      <label for="inputEmail4">First Name</label>
      <input type="text" class="form-control" value = "${people.getFirstName()}" name = "firstName" id="inputEmail4" placeholder="first name" required>
    </div>
    <div class="form-group col-md-6">
      <label for="inputPassword4">Last Name</label>
      <input type="text" class="form-control" value = "${people.getLastName()}" name = "lastName" id="inputPassword4" placeholder="last Name" required>
    </div>
  </div>
  <div class="form-group">
    <label for="inputAddress">Address</label>
    <input type="text" class="form-control"value = "${people.getAddress1()}" name = "address1" id="inputAddress" placeholder="1234 Main St" required>
  </div>
  <div class="form-group">
    <label for="inputAddress2">Address 2</label>
    <input type="text" class="form-control" value = "${people.getAddress2()}" name = "address2" id="inputAddress2" placeholder="Apartment, studio, or floor(optional)">
  </div>
  <div class="form-row">
    <div class="form-group col-md-4">
      <label for="inputCity">City</label>
      <input type="text" class="form-control" value = "${people.getCity()}" name = "city" id="inputCity" required>
    </div>
    <div class="form-group col-md-4">
      <label for="inputState">State</label>
      <select id="inputState" class="form-control" name = "state">
        <option selected><c:if test="${people == null}">Choose..</c:if><c:if test="${people != null}">${people.getState()}</c:if></option>
	<option value="AL">Alabama</option>
	<option value="AK">Alaska</option>
	<option value="AZ">Arizona</option>
	<option value="AR">Arkansas</option>
	<option value="CA">California</option>
	<option value="CO">Colorado</option>
	<option value="CT">Connecticut</option>
	<option value="DE">Delaware</option>
	<option value="DC">District Of Columbia</option>
	<option value="FL">Florida</option>
	<option value="GA">Georgia</option>
	<option value="HI">Hawaii</option>
	<option value="ID">Idaho</option>
	<option value="IL">Illinois</option>
	<option value="IN">Indiana</option>
	<option value="IA">Iowa</option>
	<option value="KS">Kansas</option>
	<option value="KY">Kentucky</option>
	<option value="LA">Louisiana</option>
	<option value="ME">Maine</option>
	<option value="MD">Maryland</option>
	<option value="MA">Massachusetts</option>
	<option value="MI">Michigan</option>
	<option value="MN">Minnesota</option>
	<option value="MS">Mississippi</option>
	<option value="MO">Missouri</option>
	<option value="MT">Montana</option>
	<option value="NE">Nebraska</option>
	<option value="NV">Nevada</option>
	<option value="NH">New Hampshire</option>
	<option value="NJ">New Jersey</option>
	<option value="NM">New Mexico</option>
	<option value="NY">New York</option>
	<option value="NC">North Carolina</option>
	<option value="ND">North Dakota</option>
	<option value="OH">Ohio</option>
	<option value="OK">Oklahoma</option>
	<option value="OR">Oregon</option>
	<option value="PA">Pennsylvania</option>
	<option value="RI">Rhode Island</option>
	<option value="SC">South Carolina</option>
	<option value="SD">South Dakota</option>
	<option value="TN">Tennessee</option>
	<option value="TX">Texas</option>
	<option value="UT">Utah</option>
	<option value="VT">Vermont</option>
	<option value="VA">Virginia</option>
	<option value="WA">Washington</option>
	<option value="WV">West Virginia</option>
	<option value="WI">Wisconsin</option>
      </select>
    </div>
    <div class="form-group col-md-2">
      <label for="inputZip">Zip</label>
      <input type="text" class="form-control" value = "${people.getZip()}" name = "zip" required pattern="(\d{5}([\-]\d{4})?)">
    </div>
    <div class="form-group col-md-2">
      <label for="inputZip">County</label>
      <input type="text" class="form-control" name = "country" value = "USA" readonly> 
    </div>
  </div>
  <center style="color:red; font-size=15pt">
  <c:if test="${alert != null}">
   ${alert}
  </c:if>
  </center>
    <input type="submit" value="Save" />
        </form>
    </div>   
</div>
 <c:if test="${message != null}">
 <div class="modal fade" id="myModal" role="dialog" >
    <div class="modal-dialog modal-sm ">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Congratulation!</h4>
        </div>
        <div class="modal-body">
          <p>${ message }</p>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-info btn-dark" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

<script>
$("#myModal").modal("show");
</script>
 </c:if>

</body>
</html>