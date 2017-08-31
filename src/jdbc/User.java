package jdbc;

import java.sql.*;
import java.util.Scanner;

public class User {
	static Connection con = ConnectionManager.getConnection();
	static Statement sta;
	static ResultSet rs;
	static PreparedStatement pre;
	public static void login() {
		System.out.println("请输入用户名：");
		Scanner name = new Scanner(System.in);
		String username = name.nextLine();
		System.out.println("请输入密码：");
		Scanner pass = new Scanner(System.in);
		String password = pass.nextLine();
		try {
			sta = con.createStatement();
			String sql = "select * from users where name='"+username+"' and password='"+password+"'";
			rs = sta.executeQuery(sql);
			if(rs.next()) {
				System.out.println("欢迎"+username);
				Index.admin();
			} else {
				System.out.println("用户名或密码错误");
				Index.menu();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void register() {
		System.out.println("请输入用户名：");
		Scanner name = new Scanner(System.in);
		String username = name.nextLine();
		System.out.println("请输入密码：");
		Scanner pass = new Scanner(System.in);
		String password = pass.nextLine();
		if (username.equals(null)|| username.equals("") || password.equals(null) || password.equals("")) {
			System.out.println("用户名和密码不能为空");
			Index.menu();
		} else {
			String sql = "insert into users(name,password) values(?,?)";
			try {
				pre = con.prepareStatement(sql);
				pre.setString(1,username);
				pre.setString(2, password);
				String sql1 = "select * from users where name='"+username+"'";
				sta = con.createStatement();
				rs = sta.executeQuery(sql1);
				if(rs.next()) {
					System.out.println("用户名已经存在");
					Index.menu();
				} else {
					pre.executeUpdate();
					System.out.println("注册成功，请登录");
					login();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
