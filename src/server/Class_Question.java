package server;

import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class Class_Question {

	private String exam_id = new String();
	private String question_id = new String();
	private String question_items = new String();
	static Gson g = new Gson();

	public Class_Question(String exam_id, String question_id, String question_items) {
		this.exam_id = exam_id;
		this.question_id = question_id;
		this.question_items = question_items;
	}

	public static void getQuestion(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String tmp = Req.getParameter("exam_id");
		System.out.println("exam_id：" + tmp);// 获取考试编号

		// 连接数据库
		String sqlString = "select * from exam_question where exam_id = \"" + tmp + "\"";
		System.out.println(sqlString);
		try {
			if (Connector2DB.joinDB())
				Connector2DB.query(sqlString);
			out.println("[");
			while (Servlet.rs.next()) {
				System.out.println(Servlet.rs.getString("question_items"));
				Class_Question question = new Class_Question(Servlet.rs.getString("exam_id"),
						Servlet.rs.getString("question_id"), Servlet.rs.getString("question_items"));
				if (Servlet.rs.isLast())// 最后一个不需要逗号
					out.println(g.toJson(question));
				else
					out.println(g.toJson(question) + ",");
				System.out.println(g.toJson(question));
			}
			out.println("]");
		} catch (Exception e) {
			System.out.println("getExam() Error!" + e.getMessage());
		}
		Connector2DB.close();
	}

	public static void saveAnswer(MyHttpServletRequest Req, HttpServletResponse response, PrintWriter out) {
		String tmp = Req.getParameter("answerList");
		System.out.println("answerList：" + tmp);// 获取考试编号
		out.println(g.toJson(tmp));
		// TODO 存储blob数据 需要存储文件
	}

}
