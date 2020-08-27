package server;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Connector2DB {
	
	private final static String ID = "root";
	private final static String PWD = "root";
	private final static String URL = "jdbc:mysql://localhost:3306/medical_database";
	
	public static Statement stmt;
	public static Connection connect;
	public static String dbms = "medical_database";

	//链接数据库
	public static void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Success loading Mysql Driver!\n");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.print("Error loading Mysql Driver!\n");
			e.printStackTrace();
		}
		try {
			connect = DriverManager.getConnection(URL, ID, PWD);//登录数据库
			System.out.println("Success connect Mysql server!");//成功连接数据库
			stmt = connect.createStatement();
		} catch (Exception e1) {
			System.out.print("Error connect Mysql server!");
			e1.printStackTrace();
		}
	}
	
	
	public static boolean joinDB() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			System.out.print("Error loading Mysql Driver!");
			return false;
		}
		try {
			connect = DriverManager.getConnection(URL, ID, PWD);
			System.out.println("Success connect Mysql server!");//成功连接数据库
			stmt = connect.createStatement();
		} catch (Exception e) {
			System.out.println("Error connect Mysql server!");
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	//查询数据库
	public static boolean query(String sqlString) {
		try {
			Servlet.rs = stmt.executeQuery(sqlString);
			System.out.println("Success query data!");
		} catch (SQLException e) {
			System.out.println("get data error!");
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	//更新数据库
	public static boolean update(String sqlString)
	{
		try
		{
			stmt.executeUpdate(sqlString);
			System.out.println("Success update data!");
		} catch (SQLException e)
		{
			System.out.println("update data error!");
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}
	
	//关闭数据库
	public static void close() {
		try {
			stmt.close();
			connect.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
