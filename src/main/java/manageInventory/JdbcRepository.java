package manageInventory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcRepository implements ItemRepository {
	private Connection connection;

	public JdbcRepository(Connection connection) {
		if (connection == null) {
			throw new IllegalArgumentException("Empty Connection!");
		}
		this.connection = connection;
	}

	@Override
	public List<Item> findAll() {
		List<Item> items = new ArrayList<Item>();
		String selectItemTableQuery = "select name, quantity from item";
		ResultSet rs = null;
		try {
			rs = connection.createStatement()
					.executeQuery(selectItemTableQuery);
		} catch (SQLException e) {
			throw new RuntimeException();
		}

		try {
			while (rs.next()) {
				try {
					items.add(new Item(rs.getString("name"), rs
							.getInt("quantity")));
				} catch (SQLException e) {
					throw new RuntimeException();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}
		return items;
	}

	@Override
	public boolean addValidItem(Item item) {
		if (!this.alreadyHas(item)) {
			String insertItemTableQuery = String.format(
					"insert into item(name,quantity) values(\'%s\', %d); ",
					item.getName(), item.getQuantity());
			try {
				connection.createStatement().execute(insertItemTableQuery);
			} catch (SQLException e) {

				throw new RuntimeException();
			}
			return true;
		}
		return false;
	}

	protected boolean alreadyHas(Item item) {
		ResultSet rs = null;
		String findDuplicateEntryQuery = String.format(
				"select * from item where name=\'%s\'", item.getName());
		try {
			rs = connection.createStatement().executeQuery(
					findDuplicateEntryQuery);
			return (rs.next());
		} catch (SQLException e) {
			throw new RuntimeException();
		}

	}
}// use prepared statement wherever possible and turn into constants

