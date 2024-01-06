package p1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegisterServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String name = request.getParameter("name");
	String city = request.getParameter("city");
	String number = request.getParameter("number");
	
	HttpSession session = request.getSession();
	
	
	if(session.getAttribute("login")!=null) {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost/login","root","test");
		Statement stmnt = con.createStatement();
		stmnt.executeUpdate("insert into register values('"+name+"','"+city+"','"+number+"')");
		
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/webapp/views/register.html");
		rd.forward(request, response);
	}catch (Exception e) {
		System.out.println(e);
	}
	}else {
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		rd.forward(request, response);
	}
	}

}
