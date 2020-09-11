package server;

import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Class_user {
	private String user_id;// 用户id
	private String login_id;// 登录名
	private String password;// 用户密码
	private String user_name;// 用户名
	private String user_phone;// 用户手机号
	private String office;// 用户手机号
	private String sex;// 性别
	private String post;// 职位
	private String head;// 用户头像
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
		System.out.println(login_id + password);// 获取表单数据

		String sqlString = "select user_id from user_ordinary where login_id='" + login_id + "' and password='"
				+ password + "'";
		System.out.println(sqlString);
		try {
			if (Connector2DB.joinDB())
				Connector2DB.query(sqlString);
			if (Servlet.rs.next()) {
				out.println(Servlet.rs.getString("user_id"));// 返回用户id
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
		System.out.println("user_id:" + user_id);// 获取表单数据

		String sqlString = "select * from user_ordinary where user_id='" + user_id + "'";
		try {
			if (Connector2DB.joinDB())
				Connector2DB.query(sqlString);
			// 管理人员信息时需要多个信息
			out.println("[");
			while (Servlet.rs.next()) {
				Class_user user = new Class_user(Servlet.rs.getString("user_id"), Servlet.rs.getString("login_id"),
						Servlet.rs.getString("password"), Servlet.rs.getString("user_name"),
						Servlet.rs.getString("user_phone"), Servlet.rs.getString("office"), Servlet.rs.getString("sex"),
						Servlet.rs.getString("post"), Servlet.rs.getString("head"));
				if (Servlet.rs.isLast())// 最后一个不需要逗号
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

}
