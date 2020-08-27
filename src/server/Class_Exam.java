package server;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Class_Exam {

	private String exam_id = new String();
	private String question_id = new String();
	private String question_items = new String();
	static Gson g = new Gson();

	public Class_Exam(String exam_id, String question_id, String question_items) {
		this.exam_id = exam_id;
		this.question_id = question_id;
		this.question_items = question_items;
	}

	public static void getExam(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String tmp = Req.getParameter("exam_id");
		System.out.println("post：" + tmp);// 获取考试编号

		// 连接数据库
		String sqlString = "select * from exam_question where exam_id = \"" + tmp + "\"";
		System.out.println(sqlString);
		try {
			if (Connector2DB.joinDB())
				Connector2DB.query(sqlString);
			Servlet.rs.last();
			int count = Servlet.rs.getRow();
			System.out.println(count);
			Servlet.rs.beforeFirst();
			while (Servlet.rs.next()) {
				System.out.println(Servlet.rs.getString("question_items"));
				Class_Exam exam = new Class_Exam(Servlet.rs.getString("exam_id"), Servlet.rs.getString("question_id"),
						Servlet.rs.getString("question_items"));
				out.println(g.toJson(exam));
				System.out.println(g.toJson(exam));
			}
		} catch (Exception e) {
			System.out.println("getExam() Error!" + e.getMessage());
		}
		// 关闭数据库
		Connector2DB.close();
	}

}
