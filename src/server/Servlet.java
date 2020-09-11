package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Server
 */
@WebServlet("/Server")
public class Servlet extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public static ResultSet rs;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		MyHttpServletRequest Req = new MyHttpServletRequest(request);// 解码中文字符
		String tmp = Req.getParameter("params");
		System.out.println("get:" + tmp);

		// 设置返回格式
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
		response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
		PrintWriter out = response.getWriter();
		out.println("{res:get true}");

		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		synchronized (this) {

			MyHttpServletRequest Req = new MyHttpServletRequest(request);// 解码中文字符
			String tmp = Req.getParameter("type");

			System.out.println("post：" + tmp);

			response.setContentType("text/html;charset=UTF-8");
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Headers", "X-Requested-With");
			response.setHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
			PrintWriter out = response.getWriter();

			switch (tmp) {
			case "login":
				Class_user.login(Req, response, out);// 登录
				break;
			case "getInfo":
				Class_user.getInfo(Req, response, out);//获取用户信息
				break;
			case "getExam":
				Class_Question.getQuestion(Req, response, out);// 发送试卷试卷
				break;
			case "saveAnswer":
				Class_Question.saveAnswer(Req, response, out);// 存储考生答案数据
				break;
			case "getExamHistory":
				Class_Exam.getExamHistory(Req, response, out);// 获取考试记录
				break;
			case "getCourseHistory":
				Class_Course.getCourseHistory(Req, response, out);// 获取课程记录
				break;
			}
			out.close();
		}
	}
}
