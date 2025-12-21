package ckwuser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
public class UpdateMy extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMy() {
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
		    
		    email = request.getParameter("email");
		    password = request.getParameter("password");
		    
		    HttpSession session = request.getSession();
		    String oldEmail = (String) session.getAttribute("email");
		    
		    
		    Statement stmt = conn.createStatement();
		    String sql = "UPDATE users set email='" + email + "', password='" + password +"' where email = '" + oldEmail + "'";
		    System.out.println(sql);
		    
		    int cnt = stmt.executeUpdate(sql);
		    msg="更新失敗";
		    if (cnt > 0) {
		    	// 更新後、最新のメールをセッションに設定
		    	session.setAttribute("email", email);
		    	msg="更新しました";
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
