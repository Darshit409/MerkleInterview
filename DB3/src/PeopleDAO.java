import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/PeopleDAO")
public class PeopleDAO extends HttpServlet {     
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private static String sql = "SELECT * FROM user WHERE id = ?";
	
	public PeopleDAO() {

    }
	       
    /**
     * @return 
     * @see HttpServlet#HttpServlet()
     */
	public boolean CreateDatabase(Connection Connect) {
		String User ="CREATE TABLE user " +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " firstName VARCHAR(255) not null, " + 
                " lastName VARCHAR(255) not null, " + 
                " address1 VARCHAR(255) not null,"+
                "address2 VARCHAR(255)," +
                "city VARCHAR(255) not null,"+
                "state VARCHAR(255) not null,"+
                "zip VARCHAR(255) not null,"+
                "country VARCHAR(255) not null,"+
                "date VARCHAR(255) not null," + 
                " PRIMARY KEY ( id ))";
		
        try {
        	statement = connect.createStatement();
			statement.executeUpdate("use test1;");
			statement.executeUpdate(User);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
        return true;
    }
	
    protected void connect_func() throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://database-2.cuv5fiemwlec.us-east-2.rds.amazonaws.com/test1?"
  			          + "user=admin&password=23paddock");
            System.out.print("The Connection was succesfull!");
            DatabaseMetaData database = connect.getMetaData();
           ResultSet rs = database.getTables(null, "test1", "user", null);
            if(rs.next() == false) {
            	CreateDatabase(connect);
            }

            
        }
        
    }
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }
    
    //Function list Registered the user in the user Table
    public List<user> listAllPeople() throws SQLException {
        List<user> listPeople = new ArrayList<user>();   	     
        String sql = "SELECT * FROM user order by date DESC";      
        connect_func();      
        statement =  (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
        	user user = new user();
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setCity(resultSet.getString("city"));
            user.setAddress1(resultSet.getString("address1"));
            user.setAddress2(resultSet.getString("address2"));
            user.setState(resultSet.getString("state"));
            user.setZip(resultSet.getString("Zip"));
            user.setCountry(resultSet.getString("country"));
			user.setDate(resultSet.getString("date"));
            listPeople.add(user);
        } 
        resultSet.close();
        statement.close();         
        disconnect();        
        return listPeople;
    }
    
    
         
    public boolean insert(user people) throws SQLException {
    	connect_func();         
		String sql = "insert into  user(firstName, lastName, address1, address2, city, state, zip, country, date) values (?, ?, ?, ?, ?, ?, ?, ?,?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		preparedStatement.setString(1, people.firstName);
		preparedStatement.setString(2, people.lastName);
		preparedStatement.setString(3, people.address1);
		preparedStatement.setString(4, people.address2);
		preparedStatement.setString(5, people.city);
		preparedStatement.setString(6, people.State);
		preparedStatement.setString(7, people.zip);
		preparedStatement.setString(8, people.country);
		preparedStatement.setString(9, people.date.toString());
//		preparedStatement.executeUpdate();
		
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();
        return rowInserted;
    }     
     
    public boolean delete(int peopleid) throws SQLException {
        String sql = "DELETE FROM user WHERE id = ?";        
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, peopleid);
         
        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
//        disconnect();
        return rowDeleted;     
    }
     
    public boolean update(user people, int id) throws SQLException {
        String sql = "update user set firstName = ?, lastName = ?, Address1 = ?, Address2 = ?, city= ? , state = ?, zip =? , country=?, date=? where id = ?";
        connect_func();
        
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setString(1, people.firstName);
		preparedStatement.setString(2, people.lastName);
		preparedStatement.setString(3, people.address1);
		preparedStatement.setString(4, people.address2);
		preparedStatement.setString(5, people.city);
		preparedStatement.setString(6, people.State);
		preparedStatement.setString(7, people.zip);
		preparedStatement.setString(8, people.country);
		preparedStatement.setString(9, people.date.toString());
		preparedStatement.setInt(10, id);
        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        disconnect();
        return rowUpdated;     
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
    public user getPeople(int id) throws SQLException {
    	user user = null;
        
         
        connect_func();
         
        preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
        preparedStatement.setInt(1, id);
         
        ResultSet resultSet = preparedStatement.executeQuery();
        user = new user();
        if (resultSet.next()) {
            user.setId(resultSet.getInt("id"));
        	user.setFirstName(resultSet.getString("firstName"));
            user.setLastName(resultSet.getString("lastName"));
            user.setCity(resultSet.getString("city"));
            user.setAddress1(resultSet.getString("address1"));
            user.setAddress2(resultSet.getString("address2"));
            user.setState(resultSet.getString("state"));
            user.setZip(resultSet.getString("Zip"));
        }
         
        resultSet.close();
        statement.close();
         
        return user;
    }

	public boolean userExists(user people) throws SQLException {
		String sql = "Select * from user where (firstName=? and lastName=? and Address1=? and Address2=? and city=? and state=? and zip=? and country=?)";
		connect_func();
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
		System.out.print(connect);
        preparedStatement.setString(1, people.firstName);
		preparedStatement.setString(2, people.lastName);
		preparedStatement.setString(3, people.address1);
		preparedStatement.setString(4, people.address2);
		preparedStatement.setString(5, people.city);
		preparedStatement.setString(6, people.State);
		preparedStatement.setString(7, people.zip);
		preparedStatement.setString(8, people.country);
		ResultSet rowInserted = preparedStatement.executeQuery();
		boolean isThere = false;
		
        if(rowInserted.next()) {
        	isThere= true;
        }
        preparedStatement.close();
        disconnect();
        return isThere;
       }
	
}
