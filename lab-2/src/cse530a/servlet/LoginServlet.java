package cse530a.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import cse530a.dao.DatabaseManager;
import cse530a.dao.UserDao;
import cse530a.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private static final Logger LOGGER = Logger.getLogger(LoginServlet.class.getName());

	@Resource(name = "jdbc/postgres")
	private DataSource dataSource;

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

        DatabaseManager dbm = new DatabaseManager(dataSource);
        Connection conn = dbm.getConnection();
        try {
            User user = UserDao.retrieveUser(conn, username);
            
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

            request.getSession().setAttribute("user", user);
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "error retrieving user", e);
        } finally {
            DatabaseManager.closeConnection(conn);
        }
	    
	    request.getRequestDispatcher("WEB-INF/jsp/Welcome.jsp").forward(request, response);
	}
}
