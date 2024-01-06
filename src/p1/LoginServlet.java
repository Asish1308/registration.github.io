package p1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String email = request.getParameter("email");
	String password = request.getParameter("password");
	
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/login","root","test");
		Statement stmnt = con.createStatement();
		ResultSet result = stmnt.executeQuery("select * from loginDetails where emailid='"+email+"' and password='"+password+"'");
		
		HttpSession session = request.getSession(true);
		if(result.next()) {
			session.setAttribute("login", "Loggedin");
			RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/webapp/views/register.html");
			rd.forward(request, response);
		}else {
			RequestDispatcher rd1 = request.getRequestDispatcher("index.html");
			rd1.include(request, response);
		}
	}catch (Exception e) {
		System.out.println(e);
	}
	}

}
