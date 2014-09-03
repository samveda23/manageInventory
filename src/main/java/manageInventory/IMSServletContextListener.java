package manageInventory;
//imsservlet
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class IMSServletContextListener
 *
 */
@WebListener
public class IMSServletContextListener implements ServletContextListener {
//inject Logger instead of sysout but dont put static logger reference here
	public void contextInitialized(ServletContextEvent sce) {

		System.out.println("IMSServletContextListener.contextInitialized()");

		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/test", "admin", "admin");
		} catch (SQLException e) {

			throw new RuntimeException();
		} catch (ClassNotFoundException e) {

			throw new RuntimeException();
		} catch (InstantiationException e) {

			throw new RuntimeException();
		} catch (IllegalAccessException e) {

			throw new RuntimeException();
		}

		ServletContext servletContext = sce.getServletContext();

		servletContext.setAttribute("repository", new JdbcRepository(conn));

	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("IMSServletContextListener.contextDestroyed()");
	}

}
