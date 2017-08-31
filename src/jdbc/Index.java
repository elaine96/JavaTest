package jdbc;

import java.io.IOException;
import java.util.Scanner;

public class Index {
	public static void menu() {
		System.out.println("1.登录");
		System.out.println("2.注册");
		System.out.println("0.退出");
		System.out.println("请选择：");
		Scanner scanner = new Scanner(System.in);
		String choose = scanner.nextLine();
		switch(choose) {
			case "1":
				User.login();
				break;
			case "2":
				User.register();
				break;
			case "0":
				System.out.println("退出系统成功");
				System.exit(0);
				break;
			default:
				System.out.println("请输入1或2");
				menu();
				break;
		}
	}
	
	public static void admin() {
		System.out.println("1.增加");
		System.out.println("2.修改");
		System.out.println("3.删除");
		System.out.println("4.查看");
		System.out.println("5.退出登录");
		System.out.println("0.退出系统");
		System.out.println("请选择：");
		Scanner admin = new Scanner(System.in);
		String adChoose = admin.nextLine();
		switch(adChoose) {
			case "1":
				Student.insert();
				break;
			case "2":
				Student.update();
				break;
			case "3":
				Student.delete();
				break;
			case "4":
				Student.show();
				break;
			case "5":
				menu();
				break;
			case "0":
				System.out.println("退出系统成功");
				System.exit(0);
				break;
			default:
				System.out.println("请输入0-5");
				admin();
				break;
		}
	}
	
	public static void main(String []args) {
		menu();
	}
}
