package org.sdu.database;

import java.io.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

/**
 * Build database connection.
 * 
 * @version 0.1 rev 8001 Jan. 4, 2013
 * Copyright (c) HyperCube Dev Team
 */
public class Connect {
	private Statement statement;
	private Connection conn;
	private String table;
	public String webserverAddress;

	public Connect() {
		// Read configuration file
		String line, databaseAddress = "", database = "", user = "", password = "";
		try {
			FileReader in = new FileReader("database.conf");
			Scanner conf = new Scanner(in);
			while (conf.hasNextLine()) {
				line = conf.nextLine();
				if (line.equals("[database_address]"))
					databaseAddress = conf.next();
				if (line.equals("[webserver_address]"))
					webserverAddress = conf.next();
				if (line.equals("[database]"))
					database = conf.next();
				if (line.equals("[table]"))
					table = conf.next();
				if (line.equals("[user]"))
					user = conf.next();
				if (line.equals("[password]"))
					password = conf.next();
			}
			conf.close();
			in.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "配置文件错误", "启动失败",
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}

		// Connect to database
		try {
			String url = "jdbc:mysql://" + databaseAddress + "/" + database;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, password);
			statement = conn.createStatement();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "数据库连接错误", "启动失败",
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
	}

	public void close() {
		try {
			statement.close();
			conn.close();
		} catch (Exception e) {
		}
	}

	ResultSet getAll() throws Exception {
		statement.setFetchSize(1001);
		return statement.executeQuery("select * from " + table);
	}

	int getAllCount() throws Exception {
		ResultSet count = statement.executeQuery("select count(*) from "
				+ table);
		count.next();
		int countNum = count.getInt(1);
		count.close();
		return countNum;
	}

	void delete(String id) throws Exception {
		statement.execute("delete from " + table + " where id='" + id + "'");
	}
	
	void setPic() throws Exception {
		FileReader fi = new FileReader("1.txt");
		Scanner scan = new Scanner(fi);
		String s, s1, s2;
		int i;
		Random rand = new Random();
		File pic, pic1;
		while (scan.hasNext()) {
			s = scan.next();
			pic = new File("/Users/cc941201/Desktop/Database/wget/pic/" + s);
			do {
				s1 = "";
				for (i = 0; i < 27; i++)
					s1 += (char) (rand.nextInt(26) + 97);
				s2 = "";
				for (i = 0; i < 5; i++)
					s2 += (char) (rand.nextInt(26) + 97);
				pic1 = new File("/Library/Server/Web/Data/Sites/Default/pic/"
						+ s1 + "/" + s2 + ".jpg");
			} while (pic1.exists());
			File path = new File("/Library/Server/Web/Data/Sites/Default/pic/"
					+ s1 + "/");
			if (!path.exists())
				path.mkdirs();
			pic.renameTo(pic1);
			statement.execute("update stu set pic='" + s1 + s2 + "' where id='"
					+ s.substring(0, s.length() - 4) + "'");
		}
	}
}
