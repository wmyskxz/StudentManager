package servlet;

import bean.Student;
import dao.StudentDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/addStudent")
public class AddServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Student student = new Student();

		int studentID = Integer.parseInt(req.getParameter("studentID"));

		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		String sex = req.getParameter("radio");
		Date birthday = null;

		// String 类型按照 yyyy-MM-dd 的格式转换为 java.util.Date 类
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			birthday = simpleDateFormat.parse(req.getParameter("birthday"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		student.setStudentID(studentID);
		student.setName(name);
		student.setAge(age);
		student.setSex(sex);
		student.setBirthday(birthday);

		new StudentDaoImpl().add(student);

		resp.sendRedirect("/listStudent");
	}
}
