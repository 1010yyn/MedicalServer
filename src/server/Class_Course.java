package server;

import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Class_Course {
	private String course_id = new String();
	private String course_title = new String();
	private String office = new String();
	private String sum = new String();
	private String completed = new String();
	private String start = new String();
	private String end = new String();
	private String time_stamp = new String();
	static Gson g = new Gson();

	public Class_Course(String course_id, String course_title, String office, String sum, String completed,
			String start, String end, String time_stamp) {
		this.course_id = course_id;
		this.course_title = course_title;
		this.office = office;
		this.sum = sum;
		this.completed = completed;
		this.start = start;
		this.end = end;
		this.time_stamp = time_stamp;
	}

	public static void getCourseHistory(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String tmp = Req.getParameter("user_id");
		System.out.println("user_id：" + tmp);// 获取用户编号
		// 连接数据库
		String sqlString = "SELECT course_info.*,course_history.time_stamp FROM (course_info join course_history on course_info.course_id=course_history.course_id) where user_id ="
				+ tmp + " order by time_stamp desc";
		System.out.println(sqlString);
		try {
			if (Connector2DB.joinDB())
				Connector2DB.query(sqlString);
			out.println("[");
			while (Servlet.rs.next()) {
				Class_Course course = new Class_Course(Servlet.rs.getString("course_id"),
						Servlet.rs.getString("course_title"), Servlet.rs.getString("office"),
						Servlet.rs.getString("sum"), Servlet.rs.getString("completed"), Servlet.rs.getString("start"),
						Servlet.rs.getString("end"), Servlet.rs.getString("time_stamp"));
				if (Servlet.rs.isLast())// 最后一个不需要逗号
					out.println(g.toJson(course));
				else
					out.println(g.toJson(course) + ",");
				System.out.println(g.toJson(course));
			}
			out.println("]");
		} catch (Exception e) {
			System.out.println("getExamHistory() Error!" + e.getMessage());
		}
		Connector2DB.close();
	}

}
