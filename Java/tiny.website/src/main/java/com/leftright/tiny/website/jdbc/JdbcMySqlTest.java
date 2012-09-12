package com.leftright.tiny.website.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.leftright.tiny.website.utils.PropertiesUtils;

public class JdbcMySqlTest {

	public static void main(String[] args) {
		try {
			PropertiesUtils propertiesUtils = new PropertiesUtils();
			Class.forName(propertiesUtils.getProperty("jdbc.driver"));
			Connection con = DriverManager.getConnection(propertiesUtils.getProperty("jdbc.url"), propertiesUtils.getProperty("jdbc.username"), propertiesUtils.getProperty("jdbc.password"));
			Statement st = con.createStatement();
			ResultSet resultSet = st.executeQuery("select * from mppb_order where user_name='gezhonglunta' and ota_order_id is not null");
			while (resultSet.next()) {
				String value = resultSet.getString("order_id");
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
