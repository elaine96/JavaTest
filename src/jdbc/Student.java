package jdbc;

import java.sql.*;
import java.util.Scanner;

public class Student {
	static Connection con = ConnectionManager.getConnection();
	static PreparedStatement pre;
	static Statement sta;
	static ResultSet rs;
	public static void insert() {
		System.out.println("请输入学生学号");
		Scanner number = new Scanner(System.in);
		String num = number.nextLine();
		if(num.equals("") || num.equals(null)) {
			System.out.println("学号不能为空");
		} else {
			try {
				sta = con.createStatement();
				String sql = "select * from students where number='"+num+"'";
				rs = sta.executeQuery(sql);
				if(rs.next()){
					System.out.println("学号已经存在");
				} else {
					System.out.println("请输入学生姓名");
					Scanner stuName = new Scanner(System.in);
					String name = stuName.nextLine();
					if(name.equals("") || name.equals(null)) {
						System.out.println("姓名不能为空");
					} else {
						System.out.println("请输入学生班级");
						Scanner className = new Scanner(System.in);
						String classn = className.nextLine();
						String sql1 = "insert into students(number,name,class) value(?,?,?)";
						pre = con.prepareStatement(sql1);
						pre.setString(1, num);
						pre.setString(2, name);
						pre.setString(3, classn);
						pre.executeUpdate();
						System.out.println("插入成功");
					}
				}
				Index.admin();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void update() {
		System.out.println("请输入要修改的学生学号");
		Scanner number = new Scanner(System.in);
		String num = number.nextLine();
		String sql = "select * from students where number='"+num+"'";
		try {
			sta = con.createStatement();
			rs = sta.executeQuery(sql);
			if(rs.next()) {
				System.out.println("请输入该学生姓名");
				Scanner stuName = new Scanner(System.in);
				String name = stuName.nextLine();
				if(name.equals("") || name.equals(null)) {
					System.out.println("学生姓名不能为空");
				} else {
					System.out.println("请输入该学生班级");
					Scanner className = new Scanner(System.in);
					String classn = className.nextLine();
					String sql1 = "update students set name = ? , class = ? where number = ?";
					pre = con.prepareStatement(sql1);
					pre.setString(1, name);
					pre.setString(2, classn);
					pre.setString(3, num);
					pre.executeUpdate();
					System.out.println("学生信息修改成功");
				}
			} else {
				System.out.println("该学号不存在");
			}
			Index.admin();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public static void delete() {
		System.out.println("请输入要删除的学生学号：");
		Scanner number = new Scanner(System.in);
		String num = number.nextLine();
		try {
			sta = con.createStatement();
			String sql = "select * from students where number='"+num+"'";
			rs = sta.executeQuery(sql);
			if(rs.next()) {
				String sql1 = "delete from students where number='"+num+"'";
				pre = con.prepareStatement(sql1);
				pre.executeUpdate();
				System.out.println("学生删除成功");
			} else {
				System.out.println("该学号不存在");
			}
			Index.admin();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void show() {
		try {
			sta = con.createStatement();
			String sql = "select * from students";
			rs = sta.executeQuery(sql);
			System.out.println("序号\t学号\t姓名\t班级\t");
			while(rs.next()) {
				System.out.print(rs.getString("id")+"\t");
				System.out.print(rs.getString("number")+"\t");
				System.out.print(rs.getString("name")+"\t");
				System.out.print(rs.getString("class")+"\n");
			}
			Index.admin();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
