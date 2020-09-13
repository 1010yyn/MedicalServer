package server;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Class_Message {
	private String message_id = new String();
	private String message_title = new String();
	private String office = new String();
	private String text = new String();
	// TODO--目标用户

	static Gson g = new Gson();

	public Class_Message(String message_id, String message_title, String office, String text) {
		this.message_id = message_id;
		this.message_title = message_title;
		this.office = office;
		this.text = text;
	}

	public static void getMessageList(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		// TODO--获取通知列表
		String tmp = Req.getParameter("user_id");
		System.out.println("exam_id:" + tmp);

	}

	// 获取message【单个】
	public static void getMessage(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String tmp = Req.getParameter("message_id");
		System.out.println("message_id:" + tmp);

		String sqlString = "select * from message_info where message_id = \"" + tmp + "\"";
		System.out.println(sqlString);
		try {
			if (Connector2DB.joinDB())
				Connector2DB.query(sqlString);
			if (Servlet.rs.next()) {
				System.out.println(Servlet.rs.getString("message_title"));
				Class_Message message = new Class_Message(Servlet.rs.getString("message_id"),
						Servlet.rs.getString("message_title"), Servlet.rs.getString("office"),
						Servlet.rs.getString("text"));
				out.println(g.toJson(message));
				System.out.println(g.toJson(message));
			}
		} catch (Exception e) {
			System.out.println("getMessage() Error!" + e.getMessage());
		}
		Connector2DB.close();
	}

	public static void addMessage() {
		// TODO--添加新的通知
	}

	// 修改message内容
	public static void modifyMessage(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String message_id = Req.getParameter("message_id");
		String message_title = Req.getParameter("message_title");
		String office = Req.getParameter("office");
		String text = Req.getParameter("text");
		System.out.println("message_id:" + message_id);

		String sqlString = "UPDATE message_info SET message_title = '" + message_title + "', office = '" + office
				+ "', text = '" + text + "' WHERE message_id = '" + message_id + "'";
		System.out.println(sqlString);
		try {
			if (Connector2DB.joinDB()) {
				Connector2DB.update(sqlString);
				out.println(true);
			}
		} catch (Exception e) {
			System.out.println("moidfyMessage() Error!" + e.getMessage());
			out.println(false);
		}
		Connector2DB.close();
	}

	// 刪除message
	public static void deleteMessage(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String message_id = Req.getParameter("message_id");
		System.out.println("message_id:" + message_id);

		String sqlString = "delete from message_info where message_id =" + message_id;
		System.out.println(sqlString);
		
		try {
			if (Connector2DB.joinDB()) {
				Connector2DB.update(sqlString);
				out.println(true);
			}
		} catch (Exception e) {
			System.out.println("deleteMessage() Error!" + e.getMessage());
			out.println(false);
		}
		Connector2DB.close();

	}
}
