package dao;

import bean.Student;
import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StudentDAO {

	/**
	 * 获取数据库中的数据总数目
	 * @return
	 */
	public int getTotal() {

		int total = 0;

		String sql = "SELECT COUNT(*) FROM student";
		try (Connection c = DBUtil.getConnection(); Statement st = c.createStatement()) {

			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return total;
	}

	/**
	 * 向数据库中增加一条数据
	 * @param student
	 */
	public void add(Student student) {

		String sql = "INSERT INTO student VALUES(NULL,?,?,?,?,?)";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setInt(1, student.getStudentID());
			ps.setString(2, student.getName());
			ps.setInt(3, student.getAge());
			ps.setString(4, student.getSex());
			ps.setDate(5, new java.sql.Date(student.getBirthday().getTime()));

			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void delete(int id) {

		String sql = "DELETE FROM student WHERE ID = ?";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setInt(1, id);

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Student student) {

		String sql = "update student set student_id = ?, name = ?, age = ?, sex = ?, birthday = ? where id = ? ";
		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {

			ps.setInt(1, student.getStudentID());
			ps.setString(2, student.getName());
			ps.setInt(3, student.getAge());
			ps.setString(4, student.getSex());
			ps.setDate(5, new java.sql.Date(student.getBirthday().getTime()));
			ps.setInt(6, student.getId());

			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Student get(int id) {

		Student student = new Student();

		String sql = "SELECT * FROM student WHERE ID = " + id;
		try (Connection c = DBUtil.getConnection(); Statement st = c.createStatement()) {

			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {

				int student_id = rs.getInt("student_id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String sex = rs.getString("sex");
				Date birthday = rs.getDate("birthday");
				student.setStudentID(student_id);
				student.setName(name);
				student.setAge(age);
				student.setSex(sex);
				student.setBirthday(birthday);
				student.setId(id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	public List<Student> list() {
		return list(0, Short.MAX_VALUE);
	}

	public List<Student> list(int start, int count) {

		List<Student> students = new ArrayList<>();

		String sql = "SELECT * FROM student ORDER BY student_id desc limit ?,?";

		try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
			ps.setInt(1, start);
			ps.setInt(2, count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Student student = new Student();
				int id = rs.getInt("id");
				int studentID = rs.getInt("student_id");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				String sex = rs.getString("sex");
				Date birthday = rs.getDate("birthday");
				student.setId(id);
				student.setStudentID(studentID);
				student.setName(name);
				student.setAge(age);
				student.setSex(sex);
				student.setBirthday(birthday);

				students.add(student);
			}

			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return students;
	}

}
