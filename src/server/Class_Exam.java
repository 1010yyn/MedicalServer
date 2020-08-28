package server;

import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Class_Exam {
	private String exam_id = new String();
	private String exam_title = new String();
	private String office = new String();
	private String start = new String();
	private String end = new String();
	private String time_stamp = new String();
	static Gson g = new Gson();

	public Class_Exam(String exam_id, String exam_title, String office, String start, String end, String time_stamp) {
		this.exam_id = exam_id;
		this.exam_title = exam_title;
		this.office = office;
		this.start = start;
		this.end = end;
		this.time_stamp = time_stamp;
	}

	public static void getExamHistory(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String tmp = Req.getParameter("user_id");
		System.out.println("user_id：" + tmp);// 获取用户编号
		// 连接数据库
		String sqlString = "select * from (exam_info left join exam_history on exam_history.exam_id = exam_info.exam_id) where user_id = '"
				+ tmp + "' order by time_stamp desc";
		System.out.println(sqlString);
		try {
			if (Connector2DB.joinDB())
				Connector2DB.query(sqlString);
			out.println("[");
			while (Servlet.rs.next()) {
				Class_Exam exam = new Class_Exam(Servlet.rs.getString("exam_id"), Servlet.rs.getString("exam_title"),
						Servlet.rs.getString("office"), Servlet.rs.getString("start"), Servlet.rs.getString("end"), Servlet.rs.getString("time_stamp"));
				if (Servlet.rs.isLast())// 最后一个不需要逗号
					out.println(g.toJson(exam));
				else
					out.println(g.toJson(exam) + ",");
				System.out.println(g.toJson(exam));
			}
			out.println("]");
		} catch (Exception e) {
			System.out.println("getExamHistory() Error!" + e.getMessage());
		}
		Connector2DB.close();
	}

}
