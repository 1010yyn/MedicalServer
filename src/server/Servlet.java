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
		MyHttpServletRequest Req = new MyHttpServletRequest(request);// 瑙ｇ爜涓枃瀛楃
		String tmp = Req.getParameter("params");
		System.out.println("get:" + tmp);

		// 璁剧疆杩斿洖鏍煎紡
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

			MyHttpServletRequest Req = new MyHttpServletRequest(request);// 瑙ｇ爜涓枃瀛楃
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
			case "getUserList":Class_user.getUserList(Req, response, out);//获取用户列表
				break;
			case "getInfo":
				Class_user.getInfo(Req, response, out);// 获取个人信息
				break;
			case "updateInfo":
				Class_user.updateInfo(Req, response, out);// 管理员修改个人信息
				break;
			case "deleteUser":
				Class_user.deleteUser(Req, response, out);// 删除用户
				break;
			case "getExamInfo":
				Class_Exam.getExamInfo(Req, response, out);// 获取考试信息
				break;
			case "updateExamInfo":
				Class_Exam.updateExamInfo(Req, response, out);// 更新考试信息
				break;
			case "getExam":
				Class_Question.getQuestion(Req, response, out);// 获取考试列表
				break;
			case "saveAnswer":
				Class_Question.saveAnswer(Req, response, out);// 保存答案
				break;
			case "getExamList":
				Class_Exam.getExamList(Req, response, out);// 获取所有的考试信息（管理员
				break;
			case "getExamHistory":
				Class_Exam.getExamHistory(Req, response, out);// 获取考试记录
				break;
			case "getCourseList":
				Class_Course.getCourseList(Req, response, out);// 获取所有的课程信息（管理员
				break;
			case "getCourseHistory":
				Class_Course.getCourseHistory(Req, response, out);// 获取课程学习记录
				break;
			case "getMessageList":
				break;
			case "getMessage":
				Class_Message.getMessage(Req, response, out);// 获取单条message
				break;
			case "modifyMessage":
				Class_Message.modifyMessage(Req, response, out);// 修改message
				break;
			case "deleteMessage":
				Class_Message.deleteMessage(Req, response, out);// 刪除message
				break;

			}
			out.close();
		}
	}
}
