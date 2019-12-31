package net.slipp.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/users/updateForm")
public class UpdateFormUserServlet extends HttpServlet {
	
	final static Logger logger = LoggerFactory.getLogger(UpdateFormUserServlet.class);


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		
		if(userId == null) {
			resp.sendRedirect("/");
			return;
		}
		
		logger.debug("User Id : {}" + userId);
		
//		System.out.println("User Id : " + userId);
		UserDAO userDao = new UserDAO();
		User user = userDao.findByUserId(userId);
		req.setAttribute("user",  user);
		RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
		rd.forward(req, resp);
		
	}
	
}
