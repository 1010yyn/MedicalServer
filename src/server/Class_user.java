package server;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Class_user {
	private String user_id;// 鐢ㄦ埛id
	private String login_id;// 鐧诲綍鍚�
	private String password;// 鐢ㄦ埛瀵嗙爜
	private String user_name;// 鐢ㄦ埛鍚�
	private String user_phone;// 鐢ㄦ埛鎵嬫満鍙�
	private String office;// 鐢ㄦ埛鎵嬫満鍙�
	private String sex;// 鎬у埆
	private String post;// 鑱屼綅
	private String head;// 鐢ㄦ埛澶村儚
	static Gson g = new Gson();

	public Class_user(String user_id, String login_id, String password, String user_name, String user_phone,
			String office, String sex, String post, String head) {
		super();
		this.user_id = user_id;
		this.login_id = login_id;
		this.password = password;
		this.user_name = user_name;
		this.user_phone = user_phone;
		this.office = office;
		this.sex = sex;
		this.post = post;
		this.head = head;
	}

	public static void login(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String login_id = Req.getParameter("login_id");
		String password = Req.getParameter("password");
		System.out.println(login_id + password);// 鑾峰彇琛ㄥ崟鏁版嵁

		String sqlString = "select user_id from user_ordinary where login_id='" + login_id + "' and password='"
				+ password + "'";
		System.out.println(sqlString);
		try {
			if (Connector2DB.joinDB())
				Connector2DB.query(sqlString);
			if (Servlet.rs.next()) {
				out.println(Servlet.rs.getString("user_id"));// 杩斿洖鐢ㄦ埛id
				System.out.println(Servlet.rs.getString("user_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connector2DB.close();
	}

	public static void getInfo(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String user_id = Req.getParameter("user_id");
		System.out.println("user_id:" + user_id);// 获取UID

		String sqlString = "select * from user_ordinary where user_id='" + user_id + "'";
		try {
			if (Connector2DB.joinDB())
				Connector2DB.query(sqlString);
			out.println("[");
			while (Servlet.rs.next()) {
				Class_user user = new Class_user(Servlet.rs.getString("user_id"), Servlet.rs.getString("login_id"),
						Servlet.rs.getString("password"), Servlet.rs.getString("user_name"),
						Servlet.rs.getString("user_phone"), Servlet.rs.getString("office"), Servlet.rs.getString("sex"),
						Servlet.rs.getString("post"), Servlet.rs.getString("head"));
				if (Servlet.rs.isLast())// 最后一个用户信息
					out.println(g.toJson(user));
				else
					out.println(g.toJson(user) + ",");
				System.out.println(g.toJson(user));
			}
			out.println("]");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connector2DB.close();
	}

	public static void updateInfo(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String user_id = Req.getParameter("user_id");
		System.out.println("user_id:" + user_id);// 获取UID
		String login_id = Req.getParameter("login_id");
		String password = Req.getParameter("password");
		String user_name = Req.getParameter("user_name");
		String user_phone = Req.getParameter("user_phone");
		String office = Req.getParameter("office");
		String sex = Req.getParameter("sex");
		String post = Req.getParameter("post");
		String head = Req.getParameter("head");

		String sqlString = "update user_ordinary set login_id='" + login_id + "',password='" + password
				+ "',user_name='" + user_name + "',user_phone='" + user_phone + "',office='" + office + "',sex='" + sex
				+ "',post='" + post + "',head='" + head + "' where user_id=" + user_id;

		System.out.println(sqlString);
		try {
			if (Connector2DB.joinDB()) {
				Connector2DB.update(sqlString);
				out.println(true);
			}
		} catch (Exception e) {
			System.out.println("updateInfo() Error!" + e.getMessage());
			out.println(false);
		}
		Connector2DB.close();
	}

	public static void deleteUser(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {

	}
}
