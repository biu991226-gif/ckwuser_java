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
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		String flag = request.getParameter("loginFlag");
		System.out.println(flag);
		String firstName = "";
		String lastName = "";
		String email = "";
		String password = "";


		try {
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    conn = DriverManager.getConnection(url, user, passwords);
		    HttpSession session = request.getSession();
		    session.setAttribute("email", "");
		    
		    if("1".equals(flag)){
				// 新規登録
				firstName = request.getParameter("firstName");
				lastName = request.getParameter("lastName");
				email = request.getParameter("email");
			    password = request.getParameter("password");

			    Statement stmt = conn.createStatement();
			    String sql = "insert into users (firstName,lastName,email,password) values ('" 
			    + firstName + "', '" + lastName + "', '" + email + "', '" + password + "')";
			    int num = stmt.executeUpdate(sql);
//			$link = mysqli_connect("mysql3101.db.sakura.ne.jp", "mzx991226_zhixin", "mzx991226", "mzx991226_zhixin");
	//
//			$sql ="INSERT INTO `users`(`firstName`,`lastName`,`email`, `password`) VALUES ('$firstname','$lastname','$email','$password ')";
//			$res = mysqli_query($link, $sql);
	//
	//
//			echo "注册成功";
//			echo "<a href='login.html'>登录</a>";
			    
			    response.setContentType("text/html; charset=UTF-8");
			    
			   msg = "注册成功" + "<a href='login.html'>登录</a>";

			    



			} else {
				// login
				email = request.getParameter("login_email");
			    password = request.getParameter("login_password");
			    
			    Statement stmt = conn.createStatement();
			    String sql = "SELECT email, password FROM users where email='" + email + "' and password='" + password + "'";
			    System.out.println(sql);
			    ResultSet rs = stmt.executeQuery(sql);
			    
			    msg = "メールまたはパスワードが不正です";
			    while (rs.next()) {
			    	
			    	msg =
			    	    "<!DOCTYPE html>" +
			    	    "<html>" +
			    	    "<head>" +
			    	    "<meta charset='UTF-8'>" +
			    	    "<meta http-equiv='refresh' content='2;url=my.html'>" + // 2秒后跳转
			    	    "<style>" +
			    	    "body{font-family:sans-serif;text-align:center;padding-top:100px;}" +
			    	    ".box{display:inline-block;padding:30px 40px;" +
			    	    "background:rgba(255,255,255,0.7);" +
			    	    "border-radius:20px;}" +
			    	    "</style>" +
			    	    "</head>" +
			    	    "<body>" +
			    	    "<div class='box'>" +
			    	    "<h2>登录成功</h2>" +
			    	    "<p>正在跳转到个人页面…</p>" +
			    	    "<p><a href='my.html'>如果没有跳转，请点击这里</a></p>" +
			    	    "</div>" +
			    	    "</body></html>";

			    	session.setAttribute("email", email);
			    }


	//
//			$link = mysqli_connect("mysql3101.db.sakura.ne.jp", "mzx991226_zhixin", "mzx991226", "mzx991226_zhixin");
	//
//			$sql ="SELECT email, password FROM `users` 
//			WHERE 
//			email='$email' and password='$password'";
//			$res = mysqli_query($link, $sql);
	//
//			if($res->num_rows==0){
	//
//			echo "这个用户不存在！".$sql;
//			}else{ 
//			$_SESSION["email"]= $email;
//			echo "登录成功";
//			echo "<a href='my.php'>自己的信息</a>";
//			}
			}
		} catch (Exception e) {
			e.printStackTrace();
		    msg = "処理失敗しました。";
		} finally {
			response.setContentType("text/html; charset=UTF-8");
			response.getWriter().append(msg);
		}
		
	}

}
