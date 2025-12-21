package ckwuser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
public class Sh extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Sh() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// DB情報
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/mzx991226_zhixin";
		String user = "mzx991226_zhixin";
		String passwords = "mzx991226";
		
		String msg = "";
		
		//user情報
		String email = "";
		String password = "";


		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    conn = DriverManager.getConnection(url, user, passwords);
		    String toEmail = "";
		    HttpSession session = request.getSession();
		    toEmail = request.getParameter("email");
		    
		    
		    
		    Statement stmt = conn.createStatement();
		    String sql = "SELECT email, password FROM users where email='" + toEmail + "'";
		    System.out.println(sql);
		    ResultSet rs = stmt.executeQuery(sql);
		    int count=0;
		    while (rs.next()) {
		    	count += 1;
		    }
		    if (count  == 0) {
		    	msg = "存在しません";
		    } else {
		    	msg="<span id='to'>"+ toEmail +"</span><input type=\"button\" value=\"加好友\" class=\"x4\" id=\"addFriend\" onclick=\"addFriend()\">";
		    }
		    
			
		} catch (Exception e) {
			e.printStackTrace();
		    msg = "処理失敗しました。";
		} finally {
			System.out.println(msg);
			response.getWriter().append(msg);
		}
		
	}

}
