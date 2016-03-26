package cse530a.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import cse530a.dao.UserDao;
import cse530a.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
        LOGGER.info("servlet /login");
        
        String username = request.getParameter("username");
	    String password = request.getParameter("password");
	    
	    LOGGER.info("Got username \"" + username + "\" and password \"" + password + "\"");
	    
	    if (username == null || password == null) {
            request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
            return;
	    }

	    SessionFactory sessionFactory = (SessionFactory) request.getServletContext().getAttribute("hibernateSessionFactory");
	    Session session = sessionFactory.openSession();
	    Transaction tx = null;
	    
        try {
            tx = session.beginTransaction();
            
            User user = UserDao.retrieveUser(session, username);
            
            if (user == null) {
                // username not found
                request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
                return;
            }
            
            // We should be salting and hashing the passwords, but we'll skip it.
            
            if (!user.getPassword().equals(password)) {
                // password doesn't match
                request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
                return;
            }

            request.getSession().setAttribute("userId", user.getId());
            request.setAttribute("user", user);
            request.getRequestDispatcher("WEB-INF/jsp/Welcome.jsp").forward(request, response);
            
            tx.commit();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "error retrieving user", e);
            request.getRequestDispatcher("WEB-INF/jsp/Login.jsp").forward(request, response);
            tx.rollback();
        } finally {
            session.close();
        }
	}
}
