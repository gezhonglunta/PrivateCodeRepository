package com.leftright.tiny.website.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcAccessTest {

	public static void main(String[] args) {
		try {
			System.out.println(Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"));
			Connection con = DriverManager.getConnection("jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ=db.accdb", "admin", "");
			Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery("select * from User");
			while (resultSet.next()) {
				String value = resultSet.getString("UserName");
				System.out.println(value);
			}
			con.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
