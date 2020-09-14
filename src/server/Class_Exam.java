package server;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public static void getExamList(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String sqlString = "SELECT * FROM exam_info";
		System.out.println(sqlString);
		try {
			if (Connector2DB.joinDB())
				Connector2DB.query(sqlString);
			out.println("[");
			while (Servlet.rs.next()) {
				Class_Exam exam = new Class_Exam(Servlet.rs.getString("exam_id"), Servlet.rs.getString("exam_title"),
						Servlet.rs.getString("office"), Servlet.rs.getString("start"), Servlet.rs.getString("end"), "");
				if (Servlet.rs.isLast())// 最后一条记录
					out.println(g.toJson(exam));
				else
					out.println(g.toJson(exam) + ",");
				System.out.println(g.toJson(exam));
			}
			out.println("]");
		} catch (Exception e) {
			System.out.println("getExamList() Error!" + e.getMessage());
		}
		Connector2DB.close();
	}

	public static void getExamHistory(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String tmp = Req.getParameter("user_id");
		System.out.println("user_id:" + tmp);// 获取用户ID

		String sqlString = "select * from (exam_info left join exam_history on exam_history.exam_id = exam_info.exam_id) where user_id = '"
				+ tmp + "' order by time_stamp desc";
		System.out.println(sqlString);
		try {
			if (Connector2DB.joinDB())
				Connector2DB.query(sqlString);
			out.println("[");
			while (Servlet.rs.next()) {
				Class_Exam exam = new Class_Exam(Servlet.rs.getString("exam_id"), Servlet.rs.getString("exam_title"),
						Servlet.rs.getString("office"), Servlet.rs.getString("start"), Servlet.rs.getString("end"),
						Servlet.rs.getString("time_stamp"));
				if (Servlet.rs.isLast())// 最后一条记录
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

	public static void getExamInfo(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String exam_id = Req.getParameter("exam_id");
		System.out.println("exam_id:" + exam_id);// 获取考试编号

		String sqlString = "select * from exam_info where exam_id='" + exam_id + "'";

		try {
			if (Connector2DB.joinDB())
				Connector2DB.query(sqlString);
			if (Servlet.rs.next()) {
				Class_Exam exam = new Class_Exam(Servlet.rs.getString("exam_id"), Servlet.rs.getString("exam_title"),
						Servlet.rs.getString("office"), Servlet.rs.getString("start"), Servlet.rs.getString("end"), "");
				out.println(g.toJson(exam));// 杩斿洖鐢ㄦ埛id
				System.out.println(Servlet.rs.getString("user_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("getExamInfo() Error!" + e.getMessage());
		}
		Connector2DB.close();
	}

	public static void updateExamInfo(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String exam_id = Req.getParameter("exam_id");
		System.out.println("exam_id:" + exam_id);// 获取考试编号
		String exam_title = Req.getParameter("exam_title");
		String office = Req.getParameter("office");
		String start = Req.getParameter("start");
		String end = Req.getParameter("end");

		String sqlString = "UPDATE exam_info SET exam_title = '" + exam_title + "', office = '" + office
				+ "', start = '" + start + "',end='" + end + "' WHERE exam_id = '" + exam_id + "'";
		System.out.println(sqlString);
		try {
			if (Connector2DB.joinDB()) {
				Connector2DB.update(sqlString);
				out.println(true);
			}
		} catch (Exception e) {
			System.out.println("updateExam() Error!" + e.getMessage());
			out.println(false);
		}
		Connector2DB.close();

	}

}
