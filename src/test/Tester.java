package test;

import org.junit.Test;
import util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Tester {

	public static void main(String[] args) {


		String sql = "SELECT birthday FROM student WHERE id = 1;";
		try (Connection c = DBUtil.getConnection(); Statement st = c.createStatement()) {

			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				Date date = rs.getDate(1);
				System.out.println(date);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() {

		// 反例
		List<String> list = new ArrayList<>();
		list.add("1");
		list.add("2");
		for (String item : list) {
			if ("1".equals(item)) {
				list.remove(item);
			}
		}

		for (String item : list) {
			System.out.println(item);
		}


	}


}
