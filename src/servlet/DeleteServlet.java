package servlet;

import dao.StudentDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteStudent")
public class DeleteServlet extends HttpServlet {

	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));
		new StudentDaoImpl().delete(id);

		resp.sendRedirect("/listStudent");
	}
}

