package servlet;

import bean.Student;
import dao.StudentDao;
import dao.StudentDaoImpl;
import util.Page;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/listStudent")
public class ListServlet extends HttpServlet {

	private StudentDao studentDao = new StudentDaoImpl();

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int start = 0;
		int count = 10;

		try {
			start = Integer.parseInt(req.getParameter("page.start"));
			count = Integer.parseInt(req.getParameter("page.count"));
		} catch (Exception e) {
		}

		Page page = new Page(start, count);

		List<Student> students = studentDao.list(page.getStart(), page.getCount());
		int total = studentDao.getTotal();
		page.setTotal(total);

		req.setAttribute("students", students);
		req.setAttribute("page", page);

		req.getRequestDispatcher("/listStudent.jsp").forward(req, resp);
	}
}
