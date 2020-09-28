import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
 
/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @author www.codejava.net
 */
public class ControlServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private PeopleDAO peopleDAO;

 
    public void init() {
        peopleDAO = new PeopleDAO(); 
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        System.out.println(action);
        try {
            switch (action) {
            case "/new":
                showNewForm(request, response);
                break;
            case "/insert":
            	insertPeople(request, response);
                break;
            case "/delete":
            	deletePeople(request, response);
                break;
            case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
            	updatePeople(request, response);
                break;
            default:          	
            	listPeople(request, response);           	
                break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void listPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<user> listPeople = peopleDAO.listAllPeople();
        request.setAttribute("listPeople", listPeople);       
        RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleList.jsp");       
        dispatcher.forward(request, response);
    }
 
    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleForm.jsp");
       dispatcher.forward(request, response);
    }
 
    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        user existingPeople = peopleDAO.getPeople(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleForm.jsp");
        request.setAttribute("people", existingPeople);
        dispatcher.forward(request, response);
 
    }
 
    private void insertPeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	String firstName = request.getParameter("firstName");
    	String lastName = request.getParameter("lastName");
    	String address1 = request.getParameter("address1");
    	String address2 = request.getParameter("address2");
    	String city = request.getParameter("city");
    	String state = request.getParameter("state");
    	String zip = request.getParameter("zip");
    	String country = request.getParameter("country");
        user newPeople = new user(firstName, lastName, address1, address2, city, state, country, zip);
        boolean doesExist = peopleDAO.userExists(newPeople);
        if(doesExist == false) {
        peopleDAO.insert(newPeople);
        request.setAttribute("message", "The User " + firstName + " " +lastName + " has been Succesfully Registered");
        }
        else {
        	String streetAddress = address1;
        	if(address2 != null) {
        		streetAddress += "," + address2;
        	}
        	request.setAttribute("alert", "The user "  + firstName + " " +lastName + " with Address " + streetAddress + "," + city + "," + state + "," + zip + " already Exist! <Br><Strong>Please enter your information again<Strong>");
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("PeopleForm.jsp");
        dispatcher.forward(request, response);
 
    }
 
    private void updatePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
    	String lastName = request.getParameter("lastName");
    	String address1 = request.getParameter("address1");
    	String address2 = request.getParameter("address2");
    	String city = request.getParameter("city");
    	String state = request.getParameter("state");
    	String zip = (request.getParameter("zip"));
    	String country = request.getParameter("country");
        user people = new user(firstName, lastName, address1, address2, city, state, country, zip);
        peopleDAO.update(people, id);
        response.sendRedirect("list");
    }
 
    private void deletePeople(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
      int id = Integer.parseInt(request.getParameter("id"));
      peopleDAO.delete(id);
      response.sendRedirect("list"); 
    }

}