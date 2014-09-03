package manageInventory;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ItemController
 */
@WebServlet(urlPatterns="/viewItems")
public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ItemRepository repository;

	@Override
	public void init(ServletConfig config) throws ServletException {

		repository = (ItemRepository) config.getServletContext().getAttribute(
				"repository");

	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("/viewItems.jsp");

		List<Item> items = repository.findAll();
		Collections.sort(items);
		request.setAttribute("Items", items);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("itemName");
		int quanity = Integer.parseInt(request.getParameter("itemQty"));
		System.out.println(name);
		Item newItem = new Item(name,quanity);
		response.setContentType("application/json");
		Boolean isAdded = repository.addValidItem(newItem);
		response.getWriter().append(isAdded.toString());
		response.flushBuffer();
		
	}

}
