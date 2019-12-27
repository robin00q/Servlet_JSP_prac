package net.slipp.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/updateForm")
public class UpdateFormUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		Object object = session.getAttribute(LoginServlet.SESSION_USER_ID);
		if(object == null) {
			resp.sendRedirect("/");
			return;
		}
		String userId = (String)object;
		System.out.println("User Id : " + userId);
		UserDAO userDao = new UserDAO();
		try {
			User user = userDao.findByUserId(userId);
			req.setAttribute("user",  user);
			RequestDispatcher rd = req.getRequestDispatcher("/update_form.jsp");
			rd.forward(req, resp);
		} catch (Exception e) {
		}
		
	}
	
}
