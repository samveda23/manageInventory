package manageInventory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ItemRepositorySupport {
	private Connection conn;

	private static void createItemTable(Connection conn) throws SQLException {
		StringBuilder createTableQuery = new StringBuilder();
		createTableQuery
				.append("Create table item ")
				.append(" (id int generated by default as identity primary key, ")
				.append(" name varchar(50) not NULL, ")
				.append(" quantity int not NULL) ");

		conn.createStatement().execute(createTableQuery.toString());
	}

	public ItemRepository createTableAndRepository()
			throws ClassNotFoundException, SQLException {
		Class.forName("org.hsqldb.jdbcDriver");
		conn = DriverManager.getConnection("jdbc:hsqldb:mem:inventory", "SA",
				"");
		createItemTable(conn);
		return new JdbcRepository(conn);
	}

	public void insert(Item item) throws ClassNotFoundException, SQLException {
		String insertItemTableQuery = String.format(
				"insert into item values(IDENTITY(), \'%s\', %d); ",
				item.getName(), item.getQuantity());
		conn.createStatement().execute(insertItemTableQuery);
	}

	public void cleanTable() throws SQLException {
		String deleteItemTableQuery = "delete from item; ";
		conn.createStatement().execute(deleteItemTableQuery);
	}

	public void dropTable() throws SQLException {
		String dropItemTableQuery = "drop table item; ";
		conn.createStatement().execute(dropItemTableQuery);
	}
}
