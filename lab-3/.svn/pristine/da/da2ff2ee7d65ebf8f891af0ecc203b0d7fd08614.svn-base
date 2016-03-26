package cse530a.servlet;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static final Logger LOGGER = Logger.getLogger(LogoutServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
	}

    private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("servlet /logout");
        
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId != null) {
            LOGGER.info("User " + userId + " logged out");
            request.getSession().removeAttribute("userId");
        }
        
        request.getSession().invalidate();
	    request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
	}
}
