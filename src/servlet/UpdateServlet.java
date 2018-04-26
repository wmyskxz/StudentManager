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

@WebServlet("/updateStudent")
public class UpdateServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Student student = new Student();

		int id = Integer.parseInt(req.getParameter("id"));
		int studentID = Integer.parseInt(req.getParameter("studentID"));
		String name = req.getParameter("name");
		int age = Integer.parseInt(req.getParameter("age"));
		String sex = req.getParameter("radio");

		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date birthday = null;
		try {
			birthday = simpleDateFormat.parse(req.getParameter("birthday"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		student.setId(id);
		student.setStudentID(studentID);
		student.setName(name);
		student.setAge(age);
		student.setSex(sex);
		student.setBirthday(birthday);

		new StudentDaoImpl().update(student);

		resp.sendRedirect("/listStudent");
	}
}
