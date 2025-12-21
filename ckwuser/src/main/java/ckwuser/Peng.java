package ckwuser;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
public class Peng extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Peng() {
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
		    
		    HttpSession session = request.getSession();
		    email = (String) session.getAttribute("email");
		    
		    List<String> list = new ArrayList<>();
		    
		    Statement stmt = conn.createStatement();
		    String sql1 = "SELECT `to` FROM friend where `from`='" + email + "'";
		    String sql2 = "SELECT `from` FROM friend where `to`='" + email + "'";
		    
		    
		    
		    ResultSet rs = stmt.executeQuery(sql1);
		    while (rs.next()) {
		    	list.add(rs.getString(1));
		    }
		    ResultSet rs2 = stmt.executeQuery(sql2);
		    while (rs2.next()) {
		    	list.add(rs2.getString(1));
		    }
		    // 为了看到自己的朋友圈内容，加上自己的邮件地址
		    list.add(email);
		    
		    String sql3 = "SELECT `from`,content,`time` FROM moments WHERE `from` IN (";

		    for (int i = 0; i < list.size(); i++) {
		        sql3 += "'" + list.get(i) + "'";
		        if (i != list.size() - 1) {
		            sql3 += ",";
		        }
		    }

		    sql3 += ") ORDER BY `time` DESC";
		    System.out.println(sql3);
		    
		    ResultSet rs3 = stmt.executeQuery(sql3);
		    
		    while (rs3.next()) {
		    	msg += rs3.getString(1) + ": " + rs3.getString(2) + " ---- " + rs3.getString(3) + "<br>";
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
